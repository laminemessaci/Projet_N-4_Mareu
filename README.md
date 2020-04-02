# Projet_N-4_Mareu


**Maréu** est le projet 4 du parcours DA Android d'OpenClassrooms.  

## Introduction

**Maréu** est une application permettant de réserver des salles de 
réunion pour les collaborateurs de la société Lamzone.  

Dans ce projet l'application doit être développée en "partant de zéro".  

## Travail a réaliser

Voici les fonctionnalités de l'application qui devront être 
implémentées :  

  - liste des réunions comprenant :
    - l’heure et la durée de la réunion
    - le lieu de la réunion (nom de la salle)
    - le sujet de la réunion
    - la liste des participants (adresses mail)
  - ajout d’une réunion
  - suppression d’une réunion
  - filtre des réunions par date ou par lieu
  - gestion de l’affichage responsive sur toutes les tailles de 
  téléphone et de tablette Android, en modes portrait et paysage

*Il faudra veiller à ce que l'application supporte Android 5.0 (API21) 
et ses versions supérieures.*  

### Outils et technologies utilisés
  - **Trello :** planification projet https://trello.com/b/nOvYDRAR/planification-de-projet-n-4
  - **Draw.io :** dessin de diagramme en ligne
  - **Android Strudio :** Environnement de Développement Intégré (IDE)
  - **Java :** langage de développement utilisé (contrainte projet)
  - **Git :** gestionnaire de version de code source décentralisé
  - **GitHub :** forge logiciel (dépôt de code source, bugtracker ... 
  contrainte projet)

### Planification du travail

Avant de commencer a développer l'application en elle-même :  

  - générer des données de test
  - implémenter un "fake service" pour simuler la communication avec 
  l'API de l'application

Le développement de l'application :  

  - **v0.1 :** afficher la liste des réunions
  - **v0.2 :** ajouter ou supprimer une réunion
  - **v0.3 :** filtrer l'affichage des réunions (par date ou lieu)
  - **v0.4 :** améliorer l'ergonomie (design, compatibilité matériel, 
  ...)

### Mon organisation

  - analyse et conception global
  - pour chaque fonctionnalité (v0.1 a v0.3) :
    - analyse et conception
    - implémentation de la fonctionnalité
    - implémentation des tests unitaires et/ou instrumentaux
    - tests (exécution des nouveaux tests)
    - tests de non régréssion (exécution des tests des précédantes 
    fonctionnalités)
    - validation (documenter et versionner la nouvelle fonctionnalité)
  - test et validation global (tests unitaires, instrumentaux et 
  utilisateurs)

### Livrables

  - les rapports d'exécution des tests unitaires, instrumentaux et 
  finaux réussis (format HTML)
  - un lien vers le dépôt GitHub contenant le code de votre application
  - un paragraphe justifiant le choix du langage Java par rapport a 
  Kotlin pour le développement de l’application

## Réalisation

### Version 0.1

Afficher la liste des réunions. Voici le prototype :  

![MaReu_v0 1](https://user-images.githubusercontent.com/60298344/78250742-9b9dbb00-74f0-11ea-98a3-0849a1d5a596.png)

Une réunion est représentée par la classe `Meeting` (nous 
n'affichons que les attributs) :  

![model_class_diagram_v0 1](https://user-images.githubusercontent.com/60298344/78250199-cb988e80-74ef-11ea-9350-6074a9b2a0aa.png)


### Version 0.2

Ajouter ou supprimer une réunion. Voici le prototype :  

![MaReu_v0 2](https://user-images.githubusercontent.com/60298344/78250159-bde30900-74ef-11ea-9ce4-a55720f6063c.png)

## Sources

[**Date & Time** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=Le47R9H3qow&list=PLrnPJCHvNZuAWIa1JCho4PGgNTNK0dfJ_)  
[**Validate Email & Password with Regular Expression** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=cnD_7qFeZcY&list=PLrnPJCHvNZuBJwo9Zt3AQwpRax4RtrReD&index=5)  
[**Search Functionality for RecyclerView** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=OWwOSLfWboY&list=PLrnPJCHvNZuBJwo9Zt3AQwpRax4RtrReD&index=7)  
[**How to Filter a RecyclerView with SearchView** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=sJ-Z9G0SDhc)  
[**Check if a Toast was displayed** - Gist brunodles](https://gist.github.com/brunodles/badaa6de2ad3a84138d517795f15efc7)  
