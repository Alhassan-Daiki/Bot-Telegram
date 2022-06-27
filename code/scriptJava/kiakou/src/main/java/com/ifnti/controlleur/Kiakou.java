package com.ifnti.controlleur;

import java.util.ArrayList;

import com.ifnti.modele.adresse.LieuDeReference;
import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.adresse.Region;
import com.ifnti.modele.adresse.Ville;
import com.ifnti.modele.categorie.Activite;
import com.ifnti.modele.categorie.Categorie;
import com.ifnti.modele.dao.UseSqlFunction;
import com.ifnti.modele.dao.adresse.AdresseDAO;
import com.ifnti.modele.dao.adresse.LieuDeReferenceDAO;
import com.ifnti.modele.dao.adresse.QuartierDAO;
import com.ifnti.modele.dao.adresse.RegionDAO;
import com.ifnti.modele.dao.adresse.VilleDAO;
import com.ifnti.modele.dao.categorie.ActiviteDAO;
import com.ifnti.modele.dao.categorie.CategorieDAO;
import com.ifnti.modele.dao.service.PersonneDAO;
import com.ifnti.modele.dao.service.ServiceDAO;
import com.ifnti.modele.service.Service;

public class Kiakou {
    
    public static ServiceDAO sDAO = new ServiceDAO();
    public static PersonneDAO pDAO = new PersonneDAO();
    public static CategorieDAO cDAO = new CategorieDAO();
    public static ActiviteDAO aDAO = new ActiviteDAO();
    public static RegionDAO rDAO = new RegionDAO();
    public static VilleDAO vDAO = new VilleDAO();
    public static QuartierDAO qDAO = new QuartierDAO();
    public static LieuDeReferenceDAO lrDAO = new LieuDeReferenceDAO();
    public static AdresseDAO adDAO = new AdresseDAO();
    public static UseSqlFunction useFunction = new UseSqlFunction();

    /*public static HoraireDAO hDao = new HoraireDAO();
    public static HorraireParJourDAO hjDao = new HorraireParJourDAO();
    */

    public static ArrayList<Service> findServiceByActivities() {
        ArrayList<Service> services = new ArrayList<Service>();
        for (Service s : sDAO.getAll()){
            if (s.getMActivite().equals(s)){
                services.add(s);
            }
        }
        return services;
    }

    public static ArrayList<Service> findServiceByReferencesPlace(String number) {
        number = "REF"+Kiakou.useFunction.useDclKeyFormat(number);
        ArrayList<Service> services = new ArrayList<Service>();
        for (Service s : sDAO.getAll()){
            if (s.getMAdresse().getLieuDeReference().getMNum().equals(number)){
                services.add(s);
            }
        }
        return services;
    }

    public static String showResultatSeacheService(String number){
        String result = "";
        for (Service s : Kiakou.findServiceByReferencesPlace("LRF100001")){
            result = "================================================"
                    +"\n| Nom du service : "+s.getMDesignation()
                    +"\n| Description : "+s.getMDescription()
                    +"\n| Contact : "+s.getMPersonne().getmContact().toString()
                    +"\n==============================================="
                    +"\n| Se trouve à "+s.getMAdresse().getLieuDeReference().getMQuartier().getMNom()
                    +"\n| Vers "+s.getMAdresse().getLieuDeReference().getMNom()
                    +"\n| Horair : "
                    +"\n|   Lundi "
                    +"\n|   Mardi"
                    +"\n|   Mercredi"
                    +"\n|   Jeudi"
                    +"\n|   Vendredi"
                    +"\n|   Samedi"
                    +"\n|   Dimanche"
                    +"\n"
                    +"================================================";
        }
        return result;
    }


//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
   
    public static String requestCategorie(String title, String table) {

        String result = title;
        String number = "";
        if (table.equals("categorie")){
            ArrayList<Categorie> categories = cDAO.getAll();
            for (int i = 0; i < categories.size()-1; i++){
                number = categories.get(i).getMNum();
                number = Kiakou.formatkeyForShow(number);
                result += "\n"+number+"-"+categories.get(i).getMNom() ;
            }
            return result;
        }
        if (table.equals("activite")){
            ArrayList<Activite> activites = aDAO.getAll();
            for (int i = 0; i < activites.size()-1; i++){
                number = activites.get(i).getmNum();
                number = Kiakou.formatkeyForShow(number);
                result += "\n"+number+"-"+activites.get(i).getMNom() ;
            }
            return result;
        }
        return null;
    }
    
