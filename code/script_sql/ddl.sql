\c template1

drop database if exists kiakou;
create database kiakou;

create user groupe3 password 'groupe3';

\c kiakou


create table personne ( id_personne varchar primary key, 
						 nom varchar,
 						 prenom varchar, 
						 contact varchar);


create table categorie ( id_categorie varchar primary key,
						 nom varchar);

create table activite ( id_activite varchar primary key, 
						 nom varchar,
						 id_categorie varchar references categorie(id_categorie));

create table region ( id_region varchar primary key, nom varchar);

create table ville (  id_ville varchar primary key,
					  nom varchar,
					 id_region varchar references region(id_region));

create table quartier ( id_quartier varchar primary key,
						 nom varchar,
						id_ville varchar references ville(id_ville));

create table lieu_de_reference  (id_reference varchar primary key,
							 nom varchar,
							 id_quartier varchar references quartier(id_quartier));

create table horaire (id_horaire varchar primary key,
					heure_debut_matinee integer,
					heure_fin_matinee integer,
					heure_debut_soiree integer,
					heure_fin_soiree integer,
					minute_debut_matinee integer,
					minute_fin_matinee integer,
					minute_debut_soiree integer,
					minute_fin_soiree integer);

create table adresse ( id_adresse varchar primary key ,
				   id_quartier varchar references quartier(id_quartier),
				   id_reference varchar references lieu_de_reference(id_reference));

create table service ( id_service varchar primary key,
					designation varchar,
					description varchar,
					id_personne varchar references personne(id_personne),
					id_activite varchar references activite(id_activite),
					id_adresse varchar references adresse(id_adresse));

create table horaire_par_semaine (id_service varchar primary key,
								 id_horaire varchar references horaire(id_horaire));

