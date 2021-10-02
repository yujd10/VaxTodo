# VaxTodo:re
 vaxtodo:re for ift-2255

## Objectif: 
    - permet à tout personne majeure agée de 18 ans et plus de prendre un rendez-vous avec GoodPeople pour se faire vacciner (première dose ou deuxième dose)
    - le suivi pour les futures doses et autres requêtes liées à la vaccination:
        + une demande de preuve de vaccination
        + une demande de changement
        + une annulation d'un rendez-vous

    -  garantir les périodes réservées
    -  faciliter le suivi pour toute question liée à la preuve de vaccination
    -  planifier une nouvelle dose
    - éviter tout mauvais usage des données personnelles.

## Horaire: 
    Lundi au Vendredi, excluant les jours fériés
    8h00 à 18h00

## Nombre limite de clients: 40 personnes.

## Personnels: 
    une équipe composée d'employés et bénévoles
    Les professionnels ne sont pas sous notre charge
    Les professionels apportent tout l'équipement nécessaire pour la vaccination.


    ### Tâches:
        Les bénévoles: 
            accueillir une personne et les acheminer aux employés qui font le traitement du dossier et le choix du vaccin avec la personne
        Les professionels de la santé:
            Donner le vaccin
            Gérer tout problème de santé (malaise ou réaction virulente suite au vaccin)
        GoodPeople (Nous): 
            réserver un espace dans le local pour les professionels
            Récolter les informations nécessaires pour procéder à la vaccination

## Accueil d'un visiteur:
    1. Bénévole vérifie son rendez-vous. 
        - confirmer nom et l'heure de rdv.
        - else: vérifier quantité de rendez-vous prévus dans une heure et ajouter au nombre de visiteur présents.
            a. if ( somme < capacité du local): ajouter à la plage horaire correspondant à l'heure de la visite.
            b. else (son nom est ajouté à la rpochaine plage horaire disponible )
    2. visiteur est dirigée vers une file d'attente divisée entre les visites planifiées (avec rdv) et lers visites spontannées (sans rdv).
        If( visiteur avec rdv en retard >= 15minutes ): rendez-vous est annulé && considérée comme visite spontanée.
    3. La file d'attente est gérée en premier arrivé, premier servi, en priorisant les visites planifiées.

