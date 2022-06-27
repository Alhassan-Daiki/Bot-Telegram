package com.ifnti.modele.dao.service ;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;

import com.ifnti.modele.service.Personne;

public class PersonneDAO extends DAO <Personne> {

    @Override
    public String create(Personne p) {

        String requete  = String.format(
            "insert into Personne(nom,prenom,contact)values('%s','%s','%s') returning id_personne;",
            p.getmNom(),p.getmPrenom(),p.getmContact()
            );
        System.out.println(requete);
        String resultat = this.insertObject(requete,"personne");

        return resultat;     
    }

    @Override
    public boolean update(Personne p) {
       
        String requete = String.format(
            "update Personne set(nom, prenom,contact) =('%s','%s', '%s') where id_personne = '%s' )",
            p.getmNom(),p.getmPrenom(),p.getmContact() ,p.getmNum()
            )  ;
        super.updateObject(requete);
        return true;
        
    }


    @Override
    public boolean delete(Personne p) {

        String requete = "Delete from personne where id_personne= " + p.getmNum() ;
        super.updateObject(requete);
        return true;
       
        
    }

    @Override
    public Personne findById(String id) {

        String requete = String.format("SELECT * FROM personne WHERE id_personne='%s'", id);
        ResultSet resultat = super.selectObject(requete);                 
        try {
            if (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String contact = resultat.getString("contact");
                Personne p = new Personne.PersonneBuilder()
                        .withFristName(nom)
                        .withLastName(prenom)
                        .withContact(contact)
                        .build();

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
             
        return null; 
    }

    @Override
    public Personne findByName(String pName) {
       
        String requete = String.format("SELECT * FROM personne WHERE nom = '%s';" , pName);
        ResultSet resultat = super.selectObject(requete);                 
        try {
            if (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String contact = resultat.getString("contact");
                Personne p = new Personne.PersonneBuilder()
                        .withFristName(nom)
                        .withLastName(prenom)
                        .withContact(contact)
                        .build();

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Personne> getAll() {
        
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        String requete = "SELECT * FROM personne ";
        ResultSet resultat = super.selectObject(requete);            
        try {
            
            while (resultat.next()){
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String contact = resultat.getString("contact");
                Personne p = new Personne.PersonneBuilder()
                        .withFristName(nom)
                        .withLastName(prenom)
                        .withContact(contact)
                        .build();

                personnes.add(p);
            }
            return personnes;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
