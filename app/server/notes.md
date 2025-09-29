# Questionnements serveur

Le répertoire courant hébergera la partie serveur de notre application (JS + TS).

Étant donné que [le modèle](https://git.unistra.fr/e.frison/w41) que nous avons conçu dans le cadre du module W41 met en place une authentification - et que nous souhaitions repartir d'un code propre, nous nous sommes permis d'utliser celui proposé comme correction.

Ce fichier n'a pas vocation à être pérenne, mais pourra alimenter la discussion au sein du binôme, ainsi que la soutenance.

Nos choix définitifs seront expliqués dans le fichier `docs/ARCHITECTURE.md`.

## Problèmes

- Nous avons eu de grosses difficultés pour nous connecter à l'API.
  - Solution proposée pour l'émulateur : BASE_URL 10.0.2.2, `android:usesCleartextTraffic="true"` dans le _Manifest_. 
  - Solution proposée pour un smartphone physique appartenant au même réseau local : se connecter sur l'IP de l'ordinateur sur lequel tourne le serveur.
- Il a fallu ajouter une route à notre API afin de pouvoir récupérer tous les livres associés à un tag.
- qsqdfq