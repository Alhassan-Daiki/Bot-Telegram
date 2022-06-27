package com.ifnti.modele.dao.service ;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.adresse.Adresse;
import com.ifnti.modele.categorie.Activite;
import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Personne;
import com.ifnti.modele.service.Service;

public class ServiceDAO extends DAO <Service> {

    @Override
    public String create(Service s) {
         String requete = String.format(
             "insert into Service (designation, description, id_personne, id_activite, id_adresse) values('%s', '%s', '%s', '%s') returning id_service;", 
                        s.getMDesignation(), s.getMDescription(), s.getMPersonne().getmNum(), s.getMActivite().getmNum(), s.getMAdresse().getmNum()
                        );
        String resultat = this.insertObject(requete,"service");
        return resultat;     
    }


    @Override
    public boolean update(Service s) {
        
        String requete = String.format(
            "update Service set(designation, description) = ('%s','%s') where id_service = '%s' )",
            s.getMDesignation(), s.getMDescription(), s.getMNum()
            )  ;
        super.updateObject(requete);
        return true;
    }



    @Override
    public boolean delete(Service s) {

        String requete = "Delete from service where id_service= " + s.getMNum() ;
        super.updateObject(requete);
        return true;
    }
    /**
     * 
     */
    @Override
    public Service findById(String id) {

            String requete = "SELECT * FROM service WHERE id_service = " + id;
           
            ResultSet resultat =  selectObject(requete);
            try {
                if (resultat.next()){
                    String designation=resultat.getString("designation");
                    String description = resultat.getString("description");

                    String id_personne = resultat.getString("id_personne");
                    Personne personne = Kiakou.pDAO.findById(id_personne);
                    
                    String id_activite = resultat.getString("id_activite");
                    Activite activite = Kiakou.aDAO.findById(id_activite);

                    String id_adresse = resultat.getString("id_adresse");
                    Adresse adresse = Kiakou.adDAO.findById(id_adresse);
                    
                    Service s = new Service(id, designation, description, personne, activite, adresse);
                    return s;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

             return null; 
        
    }

    @Override
    public Service findByName(String pName) {

        String requete = "SELECT * FROM service WHERE nom = " + pName;
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){

                String id_service =resultat.getString("id_service");

                String description = resultat.getString("description");

                String id_personne = resultat.getString("id_personne");
                Personne personne = Kiakou.pDAO.findById(id_personne);
                
                String id_activite = resultat.getString("id_activite");
                Activite activite = Kiakou.aDAO.findById(id_activite);
                
                String id_adresse = resultat.getString("id_adresse");
                Adresse adresse = Kiakou.adDAO.findById(id_adresse);
                
                Service s = new Service(id_service, pName, description, personne, activite, adresse);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Service> getAll() {

        ArrayList<Service> services = new ArrayList<Service>();

        String requete = "SELECT * FROM service ;";
           
        ResultSet resultat =  selectObject(requete);
        try {
            while (resultat.next()){

                String id_service =resultat.getString("id_service");

                String designation=resultat.getString("designation");
                String description = resultat.getString("description");

                String id_personne = resultat.getString("id_personne");
                Personne personne = Kiakou.pDAO.findById(id_personne);

                String id_activite = resultat.getString("id_activite");
                Activite activite = Kiakou.aDAO.findById(id_activite);

                String id_adresse = resultat.getString("id_adresse");
                Adresse adresse = Kiakou.adDAO.findById(id_adresse);

                Service s = new Service(id_service, designation, description, personne, activite, adresse);

                services.add(s);
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
