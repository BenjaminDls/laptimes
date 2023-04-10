import sys
import time
import socket
import struct
from dataclasses import dataclass

@dataclass
class Handshake:
    format='iii'
    identifier: int = 1
    version: int = 1
    operationId: int = 0

    def b(self):
        return  struct.pack(self.format, self.identifier, self.version, self.operationId)

class HandshakerResponse:
    format = '<100s100sLL100s100s'
    size = struct.calcsize(format)

    def __init__(self, t):
        t = struct.unpack(self.format, t)
        self.carName, self.driverName, self.identifier, self.version, self.trackName, self.trackConfig = t
        self.carName = self.carName.decode('utf-16', errors='ignore').split('%')[0]
        self.driverName = self.driverName.decode('utf-16', errors='ignore').split('%')[0]
        self.trackName = self.trackName.decode('utf-16', errors='ignore').split('%')[0]
        self.trackConfig = self.trackConfig.decode('utf-16', errors='ignore').split('%')[0]

    def __str__(self) -> str:
        return "{self.carName}, {self.driverName}, {self.identifier}, {self.version}, {self.trackName}, {self.trackConfig}".format(self=self)

class LapData:
    format = '<II100s100sI'
    size= struct.calcsize(format)

    def __init__(self, t):
        t = struct.unpack(self.format, t)
        self.carIdentifierNumber, self.lap, self.driverName, self.carName, self.time = t
        self.carName = self.carName.decode('utf-16', errors='ignore').split('%')[0]
        self.driverName = self.driverName.decode('utf-16', errors='ignore').split('%')[0]

    def __str__(self) -> str:
        return "{self.carIdentifierNumber}, {self.lap}, {self.driverName}, {self.carName}, {self.time}".format(self=self)

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

    def __init__(self, gameIp, gamePort, serverIp, serverPort):
        self.gameIp=gameIp
        self.gamePort=gamePort
        self.serverIp=serverIp
        self.serverPort=serverPort
        #replace binded ip by the pc's running the script (not localhost)
        self.sock.bind(('192.168.1.28', self.gamePort))

    def askToConnect(self):
        self.sock.sendto(Handshake(1,1,0).b(), (self.gameIp, self.gamePort))
        print("sent connect request")
        data = self.sock.recv(HandshakerResponse.size)
        parsed = HandshakerResponse(data)
        print("received connect confirm : %s" % parsed)

    def askToDisonnect(self):
        self.sock.sendto(Handshake(1,1,3).b(), (self.gameIp, self.gamePort))
        print("sent disconnect request")

    def askForLapData(self):
        if self.wanted=="":
            self.sock.sendto(Handshake(1,1,2).b(), (self.gameIp, self.gamePort))
            print("sent lapdata request")
            self.wanted="Lapdata"
            self.transfertDataTo()
        else:
            print("Already set up")

    def askForTelemetryData(self):
        if self.wanted=="":
            self.sock.sendto(Handshake(1,1,1).b(), (self.gameIp, self.gamePort))
            print("sent telemetry request")
            self.wanted="Telemetry"
            self.transfertDataTo()
        else:
            print("Already set up")

    def transfertDataTo(self):
        try:
            while True:
                data = None
                if self.wanted=="Lapdata":
                    print("waiting to receive lapdata")
                    data = self.sock.recv(LapData.size)
                    lapdata = LapData(data)
                    print("received lapdata : %s" % lapdata)
                else:
                    print("waiting to receive telemetry")
                    data = self.sock.recv(TelemetryData.size)
                    telemetry = TelemetryData(data)
                    print("received telemetry : %s" % telemetry)
                self.sock.sendto(data, (self.serverIp, self.serverPort))
        except KeyboardInterrupt:
            self.sock.close()
            self.askToDisonnect()


udp = SssConnection("192.168.1.10", 9996, "192.168.1.10", 9996)
udp.askToConnect()
#udp.askForTelemetryData()
udp.askForLapData()
udp.askToDisonnect()
