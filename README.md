# Projet_N-4_Mareu


**Mar�u** est le projet 4 du parcours DA Android d'OpenClassrooms.  

## Introduction

**Mar�u** est une application permettant de r�server des salles de 
r�union pour les collaborateurs de la soci�t� Lamzone.  

Dans ce projet l'application doit �tre d�velopp�e en "partant de z�ro".  

## Travail a r�aliser

Voici les fonctionnalit�s de l'application qui devront �tre 
impl�ment�es :  

  - liste des r�unions comprenant :
    - l�heure et la dur�e de la r�union
    - le lieu de la r�union (nom de la salle)
    - le sujet de la r�union
    - la liste des participants (adresses mail)
  - ajout d�une r�union
  - suppression d�une r�union
  - filtre des r�unions par date ou par lieu
  - gestion de l�affichage responsive sur toutes les tailles de 
  t�l�phone et de tablette Android, en modes portrait et paysage

*Il faudra veiller � ce que l'application supporte Android 5.0 (API21) 
et ses versions sup�rieures.*  

### Outils et technologies utilis�s
  - **Trello :** planification projet https://trello.com/b/nOvYDRAR/planification-de-projet-n-4
  - **Draw.io :** dessin de diagramme en ligne
  - **Android Strudio :** Environnement de D�veloppement Int�gr� (IDE)
  - **Java :** langage de d�veloppement utilis� (contrainte projet)
  - **Git :** gestionnaire de version de code source d�centralis�
  - **GitHub :** forge logiciel (d�p�t de code source, bugtracker ... 
  contrainte projet)

### Planification du travail

Avant de commencer a d�velopper l'application en elle-m�me :  

  - g�n�rer des donn�es de test
  - impl�menter un "fake service" pour simuler la communication avec 
  l'API de l'application

Le d�veloppement de l'application :  

  - **v0.1 :** afficher la liste des r�unions
  - **v0.2 :** ajouter ou supprimer une r�union
  - **v0.3 :** filtrer l'affichage des r�unions (par date ou lieu)
  - **v0.4 :** am�liorer l'ergonomie (design, compatibilit� mat�riel, 
  ...)

### Mon organisation

  - analyse et conception global
  - pour chaque fonctionnalit� (v0.1 a v0.3) :
    - analyse et conception
    - impl�mentation de la fonctionnalit�
    - impl�mentation des tests unitaires et/ou instrumentaux
    - tests (ex�cution des nouveaux tests)
    - tests de non r�gr�ssion (ex�cution des tests des pr�c�dantes 
    fonctionnalit�s)
    - validation (documenter et versionner la nouvelle fonctionnalit�)
  - test et validation global (tests unitaires, instrumentaux et 
  utilisateurs)

### Livrables

  - les rapports d'ex�cution des tests unitaires, instrumentaux et 
  finaux r�ussis (format HTML)
  - un lien vers le d�p�t GitHub contenant le code de votre application
  - un paragraphe justifiant le choix du langage Java par rapport a 
  Kotlin pour le d�veloppement de l�application

## R�alisation

### Version 0.1

Afficher la liste des r�unions. Voici le prototype :  

![MaReu_v0 1](https://user-images.githubusercontent.com/60298344/78250742-9b9dbb00-74f0-11ea-98a3-0849a1d5a596.png)

Une r�union est repr�sent�e par la classe `Meeting` (nous 
n'affichons que les attributs) :  

![model_class_diagram_v0 1](https://user-images.githubusercontent.com/60298344/78250199-cb988e80-74ef-11ea-9350-6074a9b2a0aa.png)


### Version 0.2

Ajouter ou supprimer une r�union. Voici le prototype :  

![MaReu_v0 2](https://user-images.githubusercontent.com/60298344/78250159-bde30900-74ef-11ea-9ce4-a55720f6063c.png)

## Sources

[**Date & Time** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=Le47R9H3qow&list=PLrnPJCHvNZuAWIa1JCho4PGgNTNK0dfJ_)  
[**Validate Email & Password with Regular Expression** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=cnD_7qFeZcY&list=PLrnPJCHvNZuBJwo9Zt3AQwpRax4RtrReD&index=5)  
[**Search Functionality for RecyclerView** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=OWwOSLfWboY&list=PLrnPJCHvNZuBJwo9Zt3AQwpRax4RtrReD&index=7)  
[**How to Filter a RecyclerView with SearchView** - YouTube "Coding In Flow"](https://www.youtube.com/watch?v=sJ-Z9G0SDhc)  
[**Check if a Toast was displayed** - Gist brunodles](https://gist.github.com/brunodles/badaa6de2ad3a84138d517795f15efc7)  
