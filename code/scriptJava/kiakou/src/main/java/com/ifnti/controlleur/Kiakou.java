package com.ifnti.controlleur;

import com.ifnti.modele.dao.service.PersonneDAO;

public class Kiakou {
    
    //public static ServiceDAO sDao = new ServiceDAO();
    public static PersonneDAO pDao = new PersonneDAO();
    /*public static HoraireDAO hDao = new HoraireDAO();
    public static HorraireParJourDAO hjDao = new HorraireParJourDAO();
    public CategorieDAO cDao = new CategorieDAO();
    public static ActiviteDAO aDao = new ActiviteDAO();
    public static VilleDAO vDao = new VilleDAO();*/


    public static String requestQuartier(){
        String title = "Dans Quelle quartier êtes vous ?&";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }

    public static String requestVille(){
        String title = "Dans Quelle ville vous proposé ce service ?&";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }
    
    public static String requestCategorie(){
        String title = "Dans quelle catégorie votre activité se place votre activité  ?";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }

    public static String requestActivite(){
        String title = "Quelle est votre secteur d'activité ?";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }


}