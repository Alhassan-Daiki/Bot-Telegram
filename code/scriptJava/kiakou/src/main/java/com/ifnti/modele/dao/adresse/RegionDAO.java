package com.ifnti.modele.dao.adresse ;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.adresse.Region;
import com.ifnti.modele.dao.DAO;

public class RegionDAO extends DAO <Region> {

    @Override
    public boolean create(Region object) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Region object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Region findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Region findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Region> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
