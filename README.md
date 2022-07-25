# Bot-Telegram
--------------------------------------
Utilisation du bot
--------------------------------------

* Package controller
	Dans le package controlleur nous avons quatre (4) fichier classe.
	- Kiakou : qui ne comporte que des méthodes et de nouvelle instance d'objet DAO
	- KiakouBot : Il comporte trois (3) méthodes : onUpdate(), get.

--------------------------------------
compréhension pour propriétaires.
--------------------------------------

* Lire le fichier png hierarchieDesFichiers.png on y explique l'organisation de notre repertoire kiekou.
* Le ficher clean.py qui se trouve dans le modele sert à nettoyer nos fichiers Java générer par modélio.

* Lire le fichier branch. Nous avons deux branches: 
	** main
	** modification
	
	Travailler toujours sur la branche modification. Une fois le travail fini, basculer sur la branche
main et fusionner les deux branches.

Rappel Git :
	** Récupération des fichiers depuis gitHub: git clone https://github.com/Alhassan-Daiki/Bot-Telegram.git
	** Création d'une branche: git branch branche_name
	** Basculer sur une autre branche: git checkout branche_name
	** Pousser des fichiers sur gitHub. 
		** git commit
	** Fusionner les branches avec le pull request de git hub
	
NB: Si on a modifier sa branche local main par erreur.
	** git stash : Enléve les modifications de la branche actuelle(main) et les gardes dans un coin.
	** Changer de branche pour passer sur la branche sur la quelle vous voulez appiliquer ces modifications (git checkout nom_branche)
	** git stash pop: récupére les modifications qui avaient été mises dans un coin et les applique sur cette branche.
