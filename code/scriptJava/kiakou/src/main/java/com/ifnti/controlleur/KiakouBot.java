package com.ifnti.controlleur;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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

import com.ifnti.modele.service.Personne;
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

        SendMessage message = new SendMessage(); //nouvelle instance de send message.
        message.setChatId(update.getMessage().getChatId().toString()); // Définir l'id du chat.
        
        //....rechercher la position de la session.
        int position = findSession(update);
<<<<<<< Updated upstream
        int etape = SessionID.listeSession.get(position).getEtape();

        System.out.println("Etape deb: "+SessionID.listeSession.get(position).getEtape());
        System.out.println("user: "+SessionID.listeSession.get(position).getUser());

        System.out.println(update);

        /*if (update.hasCallbackQuery()){
            System.out.println("mmmmmmmm");
        } else {
            try {
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId().toString(), "saisir heure", "http://saisirheure.com/", "Go to web site.")); // envoyer le message.
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }*/

        /*if (update.getMessage().getContact()!=null || update.getMessage().getLocation()!=null){
            //SessionID.listeSession.get(position).setContact(update.getMessage().getContact());
=======

        int etape = SessionID.listeSession.get(position).getEtape();
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();
        System.out.println(":::::::::::: new value = "+listeSousEtape[1][0]+"::::::::::::");


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
>>>>>>> Stashed changes
            update.getMessage().setText("");
            System.out.println(":::::: numéro de telephone reçu ::::::");
            System.out.println("::::::: location ::"+update.getMessage().getLocation());
        } 

        if(update.getMessage().getText().equals("h") && etape==0){
            message.setText(welcome()); //Définir un text
<<<<<<< Updated upstream
            ArrayList<String> etapeInfos = new ArrayList<>();
            etapeInfos.add("Welcome");
            //SessionID.listeSession.get(position).addArrayEtapeInfo(etapeInfos); // Ajouter un tableau [ ["welcome"] ]
            SessionID.listeSession.get(position).getArrayEtapeInfo().add(etapeInfos);   // Ajouter un tableau [ [""] ]
            SessionID.listeSession.get(position).incrementEtape(); //incrementer l'étape.

            System.out.println(SessionID.listeSession.get(position).getArrayEtapeInfo());
            System.out.println("Etape fin: "+SessionID.listeSession.get(position).getEtape());

=======
            SessionID.listeSession.get(position).incrementEtape(); //incrementer l'étape.
>>>>>>> Stashed changes
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
                default:
                    System.err.println("OKKKKK");
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
        } */
        
    }

    public SendMessage processSaveService(Update update, SendMessage message){
         //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();
        
        if (listeSousEtape[1][0] >= 0 && listeSousEtape[1][0] <= 4){
            message = savePersonne(update, message);
        }
        
        if (listeSousEtape[1][0] >= 4){
            if (listeSousEtape[1][0]==4){
                String firstMessage = message.getText();
                message = saveService(update, message);
                String secondMessage = message.getText();
                message.setText(firstMessage+secondMessage);
            } else {
                message = saveService(update, message);
            }

        }
        System.out.println(":::::::::::: new value = "+listeSousEtape[1][0]+"::::::::::::");

        return message;
    }

    public SendMessage processSeachService(Update update, SendMessage message) {

        //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();

        System.out.println(":::::::::::: new value = "+listeSousEtape[2][0]+"::::::::::::");

        switch(listeSousEtape[2][0]){
            case 0: //::::::: Demander la categorie. :::::::
                if (update.getMessage().getText().equals("2")){
                    message.setText(
                        Kiakou.requestCategorie(
                            "Dans quelle catégorie se trouve l'activité recherché ?",
                             "categorie")
                    );
                    listeSousEtape[2][0] += 1;                    
                    return message;
                } 
                message.setText("nombre incorrect.");
                return message;
                
            case 1: //::::::: Demander l'activité. :::::::  
                if (update.getMessage().getText().equals("1")){
                    message.setText(
                        Kiakou.requestCategorie(
                            "Quelle service recherché vous ?", 
                            "activite"
                            ) 
                    );
                    listeSousEtape[2][0] += 1;
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
                if (update.getMessage().getText().equals("1")){
                    message.setText(
                        "Dans quelle région du Togo êtes vous ?"+
                        Kiakou.requestRegionByCountry()
                    );
                    listeSousEtape[2][0] += 1;
                    return message;
                }
                message.setText(
                    "nombre incorrect.&"
                    +Kiakou.requestCategorie(
                        "Quelle service recherché vous ?", 
                        "activite"
                        ) 
                    );
                return message;
            
            case 3: //::::::: Demander la ville. :::::::  
                if (update.getMessage().getText().equals("1")){
                    message.setText(
                        "Dans quelle ville êtes vous ?"+
                        Kiakou.requestTownByRegion("REG000001")
                    );
                    listeSousEtape[2][0] += 1;
                    return message;
                }
                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle région du Togo êtes vous ?"+
                    Kiakou.requestRegionByCountry()
                    );
                return message;

            case 4: //::::::: Demander le quartier. :::::::  
                if (update.getMessage().getText().equals("1")){
                    message.setText(
                        "Dans quelle quartier êtes vous ?"
                        +Kiakou.requestQuartierByTown("VIL000001") 
                    );
                    listeSousEtape[2][0] += 1;
                    return message;
                }
                message.setText(
                    "nombre incorrect.&"
                    +"Dans quelle ville êtes vous ?"+
                    Kiakou.requestTownByRegion("REG000001")
                    );
                return message;  
                
            case 5: //::::::: Demander l'existence d'un lieu populaire. :::::::  
                if (update.getMessage().getText().equals("1")){
                    message.setText("Vous êtes vers un lieu populaire ?(oui ou non)");
                    listeSousEtape[2][0] += 1;
                    return message;
                }
                message.setText(
                    "nombre incorrect."
                    +"Dans quelle quartier êtes vous ?"
                    +Kiakou.requestQuartierByTown("VIL000001") 
                    );
                return message; 
            
            case 6: //::::::: Demander le nom du lieu. :::::::  
                if (update.getMessage().getText().toLowerCase().equals("oui")){
                    message.setText(
                        "Le quelle parmis la liste ?"+
                        Kiakou.requestReferencePlaceByQuartier("QAR000002")
                    );
                    listeSousEtape[2][0] += 1;
                    return message;
                } else if (update.getMessage().getText().toLowerCase().equals("non")){
                    listeSousEtape[2][0] += 1;
                    message.setText(Kiakou.showResultatSeacheService("QAR000002"));
                    return message;
                }
                message.setText(
                    "réponse incorrect.&"
                    +"Vous êtes vers un lieu populaire ?(oui ou non)"
                    );
                return message;  
            
            case 7: //::::::: Demander le nom du lieu. :::::::  
                if (update.getMessage().getText().toLowerCase().equals("1")){
                    message.setText(
                        "Voici le service qu'on à trouvé ?&"
                        +Kiakou.showResultatSeacheService("QAR000002")
                    );
                    listeSousEtape[2][0] += 1;
                    return message;
                }
                message.setText(
                    "réponse incorrect."
                    +"Le quelle parmis la liste ?"+
                    Kiakou.requestReferencePlaceByQuartier("QAR000002")
                    );
                return message;
            
            /*case 8:
                message.setText(Kiakou.showResultatSeacheService(""));
                return message;*/
        }
        
        message.setText(Kiakou.showResultatSeacheService(""));
        return message;
    }

    public SendMessage savePersonne(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();


        switch(listeSousEtape[1][0]){
            case 0: //::::::: Demander le nom. :::::::
                if (update.getMessage().getText().equals("1")){
                    message.setText("Quelle est Votre nom.");
                    listeSousEtape[1][0] += 1;
                    //::::::: Mise à jour de la liste Info. :::::::
                    PersonneBuilder personneBuilder = new Personne.PersonneBuilder();
                    sid.getArrayEtapeInfo().add(personneBuilder);
                    return message;

                } else if (update.getMessage().getText().equals("2")){
                    sid.incrementEtape();
                    message = processSeachService(update, message);
                    return message;
                } else {
                    message.setText("Choix incorrect.&"+menuWelcome());
                    return message;
                }
                

            case 1: //::::::: Demander le prenom. :::::::

                if (update.getMessage().getText().length() > 2){
                    sid.getArrayEtapeInfo().get(0).withFristName(update.getMessage().getText().toUpperCase()); 
                    listeSousEtape[1][0] += 1;
                    message.setText("Quelle est votre prenom.");
                    return message;
                } 
                message.setText("Nom incorrect.");
                return message;

            case 2: //::::::: Demander le contact. :::::::
                if (update.getMessage().getText().length() > 2) {
                    listeSousEtape[1][0] += 1;
                    sid.getArrayEtapeInfo().get(0).withLastName(update.getMessage().getText().toLowerCase());
                    message.setText("Envoyer nous votre contact.");
                    request(message, true, false);
                    return message;
                } 
                message.setText("Prénom incorrect.");
                return message;

            case 3: //::::::: Demander . :::::::
                if (sid.getContact() != null){
                    listeSousEtape[1][0] += 1;
                    sid.getArrayEtapeInfo().get(0).withContact(sid.getContact().getPhoneNumber().toString());
                    //sid.getArrayEtapeInfo().get(0).build();
                    message.setText("Contact bien reçu.&");
                    System.out.println("vcard==="+sid.getContact().getVCard());
                    return message;   
                }
                message.setText("numéro de téléphone incorrect.");
                return message;
        }
        return null;
    }

    public SendMessage saveService(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);  
        SessionID sid =  SessionID.listeSession.get(position);
        int[][] listeSousEtape = sid.getListeSousEtape();

        switch(listeSousEtape[1][0]){
            case 4: //::::::::::catégorie::::::::::: 
                //Creation d'une insatance de send message.
                //nouvelle instance de personne.
                //::::::::::ici:::::::::::
                listeSousEtape[1][0] += 1;
                message.setText(
                    Kiakou.requestCategorie(
                        "Dans quelle categorie se trouve votre activité ?", 
                        "categorie"
                        )
                    );
                return message;
            case 5: //::::::::::activité:::::::::::
                //:::::::::: Récupérer la catégorie
                if (update.getMessage().getText().equals("1")){
                    listeSousEtape[1][0] += 1;
                    message.setText(
                        Kiakou.requestCategorie(
                            "Quelle est votre activité ?", 
                            "activite"
                            )
                    );
                    return message;
                }
                message.setText("nombre incorrect.");
                return message;
                
            case 6: //:::::::::: Désignation:::::::::::
                //:::::::::: Récupérer l'activité
                if (update.getMessage().getText().equals("1")) {
                    message.setText("Quelle est le nom de votre service ?(au moins 3 lettres)");
                    listeSousEtape[1][0] += 1;
                    return message;
                }
                message.setText("nom incorrect.");
                return message;
            
            case 7: //:::::::::: Déscription:::::::::::
                //:::::::::: Récupérer le nom
                if (update.getMessage().getText().length() > 240 ) {
                    message.setText("Expliquer ce que vous faite.(au plus 60 mots)");
                    listeSousEtape[1][0] += 1;
                    return message;
                }
                message.setText("description invalide.");
                return message;
            
            case 8: //:::::::::: Ville :::::::::::
                //:::::::::: Récupérer la description.
                message.setText(Kiakou.requestVille());
                listeSousEtape[1][0] += 1;
                return message;
            
            case 9: //:::::::::: quartier :::::::::::
                //:::::::::: Récupérer la ville.
                message.setText(Kiakou.requestQuartier());
                listeSousEtape[1][0] += 1;
                return message;
            
            /*case 11: //:::::::::: Localisation :::::::::::
                //:::::::::: Récupérer la ville.
                ArrayList infoQuartier = (ArrayList) sid.getArrayEtapeInfo().get(listSize-4);
                infoQuartier.add(update.getMessage().getText());
                message.setText("Où se trouve votre service?(localisation).");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                request(message, false, true);
                return message;*/
            
            case 11: //:::::::::: Horrair :::::::::::
                //:::::::::: Récupérer la localisation .
                String text = "Cliquer sur le bouton en bas pour saisir vos horaire";
                message = sendInlineKeyBoardMessage(""+update.getMessage().getChatId(),"saisir heure","saisirHeure.com", text);
                return message;

            case 12://:::::::::: Fin :::::::::::
                if (update.hasCallbackQuery()){
                    System.out.println(update.getCallbackQuery().getMessage().getText());
                }
                /*ArrayList infoService = (ArrayList) sid.getArrayEtapeInfo().get(listSize-5);
                infoService.add(update.getMessage().getLocation());
                message.setText("Localisation bien reçut.&Fin");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                request(message, false, true);
                sid.resetEtape();
                sid.resetEtapeInfo();*/
                message.setText("00000");
                return message;
        }
        
        return null;
    }



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
        return "Bienvenue sur Kiakou.\nIci nous mettons en relations les prestataires de service et les demmandeurs de service.\n"+menuWelcome();
    }

    public String menuWelcome(){
        return "Entrez:\n1.Enregister votre(vos) service(s).\n2.Demmander un service.";
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

        return "5148846345:AAGTo5uKUOcqhRnMvn1X1gWWdHid4P1pjtE";
    }
}