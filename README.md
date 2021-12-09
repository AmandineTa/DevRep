# DevRep
## TA Amandine & UNG Richard

Projet réalisé sur Intellij avec Spring Boot et VueJs.

## INSPIRE PAR:

https://www.futurespace.es/en/crear-un-proyecto-spring-boot-vue-js/

https://spring.io/guides/tutorials/rest/

https://www.youtube.com/watch?v=xZqhFmsEaIQ&t=641s

https://www.youtube.com/watch?v=aioepiq7Yuw

https://www.youtube.com/watch?v=W2ZWbE45vkg&t=1990s

https://www.baeldung.com/spring-boot-logging


## DEPENDANCE:

- Java 11
- Vue/cli
- Maven
- NodeJs
- MySQL

## BASE DE DONNEES:

A l'aide de MySQL Workbench:

Connexion sur le port de MySql 3306 avec:
- user : root
- password : mdpbdd


## EXECUTION DU PROJET:

L'exécution se fait en deux phases. On commence par lancé le backend Application.java puis le frontend (pour cela, il faut se rendre dans le fichier frontend puis lancer les commandes " npm install" puis "npm run serve").

## FONCTIONNALITES REALISEES ET FONCTIONNELLES:
  
Côté back:
  - Requêtes REST:
    - Affichage de la liste des clients
    - Affichage d'un client selon son ID unique
    - Obtenir un client selon son email unique ( email sert à l'authentification)
    - Obtenir un client selon son numéro de téléphone unique ( tel sert à l'authentification)
    - Dépot sur le compte d'un client selon son ID
    - Retrait sur le compte d'un client selon son ID ( le retrait respecte une valeur de découvert et un plafond de retrait)
    - Ajout de clients dans la base de données

  - Base de données:
    - table clients
    - colonnes de la table client
      - id (unique)
      - firstname
      - lastname
      - email (unique)
      - tel (unique)
      - balance
      - overdraft
      - cap

  - Modifications des champs d'un clients
  - Logging ( à fonctionné pendant un moment, malheureusement actuellement non fonctionnel à cause d'un problème de dépendance) 
  - Administrateur à pour coordonné:
    - user : user
    - password : clé généré sur le terminal au lancement du back

Coté front:
  - une Vue Home
  - une Vue BankApp
  - Affichage des boutons Google, Github et Microsoft uniquement si le client n'est pas connecté
  - Affichage "connected" ou "not connected" selon si le client est connecté





