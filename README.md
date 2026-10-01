# 👥 Application web de gestion des membres

Application web développée avec **Spring Boot** permettant l'authentification et la gestion des membres avec différents rôles utilisateurs.

Le projet est conteneurisé avec **Docker** et **Docker Compose**, avec une base de données **MySQL**.

---

## 🚀 Fonctionnalités

- Authentification des utilisateurs
- Gestion des profils utilisateurs
- Gestion des rôles Administrateur / Client
- Création, modification et suppression des membres
- Gestion des mots de passe
- Validation des données
- Hachage des mots de passe avec BCrypt
- API REST développée avec Spring Boot

---

## 🛠️ Technologies utilisées

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- REST API
- Maven

### Base de données
- MySQL

### Frontend
- HTML
- CSS
- JavaScript

### DevOps
- Docker
- Docker Compose
- Docker multi-stage build
- Healthcheck MySQL
- Variables d'environnement

### Versioning
- Git
- GitHub

---

## 🏗️ Architecture

```text
Utilisateur
    |
    v
Frontend
HTML / CSS / JavaScript
    |
    | HTTP / REST
    v
Spring Boot
    |
    | Spring Data JPA
    v
MySQL
```

Avec Docker :

```text
Docker Compose
│
├── Spring Boot Container
│      └── Port 8080
│
└── MySQL Container
       └── Port 3306
```

---

## 📦 Docker

L'application utilise un **Dockerfile multi-stage** afin de séparer la phase de compilation de la phase d'exécution.

Docker Compose permet de lancer et connecter :

- l'application Spring Boot ;
- la base de données MySQL ;
- les volumes nécessaires à la persistance des données.

Un **healthcheck** est utilisé afin de vérifier que MySQL est disponible avant le démarrage de l'application.

---

## ▶️ Lancer le projet

### Cloner le dépôt

```bash
git clone <URL_DU_REPOSITORY>
cd <NOM_DU_PROJET>
```

### Lancer les containers

```bash
docker compose up -d --build
```

### Vérifier les containers

```bash
docker ps
```

L'application est ensuite accessible sur :

```text
http://localhost:8080
```

---

## 🛑 Arrêter le projet

```bash
docker compose down
```

---

## 🔍 Logs

Afficher les logs :

```bash
docker compose logs -f
```

---

## 📂 Structure simplifiée

```text
gestion-membres/
│
├── src/
├── .dockerignore
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── mvnw
└── README.md
```

---

## 🔐 Sécurité

Les mots de passe utilisateurs sont hachés avec **BCrypt** avant leur stockage en base de données.

Les informations sensibles de configuration sont gérées via des variables d'environnement et ne sont pas versionnées dans le dépôt Git.

---

## 🎯 Objectifs du projet

Ce projet m'a permis de mettre en pratique :

- Spring Boot et les API REST
- Spring Data JPA
- MySQL
- Docker et Docker Compose
- Docker multi-stage builds
- Communication entre plusieurs containers
- Healthchecks
- Gestion des variables d'environnement
- Git et GitHub

---

## 👤 Auteur

**Abdelhamid El Amrani**

GitHub : [abdelhamid-elamrani](https://github.com/abdelhamid-elamrani)