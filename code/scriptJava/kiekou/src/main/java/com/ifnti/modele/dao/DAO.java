package com.ifnti.modele.dao ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 

public abstract class DAO <T> {
    public static Connection connection;

    public abstract String create(final T object) throws SQLException;

    public abstract boolean update(final String requete);

    public abstract boolean delete(final T object);

    public abstract T findById(final String id);

    public abstract T findByName(final String pName);

    public abstract ArrayList<T> getAll();

    static {
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kiakou", "group3", "group3");
        }catch (SQLException e){
            e.printStackTrace();
        }
       
    }

    protected T mRead(final String pSearchName) {
        // TODO Auto-generated return
        return null;
    }
    /**
     * @simone
     * @param pRequest : requete sql en caractère.
     */
    protected String insertObject(final String pRequest, String table) {
        // Insertion d'un objet
        ResultSet resultat = selectObject(pRequest);
        try{
            if (resultat.next()){
                String idTable = resultat.getString("id_"+ table);
                return idTable;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }

    protected ResultSet selectObject(final String pRequest) {
        // TODO Auto-generated return
        ResultSet resultat = null;
        try{
            Statement transaction = DAO.connection.createStatement();
            resultat =  transaction.executeQuery(pRequest );
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  resultat;
    }

    protected int getLastId() {
        // TODO Auto-generated return
        return 0;
    }

    public void updateObject(final String pRequest) {
       
        try{
            Statement transaction = DAO.connection.createStatement();
            transaction.executeUpdate(pRequest);
           
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    
}
