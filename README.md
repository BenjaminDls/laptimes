# Application Laptimes

## A faire
(coché = encours)
- [ ] Détection de nouveau meilleur temps et envoi d'un message dans un channel avec cette info (channels dispo a ajouter au properties ou en db)
- [ ] Amélioration des messages retournés en réponse aux commandes
- [ ] Commandes de listage des jeux, joueurs, voitures ...
- [x] Faire marcher le script python de transfert des data AC
- [x] Ajouter le support pour d'autres jeux (F1 22 commencé)
- [ ] Ajouter un alias aux noms de circuits/voiture (a cause d'Assetto Corsa)


## Lancer en local

- définir les variables d'environnement pour la base de données (dans la run config pour Intellij, ou avec la commande `export`) :
  - dev_dbhost
  - dev_dbuser
  - dev_dbpass
- définir les variables d'environnement pour le bot Discord de la même façon :
  - discord_token
  - discord_bot_id
  - discord_events_channelId
- possibilité d'override la propriété `listenAddress` dans le cas où l'app ne tourne pas sur la même machine que le(s) jeu(x) (mettre par ex `192.168.1.28` dans le profile dev)
- lancer un build gradle puis lancer l'app
  - les logs disent quels ports sont en écoute et sur quelle IP au démarrage, et il ne doit pas y avoir d'exceptions
- possibilité de créer une DB locale, le script SQL est donné dans les ressources, des données aussi


## Lancer en 'prod'

- définir les variables d'environnement pour la base de données (dans la run config pour Intellij, ou avec la commande `export`) :
  - dbhost
  - dbuser
  - dbpass
- définir les variables d'environnement pour le bot Discord de la même façon :
  - discord_token
  - discord_bot_id
  - discord_events_channelId
- lancer un build gradle puis lancer l'app
  - les logs disent quels ports sont en écoute et sur quelle IP au démarrage, et il ne doit pas y avoir d'exceptions

## Avec Docker

### Build
```shell
docker build -t laptimes:dev .
```
### Run
Se lancera avec le profile de prod.
```shell
docker run -d --name laptimesServer -p 9000:9000 -e dbhost=$dbhost -e dbuser=$dbuser -e dbpass=$dbpass -e discord_token=$discord_token -e discord_bot_id=$discord_bot_id -e discord_events_channelId=$discord_channelId laptimes
```

## Ajouter des commandes Discord  
Pour ajouter des commandes dans le Bot Discord, il faut proceder en 3 étapes :  
- Créer la méthode de définition de la commande et de ses options dans la classe `LaptimesBot` (se référer aux commandes déjà existantes pour un exemple)
- Appeler cette nouvelle méthode dans le `init()`. Attention, une fois que cette méthode a été executée  pour un bot, celui ci aura la commande disponible pour les utilisateurs meme apres retrait de la commande du code. Il faudra la supprimer explicitement.
- Ajouter un `case` dans `CommandHandler::handle()` qui appelle la methode à créer `handle<nomdelacommande>()`
- Ajouter la logique métier dans un service pour permettre sa réutilisation

## Jeux supportés
 - Assetto Corsa (lapdata uniquement et en version étendue avec le script Python)
 - F1 2022


# Applications secondaires

## Assetto Corsa UDP Forwarder

Présent dans 