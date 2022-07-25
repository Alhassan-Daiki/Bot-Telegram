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
import com.ifnti.modele.dao.service.HoraireDAO;
import com.ifnti.modele.dao.service.HoraireParJourDAO;
import com.ifnti.modele.dao.service.PersonneDAO;
import com.ifnti.modele.dao.service.ServiceDAO;
import com.ifnti.modele.service.Horaire;
import com.ifnti.modele.service.HoraireParJour;
import com.ifnti.modele.service.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public static HoraireDAO hDao = new HoraireDAO();
    public static HoraireParJourDAO hpjDao = new HoraireParJourDAO();
    public static String[] jours_semaine = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};

    //%;p;07:30;17:00;8:00;16:00%24h24%24h24%24h24%24h24
    public static void saveHour(String hoursStr, Service service){
    	String[] jours = hoursStr.split("%", 8);
        String[] jour ;
        String[] liste = null ;
        int indice = 0;
        Horaire horaire;
        int heurMatine = 0;
        int heurSoire = 0 ;
        int minMatine = 0;
        int minSoire = 0;

        /*for (String j : jours) {
            System.out.println(j);
        }*/

    	for (int i = 1; i < jours.length; i++){
			jour = jours[i].split(";", 2);
            if (jour[0].equals("24h24")){
                horaire = new Horaire(0, 
                                                0, 
                                                0, 
                                                0, 
                                                0, 
                                                0, 
                                                0, 
                                                0, 
                                                24);
            } else {
                horaire = savingHour(jour, heurMatine, heurSoire, minMatine, minSoire, liste);
            }
            new HoraireParJour(Kiakou.jours_semaine[indice], service, horaire);
            if (indice < jours_semaine.length){
                //System.out.println(indice);
                indice ++;
            }
            //System.out.println("Fin:::::::::::::::::::"+jours_semaine[indice]);
    	}
    	
    }

    public static Horaire savingHour(String[] jour, int heurMatine, int heurSoire, int minMatine, int minSoire, String[] liste){
        
        liste = jour[0].split(":", 2);
    
        try {
            heurMatine = Integer.parseInt(liste[0]);
            minMatine = Integer.parseInt(liste[1]);
        } catch (Exception e) {
            //TODO: handle exception
        }

        liste = jour[1].split(":", 2);
        
        try {
            heurSoire = Integer.parseInt(liste[0]);
            minSoire = Integer.parseInt(liste[1]);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        /*System.out.println(heurMatine+" : "+minMatine);
        System.out.println(heurSoire+" : "+minSoire);*/

        Horaire horaire = new Horaire(heurMatine, 
                                        0, 
                                        0, 
                                        heurSoire, 
                                        minMatine, 
                                        0, 
                                        0, 
                                        minSoire, 
                                        0);
        return horaire;
        
    }
    
    public static boolean checkHour(String time){
        if (time.equals("24h24")){
            return true;
        }
        String[] timeListe = time.split(":", 2);
        try {
            Integer.parseInt(timeListe[0]);
            Integer.parseInt(timeListe[1]);
            return true;
        } catch (Exception e) {
            System.out.println("impossible de convertir un string en int");
        }
        return false;
    }

    public static ArrayList<Service> getServiceByActivityAndRefPlace(Activite activite, LieuDeReference lieuDeReference) {

        if (activite == null | lieuDeReference == null){
            return null;
        }
        ArrayList<Service> services = new ArrayList<Service>();

        for (Service s : sDAO.findServicesByActivity(activite)){
            if (s.getMAdresse().getMLieuDeReference().getMNum().equals(lieuDeReference.getMNum())){
                services.add(s);
            }
        }
        return services;
    }

    public static ArrayList<Service> getServiceByActivityAndTown(Activite activite, Ville ville) {

        if (activite == null | ville == null){
            return null;
        }

        ArrayList<Service> services = new ArrayList<Service>();

        for (Service s : sDAO.findServicesByActivity(activite)){
            if (s.getMAdresse().getMQuartier().getMVille().getMNum().equals(ville.getMNum())){
                services.add(s);
            }
        }
        return services;
    }

    public static ArrayList<Service> getServiceByActivityAndQuartier(Activite activite, Quartier quartier) {

        if (activite == null | quartier == null){
            return null;
        }

        ArrayList<Service> services = new ArrayList<Service>();

        for (Service s : sDAO.findServicesByActivity(activite)){
            if (s.getMAdresse().getMQuartier().getMNum().equals(quartier.getMNum())){
                services.add(s);
            }
        }
        return services;
    }

    public static String showResultatSeacheService(ArrayList<Service> services){
        String result = "";
        String showLRF = "";
        String showHoraire = "";

        for (Service s : services){
            if (s.getMAdresse().getMLieuDeReference() != null){
                showLRF = "\n| Vers "+s.getMAdresse().getMLieuDeReference().getMNom();
            }
            ArrayList<HoraireParJour> horaireParJours = Kiakou.getHoraireParJourByService(s);
            showHoraire = "";
            if (horaireParJours == null){
                showHoraire = "Aucune horaire";
            } else {
                for (HoraireParJour hpj : horaireParJours) {
                    showHoraire += " ";
                    if (hpj.getHoraires().getmH24() == 24){
                        showHoraire += hpj.getJour().toUpperCase()+"\n|\t|Ouvre 24h/24\n";
                    } else {
                        showHoraire += hpj.getJour().toUpperCase()+"\n|\t|Ouvre à "
                                +hpj.getHoraires().getmHeure_debut_matinee()+"H"
                                +hpj.getHoraires().getmMinute_debut_matinee()+" min ferme à "
                                +hpj.getHoraires().getmHeure_fin_soiree()+"H"
                                +hpj.getHoraires().getmMinute_fin_soiree()+" min.\n";
                    }
                    
                    //showHoraire = "Lundi:\n\tOuvre à 7H00min ferme à 17H30min";
                }
            }
            //System.out.println("-------"+showHoraire);
            result +=  "*****************************************************"
                    +"\n*****************************************************"
                    +"\n|# Identifant : "+s.getMNum()
                    +"\n|# Nom du service : "+s.getMDesignation().toUpperCase()
                    +"\n|# Description : "+s.getMDescription()
                    +"\n|# Contact : "+s.getMPersonne().getmContact().toUpperCase()
                    +"\n==================================================="
                    +"\n|# Se trouve à "+s.getMAdresse().getMQuartier().getMNom()
                    +"\n|# "+showLRF
                    +"\n|# Horaires : "
                    +"\n "+showHoraire
                    +"*****************************************************"
                    +"\n*****************************************************&";
        }
        return result;
    }


    public static ArrayList<HoraireParJour> getHoraireParJourByService(Service s){
        ArrayList<HoraireParJour> horaireParJours = new ArrayList<HoraireParJour>();
        for (HoraireParJour hpj : hpjDao.getAll()) {
            if (hpj.getService().getMNum().equals(s.getMNum())){
                horaireParJours.add(hpj);
            }
        }
        return horaireParJours;
    }


//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
   
    public static String requestCategorie(String title, String table) {

        String result = title;
        String number = "";
        if (table.equals("categorie")){
            ArrayList<Categorie> categories = cDAO.getAll();
            for (int i = 0; i < categories.size(); i++){
                number = categories.get(i).getMNum();
                number = Kiakou.formatkeyForShow(number);
                result += "\n"+number+"-"+categories.get(i).getMNom() ;
            }
            return result;
        }
        return null;
    }
    
    public static String requestActiviteByCategorie(String numero) {
        String result = "";
        String number = "";
        ArrayList<Activite> activites = aDAO.getAll();
        for (int i = 0; i < activites.size(); i++){
            if (activites.get(i).getmCategorie().getMNum().equals(numero)){
                number = activites.get(i).getmNum();
                number = Kiakou.formatkeyForShow(number);
                result += "\n"+number+"-"+activites.get(i).getMNom() ;
            }
        }
        return result;
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

    public static String formatkeyForShow(String number){
        char[] CharNumbers = number.toCharArray();
        number = "";
        for (int i = 7; i < CharNumbers.length; i++){
            number += CharNumbers[i] ;
        }
        return number;
    }

    public static String hashPassword(String passwordToHash){
        String generatedPassword = null;
    
        try 
        {
          // Create MessageDigest instance for MD5
          MessageDigest md = MessageDigest.getInstance("MD5");
    
          // Add password bytes to digest
          md.update(passwordToHash.getBytes());
    
          // Get the hash's bytes
          byte[] bytes = md.digest();
    
          // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }
    
          // Get complete hashed password in hex format
          generatedPassword = sb.toString();
          return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        return generatedPassword;
      }
}