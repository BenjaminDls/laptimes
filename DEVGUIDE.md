## A faire
(coché = encours)
- [ ] Détection de nouveau meilleur temps et envoi d'un message dans un channel avec cette info (channels dispo a ajouter au properties ou en db)
- [ ] Amélioration des messages retournés en réponse aux commandes
- [ ] Commandes de listage des jeux, joueurs, voitures ...
- [x] Faire marcher le script python de transfert des data AC
- [x] Ajouter le support pour d'autres jeux (F1 22 commencé)
- [ ] Ajouter un alias aux noms de circuits/voiture (a cause d'Assetto Corsa)

## Ajouter des commandes Discord  
Pour ajouter des commandes dans le Bot Discord, il faut proceder en 3 étapes :  
- Créer la méthode de définition de la commande et de ses options dans la classe `LaptimesBot` (se référer aux commandes déjà existantes pour un exemple)
- Appeler cette nouvelle méthode dans le `init()`. Attention, une fois que cette méthode a été executée  pour un bot, celui ci aura la commande disponible pour les utilisateurs meme apres retrait de la commande du code. Il faudra la supprimer explicitement.
- Ajouter un `case` dans `CommandHandler::handle()` qui appelle la methode à créer `handle<nomdelacommande>()`
- Ajouter la logique métier dans un service pour permettre sa réutilisation
