package com.ifnti.modele.dao.service ;

import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.HorraireParJour;

public class HorraireParJourDAO extends DAO <HorraireParJour> {

    @Override
    public String create(HorraireParJour p) {
        // TODO Auto-generated method stub
       // String jour =  p.getJour();
       
        String requete = "insert into horaire_par_semaine()values()";
        super.insertObject(requete,"horaireParJour");


        return null;   
     
    }

    @Override
    public boolean update(HorraireParJour object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(HorraireParJour object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HorraireParJour findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HorraireParJour findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<HorraireParJour> getAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
