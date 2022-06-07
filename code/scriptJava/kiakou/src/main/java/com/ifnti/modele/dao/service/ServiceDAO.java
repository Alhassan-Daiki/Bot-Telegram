package com.ifnti.modele.dao.service ;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Service;

public class ServiceDAO extends DAO <Service> {

    @Override
    public boolean create(Service object) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Service object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Service findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Service findByName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Service> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

   
}
