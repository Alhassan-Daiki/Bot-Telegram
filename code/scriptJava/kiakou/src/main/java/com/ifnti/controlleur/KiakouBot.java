package com.ifnti.controlleur;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.session.Session;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import com.ifnti.modele.adresse.Adresse;
import com.ifnti.modele.adresse.LieuDeReference;
import com.ifnti.modele.adresse.Quartier;
import com.ifnti.modele.adresse.Region;
import com.ifnti.modele.adresse.Ville;
import com.ifnti.modele.categorie.Activite;
import com.ifnti.modele.categorie.Categorie;
import com.ifnti.modele.service.Personne;
import com.ifnti.modele.service.Service;
import com.ifnti.modele.service.Personne.PersonneBuilder;

public class KiakouBot extends TelegramLongPollingSessionBot{

    @Override
    public String getBotUsername() {
        
        return "Kiakou";
    }

    // Ecouteur de message.
    @Override
    public void onUpdateReceived(Update update, java.util.Optional<Session> botSession) {
        System.out.println(":::::::::::::::::::Réception d'un message::::::::::::::::::::");
        
        //....rechercher la position de la session.
        int position = findSession(update);

        int etape = SessionID.listeSession.get(position).getEtape();
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();
        System.out.println(":::::::::::: new value = "+listeSousEtape[1][0]+"::::::::::::");
        System.out.println(sid.getEtape());

        SendMessage message = new SendMessage(); //nouvelle instance de send message.

        if (update.hasCallbackQuery()){
            System.out.println(update.getCallbackQuery().getData());
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            SessionID.sousEtapeInfo += update.getCallbackQuery().getData().toString();
            message.setText("O1111OOO");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return ;
        } else {
            message.setChatId(update.getMessage().getChatId().toString()); // Définir l'id du chat.
        }
        

        //System.out.println(update);

        /*if (update.hasCallbackQuery()){
            System.out.println("mmmmmmmm");
        } else {
            try {
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId().toString(), "saisir heure", "http://saisirheure.com/", "Go to web site.")); // envoyer le message.
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }*/

        if (update.getMessage().getContact()!=null || update.getMessage().getLocation()!=null){
            SessionID.listeSession.get(position).setContact(update.getMessage().getContact());
            update.getMessage().setText("");
            System.out.println(":::::: numéro de telephone reçu ::::::");
            System.out.println("::::::: location ::"+update.getMessage().getLocation());
        } 

        if((update.getMessage().getText().toLowerCase().equals("bonjour") || update.getMessage().getText().toLowerCase().equals("/start")) && etape==0){
            message.setText(welcome()); //Définir un text
            sid.setEtape(1); //incrementer l'étape.
            //sid.setSousEtapeValue(1, 17);
            try {
                execute(message); // envoyer le message.
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            
        } else if (etape > 0){
            switch (etape){
                case 1: 
                    message = processSaveService(update, message);
                    break;
                case 2:
                    message = processSeachService(update, message);
                    break;
                case 3:
                    message = processModifyService(update, message);
                    break;
                default:
                    System.out.println("OKKKKK");
                    message.setText("OK");
                    break;
            }
            try {
                //message = sendInlineKeyBoardMessage(message.getChatId());
                String str = message.getText().toString();
                String[] listeStrings = str.split("&");
                for (String string : listeStrings) {
                    message.setText(string);
                    execute(message); // Call method to send the message
                }
                
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            try {
                message.setText("Entrez bonjour pour commencer.");
                execute(message); // Call method to send the messag
                
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }
        
    }

    /*
     * ****************************************** Process start ***************************************************
     */

    public SendMessage processModifyService(Update update, SendMessage message){
        // etape = 3 0
        // la position vaux 3
      int position = findSession(update);
      SessionID sid =  SessionID.listeSession.get(position);
      String identifiant = "";
      Service service;
      int indice = 0;
      String key ;
      Ville ville;

      switch(sid.getSousEtapeValue(indice)){
        case 0:
            message.setText("Entrez l'identifaint de votre service : ");
            sid.incrementSousEtape(indice);
            return message;
        case 1:
            identifiant = update.getMessage().getText().toUpperCase();
            service = Kiakou.sDAO.findById(identifiant);
             //System.out.println(service);
            if ( service != null){
                SessionID.sousEtapeInfo = identifiant;
                message.setText("Entrez votre mot de passe.");
                sid.incrementSousEtape(indice);
                return message;
            }
            message.setText("Ce identifiant n'exite pas.&Entrez votre identifaint de votre service : ");
            return message;
        case 2:
            identifiant = SessionID.sousEtapeInfo;
            service = Kiakou.sDAO.findById(identifiant);
            System.out.println(identifiant);
            String password = Kiakou.hashPassword(update.getMessage().getText());
            System.out.println(password);
            if (service.getMPersonne().getMotDePasse().equals(password)){
                SessionID.sousEtapeInfo = identifiant;
                String nom = Kiakou.sDAO.findById(identifiant).getMDesignation().toUpperCase();
                message.setText(this.getModifyMenu(nom));
                sid.incrementSousEtape(indice);
                return message;
            }
            message.setText("Mot de passe incorrect.&Entrez votre mot de passe.");
            return message;
          
        case 3: //::::::: :::::::  
            identifiant = SessionID.sousEtapeInfo;
            service = Kiakou.sDAO.findById(identifiant);
            //String nomService = update.getMessage().getText().toString();
            int reponse = this.verifierReponse(update.getMessage().getText());
            if (reponse != -1){
                switch(reponse){
                    case 1:
                        message.setText("Quel est le nouveau nom de votre service : ");
                        sid.setSousEtapeValue(indice, 4);
                        return message;
                    case 2:
                        message.setText("Quel est le nouveau contact de Mr ou Mm  "
                                        +service.getMPersonne().getmNom()
                                        +" "+service.getMPersonne().getmPrenom()+" ? ");

                        sid.setContact(null);
                        request(message, true, false);
                        sid.setSousEtapeValue(indice, 5);
                        return message;
                    case 3:
                        message.setText(
                            "Dans quelle région du Togo êtes vous ?"+
                            Kiakou.requestRegionByCountry()
                        );
                        sid.setSousEtapeValue(indice, 6);
                        return message;
                        
                    case 4:
                        message.setText("fonction indisponible pour le moment.&"
                                        +this.getModifyMenu(service.getMDesignation().toUpperCase()));
                        sid.setSousEtapeValue(indice, 2);
                        return message;
                    case 5:
                        message.setText("Session terminer");
                        sid.resetListeSousEtape();
                        sid.setEtape(0);
                        return message;
                }
            }
            message.setText(
                "ce numero n'exite pas.&"
                +this.getModifyMenu(service.getMDesignation())
                );
            return message;

          
          case 4: //:::::::  Demander de modifier le nom du service. ::::::: 
              
            if (update.getMessage().getText().length()>=3){
                identifiant = SessionID.sousEtapeInfo;
                service = Kiakou.sDAO.findById(identifiant);
                service.setMDesignation(update.getMessage().getText());
                Kiakou.sDAO.update(service);
                message.setText(this.getModifyMenu(service.getMDesignation().toUpperCase()));
                sid.setSousEtapeValue(indice, 3);
                return message;
            }
            message.setText(
                "reponse incorrect.&Le nom doit comporter au moins trois(3) lettres."
                );
            return message;
          
          case 5: //::::::: Demander de modifier le contact :::::::                 
            if (sid.getContact() != null){
                identifiant = SessionID.sousEtapeInfo;
                service = Kiakou.sDAO.findById(identifiant);
                service.getMPersonne().setmContact(sid.getContact().getPhoneNumber());;
                Kiakou.pDAO.update(service.getMPersonne());
                message.setText("Mr ou Mm  "
                            +service.getMPersonne().getmNom()+" "
                            +service.getMPersonne().getmPrenom()+" votre nouveau numéro de téléphone est le "
                            +service.getMPersonne().getmContact()+"&"
                            +this.getModifyMenu(service.getMDesignation().toUpperCase()));
                sid.setSousEtapeValue(indice, 3);
                return message;   
            }
            message.setText("numéro de téléphone incorrect.");
            return message;
      
        case 6: //::::::: Demander la ville. :::::::  
            key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
            Region region = Kiakou.rDAO.findById("REG"+key);

            if (region != null){
                SessionID.sousEtapeInfo += ";"+region.getMNum();
                message.setText(
                    "Dans quelle ville êtes vous ?"+
                    Kiakou.requestTownByRegion(region.getMNum())
                );
                sid.incrementSousEtape(indice);
                return message;
            }
            message.setText(
                "nombre incorrect.&"
                +"Dans quelle région du Togo êtes vous ?"+
                Kiakou.requestRegionByCountry()
                );
            return message;
        case 7: //::::::: Demander le quartier. :::::::  
            key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
            ville = Kiakou.vDAO.findById("VIL"+key);

            if (ville != null){
                SessionID.sousEtapeInfo += ";"+ville.getMNum();
                message.setText(
                    "Dans quelle quartier êtes vous ?"
                    +Kiakou.requestQuartierByTown(ville.getMNum()) 
                );
                sid.incrementSousEtape(indice);
                return message;
            }
            String keyRegion = SessionID.sousEtapeInfo.split(";", 3)[1];
            System.out.println(keyRegion);
            message.setText(
                "nombre incorrect.&"
                +"Dans quelle ville êtes vous ?"
                + Kiakou.requestTownByRegion(keyRegion)
                );
            return message;  
        case 8:
            key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
            Quartier quartier = Kiakou.qDAO.findById("QAR"+key);
            System.out.println(SessionID.sousEtapeInfo);
            if (quartier != null){
                identifiant = SessionID.sousEtapeInfo.split(";", 3)[0];
                service = Kiakou.sDAO.findById(identifiant);
                service.getMAdresse().setmQuartier(quartier);
                Kiakou.adDAO.update(service.getMAdresse());
                message.setText("Le service "
                                +service.getMDesignation()+" est maintemant dans la ville de "
                                +service.getMAdresse().getMQuartier().getMVille().getMNom()+" dans le quatier de "
                                +service.getMAdresse().getMQuartier().getMNom()+"&"
                                +this.getModifyMenu(service.getMDesignation().toUpperCase())
                                );
                sid.setSousEtapeValue(indice, 3);
                return message;   
            }
            String keyVille = SessionID.sousEtapeInfo.split(";", 3)[2];
            
            message.setText(
                "nombre incorrect.&"
                +"Dans quelle quartier êtes vous ?"
                +Kiakou.requestQuartierByTown(keyVille) 
            );
            return message;  
        }
        return message;
  }

  public String getModifyMenu(String nom){
      return "Entreprise  " + nom
              +"\nQue voulez vous modifier ?"
              +"\n1.Le nom de votre service."
              +"\n2.Votre contact."
              +"\n3.Votre adresse."
              +"\n4.Vos horaires de service(ne fonctionne pas encore)."
              +"\n5.Quitter";
  }

    public SendMessage processSeachService(Update update, SendMessage message) {

        //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        //etape  = 2 0
        int indice = 2;
        String key = "";
        String id_quartier;
        String id_activite;
        String id_ville;
        Activite activite;
        Ville ville;
        Quartier quartier;
        LieuDeReference lieuDeReference;
        String[] listeInfos;
        String result;
        String reponse;

        switch(sid.getSousEtapeValue(indice)){
            case 0: //::::::: Demander la categorie. :::::::
                reponse = update.getMessage().getText();
                if (verifierReponse(reponse) == 2){
                    message.setText(
                        Kiakou.requestCategorie(
                            "Dans quelle catégorie se trouve l'activité recherché ?",
                             "categorie")
                    );
                    sid.incrementSousEtape(indice);  
                    return message;
                } 
                message.setText("nombre incorrect.");
                return message;
                
            case 1: //::::::: Demander l'activité. :::::::  
                //:::::::::: Récupérer la catégorie
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                Categorie categorie = Kiakou.cDAO.findById("CAT"+key);
                if (categorie != null){
                    SessionID.sousEtapeInfo = categorie.getMNum();
                    message.setText(
                        "Quelle activité recherhé vous ?"
                        +Kiakou.requestActiviteByCategorie(categorie.getMNum())
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                        "Réponse incorrect.&"
                        +Kiakou.requestCategorie(
                            "Dans quelle catégorie se trouve l'activité recherché ?",
                             "categorie")
                    );
                return message;  
            
            case 2: //::::::: Demander la région. ::::::: 
                //String num_cat = SessionID.sousEtapeInfo.split(";", 1)[0].split("-", 2)[0];
                //System.out.println("Num cat: "+num_cat);

                categorie = Kiakou.cDAO.findById(SessionID.sousEtapeInfo.split(";", 2)[0]);
                System.out.println("Categorie "+categorie.getMNom());
                
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                activite = Kiakou.aDAO.findById("ACT"+key);

                if (activite != null){
                    SessionID.sousEtapeInfo += ";"+activite.getmNum();
                    message.setText(
                        "Dans quelle région du Togo êtes vous ?"+
                        Kiakou.requestRegionByCountry()
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }

                message.setText(
                    "nombre incorrect.&"
                    +Kiakou.requestActiviteByCategorie(categorie.getMNum())
                    );
                return message;
            
            case 3: //::::::: Demander la ville. :::::::  
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                Region region = Kiakou.rDAO.findById("REG"+key);

                if (region != null){
                    SessionID.sousEtapeInfo += ";"+region.getMNum();
                    message.setText(
                        "Dans quelle ville êtes vous ?"+
                        Kiakou.requestTownByRegion(region.getMNum())
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle région du Togo êtes vous ?"+
                    Kiakou.requestRegionByCountry()
                    );
                return message;

            case 4: //::::::: Demander le quartier. :::::::  
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                ville = Kiakou.vDAO.findById("VIL"+key);

                if (ville != null){
                    SessionID.sousEtapeInfo += ";"+ville.getMNum();
                    message.setText(
                        "Dans quelle quartier êtes vous ?"
                        +Kiakou.requestQuartierByTown(ville.getMNum()) 
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                String keyRegion = SessionID.sousEtapeInfo.split(";", 4)[2];
                System.out.println(keyRegion);
                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle ville êtes vous ?"
                    + Kiakou.requestTownByRegion(keyRegion)
                    );
                return message;  
                
            case 5: //::::::: Demander l'existence d'un lieu populaire. :::::::
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                quartier = Kiakou.qDAO.findById("QAR"+key);

                if (quartier != null){
                    SessionID.sousEtapeInfo += ";"+quartier.getMNum();
                    System.out.println(SessionID.sousEtapeInfo);
                    message.setText("Vous êtes vers un lieu populaire ?(oui ou non)");
                    sid.incrementSousEtape(indice);
                    return message;
                }

                id_ville = SessionID.sousEtapeInfo.split(";", 5)[3];
                System.out.println(id_ville);

                message.setText(
                    "nombre incorrect."
                    +"Dans quelle quartier êtes vous ?"
                    +Kiakou.requestQuartierByTown(id_ville) 
                    );
                return message; 
            
            case 6: //::::::: Demander le nom du lieu. ::::::: 
                listeInfos = SessionID.sousEtapeInfo.split(";", 5);
                id_quartier = listeInfos[4];
                id_ville = listeInfos[3];
                id_activite = listeInfos[1];
                activite = Kiakou.aDAO.findById(id_activite);

                if (update.getMessage().getText().toLowerCase().equals("oui")){

                    if (Kiakou.requestReferencePlaceByQuartier(id_quartier) != ""){
                        message.setText(
                        "Le quelle parmis la liste ?"+
                        Kiakou.requestReferencePlaceByQuartier(id_quartier)
                        );
                        sid.incrementSousEtape(indice);
                        return message;
                    } 
                    message.setText("Nous n'avons pas trouvé de lieu de référence dans votre quartié.&"
                                    +"\n1. Voulez vous rechoisir le quartier."
                                    +"\n2. voir les services de "+activite.getMNom()+" trouvé dans votre localité."
                                    );
                    sid.setSousEtapeValue(indice, 8);
                    return message;
                    
                } else if (update.getMessage().getText().toLowerCase().equals("non")){
                    result = this.showFindService(listeInfos);
                    message.setText(result+"&Session terminé.");
                    sid.resetListeSousEtape();
                    sid.setEtape(0);

                    return message;

                }
                message.setText(
                    "réponse incorrect. '"+update.getMessage().getText()+"' &"
                    +"Vous êtes vers un lieu populaire ?(oui ou non)"
                    );
                return message;  
            
            case 7: //::::::: Afficher résultat. :::::::  
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                lieuDeReference = Kiakou.lrDAO.findById("LRF"+key);
                
                listeInfos = SessionID.sousEtapeInfo.split(";", 5);
                id_activite = listeInfos[1];
                activite = Kiakou.aDAO.findById(id_activite);

                ArrayList<Service> services = Kiakou.getServiceByActivityAndRefPlace(activite, lieuDeReference);
                result = "";
                if (lieuDeReference != null){
                    if (services.size() != 0){
                        result = "Voici le service qu'on à trouvé  "+lieuDeReference.getMNom()+"&";
                        result += Kiakou.showResultatSeacheService(services);
                    } else {
                        result = this.showFindService(listeInfos);
                    }
                    
                    SessionID.sousEtapeInfo += lieuDeReference.getMNum();
                    message.setText(result+"&Session terminé.");
                    
                    sid.resetListeSousEtape();
                    sid.setEtape(0);
                    
                    return message;
                }

                id_quartier = SessionID.sousEtapeInfo.split(";", 5)[4];

                message.setText(
                    "réponse incorrect. '"+update.getMessage().getText()+"'\n"
                    +"Le quelle parmis la liste ?"
                    +Kiakou.requestReferencePlaceByQuartier(id_quartier)
                    );
                return message;
            
            case 8:
                if (update.getMessage().getText().toString().equals("1")){
                    String villeKey = SessionID.sousEtapeInfo.split(";", 5)[3];
                    SessionID.sousEtapeInfo = SessionID.sousEtapeInfo.split(";", 5)[0]+";"+SessionID.sousEtapeInfo.split(";", 5)[1]+";"
                                            +SessionID.sousEtapeInfo.split(";", 5)[2]+";"+SessionID.sousEtapeInfo.split(";", 5)[3];

                    message.setText(
                        "Dans quelle quartier êtes vous ?"
                        +Kiakou.requestQuartierByTown(villeKey) 
                    );
                    sid.setSousEtapeValue(indice, 5);
                } else if (update.getMessage().getText().toString().equals("2")) {
                    listeInfos =  SessionID.sousEtapeInfo.split(";", 5);
                    result = this.showFindService(listeInfos);
                    message.setText("text");
                    sid.setEtape(0);
                } else {
                    id_quartier = SessionID.sousEtapeInfo.split(";", 5)[4];
                    message.setText("Réponse incorrect '"+update.getMessage().getText().toString()+"'"
                                    +"\n1. Voulez vous rechoisir le quartier.\n2. voir les services trouvé à "
                                    +Kiakou.qDAO.findById(id_quartier).getMNom());
                }
                
                return message;
        }
        return null;
    }

    public String showFindService(String[] listeInfos){
        String result;
        Quartier quartier;
        Activite activite;
        Ville ville;
        String id_activite = listeInfos[1];
        activite = Kiakou.aDAO.findById(id_activite);
        quartier = Kiakou.qDAO.findById(listeInfos[4]);
        ArrayList<Service> services = Kiakou.getServiceByActivityAndQuartier(activite, quartier);

        if (services.size() != 0){
            result = "Voici le service de '"+activite.getMNom()+"' qu'on à trouvé "+quartier.getMNom()+"&";
            result += Kiakou.showResultatSeacheService(services);
        } else {
            ville = Kiakou.vDAO.findById(listeInfos[3]);
            services = Kiakou.getServiceByActivityAndTown(activite, ville);
            
            if (services.size() != 0){
                result = "Voici le service de '"+activite.getMNom()+"' qu'on à trouvé "+quartier.getMNom()+"&";
                result += Kiakou.showResultatSeacheService(services);
            } else {
                result = "Aucun service de '"+activite.getMNom()+"' Trouvé à "+ville.getMNom();
            }
        }
        //System.out.println(result);
        return result;
    }

    public SendMessage processSaveService(Update update, SendMessage message){
        
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        // etape = 1 0
        int indice = 1;
        if (sid.getSousEtapeValue(indice) >= 0 && sid.getSousEtapeValue(indice) <= 5){
            message = savePersonne(update, message);
        }

        if (sid.getSousEtapeValue(indice) >= 6){
            if (sid.getSousEtapeValue(indice) == 6){
                String firstMessage = message.getText();
                message = saveService(update, message);
                String secondMessage = message.getText();
                message.setText(firstMessage+secondMessage);
            } else {
                message = saveService(update, message);
            }
        }
        return message;
    }

    public int verifierReponse(String reponseStr){
        int reponse = -1;
        try {
            reponse = Integer.parseInt(reponseStr);
        } catch (Exception e) {
            return reponse;
        }
        return reponse;
    }

    public SendMessage savePersonne(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        String reponse;
        
        int indice = 1;
        switch(sid.getSousEtapeValue(indice)){
            case 0: //::::::: Demander le nom. :::::::
                reponse = update.getMessage().getText();
                int choix = this.verifierReponse(reponse);
                System.out.println(choix);
                switch(choix){
                    case 1:
                        message.setText("Quelle est Votre nom.");
                        //sid.incrementSousEtape(indice);
                        //::::::: Mise à jour de la liste Info. :::::::
                        PersonneBuilder personneBuilder = new Personne.PersonneBuilder();
                        sid.getArrayEtapeInfo().add(personneBuilder);
                        sid.incrementSousEtape(indice);
                        //sid.setSousEtapeValue(indice, 6);
                        return message;
                    case 2:
                        sid.setEtape(2);;
                        message = processSeachService(update, message);
                        return message;
                    case 3:
                        sid.setEtape(3);
                        message = processModifyService(update, message);
                        return message;
                    default:
                        message.setText("Réponse incorrect.&"+this.menuWelcome());
                        return message;
                }

            case 1: //::::::: Demander le prenom. :::::::
                reponse = update.getMessage().getText();
                if (reponse.length() >= 2){
                    SessionID.sousEtapeInfo = reponse;
                    ((PersonneBuilder) sid.getArrayEtapeInfo().get(0)).withFristName(update.getMessage().getText().toUpperCase()); 
                    sid.incrementSousEtape(indice);
                    message.setText("Quelle est votre prenom.");
                    return message;
                } 
                message.setText("Nom incorrect.");
                return message;

            case 2: //::::::: Demander le contact. :::::::
                reponse = update.getMessage().getText();
                if (update.getMessage().getText().length() > 2) {
                    Personne p = null;
                    try {
                        p = Kiakou.pDAO.findByPersonne(SessionID.sousEtapeInfo, reponse);
                        if (p == null){
                            sid.incrementSousEtape(indice);
                            ((PersonneBuilder) sid.getArrayEtapeInfo().get(0)).withLastName(update.getMessage().getText().toLowerCase());
                            message.setText("Envoyer nous votre contact.");
                            request(message, true, false);
                            return message;
                        }
                        message.setText("Mr ou Mm "
                                    +SessionID.sousEtapeInfo
                                    +" "+reponse+", "
                                    +"vous êtes déjà inscrit à KIAKOU & Voulez vous enregister un nouveau service ?(oui ou non)"); 
                        sid.getArrayEtapeInfo().remove(0);
                        sid.getArrayEtapeInfo().add(p);
                        sid.setSousEtapeValue(indice, 99);
                        return message;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                message.setText("Prénom incorrect.");
                return message;

            case 3: //::::::: Demander . :::::::
                if (sid.getContact() != null){
                    sid.incrementSousEtape(indice);
                    ((PersonneBuilder) sid.getArrayEtapeInfo().get(0)).withContact(sid.getContact().getPhoneNumber().toString());
                    //((PersonneBuilder) sid.getArrayEtapeInfo().get(0)).build();
                    message.setText("Contact bien reçu.&Entrez un mot de passe(au moins 8 caractere).");
                    System.out.println("vcard==="+sid.getContact().getVCard());
                    return message;   
                }
                message.setText("numéro de téléphone incorrect.");
                return message;
            case 4:
                if (update.getMessage().getText().length() >= 8){
                    SessionID.sousEtapeInfo = update.getMessage().getText();
                    message.setText("Confirmer le mot de passe.");
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText("Mot de passe invalide (au moins 8 caractere).");
                return message;
               
            case 5:
                if (SessionID.sousEtapeInfo.equals(update.getMessage().getText())){
                    Personne p = ((PersonneBuilder) sid.getArrayEtapeInfo().get(0)).withPassword(update.getMessage().getText()).build();
                    sid.getArrayEtapeInfo().remove(0);
                    sid.getArrayEtapeInfo().add(p);
                    message.setText("Mot de passe correct.&");
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText("Mot de passe incorrect (au moins 8 caractere).&Confirmer le mot de passe.");
                return message;
            case 99:
                reponse = update.getMessage().getText();
                if (reponse.toLowerCase().equals("oui")){//personne exist
                    message.setText(
                        Kiakou.requestCategorie(
                            "Dans quelle categorie se trouve votre activité ?", 
                            "categorie"
                            )
                        );
                    sid.setSousEtapeValue(indice, 7);
                    return message;
                } else if (reponse.toLowerCase().equals("non")) {
                    message.setText("Session terminé.&"+menuWelcome());
                    sid.resetListeSousEtape();
                    sid.setEtape(0);
                    return message;
                } else {
                    message.setText("Réponse incorrect."
                    +"vous êtes déjà inscrit à KIAKOU & Voulez vous enregister un nouveau service ?(oui ou non)");
                    sid.setSousEtapeValue(indice, 99); 
                    return message;

                }
        }
        return null;
    }

    public SendMessage saveService(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);  
        SessionID sid =  SessionID.listeSession.get(position);
        String key = "";
        String reponse ;
        //etape 1 0
        int indice = 1;
        switch(sid.getSousEtapeValue(indice)){ 
            case 6: //::::::::::catégorie::::::::::: 
                reponse = update.getMessage().getText();
    
                message.setText(
                    Kiakou.requestCategorie(
                        "Dans quelle categorie se trouve votre activité ?", 
                        "categorie"
                        )
                    );
                sid.incrementSousEtape(indice);
                return message;
            case 7: //::::::::::activité:::::::::::
                //:::::::::: Récupérer la catégorie
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText());
                //System.out.println("//////*********///////"+key);
                Categorie categorie = Kiakou.cDAO.findById("CAT"+key);
                //System.out.println(categorie.getMNom());
                if (categorie != null){
                    SessionID.sousEtapeInfo += categorie.getMNum()+";";
                    message.setText(
                        "Dans quelle activité est votre service."
                        +Kiakou.requestActiviteByCategorie(categorie.getMNum())
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "Réponse incorrect.\n"
                    +Kiakou.requestCategorie(
                        "Dans quelle categorie se trouve votre activité ?", 
                        "categorie"
                        )
                    );
                return message;
                
            case 8: //:::::::::: Désignation:::::::::::
                //:::::::::: Récupérer l'activité
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                Activite activite = Kiakou.aDAO.findById("ACT"+key);
                if (activite != null) {
                    SessionID.sousEtapeInfo += activite.getmNum()+";";
                    message.setText("Quelle est le nom de votre service ?(au moins 3 lettres)");
                    sid.getArrayEtapeInfo().add(new Service.ServiceBuilder());
                    
                    try {
                        ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withActivite(activite);
                    } catch (ClassCastException e) {
                       e.printStackTrace();
                    }

                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "Réponse incorrect.\n"
                    +"Dans quelle activité est votre service."
                    +Kiakou.requestActiviteByCategorie(activite.getmCategorie().getMNum())
                    );
                return message;
            
            case 9: //:::::::::: Déscription:::::::::::
                //:::::::::: Récupérer le nom
                reponse = update.getMessage().getText();
                if (reponse.length() >= 3 ) {
                   try {
                        ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withDesignation(update.getMessage().getText());
                   } catch (ClassCastException e) {
                        e.printStackTrace();
                   }
                    message.setText("Expliquer ce que vous faite.(au plus 25 mots)");
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "Désignation invalide.\n"
                    +"Quelle est le nom de votre service ?(au moins 3 lettres)"
                    );
                return message;
            
            case 10: //::::::: Demander la région. :::::::  
                reponse = update.getMessage().getText();
                if (reponse.length() > 3 && reponse.length() <= 100){
                    try {
                        ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withDescription(update.getMessage().getText());
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                    message.setText(
                        "Dans quelle région du Togo êtes vous ?"
                        +Kiakou.requestRegionByCountry()
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "Déscription invalide.\n"
                    +"Expliquer ce que vous faite.(au plus 60 mots)"
                    );
                return message;
            
            case 11: //::::::: Demander la ville. ::::::: 
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString()) ;
                Region region = Kiakou.rDAO.findById("REG"+key);
                if (region != null){
                    SessionID.sousEtapeInfo += region.getMNum(); 
                    message.setText(
                        "Dans quelle ville êtes vous ?"+
                        Kiakou.requestTownByRegion(region.getMNum())
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle région du Togo êtes vous ?"+
                    Kiakou.requestRegionByCountry()
                    );
                return message;

            case 12: //::::::: Demander le quartier. :::::::  
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                Ville ville = Kiakou.vDAO.findById("VIL"+key);
                if (ville != null){
                    SessionID.sousEtapeInfo += ";" + ville.getMNum();
                    message.setText(
                        "Dans quelle quartier êtes vous ?"
                        +Kiakou.requestQuartierByTown(ville.getMNum()) 
                    );
                    sid.incrementSousEtape(indice);
                    return message;
                }
               
                String keyRegion = SessionID.sousEtapeInfo.split(";", 3)[2];                 

                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle ville êtes vous ?"+
                    Kiakou.requestTownByRegion(keyRegion) 
                    );

                return message;  
                
            case 13: //::::::: Demander l'existence d'un lieu populaire. :::::::  
                key = Kiakou.useFunction.useDclKeyFormat(update.getMessage().getText().toString());
                Quartier quartier = Kiakou.qDAO.findById("QAR"+key);
                String refStr = "";
                System.out.println("//////////"+SessionID.sousEtapeInfo);
                if (quartier != null){
                    refStr = "\n0-Aucun.";
                    if (Kiakou.requestReferencePlaceByQuartier(quartier.getMNum())!= null){
                        refStr += Kiakou.requestReferencePlaceByQuartier(quartier.getMNum());
                    }
                    SessionID.sousEtapeInfo += ";"+quartier.getMNum();
                    message.setText("Vous êtes proche de quelle lieu populaire ?"+refStr);
                    //sid.incrementSousEtape(indice);
                    sid.setSousEtapeValue(indice, 15);
                    return message;
                }
                String keyVille = SessionID.sousEtapeInfo.split(";", 4)[3];
                message.setText(
                    "Réponse incorrect."
                    +"Dans quelle quartier êtes vous ?"
                    +Kiakou.requestQuartierByTown(keyVille) 
                    );
                return message; 
            
            case 15:
                reponse = update.getMessage().getText();
                key = Kiakou.useFunction.useDclKeyFormat(reponse);
                LieuDeReference lieuDeReference = Kiakou.lrDAO.findById("LRF"+key);
                String keyQuartie = SessionID.sousEtapeInfo.split(";", 5)[4];

                System.out.println("//////////"+SessionID.sousEtapeInfo);

                if (verifierReponse(reponse)==0){
                    try {
                        ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withAdresse(
                        new Adresse.AdresseBuilder().withQuartier(Kiakou.qDAO.findById(keyQuartie)).build()
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.println("0000000");
                } else if (verifierReponse(reponse) > 0){
                    if (lieuDeReference != null){
                        try {
                            ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withAdresse(
                            new Adresse.AdresseBuilder().withQuartier(lieuDeReference.getMQuartier())
                                       .withLieuDeReference(lieuDeReference)
                                       .build()
                            );
                        } catch (ClassCastException e) {
                           e.printStackTrace();
                        }
                        //System.out.println("000111110000");
                    } else {
                        if (Kiakou.qDAO.findById(keyQuartie) != null){
                            String srtRef = "";
                            srtRef = "\n0-Aucun";
                            if (Kiakou.requestReferencePlaceByQuartier(Kiakou.qDAO.findById(keyQuartie).getMNum())!=null){
                                srtRef += Kiakou.requestReferencePlaceByQuartier(Kiakou.qDAO.findById(keyQuartie).getMNum());
                            }
                            message.setText(
                                "réponse incorrect.&"
                                +"Vous êtes proche de quelle lieu populaire ?"+srtRef
                                );
                            return message;
                        }
                    }
                }
                reponse = update.getMessage().getText();
                SessionID.sousEtapeInfo = "";
                message.setText("A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ?\n(hh:mm) ou toute la journée (24h24)");
                sid.setSousEtapeValue(indice, 17);
                return message;
                
                
            case 16: //:::::::::: Horrair :::::::::::
                reponse = update.getMessage().getText();
                System.out.println("//****///*---"+SessionID.ijour);
                /*if (SessionID.ijour == 0){
                    SessionID.sousEtapeInfo += "%";
                } else {
                    SessionID.sousEtapeInfo += reponse;
                }*/
                if (Kiakou.checkHour(reponse)){
                    //if 24 h save
                    //sinon heur ferm 
                    if (SessionID.ijour == 6){
                        if (reponse.equals("24h24")){
                            //save.
                            SessionID.sousEtapeInfo += reponse;
                            System.out.println(SessionID.sousEtapeInfo);
                            message.setText(SessionID.sousEtapeInfo);
                            return message;
                        } else {
                            SessionID.sousEtapeInfo += "%"+reponse+";";
                            message.setText("A quelle heure vous fermé le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                            //save.
                            //System.out.println(SessionID.sousEtapeInfo);
                            //message.setText(SessionID.sousEtapeInfo);
                            sid.setSousEtapeValue(indice, 18);
                            return message;
                        }
                    }
                    SessionID.sousEtapeInfo += reponse;
                    message.setText("A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ?\n(hh:mm) ou toute la journée (24h24)");
                    sid.incrementSousEtape(indice);
                    return message;
                }
                message.setText("Vous avez mal saisi l'heur.\n"
                +"A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                return message;
            
            case 17:
                reponse = update.getMessage().getText();
                System.out.println(SessionID.sousEtapeInfo);
                if (Kiakou.checkHour(reponse)){
                    if (reponse.equals("24h24")){
                        SessionID.ijour ++;
                        SessionID.sousEtapeInfo += "%"+reponse;
                        message.setText("A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ?\n(hh:mm) ou toute la journée (24h24)");
                        if (SessionID.ijour == 6){
                            sid.setSousEtapeValue(indice, 18);
                        } else {
                            sid.setSousEtapeValue(indice, 17);
                        }
                        return message ;
                    }
                    SessionID.sousEtapeInfo += "%"+reponse+";";
                    message.setText("A quelle heure vous fermé le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                    SessionID.ijour ++;
                    //sid.setSousEtapeValue(indice, 16);
                    if (SessionID.ijour == 6){
                        sid.setSousEtapeValue(indice, 21);
                    } else {
                        sid.setSousEtapeValue(indice, 16);
                    }
                    return message ;
                }
                message.setText("Vous avez mal saisi l'heur.\n"
                +"A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                return message;
            case 18:
                reponse = update.getMessage().getText();
                
                if (Kiakou.checkHour(reponse)){
                    SessionID.sousEtapeInfo += "%"+reponse;
                    KiakouBot.saveService(position);
                    System.out.println(SessionID.sousEtapeInfo);
                    message.setText("Session terminé.");
                    return message;
                }
                message.setText("Vous avez mal saisi l'heur1.\n"
                +"A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                return message;

            case 19:
                
                Service service = null;
                try {
                    System.out.println((Personne) sid.getArrayEtapeInfo().get(0));
                    service = ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withPersonne(
                       (Personne) sid.getArrayEtapeInfo().get(0)
                    ).build();
                    System.out.println((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1));
                    Kiakou.saveHour(SessionID.sousEtapeInfo, service);
                } catch (Exception e) {
                   e.printStackTrace();
                }
                
                /*ArrayList<Service> services = new ArrayList<Service>();
                try {
                    services.add((Service) sid.getArrayEtapeInfo().get(1));
                } catch (Exception e) {
                    message.setText("inexistant.....?");
                }
                message.setText(Kiakou.showResultatSeacheService(services));*/
                message.setText("text");
                return message;
            case 21:
                reponse = update.getMessage().getText();
                SessionID.sousEtapeInfo += reponse;
                message.setText("A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ?\n(hh:mm) ou toute la journée (24h24)");
                sid.incrementSousEtape(indice);
                return message;
            case 22:
            reponse = update.getMessage().getText();
                if (Kiakou.checkHour(reponse)){
                    if (reponse.equals("24h24")){
                        //save.
                        SessionID.sousEtapeInfo += "%"+reponse;
                        KiakouBot.saveService(position);
                        System.out.println(SessionID.sousEtapeInfo);
                        message.setText("Session terminé.");
                        return message;
                    } else {
                        SessionID.sousEtapeInfo += "%"+reponse+";";
                        message.setText("A quelle heure vous fermé le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
                        
                        //System.out.println(SessionID.sousEtapeInfo);
                        //message.setText(SessionID.sousEtapeInfo);
                        
                        sid.setSousEtapeValue(indice, 18);
                        return message;
                    }
            }
            message.setText("Vous avez mal saisi l'heur1.\n"
                            +"A quelle heure vous ouvrez le "+Kiakou.jours_semaine[SessionID.ijour]+" ? (hh:mm)");
            return message;
        }
        
        return null;
    }

    public static void saveService(int position) {
        SessionID sid =  SessionID.listeSession.get(position);
        Service service = null;
        try {
            System.out.println((Personne) sid.getArrayEtapeInfo().get(0));
            service = ((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1)).withPersonne(
                (Personne) sid.getArrayEtapeInfo().get(0)
            ).build();
            System.out.println((Service.ServiceBuilder) sid.getArrayEtapeInfo().get(1));
            Kiakou.saveHour(SessionID.sousEtapeInfo, service);
            sid.resetListeSousEtape();
            sid.setEtape(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * ****************************************** Process end ******************************************
     */

    public void request(SendMessage message, boolean contact, boolean location){
         // create keyboard
         ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
         message.setReplyMarkup(replyKeyboardMarkup);
         replyKeyboardMarkup.setSelective(true);
         replyKeyboardMarkup.setResizeKeyboard(true);
         replyKeyboardMarkup.setOneTimeKeyboard(true);

         // new list
         List<KeyboardRow> keyboard = new ArrayList<>();

         // first keyboard line
         KeyboardRow keyboardFirstRow = new KeyboardRow();
         KeyboardButton keyboardButton = new KeyboardButton();

         String action = "numéro";
         if(location){
            action = "localisation";
         }
         keyboardButton.setText("Envoyer votre "+action+".  📨️");

         keyboardButton.setRequestContact(contact);
         keyboardButton.setRequestLocation(location);
         keyboardButton.getRequestContact();
         keyboardFirstRow.add(keyboardButton);

         // add array to list 📨️>
         keyboard.add(keyboardFirstRow);

         // add list to our keyboard
         replyKeyboardMarkup.setKeyboard(keyboard);
    }
    
    public String welcome() {
        return "Bienvenue sur Kiakou."
            +"\nIci nous mettons en relations les prestataires de service et les demmandeurs de service.\n"
            +menuWelcome();
    }

    public String menuWelcome(){
        return "Entrez:\n1.Enregister votre(vos) service(s)."
            +"\n2.Demmander un service."
            +"\n3.Modifier les informations de votre service.";
    }

    public int findSession(Update update){
        // Si l'id n'existe pas créer une nouvelle instance de session.
        int position = SessionID.findChatId(update.getMessage().getChatId()) ;
        if (position==-1){
            System.out.println("La session n'existe pas .");
            Long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            //System.out.println(user);
            SessionID sid = new SessionID(chatId, user);
            return SessionID.listeSession.indexOf(sid);
        }
        return position;
    }

    /**
     * crée un message contenant des boutons.
     * @param chatId
     * @return SendMessage
     */

    public static SendMessage sendInlineKeyBoardMessage(String chatId, String name, String url, String text){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();


        inlineKeyboardButton.setText(name);
        inlineKeyboardButton.setUrl(url);
        inlineKeyboardButton.setCallbackData("90");
        
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow2.add(inlineKeyboardButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
       
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sm = new SendMessage();

        sm.setChatId(chatId);
        sm.setText(text);
        sm.setReplyMarkup(inlineKeyboardMarkup);
        return sm;
       }

    @Override
    public String getBotToken() {
        //"5148846345:AAGTo5uKUOcqhRnMvn1X1gWWdHid4P1pjtE"; amk_bot
        return  "5125837970:AAHQf14s5zdIpJwQ5aDoJAJQB6gKO9nkdBI"; 
    }
}