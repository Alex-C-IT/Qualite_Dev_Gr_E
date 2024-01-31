# Projet de qualité développement ASBank2023
### Groupe E

## Pré-requis

**IDE** : IntelIJ IDEA - [Télécharger](https://www.jetbrains.com/fr-fr/idea/download/?section=windows)<br>
**Serveur** : Apache Tomcat version >= 9.0 - [Télécharger](https://tomcat.apache.org/download-90.cgi)<br>
**JRE** : Java 1.8 - [Télécharger](https://www.java.com/fr/download/manual.jsp)<br>
**SGBD** : MySQL 8.0.x - [Télécharger](https://dev.mysql.com/downloads/mysql/)<br>

> [!NOTE]
> Veuillez installer ~~(installation qui ne sera pas détaillée ici car vous êtes normalement développeur)~~ les différents programmes ci-dessus pour faire fonctionner le projet de manière optimale.

Récupérer le projet via cette commande :<br> 
```git clone https://github.com/Alex-C-IT/Qualite_Dev_Gr_E.git```

Le projet est lié à une base de données en ligne.
Si vous voulez utiliser votre propre base de données, créez en une et chargez les scripts SQL qui se trouvent dans le dossier `script`.

Pour changer les informations de connexion de la base de données principale :<br>
```WebContent/WEB-INF/applicationContext.xml```. Adaptez les lignes 49 à 51.<br>
<br>
Pour changer les informations de connexion de la base de données de tests :<br>
```src/test/ressources/TestsDaoHibernate-context.xml```. Adaptez les lignes 41 à 43.<br>
```src/test/ressources/TestsBanqueManager-context.xml```. Adaptez les lignes 41 à 43.<br>

Ouvrez IntelIJ IDEA.

## Maven
  Le projet nécessite la mise en place d’une configuration de « Maven » au sein de l’IDE. Maven est
l’outil permettant de « build » le projet tout en gérant les dépendances de celui-ci.

### Configuration de Maven

Dans IntellIJ, cliquez sur `Run > Edit Configuration`.<br>

Une fenêtre s'ouvre. Cliquez sur le `+` en haut à gauche, puis cherchez et cliquez sur `Maven`. <br>

Dans `Run`, Tapez `clean install`. Vous pouvez rajouter `-DskipTests` si vous voulez passer les tests unitaires lors du build.<br>

Dans `Working Directory`, vérifiez bien que le projet est ciblé. Si non, chargez-le. <br>

Cliquez maintement sur `Maven Options` et vérifiez que Maven a correctement été "capté". Si non, décochez `Inherit from settings` et aller trouver son répertoire.

Pour finir, cliquez sur `Java Options`, vérifiez que la version de Java 1.8 est sélectionnée puis valider la configuration.

## Serveur Tomcat

Le projet étant une application web, un serveur web est nécessaire pour déployer le projet et y accéder par la suite. Il est donc nécessaire de configurer un serveur apache Tomcat version 9 ou supérieure au sein de l’IDE.

Dans IntellIJ, cliquez sur `Run > Edit Configuration`.<br>

Comme pour Maven, cliquez sur le `+` en haut à gauche, puis cherchez et cliquez sur `Tomcat Server > Local`. <br>

Dans `Name` : mettez le nom que vous souhaitez à votre serveur.

Dans `Application server` : cliquez sur `Configure` puis aller chercher le dossier de votre serveur à partir de `Tomcat Home`. Validez.

Dans `URL` : tapez l'adreese que `http://localhost:port/`. Changer `port` par le numéro de port que vous souhaitez.

Dans `JRE` : chargez de nouveau `Java 1.8`

Dans `HTTP port` : Mettez le port que vous avez défini dans `URL`.

Dans la section `Before launch` : cliquez sur le `+`, sélectionnez `Build Artifacts` et sélectionnez `_00_ASBank2023:war exploded`. Validez.

Dans l'onglet `Deployement` : cliquez sur `+ > Artifacts...`, sélectionnez `_00_ASBank2023:war exploded` puis validez.

Dans `Application context` : renseignez `/_00_ASBank2023`. 

Vous pouvez valider la configuration du serveur !

## Exécution du projet

Dans IntellIJ : 

[![Image](https://i.goopics.net/95jrhu.png)](https://goopics.net/i/95jrhu)

Sélectionnz la configuration Maven puis cliquez sur la flèche verte pour exécuter le build.

Si tout s'est bien passé, vous devriez avoir ceci : 

[![Image](https://i.goopics.net/z6av8w.png)](https://goopics.net/i/z6av8w)

Maintenant que le build est réussi, il est possible de le déployer sur le serveur Tomcat. 

Toujours sur IntellIJ : 

[![Image](https://i.goopics.net/7ifuu8.png)](https://goopics.net/i/7ifuu8)

Sélectionnz la configuration Tomcat puis cliquez sur la flèche verte pour exécuter le déploiement du build.

Une fois le déploiement effectué, IntellIJ lance votre navigateur par défaut et ouvre le chemin du projet.

[![Image](https://i.goopics.net/zmzjsz.png)](https://goopics.net/i/zmzjsz)

**La configuration du projet est terminée ! Bonne chance pour la suite et amusez-vous bien !**

### Accès comptes

Compte administrateur :<br>
```Utilisateur : admin```<br>
```Mot de passe : admin```<br><br>
Comptes utilisateurs :<br>
```Utilisateur : client1```<br>
```Mot de passe : client1```<br><br>
```Utilisateur : client2```<br>
```Mot de passe : client2```<br><br>

### Informations sur le projet

1. Modifications apportées au projet
  - Mise à jour des différentes dépendances pour accéder à l'application.
  - Faire les modifications nécessaire pour build sans passer les tests.
  - Mise en place de l'outil SonarCloud pour la qualité de code.
  - Mise en place de l'outil Jenkins pour l'intégration continue du projet.
  - Rédaction de la documentation d'installation et d'exécution du programme.
  - Rédaction de la documentation technique pour SonarCloud.
  - Rédaction de la documentation technique pour Jenkins.
  - Fonctionnalité : Cryptage des mots de passe en base de données avec BCrypt.
  - Fonctionnalité : Renvoi de mot de passe par email.
  - Fonctionnalité : Bloquer l'accès à un compte après 3 tentatives de connexion infructueuses.
  - Couverture du code avec des tests unitaires (+50). Converture actuelle : 42,3 %
  - Ajout de la dépendance Mockito pour certains tests unitaires.
  - Correction de code selon les indicateurs de SonarCloud. Codes restants : 47.
  - Début de refonte de l'interface.
  - Diverses corrections de bugs.

### Indicateurs 

1. SPRINT I
  - Indicateurs qualité SonarCloud -- Début --
![alt text](https://i.goopics.net/c8n3nm.png)

  - Indicateurs qualité SonarCloud -- Fin --
![alt text](https://i.goopics.net/r7nt0h.png)

> [!NOTE]
> Malheureusement, pas d'indicateurs Jenkins de début de sprint...

  - Indicateurs build Jenkins -- Fin --
[![Image](https://i.goopics.net/oi4o5s.png)](https://goopics.net/i/oi4o5s)

2. SPRINT II
  - Indicateurs qualité SonarCloud -- Début --
[![Image](https://i.goopics.net/r7nt0h.png)](https://goopics.net/i/r7nt0h)

  - Indicateurs qualité SonarCloud -- Fin --
[![Image](https://i.goopics.net/qmfo16.png)](https://goopics.net/i/qmfo16)
    
  - Indicateurs build Jenkins -- Début --
[![Image](https://i.goopics.net/m3luap.png)](https://goopics.net/i/m3luap)
[![Image](https://i.goopics.net/83bpmz.png)](https://goopics.net/i/83bpmz)

  - Indicateurs build Jenkins -- Fin --
[![Image](https://i.goopics.net/a44pgg.png)](https://goopics.net/i/a44pgg)
[![Image](https://i.goopics.net/dnsyhr.png)](https://goopics.net/i/dnsyhr)


































