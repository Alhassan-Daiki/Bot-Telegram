package com.ifnti.modele.dao.categorie ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.categorie.Activite;
import com.ifnti.modele.categorie.Categorie;
import com.ifnti.modele.dao.DAO;

public class ActiviteDAO extends DAO <Activite> {

    @Override
    public String create(Activite object)  {
        
        return null;
    }

    @Override
    public boolean update(Activite object) {

        return false;
    }

    @Override
    public boolean delete(Activite object) {

        return false;
    }

    @Override
    public Activite findById(String id) {

        String requete = String.format("SELECT * FROM activite where id_activite = '%s';", id);
        ResultSet resultat = super.selectObject(requete);
        try {
            if (resultat.next()){
                String nom = resultat.getString("nom");
                String id_categorie = resultat.getString("id_categorie");
                Categorie categorie = Kiakou.cDAO.findById(id_categorie);

                return new Activite(id, nom, categorie);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Activite findByName(String pName) {

        String requete = String.format("SELECT * FROM activite where nom = '%s';", pName);
        ResultSet resultat = super.selectObject(requete);
        try {
            if (resultat.next()){
                String id_activite = resultat.getString("id_activite");
                String id_categorie = resultat.getString("id_categorie");
                Categorie categorie = Kiakou.cDAO.findById(id_categorie);

                return new Activite(id_activite, pName, categorie);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Activite> getAll() {

        ArrayList<Activite> activites = new ArrayList<Activite>();
        String requete = "SELECT * FROM activite ";
        ResultSet resultat = super.selectObject(requete);
        try {
            while (resultat.next()) {
                String id_activite = resultat.getString("id_activite");
                String nom = resultat.getString("nom");
                String id_categorie = resultat.getString("id_categorie");
                Categorie categorie = Kiakou.cDAO.findById(id_categorie);

                activites.add(new Activite(id_activite, nom, categorie));
            }
            return activites;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    

}
