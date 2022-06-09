package com.ifnti.modele.dao.service ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Service;

public class ServiceDAO extends DAO <Service> {

    @Override
    public String create(Service s) {
         String requete = String.format("insert into Service (designation,description) values('%s', '%s') returning id_service;", 
                        s.getMDesignation(), s.getMDescription()/*,s.getmPersonne().getmNom()*/);
        System.out.println(requete);
        String resultat = this.insertObject(requete,"service");
        return resultat;     
    }

   // @Override
    public boolean update(Service object) {
        // TODO Auto-generated method stub
        //this.setObject(object);
       
          String requete = StringFormat("update Service set(designation, description) =('%s','%s') where id_service = '%s' )",object.getMDesignation(),object.getMDescription() ,object.getmNum())  ;
        super.updateObject(requete);
        return true;
    }

    private String StringFormat(String string, String mDesignation, String mDescription, String getmNum) {
        return null;
    }

    @Override
    public boolean delete(Service object) {
        // TODO Auto-generated method stub
        String requete = "Delete from service where id_service= " + object.getmNum() ;
        super.updateObject(requete);
        return true;
    }
    /**
     * 
     */
    @Override
    public Service findById(String id) {
        // TODO Auto-generated method stub
            String requete = "SELECT * FROM personne WHERE id_personne=" + id;
           
            ResultSet resultat =  selectObject(requete);
            try {
                String designation=resultat.getString("designation");
                String description = resultat.getString("description");
                String id_personne = resultat.getString("id_personne");
                String id_activite = resultat.getString("id_activite");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

             return null; 
        
    }

    @Override
    public Service findByName(String pName) {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public ArrayList<Service> getAll() {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub

        return false;
    }
    
}
