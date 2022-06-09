package com.ifnti.controlleur;

import com.ifnti.modele.service.Personne;
import com.ifnti.modele.service.Service;

/**
 *
 * @author amk
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("ALLO ici la TERRE");
      Personne p = new Personne("ben", "dam", "90411584");
     // Service s = new Service("sam","coifeuse");
     // System.out.println(s);
     // Service s1 = Service.sDao.update(p);
     //System.out.println(s.getmNum());
     System.out.println(p);
     System.out.println(p.getmNum());
    }
    
}


