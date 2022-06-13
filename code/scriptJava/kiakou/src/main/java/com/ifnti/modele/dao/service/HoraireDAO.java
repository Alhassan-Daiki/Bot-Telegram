package com.ifnti.modele.dao.service ;

import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Horaire;

public class HoraireDAO extends DAO <Horaire> {

    @Override
    public String create(Horaire h) {
        // insertion des n-uplet dans la table
        int heure_debut_matinee =  h.getmHeure_debut_matinee();
        int heure_fin_matinee =  h.getmHeure_fin_matinee();
        int heure_debut_soiree =  h.getmHeure_debut_soiree();
        int heure_fin_soiree =  h.getmHeure_fin_soiree();
        int minute_debut_matinee =  h.getmMinute_debut_matinee();
        int minute_fin_matinee =  h.getmMinute_fin_matinee();
        int minute_debut_soiree =  h.getmMinute_debut_soiree();
        int minute_fin_soiree =  h.getmMinute_fin_soiree();

        String requete = "insert into Horaire(heure_debut_matinee,heure_fin_matinee,heure_debut_soiree,heure_fin_soiree,minute_debut_matinee,minute_fin_matinee,minute_debut_soiree,minute_fin_soiree )values("+heure_debut_matinee+","+heure_fin_matinee+heure_debut_soiree + ","+ heure_fin_soiree + ","+minute_debut_matinee+ ","+ minute_fin_matinee+ ","+ minute_debut_soiree + ","+ minute_fin_soiree +")";
        super.insertObject(requete,"horaire");


        return null; 
    }

    @Override
    public boolean update(Horaire object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Horaire object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Horaire findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Horaire findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Horaire> getAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
