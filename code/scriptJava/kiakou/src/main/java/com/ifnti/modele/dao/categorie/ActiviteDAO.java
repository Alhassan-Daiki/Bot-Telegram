package com.ifnti.modele.dao.categorie ;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.categorie.Activite;
import com.ifnti.modele.dao.DAO;

public class ActiviteDAO extends DAO <Activite> {

    @Override
    public String create(Activite object) throws SQLException {
        // TODO Auto-generated method stub
        /*
         * // TODO Auto-generated method stub
        String requete = String.format("insert into Activité (id_catégorie) values('%s') ;", 
        a.getMNom(),s.getmPersonne().getmNom());
        System.out.println(requete);
        this.insertObject(requete,"activite");
        return null; 
        */
        return null;
    }

    @Override
    public boolean update(Activite object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Activite object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Activite findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Activite findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Activite> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    

}
