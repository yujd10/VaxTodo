# README

Cette application est un prototype de VaxTodo permettant de prendre un rendez à toute personne majeure agée de 18 et plus pour se faire vacciner avec GoodPeople.
Elle permet aussi de créer et gérer les comptes des visiteurs et des bénévoles. De plus, cette application est utilisé pour faire du suivi de la vaccination 
ainsi qu'envoyer le profil de vaccination au visiteur.

## Fonctionnalités

- Réserver une période de vaccination
- Gérer les comptes des visiteurs, bénévoles
- Enovyer courriel de suivi
- Consulter et modifier le calendrier
- Envoyer rapport de vaccination
- Remplir formulaire

## Manuel d'utilisation

Pour utiliser l'application, il vous faut exécuter la commande suivante: `java App` (ou autre commande).
Étant un prototype, nous avons inclus un jeu de données afin de tester l'application.
À l'ouverture, vous devez vous connecter en tant qu'employé ou bénévole. Ceci vous donnera accès
au menu principal propre au rôle.

### Données incluses dans l'application

- Rendez-vous
  - 000001;foret;aura;2021-11-05;10:00;1
  - 000002;buisson;paul;2021-12-02;14:00;2
- Visiteur
  - 202010250001;cortez;juan;1990-01-13;juan.cortez@gmail.com;5147836802
  - 202004280010;hannoune;isabelle;1994-10-06;isa.hannoune@gmail.com;4382835821
- ...

### Connexion

Pour se connecter à l'application, veuillez utiliser un des identifiants suivants:

- Rôle de l'employé
  - username: argo | password: argopass
  - username: anna | password: annapass
- Rôle du bénévole
  - username: benoit | password: benoitpass
  - username: viola | password: violapass

### Menu principal (Employé)

À partir du menu principal, dans le rôle de l'employé, vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
En tout tant vous pouvez taper 0 pour revenir au menu principal.

- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.
- [2] Envoi courriel de suivi: Envoyez un courriel de suivi à un visiteur pour lui rappeler
- [3] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir
- [4] Réserver un rendez-vous: Faire la réservation d'un rendez vous
- [5] Remplir questionnaire: Remplir le questionnaire pour avoir les informations personnelles du visiteur
- [0] Retour au menu principal

#### Gestion des visiteurs

Dans cette section, vous pouvez effectuer les actions suivantes en tapant le chiffre correspondant.
Suivez les instructions à l'écran pour compléter la tache

- [1] Créer un compte
- [2] Modifier un compte
- [3] Supprimer un compte
- [4] Chercher un compte
- [0] Retour au menu principal

#### Envoi courriel de suivi:

Dans cette section, vous pouvez effectuer les actions suivantes en tapant le chiffre correspondant.
Suivez les instructions à l'écran pour compléter la tache

- [1] Enovyer un rappel
- [2] Enovyer un rapport de vaccination
- [0] Retour au menu principal

#### Consultation du calendrier:

Dans cette section, vous pouvez effectuer les actions suivantes en tapant le chiffre correspondant.
Suivez les instructions à l'écran pour compléter la tache

- [1] Consulter le calendrier
- [2] Consulter le nombre de personnes présents
- [0] Retour au menu principal

### Menu principal (Bénévole)

À partir du menu principal, dans le rôle du bénévole, vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
En tout tant vous pouvez taper 0 pour revenir au menu principal.

- [1] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir
- [2] Consulation de la liste des visiteurs: Accédez à la liste des visiteurs.
- [3] Réserver un rendez-vous: faire une réservation pour un visiteur
- [0] Retour au menu principal
