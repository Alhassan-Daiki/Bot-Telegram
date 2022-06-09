package com.ifnti.controlleur;

import com.ifnti.modele.dao.adresse.VilleDAO;
import com.ifnti.modele.dao.categorie.ActiviteDAO;
import com.ifnti.modele.dao.categorie.CategorieDAO;
import com.ifnti.modele.dao.service.HoraireDAO;
import com.ifnti.modele.dao.service.HorraireParJourDAO;
import com.ifnti.modele.dao.service.PersonneDAO;
import com.ifnti.modele.dao.service.ServiceDAO;

public class Kiakou {
    public static ServiceDAO sDao = new ServiceDAO();
    public static PersonneDAO pDao = new PersonneDAO();
    public static HoraireDAO hDao = new HoraireDAO();
    public static HorraireParJourDAO hjDao = new HorraireParJourDAO();
    public CategorieDAO cDao = new CategorieDAO();
    public static ActiviteDAO aDao = new ActiviteDAO();
    public static VilleDAO vDao = new VilleDAO();
    public Kiakou(){

    }
}
