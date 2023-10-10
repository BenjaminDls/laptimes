#!/bin/python3

import sys
import time
import socket
import struct
import json
import ipaddress
from dataclasses import dataclass

@dataclass
class Handshake:
    format='iii'
    identifier: int = 1
    version: int = 1
    operationId: int = 0

    def b(self):
        return struct.pack(self.format, self.identifier, self.version, self.operationId)

class HandshakerResponse:
    format = '<100s100sLL100s100s'
    size = struct.calcsize(format)

    def __init__(self, t):
        t = struct.unpack(self.format, t)
        self.carName, self.driverName, self.identifier, self.version, self.trackName, self.trackConfig = t

        self.carName = self.carName.decode('utf-16', errors='ignore').split('%')[0]+'%'
        self.driverName = self.driverName.decode('utf-16', errors='ignore').split('%')[0]+'%'
        self.trackName = self.trackName.decode('utf-16', errors='ignore').split('%')[0]+'%'
        self.trackConfig = self.trackConfig.decode('utf-16', errors='ignore').split('%')[0]+'%'

        self.carName=self.carName.encode(encoding="UTF-16-BE",errors="ignore")
        self.driverName=self.driverName.encode(encoding="UTF-16-BE",errors="ignore")
        self.trackName=self.trackName.encode(encoding="UTF-16-BE",errors="ignore")
        self.trackConfig=self.trackConfig.encode(encoding="UTF-16-BE",errors="ignore")

    def __str__(self):
        return "{self.carName}, {self.driverName}, {self.identifier}, {self.version}, {self.trackName}, {self.trackConfig}".format(self=self)

class LapData:
    format = '<II100s100sI'
    size = struct.calcsize(format)

    def __init__(self, t):
        t = struct.unpack(self.format, t)
        self.carIdentifierNumber, self.lap, self.driverName, self.carName, self.time = t
        self.carName = self.carName.decode('utf-16', errors='ignore').split('%')[0]+'%'
        self.driverName = self.driverName.decode('utf-16', errors='ignore').split('%')[0]+'%'
        self.carName=self.carName.encode(encoding="UTF-16-BE",errors="ignore")
        self.driverName=self.driverName.encode(encoding="UTF-16-BE",errors="ignore")

    def __str__(self):
        return "{self.carIdentifierNumber}, {self.lap}, {self.driverName}, {self.carName}, {self.time}".format(self=self)

class LapDataExtended:
    format='<I100sII100s100sI'
    size = struct.calcsize(format)

    def __init__(self, track, lapdata: LapData):
        self.identifier = 2
        self.trackName = track
        self.lapdata = lapdata

    def toBytes(self):
        return struct.pack(self.format, self.identifier, self.trackName, self.lapdata.carIdentifierNumber, self.lapdata.lap,
            self.lapdata.driverName, self.lapdata.carName, self.lapdata.time)

    def __str__(self):
        return "{self.identifier}, {self.trackName}, {self.lapdata.carIdentifierNumber}, {self.lapdata.lap}, {self.lapdata.driverName}, {self.lapdata.carName}, {self.lapdata.time}".format(self=self)


#TODO : telemetrie
class TelemetryData:
    format = 'cIfff??????fffIIIIfffffIf249x'#4f4f4f4f4f4f4f4f4f4f4f4f4f4fff3f'
    size = struct.calcsize(format)

    def __init__(self, t):
        t = struct.unpack(self.format, t)
        self.identifier, self.size, \
        self.speedKmh, self.speedMph, self.speedMs, \
        self.isAbsEnabled, self.isAbsInAction, self.isTcInAction, self.isTcEnabled, self.isInPit, self.isEngineLimiterOn,\
        self.lapTime, self.lastLap, self.bestLap, self.lapCount, \
        self.gas, self.brake, self.clutch, self.engineRPM, self.steer, self.gear, \
        self.cgHeight = t
        #self.x, self.y, self.z = t

        self.lapTime = self.lapTime / 1000
        self.lastLap = self.lastLap / 1000
        self.bestLap = self.bestLap / 1000

    def __str__(self) -> str:
        return "{self.speedKmh}".format(self=self)


