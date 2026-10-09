# 👥 Application web de gestion des membres

Application web développée avec **Spring Boot** permettant l'authentification et la gestion des membres avec différents rôles utilisateurs.

Le projet est conteneurisé avec **Docker / Docker Compose** et intégré à une chaîne **CI/CD Jenkins** avec déploiement sur **AWS EC2** provisionné avec **Terraform**.

---

## 🚀 Fonctionnalités

- Authentification des utilisateurs
- Gestion des profils utilisateurs
- Gestion des rôles Administrateur / Client
- Création, modification et suppression des membres
- Gestion et validation des mots de passe
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

### DevOps & Cloud
- Docker
- Docker Compose
- Docker multi-stage build
- Jenkins
- CI/CD
- Docker Hub
- Terraform
- AWS EC2
- SSH
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