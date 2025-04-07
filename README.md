# MICROSERVICE RESSOURCE – SPRING BOOT

Microservice pour la gestion des ressources pédagogiques avec statistiques par type, upload de fichiers PDF, classification par type, et génération de résumé.

## FONCTIONNALITÉS PRINCIPALES

- Ajouter une ressource avec un fichier PDF
- Récupérer / modifier / supprimer des ressources
- Statistiques par type de ressource
- Résumé automatique du fichier PDF

## TECHNOLOGIES UTILISÉES

- **Spring Boot**, **Spring Data JPA**
- **Lombok**, **MultipartFile**
- **MySQL**
- **OpenPDF** / **PDFBox** (résumé PDF)

## POINTS AJOUTÉS

- **Tests unitaires** : Tests utilisant **JUnit** et **Mockito** pour le résumé du PDF.
- **Dockerisation**
- **Logs et journalisation**
