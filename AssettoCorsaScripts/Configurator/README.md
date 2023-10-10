# Lancer le script
## Configuration

Remplir le fichier config.json avec l'adresse IP du PC faisant tourner le jeu (pas de `127.0.0.1`).  
Si ce script n'est pas executé sur le même pc que le jeu, définir "isGameOnSameComputer" à false. Pas besoin de modifier le reste.

## Lancement
Avec un terminal, acceder au dossier avec le script puis `python UDPForwarder.py`

## Arret
Sans couper le script avec un ctrl+c, définir "isRunning" à false dans le fichier de config puis au prochain message reçu du jeu le script s'arretera. (WIP improvment).

# TODO
Intégrer le lancement et l'arret du script dans une app ingame pour simplifier l'utilisation