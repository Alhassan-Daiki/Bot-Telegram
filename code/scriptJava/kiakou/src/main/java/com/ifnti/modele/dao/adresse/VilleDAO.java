package com.ifnti.modele.dao.adresse ;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.adresse.Ville;
import com.ifnti.modele.dao.DAO;

public class VilleDAO extends DAO <Ville> {

    @Override
    public boolean create(Ville object) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Ville object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Ville findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ville findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Ville> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
