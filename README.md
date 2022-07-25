# Bot-Telegram

******************
Utilisation du bot
******************

* Package controller
	Dans le package controlleur nous avons quatre (4) fichier classe.
	- Kiakou : qui ne comporte que des méthodes et de nouvelle instance d'objet DAO
	- KiakouBot : Il comporte trois (3) méthodes : getBotUsername, getBotToken et onUpdateReceived() dans le quelle on traite tous les message reçut depuis le bot.
	- SessionID : Cette classe permet au bot d'avoire une mémoire.
	- Main : Classe dans la quelle on créer un nouvelle objet de type KiakouBot. C'est elle que vous devriez 
	exécuter.
* Package model
	Contient toute nos classe modele et leur DAO.
	
-----------------------------------------------------------------------------------------------
NB:

	Vous aurez peut être un problème de dépendance dans le pom.xml.
	Pour régler ce problème :
		- Créer vous même un projet marven.
		- Ajouter les dépendances suivante dans votre fichier pom.xml
		
		```xml
		<dependency>
			<groupId>org.telegram</groupId>
		       <artifactId>telegrambots</artifactId>
		       <version>${telegram.version}</version>
   		</dependency>
		<!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-core -->
		<dependency>
			<groupId>org.apache.shiro</groupId>
		     	<artifactId>shiro-core</artifactId>
		     	<version>${shiro.version}</version>
		</dependency>
	    	<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
	    	<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.2.18.jre7</version>
	    	</dependency>
		```
		-Copier coller les répertoires controlleur et modele dans votre répertoire com/domain(dans notre cas c'est com/ifnti)

--------------------------------------
compréhension pour propriétaires.
--------------------------------------

* Lire le fichier png hierarchieDesFichiers.png on y explique l'organisation de notre repertoire kiekou.
* Le ficher clean.py qui se trouve dans le modele sert à nettoyer nos fichiers Java générer par modélio.

* Lire le fichier branch. Nous avons deux branches: 
	- main
	- modification
	
	Travailler toujours sur la branche modification. Une fois le travail fini, basculer sur la branche
main et fusionner les deux branches.

Rappel Git :
	- Récupération des fichiers depuis gitHub: git clone https://github.com/Alhassan-Daiki/Bot-Telegram.git
	- Création d'une branche: git branch branche_name
	- Basculer sur une autre branche: git checkout branche_name
	- Pousser des fichiers sur gitHub. 
		** git commit
	- Fusionner les branches avec le pull request de git hub
	
NB: Si on a modifier sa branche local main par erreur.
	- git stash : Enléve les modifications de la branche actuelle(main) et les gardes dans un coin.
	- Changer de branche pour passer sur la branche sur la quelle vous voulez appiliquer ces modifications (git checkout nom_branche)
	- git stash pop: récupére les modifications qui avaient été mises dans un coin et les applique sur cette branche.
