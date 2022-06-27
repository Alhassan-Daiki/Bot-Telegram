package com.ifnti.modele.dao.adresse ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.adresse.Ville;
import com.ifnti.modele.dao.DAO;

public class QuartierDAO extends DAO <Quartier> {

    @Override
    public String create(Quartier object) {

        return null;
    }

    @Override
    public boolean update(Quartier object) {

        return false;
    }

    @Override
    public boolean delete(Quartier object) {

        return false;
    }

    @Override
    public Quartier findById(String id) {

        String requete = String.format("SELECT * FROM quartier WHERE id_quartier = '%s'", id);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){
                String nom = resultat.getString("nom");
                String id_ville = resultat.getString("id_ville");
                Ville ville = Kiakou.vDAO.findById(id_ville);
                return new Quartier(id, nom, ville);
            }
            
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Quartier findByName(String pName) {

        String requete = String.format("SELECT * FROM quartier WHERE nom = '%s'", pName);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){
                String id_quartier = resultat.getString("id_quartier");
                String id_ville = resultat.getString("id_ville");
                Ville ville = Kiakou.vDAO.findById(id_ville);
                return new Quartier(id_quartier, pName, ville);
            }
            
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Quartier> getAll() {

        ArrayList<Quartier> quartiers = new ArrayList<Quartier>();
        
        String requete = String.format("SELECT * FROM quartier ;");
           
        ResultSet resultat =  selectObject(requete);
        try {
            while(resultat.next()){
                String id_quartier = resultat.getString("id_quartier");
                String nom = resultat.getString("nom");
                String id_ville = resultat.getString("id_ville");
                Ville ville = Kiakou.vDAO.findById(id_ville);
                quartiers.add(new Quartier(id_quartier, nom, ville));
            }
            return quartiers;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}
