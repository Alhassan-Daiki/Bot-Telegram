package com.ifnti.modele.dao ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 

public abstract class DAO <T> {
    public static Connection connection;

    public abstract boolean create(final T object) throws SQLException;

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
    protected void insertObject(final String pRequest) {
        // Insertion d'un objet
        try{
            Statement transaction = DAO.connection.createStatement();
            transaction.executeUpdate(pRequest);
        }catch(SQLException e){
            e.printStackTrace();
        }

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

    public int updateObject(final String pRequest) {
        int resultat = 0;
        try{
            Statement transaction = DAO.connection.createStatement();
            resultat =  transaction.executeUpdate(pRequest);
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  resultat;


    }

    
}
/*<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.18.jre7</version>
    </dependency>*/