## Traitement d'un visiteur:
    1. Appele personne en file
    2. Diriger vers un employé.
    3. Employé confirme son rendez-vous:
        a. Demander son nom
        b. Demander son heure de rendez-vous (avec rdv) || Demander heure de visite (sans rdv)
    4. Employé récupère les informations personnelles
    5. Confirmer première dose ou deuxième dose.
    6. Employé demande la carte d'assurance maladie.
        a. if( sans carte d'assurance maladie || carte expirée): La visite est annulée && L'employé l'invite à prendre un nouveau rendez-vous.
    7. Employé remplit avec visiteur un questionnaire papier.
        ### Questionnaire
            Nom:
            Prénom:
            Date de naissance:
            Numéro carte assurance maladie:
            Date de la visite:
            Avez-vous déjà reçu une première dose?
            Avez-vous déjà contracté la COVID?
            Avez-vous des symptômes de la COVID?
            Avez-vous des allergies?
            Quel type de vaccin souhaitez-vous recevoir?
                a. if(première dose): Employé demande si le visiteur désire de planifier une seconde dose
                    1. if(true): Employé utilise le calendrier de rendez-vous pour choisir avec le visiteur une date qu'il le convient (delais de date >= 3mois)
                        if(date trouvée): L'employé note son numéro de téléphone, son nom complet, le jour et l'heure du rendez-vous.
    8. Employé donne un billet avec un numéro au visiteur qui est dirigé vers une nouvelle file d'attente et transmet le formulaire rempli et signé aux professionnels de la santé.
    9. Numéro du visiteur est appelé, il dirige vers un professionnel de la santé qui procède avec lui à la vaccination
        Tout ce qui a trait à la santé du visiteur est traité par un professionnel de la santé.

## Prise de rendez-vous
    Par téléphone:
        1.la personne peut demander des questions sur:
          - pièces requises
          - des heures d'ouverture
          - planifier un rendez-vous
        2. Planifier un rendez-vous:
            - L'employé utilise le calendrier des rendez-vous pour choisir une date et heure avec la personne. (Heure pile, ex: 8h, 9h, 10h)
            - La personne peut prendre un rendez-vous pour un maximum de 2 personnes.
            - Maximum de 15 rendez-vous par plage horaire  (? 15 personnes ou 30 personnes)
## Suivi
    À la fin de la journée:
        1. L'employé envoie un courriel à tous les visiteurs ayant reçu le vaccin, le détail de sa visite et la preuve de vaccination sous forme de document PDF présentant son nom, sa    date de naissance, un code QR et la liste des vaccins administrés.
        2. L'employé consulte le calendrier pour vérifier les visites plannifiées pour les prochains jours.
            If(visite prévu pour les prochaines 48heures): L'employé appelle le visiteur pour confirmer sa visite.

## Liste de souhaits VaxToDo:re
    - Intégrer un système d'information VaxTodo:re qui sera manipulé uniquement par *les employés*.
    - Les bénévoles récupèrerent en début de journée, une version imprimée du calendrier présentant le nom complet des visiteurs planifiés.
    - Estimer 30-45 minutes par personne (incluant le temps d'observation de 15 minutes suivant la vaccination) 5 traitements en parallèle.
    - 12 ordinateurs Windows à la dispostion pour être dédiés au nouveau système.
    - Les données seront préservées en cas de future dose ou pour demander une preuve de vaccination. Nous ne prévoyons pas partagées ces données avec aucune autre instance.
## Activités a faire avec le système VaxTodo:re
    1. Prise de rendez-vous par téléphone:
        - l'employé utilise le système pour accéder au calendrier et vérifier les disponibilités.
        - un rendez-vous doit être pris au minimum 72 heures à l'avance. 
        - lorsque le choix est confirmé, l'employé effectue la réservation dans le système.
        - Le système produit un numéro de réservation unique et les détails de chaque rendez-vous pris ((prénom et nom du visiteur, date et heure de la visite, type de dose)
        - Le numéro de réservation est envoyé par courriel au visiteur
        - Le visiteur utilise le numéro de réservation pour confirmer son rendez-vous lors de sa visite.
    2. Lors d'une première visite: 
        - employé doit créer un compte pour le visiteur qui sera rattaché à toutes ces activités: vaccins et réservations.
        - Pour toute prochaine visite, le numéro de compte du visiteur sera utilisé pour identifier le visiteur.
        - Numéro de compte: numéro unique à douze chiffres et sera rattaché à une adresse courriel unique.
            Il présentera aussi les informations personnelles du visiteur: nom, prénom, date de naissance adresse et numéro de téléphone
        - Afin d'éviter d'avoir des comptes fantomes, nous attendons la visite pour créer un compte.
    3. L'employé utilise le système pour:
        - la confirmation
        - l'entrevue (questionnaire)
        - la planification d'une autre visite
    4. Faire le suivi dans le système:
        - produire sur demande le rapport de vaccination d'une visite. 
        - Envoyer le rapport de vaccination par courriel au visiteur concerné (option)
        - Employé utilise le système pour enovyer une notification par courriel, rappelant à un visiteur son rendez-vous prochain.
    5. Faire la gestion:
        - L'employé peut accéder à la liste des rendez-vous pour ajouter et les annuler.
        - L'employé peut accéder à la liste des bénévoles et des comptes visiteurs pour ajouter, modifier ou supprimer.
        - Chaqcun de ces éléments peut être visualisé individuellement.
    6. Identification: 
        - Un bénévole: 
            Il est identifié par son nom, prénom, adresse, numéro de téléphone et adresse courriel.
            Il présente aussi une liste de jours où il est disponible pour venir au local.
        - Un employé:
            Il est identifié avec les mêmes attributs, mais possède en plus un code d'employé à 9 chiffres et un mot de passe qui seront utilisés pour accéder au système.à
            Le mot de passe devra être composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
        - Un visiteur:
            Le système doit avoir un profil de vaccination pour chaque compte visiteur qui regroupe les informations liées aux vaccins reçus: type de dose, identification du vaccin, date et heure de vaccination.
        - Un vaccin:
            Un vaccin est identifié par un nom, un code et un lot.
    7. La sécurité:
        - Nous utiliserons un de nos ordinateurs comme serveur pour y stocker nos données, conservant ainsi toutes les données chez nous.
        - Ce serveur sera mis en réseau pour y être accédé par les autres ordinateurs, utilisés par les employés.
        - Notre budget étant présentement limité, les enregistrements seront stockés sur disque en fichier texte. Par la suite, nous pourrons migrer vers une solution plus robuste.