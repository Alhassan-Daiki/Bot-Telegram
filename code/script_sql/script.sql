

-- service par activite
-- service par activite puis par ville
-- service par 

select * from service 
where id_activite in (select id_activite from activite where id_activite='ACT000001')
	and id_adresse in (select id_adresse from adresse where id_quartier='QAR000001' or id_quartier 
						in (select id_quartier from lieu_de_reference where id_quartier = 'QAR000001')
					  );


select id_service, designation, description, id_personne, id_activite, a.id_adresse
from service as s, adresse as a, lieu_de_reference l, quartier as q, ville as v
where s.id_adresse = a.id_adresse and a.id_quartier = q.id_quartier 
	and a.id_reference = l.id_reference 
	and q.id_ville = v.id_ville	
	and id_activite in (select id_activite from activite where id_activite='ACT000001')
	and v.id_ville = 'VIL000001';
	


