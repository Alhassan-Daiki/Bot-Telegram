INSERT INTO personne(nom, prenom, contact) values
('Daiki', 'Alhasan', '91-81-79-07'),
('Kondi', 'Malik', '93-29-54-86'),
('Adjanayo', 'Simone', '92-78-46-21'),
('Shintaro', 'Midorima', '92-35-84-96');

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
('menuisier', 'CAT000001'),
('carreleur', 'CAT000001'),
('architech', 'CAT000001'),
('peintre du bâtiment', 'CAT000001'),
('maçon', 'CAT000001'),
('electricien', 'CAT000001'),
('artisan du bâtiment', 'CAT000001');

INSERT INTO region(nom) values
('Centrale');

INSERT INTO ville(nom, id_region) values
('Sokodé', 'REG000001');

INSERT INTO quartier(nom, id_ville) values
('KomahI', 'VIL000001'),
('KomahII', 'VIL000001'),
('KomahIII', 'VIL000001'),
('Kpangalam', 'VIL000001'),
('Kparato', 'VIL000001'),
('Didaourè', 'VIL000001');

INSERT INTO lieu_de_reference(nom, id_quartier) VALUES('marché de komah','QAR000002');

INSERT INTO horaire(heure_debut_matinee, heure_fin_matinee, heure_debut_soiree, heure_fin_soiree, minute_debut_matinee, minute_fin_matinee, minute_debut_soiree, minute_fin_soiree)VALUES
(07, 12, 14, 17, 00, 00, 30, 30);

INSERT INTO adresse(id_reference)VALUES
('LRF000001');

INSERT INTO service(designation, description, id_personne, id_activite, id_adresse)values
('Bon taff', 'confection des chaises et lits', 'PER000001', 'ACT000001', 'ADR000001');


INSERT INTO horaire_par_semaine(id_service, id_horaire) VALUES('SER000001', 'HOR000001');

