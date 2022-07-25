\i ddl.sql
\i dcl.sql


INSERT INTO personne(nom, prenom, contact, mot_de_passe) values
('daiki', 'alhasan', '91-81-79-07', '5f4dcc3b5aa765d61d8327deb882cf99'),
('Kondi', 'abdoul malik', '93-29-54-86', '5f4dcc3b5aa765d61d8327deb882cf99'),
('Adjanayo', 'simone', '92-78-46-21', '5f4dcc3b5aa765d61d8327deb882cf99'),
('Shintaro', 'midorima', '92-35-84-96', '5f4dcc3b5aa765d61d8327deb882cf99');

INSERT INTO categorie(nom)VALUES
('btp / matériaux de construction'),
('édition / communication / multimédia'),
('électronique'),
('informatique / télécoms'),
('agroalimentaire');


INSERT INTO activite(nom, id_categorie) VALUES
('menuisieurie', 'CAT000001'),
('carreleur', 'CAT000001'),
('architech', 'CAT000001'),
('peintre du bâtiment', 'CAT000001'),
('maçonnerie', 'CAT000001'),
('electricien', 'CAT000001'),
('copywriting/rédacteur', 'CAT000002'),
('Montage multimédia(audio/vidéo)', 'CAT000002'),
('electricien', 'CAT000003'),
('developpeur d applications mobile', 'CAT000004'),
('developpeur d applications web', 'CAT000004'),
('developpeur d applications web & mobile', 'CAT000004'),
('restoration', 'CAT000005');

INSERT INTO region(nom) values
('Centrale');

INSERT INTO ville(nom, id_region) values
('Sokodé', 'REG000001');

INSERT INTO quartier(nom, id_ville) values
('KomahI', 'VIL000001'),
('KomahII', 'VIL000001'),
('KomahIII', 'VIL000001'),
('Komah', 'VIL000001'),
('Kpangalam', 'VIL000001'),
('Kparato', 'VIL000001'),
('Didaourè', 'VIL000001');

INSERT INTO lieu_de_reference(nom, id_quartier) VALUES
('marché de komah','QAR000002');

INSERT INTO horaire(heure_debut_matinee, heure_fin_matinee, heure_debut_soiree, heure_fin_soiree, minute_debut_matinee, minute_fin_matinee, minute_debut_soiree, minute_fin_soiree)
VALUES(00, 00, 00, 00, 00, 00, 00, 00);

INSERT INTO adresse(id_quartier, id_reference) VALUES
('QAR000001', null),
('QAR000002', 'LRF000001'),
('QAR000004', null);

INSERT INTO service(designation, description, id_personne, id_activite, id_adresse) VALUES
('Bon taff', 'confection des chaises et lits.', 'PER000001', 'ACT000001', 'ADR000002'),
('menusieurie moderne', 'confection de meuble.', 'PER000003', 'ACT000001', 'ADR000002'),
('blue horizon', 'architech, realisation des plan de batimânt.', 'PER000004', 'ACT000003', 'ADR000001'),
('kondi global', 'réalisation des applications web et mobile, site web ...', 'PER000002', 'ACT000012', 'ADR000001'),
('cratos', 'Nous somme un restaurant nous avons des menus locaux et européen', 'PER000003', 'ACT000013', 'ADR000001');


INSERT INTO horaire_par_semaine(id_service, id_horaire, jour) VALUES
('SER000001', 'HOR000001', 'lundi'),
('SER000002', 'HOR000001', 'lundi'),
('SER000003', 'HOR000001', 'lundi'),
('SER000004', 'HOR000001', 'lundi');




/*
INSERT INTO categorie(nom)VALUES
('btp / matériaux de construction'),
('agroalimentaire'),
('fabrication'),
('banque / assurance'),
('Commerce / négoce / distribution'),
('édition / communication / multimédia'),
('électronique / electricité'),
('informatique / télécoms'),
('métallurgie / travail du métal'),
('transports / logistique'),
('industrie pharmaceutique'),
('machines et équipements / automobile'),
('plastique / caoutchouc'),
('textile / habillement / caoutchouc'),
('service aux entreprise');

INSERT INTO activite(nom, id_categorie) VALUES
('menuisier / charpantier', 'CAT000001'),
('carreleur', 'CAT000001'),
('architech', 'CAT000001'),
('peintre du bâtiment', 'CAT000001'),
('maçon', 'CAT000001'),
('electricien', 'CAT000001'),
('artisan du bâtiment', 'CAT000001');
*/
