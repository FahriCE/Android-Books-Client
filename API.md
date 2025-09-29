## Mode d'emploi

Nous recommandons d'uitliser notre la version du serveur, légèrement modifiée par rapport au corrigé du module W41.

### API Books

URL : https://git.unistra.fr/e_frison_f_cetinkaya/p42-abc/-/tree/main/app/server/api_books

#### Installation

Depuis la racine du projet :

```bash
$ cd app/server/api_books
$ npm install
$ npx prisma db push --force-reset
$ npx prisma db seed
```

#### Lancement

```bash
$ npm run dev
```