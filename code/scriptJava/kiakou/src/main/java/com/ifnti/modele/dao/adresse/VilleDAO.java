package com.ifnti.modele.dao.adresse ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.adresse.Region;
import com.ifnti.modele.adresse.Ville;
import com.ifnti.modele.dao.DAO;

public class VilleDAO extends DAO <Ville> {

    @Override
    public String create(Ville object) {

        return null;
    }

    @Override
    public boolean update(Ville object) {

        return false;
    }

    @Override
    public boolean delete(Ville object) {

        return false;
    }

    @Override
    public Ville findById(String id) {
       
        String requete = String.format("SELECT * FROM ville WHERE id_ville = '%s'", id);
        ResultSet resultat =  selectObject(requete);
        try {
            if(resultat.next()){
                String nom = resultat.getString("nom");
                String id_region = resultat.getString("id_region");
                Region region = Kiakou.rDAO.findById(id_region);
                return new Ville(id, nom, region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ville findByName(String pName) {
        String requete = String.format("SELECT * FROM ville WHERE nom = '%s'", pName);
        ResultSet resultat =  selectObject(requete);
        try {
            if(resultat.next()){
                String id_ville = resultat.getString("id_ville");
                Region region = Kiakou.rDAO.findById(id_ville);
                return new Ville(id_ville, pName, region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Ville> getAll() {
        
        ArrayList<Ville> villes = new ArrayList<Ville>();
        String requete = String.format("SELECT * FROM ville ;");
        ResultSet resultat =  selectObject(requete);
        try {
            while(resultat.next()){
                String id_ville = resultat.getString("id_ville");
                String id_region = resultat.getString("id_region");
                String nom = resultat.getString("nom");
                Region region = Kiakou.rDAO.findById(id_region);
                villes.add(new Ville(id_ville, nom, region));
                System.out.println("=======OK====="+id_region);
            }
            return villes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
