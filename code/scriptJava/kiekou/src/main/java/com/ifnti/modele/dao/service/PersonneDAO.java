package com.ifnti.modele.dao.service ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ifnti.modele.dao.DAO;
import com.ifnti.modele.service.Personne;

public class PersonneDAO extends DAO <Personne> {

    

   

    private Personne object;

    @Override
    public String create(Personne p) {
        String requete  = String.format("insert into Personne(nom,prenom,contact)values('%s','%s','%s') returning id_personne;",p.getmNom(),p.getmPrenom(),p.getmContact());
        System.out.println(requete);
        String resultat = this.insertObject(requete,"personne");
        /*  String nom =  p.getmNom();
        String prenom =  p.getmPrenom();
        String contact =  p.getmContact();
        
        String requete = "insert into Personne(nom,prenom,contact)values('"+nom+"','"+prenom+"','"+contact +"')";
        super.insertObject(requete,"personne");*/
        return resultat;     
    }


    public Personne getObject() {
        return object;
    }

    public void setObject(Personne object) {
        this.object = object;
    }

   // @Override
    protected boolean update(Personne object) {
        this.setObject(object);
        // 
       /* String id_personne = object.getmNum();
        String nom =  object.getmNom();
        String prenom =  object.getmPrenom();
        String contact =  object.getmContact();*/
          String requete = StringFormat("update Personne set(nom, prenom,contact) =('%s','%s', '%s') where id_personne = '%s' )",object.getmNom(),object.getmPrenom(),object.getmContact() ,object.getmNum())  ;
        super.updateObject(requete);
        return true;
        
    }

    private String StringFormat(String string, String getmNom, String getmPrenom, String getmContact, String getmNum) {
        return null;
    }

    @Override
    public boolean delete(Personne object) {
        // TODO Auto-generated method stub
        String requete = "Delete from personne where id_personne= " + object.getmNum() ;
        super.updateObject(requete);
        return true;
       
        
    }

    @Override
    public Personne findById(String id) {
        // TODO Auto-generated method stub
        try{
            Statement transaction = connection.createStatement();
                ResultSet resultat = transaction.executeQuery(
                          "SELECT * FROM personne WHERE id_personne=" + id);
                            
               
            
                 
                if (resultat.next()) {
                    Personne p = new Personne (resultat.getString("nom"),
                                             resultat.getString("prenom"),
                                             resultat.getString("contact"));
                     return p;
                }
              
            } catch(SQLException e){
                e.printStackTrace();
            }
          
           
             return null; 
    }

    @Override
    public Personne findByName(String pName) {
        // TODO Auto-generated method stub
     /*   try{Statement transaction = connection.createStatement();
            ResultSet resultat = transaction.executeQuery(
                      "SELECT * FROM Personne WHERE nom=" + "'"+pName+"'");
            
            if (resultat.next()) {
                Personne p1 = (resultat.getString("nom"),
                                resultat.getString("prenom"),
                                resultat.getString("contact"));
                 return p1;
            }
        } catch(SQLException e){
            e.printStackTrace();

            }*/
       
        
        return null;
    }

    @Override
    public ArrayList<Personne> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(String requete) {
        // TODO Auto-generated method stub
        return false;
    }

}
