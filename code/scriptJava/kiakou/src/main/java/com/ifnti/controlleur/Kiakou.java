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

public class Kiakou extends TelegramLongPollingSessionBot{

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return "Kiakou";
    }

    // Ecouteur de message.
    @Override
    public void onUpdateReceived(Update update, java.util.Optional<Session> botSession) {
        System.out.println(":::::::::::::::::::Réception d'un message::::::::::::::::::::");
        //..........................
        System.out.println("id: "+update.getMessage().getChatId());
        SendMessage message = new SendMessage(); //nouvelle instance de send message.
        message.setChatId(update.getMessage().getChatId().toString()); // Définir l'id du chat.
        
        //....rechercher la position de la session.
        int position = findSession(update);
        System.out.println("Etape deb: "+SessionID.listeSession.get(position).getEtape());
        int etape = SessionID.listeSession.get(position).getEtape();
        System.out.println("user: "+SessionID.listeSession.get(position).getUser());

        if (update.getMessage().getContact()!=null || update.getMessage().getLocation()!=null){
            //SessionID.listeSession.get(position).setContact(update.getMessage().getContact());
            update.getMessage().setText("");
            System.out.println(":::::: numéro de telephone reçu ::::::");
            System.out.println("::::::: location ::"+update.getMessage().getLocation());
        } 
       

        if(update.getMessage().getText().equals("h") && etape==0){
            message.setText(welcome()); //Définir un text
            ArrayList<String> etapeInfos = new ArrayList<>();
            etapeInfos.add("Welcome");
            //SessionID.listeSession.get(position).addArrayEtapeInfo(etapeInfos); // Ajouter un tableau [ ["welcome"] ]
            SessionID.listeSession.get(position).getArrayEtapeInfo().add(etapeInfos);   // Ajouter un tableau [ [""] ]
            System.out.println(SessionID.listeSession.get(position).getArrayEtapeInfo());
            SessionID.listeSession.get(position).incrementEtape(); //incrementer l'étape.
            System.out.println("Etape fin: "+SessionID.listeSession.get(position).getEtape());
            try {
                execute(message); // envoyer le message.
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (etape > 0){
            System.out.println("else........");
            switch (etape){
                case 1: 
                    message=processSaveService(update, message);
                    //System.out.println("valeur saisi: "+(update.getMessage().getText()));
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
        } 
        
    }
    public SendMessage processSaveService(Update update, SendMessage message){
         //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        int listeSize = sid.getArrayEtapeInfo().size();
        
        if (listeSize >=1 && listeSize <=4){
            message = savePersonne(update, message);
        }
        listeSize = sid.getArrayEtapeInfo().size();
        System.out.println(":::::::::::: new size = "+listeSize+"::::::::::::");
        if (listeSize >= 5){
            if (listeSize==5){
                String firstMessage = message.getText();
                message = saveService(update, message);
                String secondMessage = message.getText();
                message.setText(firstMessage+secondMessage);
            } else {
                message = saveService(update, message);
            }
            //return message;
        }
        System.out.println(":::::::::::: new liste::::::::::::\n"+sid.getArrayEtapeInfo());

        return message;
    }
    /**
     * Process
     * @param update
     * @param sid
     * @return SendMessage
     */
    public SendMessage savePersonne(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);
        SessionID sid =  SessionID.listeSession.get(position);
        int listeSize = sid.getArrayEtapeInfo().size();

        //Creation d'une insatance de send message.
        switch(listeSize){
            case 1: //::::::: Demander le nom. :::::::
                if (update.getMessage().getText().equals("1")){
                    message.setText("Quelle est Votre nom.");
                    //::::::: Mise à jour de la liste Info. :::::::
                    sid.getArrayEtapeInfo().add(new ArrayList<>());
                }
                return message;

            case 2: //::::::: Demander le prenom. :::::::
                ArrayList<String> infoNom = new ArrayList<>();
                infoNom.add(update.getMessage().getText()); 
                //::::::: Mise à jour de la liste Info. :::::::
                sid.getArrayEtapeInfo().add(listeSize-1, infoNom);
                message.setText("Quelle est votre prenom.");
                return message;

            case 3: //::::::: Demander le contact. :::::::
                //:::::::Récupérer la liste contenant le nom et le prenom. ::::::: 
                ArrayList infoPrenom = (ArrayList) SessionID.listeSession.get(position).getArrayEtapeInfo().get(1); 
                //::::::: Mise à jour de la liste Info. :::::::
                infoPrenom.add(update.getMessage().getText()); 
                sid.getArrayEtapeInfo().add(listeSize, new ArrayList<>()); //neccessaire pour passer au cas size==4
                message.setText("Envoyer nous votre contact.");
                request(message, true, false);
                return message;

            case 4: //::::::: Demander l' activité. :::::::
                //:::::::Récupérer la liste contenant le nom, le prenom et le contact. ::::::: 
                ArrayList infoPersonne = (ArrayList) SessionID.listeSession.get(position).getArrayEtapeInfo().get(1); 
                infoPersonne.add(update.getMessage().getContact()); 
                SessionID.listeSession.get(position).getArrayEtapeInfo().add(new ArrayList<>());
                message.setText("Contact bien reçu.&");
                return message;
        }
        return null;
    }

    public SendMessage saveService(Update update, SendMessage message){
        //....rechercher la position de la session.
        int position = findSession(update);  
        int listSize = SessionID.listeSession.get(position).getArrayEtapeInfo().size();
        SessionID sid =  SessionID.listeSession.get(position);

        switch(listSize){
            case 5: //::::::::::catégorie::::::::::: 
                //Creation d'une insatance de send message.
                //nouvelle instance de personne.
                //::::::::::ici:::::::::::
                message.setText(requestCategorie());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            case 6: //::::::::::activité:::::::::::
                //:::::::::: Verifier si le numero est valide.
                ArrayList infocat = (ArrayList) sid.getArrayEtapeInfo().get(listSize-1);
                infocat.add(update.getMessage().getText());
                message.setText(requestActivite());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 7: //:::::::::: Désignation:::::::::::
                //:::::::::: Verifier si le numero est valide.
                ArrayList infoact = (ArrayList) sid.getArrayEtapeInfo().get(listSize-2);
                infoact.add(update.getMessage().getText());
                message.setText("Quelle est le nom de votre service ?");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 8: //:::::::::: Déscription:::::::::::
                //:::::::::: Verifier le text.
                ArrayList infodes = (ArrayList) sid.getArrayEtapeInfo().get(listSize-3);
                infodes.add(update.getMessage().getText());
                message.setText("Expliquer ce que vous faite.(au plus 20 mots)");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 9: //:::::::::: Ville :::::::::::
                //:::::::::: Verifier le description.
                ArrayList infolocville = (ArrayList) sid.getArrayEtapeInfo().get(listSize-4);
                infolocville.add(update.getMessage().getText());
                message.setText(requestVille());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 10: //:::::::::: quartier :::::::::::
                //:::::::::: Verifier le description.
                ArrayList infolocquart = (ArrayList) sid.getArrayEtapeInfo().get(listSize-5);
                infolocquart.add(update.getMessage().getText());
                message.setText(requestVille());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 13: //:::::::::: Localisation :::::::::::
                //:::::::::: Verifier le text.
                ArrayList infodescr = (ArrayList) sid.getArrayEtapeInfo().get(listSize-4);
                infodescr.add(update.getMessage().getText());
                message.setText("Où se trouve votre service?(localisation).");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                request(message, false, true);
                return message;

            case 14://:::::::::: Fin :::::::::::
                ArrayList infoService = (ArrayList) sid.getArrayEtapeInfo().get(listSize-5);
                infoService.add(update.getMessage().getLocation());
                message.setText("Localisation bien reçut.&Fin");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                request(message, false, true);
                sid.resetEtape();
                sid.resetEtapeInfo();
                return message;
        }
        
        return null;
    }

    public String requestQuartier(){
        String title = "Dans Quelle quartier êtes vous ?&";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }

    public String requestVille(){
        String title = "Dans Quelle ville vous proposé ce service ?&";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }
    
    public String requestCategorie(){
        String title = "Dans quelle catégorie votre activité se place votre activité  ?";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
    }

    public String requestActivite(){
        String title = "Quelle est votre secteur d'activité ?";
        String result = "";
        result += title ;
        //Faire un find all dans categorie.
        for (int i = 1; i < 5 ; i++) {
            result += "\n"+i+". option"+i;
        }
        return result;
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

    public static SendMessage sendInlineKeyBoardMessage(String chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        KeyboardButton kbb = new KeyboardButton("Contact");
        kbb.setRequestContact(true);

        inlineKeyboardButton2.setText("contact");
        inlineKeyboardButton2.setUrl("");
        inlineKeyboardButton2.setCallbackData("90");//request_contact
        
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
       
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sm = new SendMessage();

        sm.setChatId(chatId);
        sm.setText("cliquer ci dessous pour envoyer votre contact.");
        sm.setReplyMarkup(inlineKeyboardMarkup);
        return sm;
       }
    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return "5148846345:AAGTo5uKUOcqhRnMvn1X1gWWdHid4P1pjtE";
    }
}