    public static String requestLocation(String title, String table){
        String result = title;

        if (table.equals("quartier")){
            ArrayList<Quartier> quartiers = qDAO.getAll();
            for (int i = 0; i < quartiers.size()-1; i++){
                result += "\n"+(i+1)+"-"+quartiers.get(i).getMNom() ;
            }
            return result;
        }

        return null;
    }


    public static String requestRegionByCountry(){
        String result = "";
        String number = "";

        ArrayList<Region> regions = rDAO.getAll();
        if (regions.size()==1){
            number = regions.get(0).getMNum();
            number = Kiakou.formatkeyForShow(number);
            result += "\n"+number+"-"+regions.get(0).getMNom() ;
            return result;
        }
        for (int i = 0; i < regions.size()-1; i++){
            number = regions.get(i).getMNum();
            number = Kiakou.formatkeyForShow(number);
            result += "\n"+number+"-"+regions.get(i).getMNom() ;
        }
        return result;
    }

    public static String requestTownByRegion(String number){
        String result = "";
        String townNumber = "";
        ArrayList<Ville> villes = Kiakou.vDAO.getAll();
        if (villes.size()==1){
            if (villes.get(0).getMRegion().getMNum().equals(number)) {
                townNumber = villes.get(0).getMNum();
                townNumber = Kiakou.formatkeyForShow(townNumber);
                result += "\n"+townNumber+"-"+villes.get(0).getMNom() ;
                return result;
            }
        }
        for (int i = 0; i < villes.size()-1; i++){
            if (villes.get(i).getMRegion().getMNum().equals(number)){
                townNumber = villes.get(i).getMNum();
                townNumber = Kiakou.formatkeyForShow(townNumber);
                result += "\n"+townNumber+"-"+villes.get(i).getMNom() ;
            }
        }
        return result;
    }

    public static String requestQuartierByTown(String number){
        String result = "";
        String quartNumber = "";
        ArrayList<Quartier> quartiers = Kiakou.qDAO.getAll();
        if (quartiers.size()==1){
            if (quartiers.get(0).getMVille().getMNum().equals(number)){
                quartNumber = quartiers.get(0).getMNum();
                quartNumber = Kiakou.formatkeyForShow(quartNumber);
                result += "\n"+quartNumber+"-"+quartiers.get(0).getMNom() ;
            } return result;
        }
        for (int i = 0; i < quartiers.size()-1; i++){
            if (quartiers.get(i).getMVille().getMNum().equals(number)){
                quartNumber = quartiers.get(i).getMNum();
                quartNumber = Kiakou.formatkeyForShow(quartNumber);
                result += "\n"+quartNumber+"-"+quartiers.get(i).getMNom() ;
            }
        }
        return result;
    }

    public static String requestReferencePlaceByQuartier(String number){
        String result = "";
        String refNumber = "";
        ArrayList<LieuDeReference> lieuDeReferences = Kiakou.lrDAO.getAll();
        if (lieuDeReferences.size()==1){
            if (lieuDeReferences.get(0).getMQuartier().getMNum().equals(number)) {
                refNumber = lieuDeReferences.get(0).getMNum();
                refNumber = Kiakou.formatkeyForShow(refNumber);
                result += "\n"+refNumber+"-"+lieuDeReferences.get(0).getMNom() ;
                return result;
            }
        }

        for (int i = 0; i < lieuDeReferences.size()-1; i++){
            if (lieuDeReferences.get(i).getMQuartier().getMNum().equals(number)){
                refNumber = lieuDeReferences.get(i).getMNum();
                refNumber = Kiakou.formatkeyForShow(refNumber);
                result += "\n"+refNumber+"-"+lieuDeReferences.get(i).getMNom() ;
            }
        }
        return result;
    }


    /***
     * ::::::: créer un string en partant d'un titre et d'une liste.
     * @param title
     * @param table
     * @return
     */
    public static String send_list_object(String title, String table){
        String result = "";
        result += title ;
        if (table=="categorie"){
            
        }
        return result;
    }

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
    
    public static String requestCategorie1(){
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


    public static String formatkeyForShow(String number){
        char[] CharNumbers = number.toCharArray();
        number = "";
        for (int i = 7; i < CharNumbers.length; i++){
            number += CharNumbers[i] ;
        }
        return number;
    }
}