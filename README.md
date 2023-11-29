# A faire
(coché = encours)
- [ ] Détection de nouveau meilleur temps et envoi d'un message dans un channel avec cette info (channels dispo a ajouter au properties ou en db)
- [ ] Amélioration des messages retournés en réponse aux commandes
- [ ] Commandes de listage des jeux, joueurs, voitures ...
- [x] Faire marcher le script python de transfert des data AC
- [x] Ajouter le support pour d'autres jeux (F1 22 commencé)
- [ ] Ajouter un alias aux noms de circuits/voiture (a cause d'Assetto Corsa)


# Lancer en local
- définir les variables d'environnement pour la base de données (dans la run config pour Intellij, ou avec la commande `export`) :
  - dev_dbhost
  - dev_dbuser
  - dev_dbpass
- possibilité d'override la propriété `listenAddress` dans le cas où l'app ne tourne pas sur la même machine que le(s) jeu(x) (mettre par ex `192.168.1.28` dans le profile dev)
- lancer un build gradle puis lancer l'app
  - les logs disent quels ports sont en écoute et sur quelle IP au démarrage, et il ne doit pas y avoir d'exceptions
- possibilité de créer une DB locale, le script SQL est donné dans les ressources, des données aussi

# Lancer en 'prod'
- définir les variables d'environnement pour la base de données (dans la run config pour Intellij, ou avec la commande `export`) :
  - dbhost
  - dbuser
  - dbpass
- lancer un build gradle puis lancer l'app
  - les logs disent quels ports sont en écoute et sur quelle IP au démarrage, et il ne doit pas y avoir d'exceptions

# Ajouter des commandes Discord  
Pour ajouter des commandes dans le Bot Discord, il faut proceder en 3 étapes :  
- Créer la méthode de définition de la commande et de ses options dans la classe `LaptimesBot` (se référer aux commandes déjà existantes pour un exemple)
- Appeler cette nouvelle méthode dans le `init()`. Attention, une fois que cette méthode a été executée  pour un bot, celui ci aura la commande disponible pour les utilisateurs meme apres retrait de la commande du code. Il faudra la supprimer explicitement.
- Ajouter un `case` dans `CommandHandler::handle()` qui appelle la methode à créer `handle<nomdelacommande>()`
- Ajouter la logique métier dans un service pour permettre sa réutilisation

# Jeux supportés
 - Assetto Corsa (lapdata uniquement et en version étendue avec le script Python)
 - F1 2022 (en cours)