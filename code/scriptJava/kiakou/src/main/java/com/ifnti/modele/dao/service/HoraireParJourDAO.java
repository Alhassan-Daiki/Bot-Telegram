package com.ifnti.modele.dao.service ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.controlleur.Kiakou;
import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Horaire;
import com.ifnti.modele.service.HoraireParJour;
import com.ifnti.modele.service.Service;

public class HoraireParJourDAO extends DAO <HoraireParJour> {

    @Override
    public String create(HoraireParJour hpj) {
       
        String requete = String.format(
            "insert into horaire_par_semaine(id_service, id_horaire, jour) values('%s', '%s', '%s') returning id_service", 
            hpj.getService().getMNum(), hpj.getHoraires().getNum(), hpj.getJour()
            );
        System.out.println(requete);
        super.insertObject(requete,"service");


        return null;   
     
    }

    @Override
    public boolean update(HoraireParJour object) {
        return false;
    }

    @Override
    public boolean delete(HoraireParJour object) {
        return false;
    }

    @Override
    public HoraireParJour findById(String id) {
        return null;
    }

    public HoraireParJour findById(Service service, Horaire horaire) {
        String requete = String.format(
            "SELECT * FROM horaire_par_semaine WHERE id_service ='%s' and id_horaire = '%s'", 
            service.getMNum(), horaire.getNum());
        ResultSet resultat = super.selectObject(requete);                 
        try {
            if (resultat.next()) {
                String jour = resultat.getString("jour");
                HoraireParJour hpj = new HoraireParJour(jour, service, horaire, "");
                return hpj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HoraireParJour findByName(String pName) {
        return null;
    }

    @Override
    public ArrayList<HoraireParJour> getAll() {
        ArrayList<HoraireParJour> horaireParJours = new ArrayList<HoraireParJour>();
        String requete = String.format("SELECT * FROM horaire_par_semaine ;");
        ResultSet resultat = super.selectObject(requete);                 
        try {
            while (resultat.next()) {
                String jour = resultat.getString("jour");
                String id_service = resultat.getString("id_service");
                String id_horaire = resultat.getString("id_horaire");
                Service service = Kiakou.sDAO.findById(id_service);
                Horaire horaire = Kiakou.hDao.findById(id_horaire);
                HoraireParJour hpj = new HoraireParJour(jour, service, horaire, "");
                horaireParJours.add(hpj);
                //System.out.println(jour+"/////"+id_service+"/////"+horaire);
            }
            return horaireParJours;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
