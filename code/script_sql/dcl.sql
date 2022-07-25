-----creation des sequence;

drop sequence seq_personne;
create sequence seq_personne
increment 1
start 000001 ;

drop sequence seq_categorie;
create sequence seq_categorie
increment 1
start 000001 ;

drop sequence seq_activite;
create sequence seq_activite
increment 1
start 000001 ;

drop sequence seq_adresse;
create sequence seq_adresse
increment 1
start 000001 ;

drop sequence seq_region;
create sequence seq_region
increment 1
start 000001 ;

drop sequence seq_ville;
create sequence seq_ville
increment 1
start 000001;

drop sequence seq_lieu_de_reference;
create sequence seq_lieu_de_reference
increment 1
start 000001 ;

drop sequence seq_horaire;
create sequence seq_horaire
increment 1
start 000001;


drop sequence seq_service;
create sequence seq_service
increment 1
start 000001 ;

drop sequence seq_horaire_par_semaine;
create sequence seq_horaire_par_semaine
increment 1
start 000001 ;

drop sequence seq_quartier;
create sequence seq_quartier
increment 1
start 000001 ;


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
grant all on lieu_de_reference to groupe3;
grant all on adresse to groupe3;


---::: Sequence

grant all on seq_personne to groupe3;
grant all on seq_categorie to groupe3;
grant all on seq_activite to groupe3;
grant all on seq_adresse to groupe3;
grant all on seq_ville to groupe3;
grant all on seq_quartier to groupe3;
grant all on seq_service to groupe3;
grant all on seq_horaire to groupe3;
grant all on seq_horaire_par_semaine to groupe3;

--::: Fonction longeur de la clé


create or replace function keyFormat(sequence_value integer) 
returns varchar as
$$
DECLARE
	sequence_value_format text;
BEGIN
	
	if sequence_value >= 1 and sequence_value <= 9 then
		sequence_value_format :='00000'|| sequence_value;
	elsif sequence_value >= 10 and sequence_value <= 99 then
		sequence_value_format :='0000'|| sequence_value;
	end if;
	return sequence_value_format;
END;
$$
language 'plpgsql';


create or replace function numAutho() returns trigger as
$$
DECLARE
	cle text;
	sequence_value integer;
BEGIN
	if tg_table_name = 'personne' then
		sequence_value := nextval('seq_personne');
		--raise notice 'sequence_value=%',sequence_value;
		cle := 'PER'|| keyFormat(sequence_value) ;
		new.id_personne:=cle;
	elsif tg_table_name = 'categorie' then
		sequence_value := nextval('seq_categorie');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='CAT'|| keyFormat(sequence_value);
		new.id_categorie:=cle;
	elsif tg_table_name = 'activite' then
		sequence_value := nextval('seq_activite');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='ACT'|| keyFormat(sequence_value);
		new.id_activite:=cle;
	elsif tg_table_name = 'adresse' then
		sequence_value := nextval('seq_adresse');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='ADR'|| keyFormat(sequence_value);
		new.id_adresse:=cle;
	elsif tg_table_name = 'region' then
		sequence_value := nextval('seq_region');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='REG'|| keyFormat(sequence_value);
		new.id_region:=cle;
	elsif tg_table_name = 'ville' then
		sequence_value := nextval('seq_ville');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='VIL'|| keyFormat(sequence_value);
		new.id_ville:=cle;
	elsif tg_table_name = 'quartier' then
		sequence_value := nextval('seq_quartier');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='QAR'|| keyFormat(sequence_value);
		new.id_quartier:=cle;
	elsif tg_table_name = 'lieu_de_reference' then
		sequence_value := nextval('seq_lieu_de_reference');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='LRF'|| keyFormat(sequence_value);
		new.id_reference:=cle;
	elsif tg_table_name = 'horaire' then
		sequence_value := nextval('seq_horaire');
		--raise notice 'sequence_value=%',sequence_value;
		cle:='HOR'|| keyFormat(sequence_value);
		new.id_horaire:=cle;
	elsif tg_table_name = 'service' then
		sequence_value := nextval('seq_service');
		raise notice 'sequence_value=%',sequence_value;
		cle:='SER'|| keyFormat(sequence_value);
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
for each row execute procedure numAutho();

create trigger autho_activite before insert on activite
for each row execute procedure numAutho();

create trigger autho_adresse before insert on adresse
for each row execute procedure numAutho();

create trigger autho_region before insert on region
for each row execute procedure numAutho();

create trigger autho_ville before insert on ville
for each row execute procedure numAutho();

create trigger autho_lieuRef before insert on lieu_de_reference
for each row execute procedure numAutho();

create trigger autho_horaire before insert on horaire
for each row execute procedure numAutho();

create trigger autho_service before insert on service
for each row execute procedure numAutho();

create trigger autho_horaireSem before insert on horaire_par_semaine
for each row execute procedure numAutho();

create trigger autho_quartier before insert on quartier
for each row execute procedure numAutho();


