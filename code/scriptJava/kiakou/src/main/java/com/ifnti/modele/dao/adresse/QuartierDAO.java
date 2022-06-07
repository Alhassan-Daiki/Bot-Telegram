package com.ifnti.modele.dao.adresse ;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.dao.DAO;

public class QuartierDAO extends DAO <Quartier> {

    @Override
    public boolean create(Quartier object) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Quartier object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Quartier findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Quartier findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Quartier> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

   
}
