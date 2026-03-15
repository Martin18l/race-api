# TP — Création d'une API REST : Gestion d'inscriptions à une course

## Objectif

L'objectif de ce TP est de concevoir et développer une **API REST** permettant de gérer l'inscription de coureurs à différentes courses.

Cette API devra permettre :

* de gérer les **coureurs**
* de gérer les **courses**
* de gérer les **inscriptions à une course**

Les données devront être **persistées dans une base de données PostgreSQL**.

---

# Contexte

Une organisation sportive souhaite mettre en place une plateforme permettant de gérer les inscriptions à différentes courses.

Chaque **coureur** peut s'inscrire à **plusieurs courses**, et chaque **course** peut accueillir **plusieurs coureurs**.

Votre mission est de développer l'API qui permettra de gérer ces informations.

---

# Stack technique

Pour ce TP, vous utiliserez les technologies suivantes :

* **Java 25**
* **Spring Boot 4**
* **Spring Web**
* **Spring Data JPA**
* **Flyway**


* **Docker**
* **PostgreSQL**
* **Adminer**

---
# Configuration utilisée : 

OS : Windows
IDE : VS Code
Postman
Conteneurisation avec Docker

# Procédure pour lancer le projet

dans l'invite de commande dans le dossier du projet :

- "docker compose up -d"  pour lancer les serveurs en local
- "mvn spring-boot:run" pour relancer l'API (nécessaire à chaque modification)

Connexion sur adminer (localhost:8081) avec les identifiants fournis (inchangés)

Pour observer les fonctionnalités, tout se fait sur Postman
Dans la colonne de gauche la liste des différentes commandes éxécutables.
Appuyer sur SEND pour en lancer une.
Dans l'onglet Body se trouve parfois du texte JSON, modifiable pour réaliser des tests

Dans la fenêtre de réponse de Postman, on trouve un cadre affichant la réponse HTTP (404, 502 etc..)

- "docker compose down" pour fermer les serveurs.

** Merci pour votre attention ! **