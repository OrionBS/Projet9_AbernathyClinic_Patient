# Projet n°9 : Abernathy Clinic

Sprint n° 1 : 

#### Lien Github : https://github.com/OrionBS/Projet7_Poseidon-Inc

## Pour commencer

### Pré-requis

- IntelliJ (Environnement de développement)
- Java 11
- MySQL
- Gradle 7.3.1

### Téléchargement

```
cd /"dossier de téléchargement"/
git clone https://github.com/OrionBS/Projet9_AbernathyClinic
cd /Projet9_AbernathyClinic
```

### Installation

Pensez à intaller les dernières versions de Java, MySQL, Gradle

### Paramétrage MySQL

Dans la console, démarrez MySQL
```
mysql
```
Puis créez la base de donnée et l'utilisateur avec ses privilèges.
```
create database mediscreenData;
create user 'userData'@'localhost' identified by 'passwordData';
grant all privileges on mediscreen.* to 'userData'@'localhost';
flush privileges;
```

## Démarrage

API :

```
mvn build
java -jar PoseidonAPI.jar
```

## Fabriqué avec

* [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows) - Environnement de développement
* [Java SE JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - JDK Java
* [Gradle 3.6.3](https://gradle.org/install/) - Gradle

## Versions

**Dernière version stable :** 1.0

## Auteurs
Listez le(s) auteur(s) du projet ici !
* **Orion Beauny** _alias_ [@OrionBS](https://github.com/OrionBS)
* Tous les [contributeurs](https://github.com/OrionBS/Projet9_AbernathyClinic/contributors)

## License

Ce projet est conçu pour répondre en tant que travail d'étudiant lors d'une soutenance.


