package com.ifnti.modele.dao.adresse ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.adresse.Region;
import com.ifnti.modele.dao.DAO;

public class RegionDAO extends DAO <Region> {

    @Override
    public String create(Region object) {

        return null;
    }

    @Override
    public boolean update(Region object) {

        return false;
    }

    @Override
    public boolean delete(Region object) {

        return false;
    }

    @Override
    public Region findById(String id) {
        
        String requete = String.format("SELECT * FROM region WHERE id_region = '%s'", id); 
        ResultSet resultat =  selectObject(requete);
        try {
            if(resultat.next()) {
                String nom = resultat.getString("nom");
                return new Region(id, nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Region findByName(String pName) {

        String requete = String.format("SELECT * FROM region WHERE nom = '%s'", pName);
        ResultSet resultat =  selectObject(requete);
        try {
            if(resultat.next()){
                String id_region = resultat.getString("id_region");
                return new Region(id_region, pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Region> getAll() {
        
        ArrayList<Region> regions = new ArrayList<Region>();
        String requete = String.format("SELECT * FROM region ;");
        ResultSet resultat =  selectObject(requete);
        try {
            while(resultat.next()){
                String id_region = resultat.getString("id_region");
                String nom = resultat.getString("nom");
                regions.add(new Region(id_region, nom));
            }
            return regions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
