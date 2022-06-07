package com.ifnti.modele.dao.service ;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Personne;

public class PersonneDAO extends DAO <Personne> {

    @Override
    public boolean create(Personne object) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Personne object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Personne findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Personne findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Personne> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
