import ac
import acsys
import json
import os

def acMain(ac_version):
    global ipInput, portInput, activeButton, appWindow, running, config, FILE
    FILE = str(os.path.join("apps","python","Configurator","config.json"))
    running = False

    config = loadConfig()

    appWindow = ac.newApp("SopraSpeedStarsLeaderboard")
    ac.setSize(appWindow,200,200)
    ac.drawBorder(appWindow,0)
    ac.setBackgroundOpacity(appWindow,0)
    ac.console("Starting SSSL App")

    #GUI
    #IpInput
    ipLabel = ac.addLabel(appWindow, "IP:")
    ipInput = ac.addTextInput(appWindow, config["ip"])
    ac.setPosition(ipLabel, 30,60)
    ac.setPosition(ipInput,50,60)
    ac.setSize(ipInput,100,30)
    ac.addOnValidateListener(ipInput,onChangeIpListener)
    #PortInput
    portLabel = ac.addLabel(appWindow, "Port:")
    portInput = ac.addTextInput(appWindow, str(config["port"]))
    ac.setPosition(portLabel,15,100)
    ac.setPosition(portInput,50,100)
    ac.setSize(portInput,100,30)
    ac.addOnValidateListener(portInput,onChangePortListener)
    #Button
    activeButton = ac.addButton(appWindow,"DEACTIVATE" if config["isRunning"] else "ACTIVATE")
    ac.setPosition(activeButton,15,140)
    ac.setSize(activeButton,150,30)
    ac.addOnClickedListener(activeButton,onClickButtonListener)

    return "SopraSpeedStarsLeaderboard"

#action quand on appuie sur entrée apres avoir saisi une ip
def onChangeIpListener(string):
    global ipInput, config
    ip = ac.getText(ipInput)
    ac.setText(ipInput, ip)
    ac.console("new ip:{}".format(ip))
    config = updateConfigFile(config["isRunning"], ip, config["port"])

#action quand on appuie sur entrée apres avoir saisi un port
def onChangePortListener(string):
    global portInput, config
    port = ac.getText(portInput)
    ac.setText(portInput, port)
    ac.console("new port:{}".format(port))
    config = updateConfigFile(config["isRunning"], config["ip"], int(port))

#action quand on appuie sur le bouton
def onClickButtonListener(*args):
    global running; activeButton, config
    if running :
        running = False
    else :
        running = True
    ac.console("running:{}".format(str(running)))
    config = updateConfigFile(running, config["ip"], config["port"])

#met a jour le fichier de config et l'initialise si y'en a pas
def updateConfigFile(isRunning = True, ip = "192.168.1.28", port=9996):
    global FILE
    x = {
        "isRunning": None,
        "ip": None,
        "port": None
    }
    x["isRunning"] = isRunning
    x["ip"] = ip
    x["port"] = port
    ac.console(os. getcwd())
    f = open(FILE, "w")
    f.write(json.dumps(x))
    ac.console(json.dumps(x))
    f.close()
    return x

# ouvre et lit le fichier de config et la renvoie
def loadConfig():
    global FILE
    # ni fichier n'existe pas, on initialise et return
    if (not os.path.exists(FILE)):
        ac.console("creating file")
        return updateConfigFile()

    # si il existe, on lit et return
    f = open(FILE, "r")
    x = json.loads(f.read())
    f.close()
    return x
