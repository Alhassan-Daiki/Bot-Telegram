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

create table lieu_de_reference ( id_reference varchar primary key,
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


create table service ( id_service varchar primary key,
						designation varchar,
						description varchar,
						id_personne varchar references personne(id_personne),
						id_activite varchar references activite(id_activite)
						);

create table adresse ( id_adresse varchar primary key ,
						id_service varchar references service(id_service),
						id_reference varchar references lieu_de_reference(id_reference));


create table horaire_par_semaine (id_service varchar references service(id_service),
								 id_horaire varchar references horaire(id_horaire),
								 CONSTRAINT pk_hps PRIMARY KEY(id_service, id_horaire)); 
								 



-----creation des sequence;

drop sequence seq_personne;
create sequence seq_personne
increment 1
start 1000 ;

drop sequence seq_categorie;
create sequence seq_categorie
increment 1
start 2000 ;

drop sequence seq_activite;
create sequence seq_activite
increment 1
start 3000 ;

drop sequence seq_adresse;
create sequence seq_adresse
increment 1
start 4000 ;

drop sequence seq_region;
create sequence seq_region
increment 1
start 5000 ;

drop sequence seq_ville;
create sequence seq_ville
increment 1
start 6000 ;

drop sequence seq_quartier;
create sequence seq_quartier
increment 1
start 7000 ;

drop sequence seq_lieu_de_reference;
create sequence seq_lieu_de_reference
increment 1
start 8000 ;

drop sequence seq_horaire;
create sequence seq_horaire
increment 1
start 9000 ;


drop sequence seq_service;
create sequence seq_service
increment 1
start 10000 ;





--------- permission
---::::: Table 
grant all on personne to groupe3;
grant all on service to groupe3;
grant all on horaire_par_semaine to groupe3;
grant all on categorie to groupe3;
grant all on activite to groupe3;
grant all on quartier to groupe3;
grant all on ville to groupe3;
grant all on horaire to groupe3;
grant all on region to groupe3;
grant all on adresse to groupe3;
grant all on lieu_de_reference to groupe3;

---::: Sequence

grant all on seq_personne to groupe3;
grant all on seq_categorie to groupe3;
grant all on seq_activite to groupe3;
grant all on seq_adresse to groupe3;
grant all on seq_ville to groupe3;
grant all on seq_quartier to groupe3;
grant all on seq_region to groupe3;
grant all on seq_service to groupe3;
grant all on seq_lieu_de_reference to groupe3;
grant all on seq_horaire to groupe3;




create or replace  function numAutho() returns trigger as

$$
DECLARE
	cle text;
BEGIN
	if tg_table_name = 'personne' then
		cle:='PE'|| nextval('seq_personne');
		new.id_personne:=cle;
	elsif tg_table_name = 'categorie' then
		cle:='CA'|| nextval('seq_categorie') ;
		new.id_categorie:=cle;
	elsif tg_table_name = 'activite' then
		cle:='AC'|| nextval('seq_activite');
		new.id_activite:=cle;
	elsif tg_table_name = 'adresse' then
		cle:='AD'|| nextval('seq_adresse');
		new.id_adresse:=cle;
	elsif tg_table_name = 'region' then
		cle:='RE'|| nextval('seq_region');
		new.id_region:=cle;
	elsif tg_table_name = 'ville' then
		cle:='VI'|| nextval('seq_ville');
		new.id_ville:=cle;
	elsif tg_table_name = 'quartier' then
		cle:='QU'|| nextval('seq_quartier');
		new.id_quartier:=cle;
	elsif tg_table_name = 'lieu_de_reference' then
		cle:='LR'|| nextval('seq_lieu_de_reference');
		new.id_reference:=cle;
	elsif tg_table_name = 'horaire' then
		cle:='HO'|| nextval('seq_horaire');
		new.id_horaire:=cle;
	elsif tg_table_name = 'service' then
		cle:='SE'|| nextval('seq_service');
		new.id_service:=cle;

	end if;
	return new;
END;
$$
language 'plpgsql';

--------------trigger---------------------

create trigger autho_perso before insert on personne
for each row execute procedure   numAutho();

create trigger autho_categorie before insert on categorie
for each row execute procedure   numAutho();

create trigger autho_activite before insert on activite
for each row execute procedure   numAutho();

create trigger autho_adresse before insert on adresse
for each row execute procedure   numAutho();

create trigger autho_region before insert on region
for each row execute procedure   numAutho();

create trigger autho_ville before insert on ville
for each row execute procedure   numAutho();

create trigger autho_ville before insert on quartier
for each row execute procedure   numAutho();

create trigger autho_lieuRef before insert on lieu_de_reference
for each row execute procedure   numAutho();

create trigger autho_horaire before insert on horaire
for each row execute procedure   numAutho();

create trigger autho_service before insert on service
for each row execute procedure   numAutho();



