package com.ifnti.modele.dao.categorie ;


import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import com.ifnti.modele.categorie.Categorie;

import com.ifnti.modele.dao.DAO;


public class CategorieDAO extends DAO <Categorie> {

    @Override
    public String create(Categorie object)  {

        return null;
    }

    @Override
    public boolean update(Categorie object) {

        return false;
    }

    @Override
    public boolean delete(Categorie object) {

        return false;
    }

    @Override
    public Categorie findById(String id) {
        String requete = String.format("SELECT * FROM categorie where id_categorie = '%s';", id);
        ResultSet resultat = super.selectObject(requete);
        try {
            if (resultat.next()){
                String nom = resultat.getString("nom");
                return new Categorie(id, nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Categorie findByName(String pName) {
        String requete = String.format("SELECT * FROM categorie where nom = '%s';", pName);
        ResultSet resultat = super.selectObject(requete);
        try {
            if (resultat.next()){
                String num = resultat.getString("id_categorie");
                return new Categorie(num, pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Categorie> getAll() {
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        String requete = "SELECT * FROM categorie ;" ;
        ResultSet resultat = super.selectObject(requete);
        try {
            while (resultat.next()){
                String num = resultat.getString("id_categorie");
                String nom = resultat.getString("nom");
                categories.add(new Categorie(num, nom));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

}
