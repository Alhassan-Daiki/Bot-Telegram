package com.ifnti.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseSqlFunction extends DAO {

    @Override
    public String create(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public Object findByName(String pName) {
        return null;
    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    public String useDclKeyFormat(String strNumber) {
        int number = Integer.parseInt(strNumber);
        String requete = String.format("select keyFormat(%d) as keyFormat ;", number);
        ResultSet result = selectObject(requete);
        try {
            if (result.next()){
                return result.getString("keyFormat");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    
}
