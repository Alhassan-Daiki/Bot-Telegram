package com.ifnti.modele.dao.adresse ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.adresse.LieuDeReference;
import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.dao.DAO;

public class LieuDeReferenceDAO extends DAO <LieuDeReference> {

    @Override
    public String create(LieuDeReference object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(LieuDeReference object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(LieuDeReference object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public LieuDeReference findById(String id) {

        String requete = String.format("SELECT * FROM lieu_de_reference WHERE id_reference = '%s'", id);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){
                String nom = resultat.getString("nom");
                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                return new LieuDeReference(id, nom, quartier);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LieuDeReference findByName(String pName) {

        String requete = String.format("SELECT * FROM lieu_de_reference WHERE nom = '%s'", pName);
           
        ResultSet resultat =  selectObject(requete);
        try {
            if (resultat.next()){
                String id_reference = resultat.getString("id_reference");
                String id_quartier = resultat.getString("id_quartier");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                return new LieuDeReference(id_reference, pName, quartier);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<LieuDeReference> getAll() {

        ArrayList<LieuDeReference> lieuDeReferences = new ArrayList<LieuDeReference>();
        String requete = String.format("SELECT * FROM lieu_de_reference ;");
           
        ResultSet resultat =  selectObject(requete);
        try {
            while (resultat.next()){
                String id_reference = resultat.getString("id_reference");
                String id_quartier = resultat.getString("id_quartier");
                String nom = resultat.getString("nom");
                Quartier quartier = Kiakou.qDAO.findById(id_quartier);
                lieuDeReferences.add(new LieuDeReference(id_reference, nom, quartier)) ;
            }
            return lieuDeReferences;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
