
INSERT INTO personne(nom, prenom, contact) values
('Daiki', 'Alhasan', '91-81-79-07'),
('Kondi', 'Malik', '93-29-54-86'),
('Adjanayo', 'Simone', '92-78-46-21'),
('Shintaro', 'Midorima', '92-35-84-96');

INSERT INTO categorie(nom)VALUES
('menuiserie');


INSERT INTO activite(nom, id_categorie) VALUES
('menuisier', 'CA2000');

INSERT INTO region(nom) values
('Centrale');

INSERT INTO ville(nom, id_region) values
('Sokodé', 'RE5000');

INSERT INTO quartier(nom, id_ville) values
('KomahI', 'VI6000'),
('KomahII', 'VI6000'),
('KomahIII', 'VI6000'),
('Kpangalam', 'VI6000'),
('Kparato', 'VI6000'),
('Didaourè', 'VI6000');

INSERT INTO lieu_de_reference(nom, id_quartier) VALUES('Marché de komah','QU7000');

INSERT INTO horaire(heure_debut_matinee, heure_fin_matinee, heure_debut_soiree, heure_fin_soiree, minute_debut_matinee, minute_fin_matinee, minute_debut_soiree, minute_fin_soiree)VALUES
(07, 12, 14, 17, 00, 00, 30, 30);

INSERT INTO service(designation, description, id_personne, id_activite)values
('Bon taff', 'confection des chaises et lits', 'PE1000', 'AC3000');

INSERT INTO adresse(id_service, id_reference)VALUES
('SE10000', 'LR8000');

INSERT INTO horaire_par_semaine(id_service, id_horaire) VALUES('SE10000', 'HO9000');




