
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