class SssConnection:
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    gameIp=0
    gamePort=0
    serverIp=0
    serverPort=0
    wanted=""
    config = None
    trackName = ""

    def __init__(self, isOnSameComputer):
        self.updateConfig()
        if self.config[keySameComputer]:
            localIp = self.gameIp
        else:
            localIp = getLocalIp()
        self.sock.bind((localIp, 0)) # bind to 0 => let Python choose a port
        print("Listening on {}:{}".format(localIp, self.sock.getsockname()[1]))
        print("Game {}:{}".format(self.gameIp, self.gamePort))
        print("Server {}:{}".format(self.serverIp, self.serverPort))

    def askToConnect(self):
        self.sock.sendto(Handshake(1,1,0).b(), (self.gameIp, self.gamePort))
        #print("sent connect request")
        data = self.sock.recv(HandshakerResponse.size)
        parsed = HandshakerResponse(data)
        #print("received connect confirm : %s" % parsed)
        self.trackName = parsed.trackName

    def askToDisonnect(self):
        self.sock.sendto(Handshake(1,1,3).b(), (self.gameIp, self.gamePort))
        #print("sent disconnect request")
        self.sock.close()

    def askForLapData(self):
        if self.wanted=="":
            self.sock.sendto(Handshake(1,1,2).b(), (self.gameIp, self.gamePort))
            #print("sent lapdata request")
            self.wanted="Lapdata"
            self.transfertDataTo()
        #else:
            #print("Already set up")

    def askForTelemetryData(self):
        if self.wanted=="":
            self.sock.sendto(Handshake(1,1,1).b(), (self.gameIp, self.gamePort))
            #print("sent telemetry request")
            self.wanted="Telemetry"
            self.transfertDataTo()
        #else:
            #print("Already set up")

    def transfertDataTo(self):
        try:
            while self.config[keyRun]:
                data = None
                if self.wanted=="Lapdata":
                    print("waiting to receive lapdata")
                    data = self.sock.recv(LapData.size)
                    lapdata = LapData(data)
                    lapdataExtended = LapDataExtended(self.trackName, lapdata)
                    lapdataExtended.lapdata = lapdata
                    lapdataExtended.identifier = 2
                    #print("received lapdata : %s" % lapdataExtended)
                    sendData: bytes = lapdataExtended.toBytes()
                    self.sock.sendto(sendData, (self.serverIp, self.serverPort))
                else:
                    print("waiting to receive telemetry")
                    data = self.sock.recv(TelemetryData.size)
                    telemetry = TelemetryData(data)
                    #print("received telemetry : %s" % telemetry)
                self.updateConfig()
        except KeyboardInterrupt:
            self.askToDisonnect()

    def updateConfig(self):
        self.config = readConfig()
        self.gameIp = self.config[keyGameIp]
        self.gamePort= self.config[keyGamePort]
        self.serverIp = self.config[keyServerIp]
        self.serverPort = self.config[keyServerPort]
        #print(self.config)


file = "./config.json"
keyRun = "isRunning"
keySameComputer = "isGameOnSameComputer"
keyServerIp = "serverIp"
keyServerPort = "serverPort"
keyGameIp = "gameIp"
keyGamePort = "gamePort"


def readConfig():
    f = open(file, 'r')
    data = f.read()
    f.close()
    potConfig = json.loads(data)
    if potConfig[keyServerIp] and potConfig[keyServerPort]:
        try:
            ipaddress.ip_address(potConfig[keyServerIp])
        except:
            try:
                potConfig[keyServerIp] = socket.gethostbyname(potConfig[keyServerIp])
            except:
                print("invalid Server ip : {}", potConfig[keyServerIp])
                raise ValueError
        try:
            port = int(potConfig[keyServerPort])
            if port<=1000 or port>=65535:
                raise ValueError
        except:
            print("invalid Server port : ",{potConfig[keyServerPort]})
            raise ValueError
    else:
        raise ValueError
    if potConfig[keyGameIp] and potConfig[keyGamePort]:
        try:
            ipaddress.ip_address(potConfig[keyGameIp])
        except:
            try:
                potConfig[keyGameIp] = socket.gethostbyname(potConfig[keyGameIp])
            except:
                print("invalid Game ip : {}", potConfig[keyGameIp])
                raise ValueError
        try:
            port = int(potConfig[keyGamePort])
            if port<=1000 or port>=65535:
                raise ValueError
        except:
            print("invalid Game port : ",{potConfig[keyGamePort]})
            raise ValueError
    else:
        raise ValueError
    return potConfig


def getLocalIp():
    hostname = socket.gethostname()
    ip = socket.gethostbyname(hostname)
    return ip

udp = SssConnection(True)
udp.askToConnect()
#udp.askForTelemetryData()
udp.askForLapData()
udp.askToDisonnect()
