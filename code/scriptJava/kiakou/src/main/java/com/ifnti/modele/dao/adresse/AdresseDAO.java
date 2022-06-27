package com.ifnti.modele.dao.adresse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.adresse.Adresse;
import com.ifnti.modele.adresse.LieuDeReference;
import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.dao.DAO;

public class AdresseDAO extends DAO<Adresse> {

    @Override
    public String create(Adresse a)  {

        String requete = String.format(
            "insert into adresse (id_quartier, id_reference) values('%s', '%s') returning id_adresse ;", 
                       a.getQuartier().getMNum() , a.getLieuDeReference().getMNum()
                       );
       String resultat = this.insertObject(requete,"adresse");
       return resultat;     
    }

    @Override
    public boolean update(Adresse object) {
        return false;
    }

    @Override
    public boolean delete(Adresse object) {

        return false;
    }

    @Override
    public Adresse findById(String id) {

        String requete = String.format("SELECT * FROM adresse WHERE id_adresse = '%s' ;", id);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){
                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                
                String id_reference = resultat.getString("id_reference");
                LieuDeReference lieuDeReference = Kiakou.lrDAO.findById(id_reference);

                return new Adresse(id, quartier, lieuDeReference);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Adresse findByName(String pName) {

        String id = Kiakou.qDAO.findByName(pName).getMNum();

        String requete = String.format("SELECT * FROM adresse WHERE id_quartier = '%s' ;", id);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){//id_adresse
                String id_adresse = resultat.getString("id_adresse");

                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                
                String id_reference = resultat.getString("id_reference");
                LieuDeReference lieuDeReference = Kiakou.lrDAO.findById(id_reference);

                return new Adresse(id_adresse, quartier, lieuDeReference);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Adresse findByReferenceName(String pName) {

        String id = Kiakou.lrDAO.findByName(pName).getMNum();

        String requete = String.format("SELECT * FROM adresse WHERE id_reference = '%s' ;", id);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){//id_adresse
                String id_adresse = resultat.getString("id_adresse");

                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                
                String id_reference = resultat.getString("id_reference");
                LieuDeReference lieuDeReference = Kiakou.lrDAO.findById(id_reference);

                return new Adresse(id_adresse, quartier, lieuDeReference);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<Adresse> getAll() {
        ArrayList<Adresse> adresses = new ArrayList<Adresse>();

        String requete = String.format("SELECT * FROM adresse ;");
           
        ResultSet resultat =  selectObject(requete);
        try {
            while (resultat.next()){//id_adresse
                String id_adresse = resultat.getString("id_adresse");

                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                
                String id_reference = resultat.getString("id_reference");
                LieuDeReference lieuDeReference = Kiakou.lrDAO.findById(id_reference);

                adresses.add(new Adresse(id_adresse, quartier, lieuDeReference));
            }
            return adresses;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
