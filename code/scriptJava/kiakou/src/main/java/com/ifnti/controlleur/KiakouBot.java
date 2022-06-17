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

public class KiakouBot extends TelegramLongPollingSessionBot{

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
            SessionID.listeSession.get(position).incrementEtape(); //incrementer l'étape.

            System.out.println(SessionID.listeSession.get(position).getArrayEtapeInfo());
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
        } */
        
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
                ArrayList<String> infoNom = new ArrayList<>(); //:::récupere le nom
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

            case 4: //::::::: Demander l'activité. :::::::
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
                message.setText(Kiakou.requestCategorie());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            case 6: //::::::::::activité:::::::::::
                //:::::::::: Récupérer la catégorie
                ArrayList infocCategorie = (ArrayList) sid.getArrayEtapeInfo().get(listSize-1);
                infocCategorie.add(update.getMessage().getText());
                message.setText(Kiakou.requestActivite());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 7: //:::::::::: Désignation:::::::::::
                //:::::::::: Récupérer l'activité
                ArrayList infocActivite = (ArrayList) sid.getArrayEtapeInfo().get(listSize-2);
                infocActivite.add(update.getMessage().getText());
                message.setText("Quelle est le nom de votre service ?");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 8: //:::::::::: Déscription:::::::::::
                //:::::::::: Récupérer le nom
                ArrayList infoDesignation = (ArrayList) sid.getArrayEtapeInfo().get(listSize-3);
                infoDesignation.add(update.getMessage().getText());
                message.setText("Expliquer ce que vous faite.(au plus 20 mots)");
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 9: //:::::::::: Ville :::::::::::
                //:::::::::: Récupérer la description.
                ArrayList infoDescription = (ArrayList) sid.getArrayEtapeInfo().get(listSize-4);
                infoDescription.add(update.getMessage().getText());
                message.setText(Kiakou.requestVille());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
                return message;
            
            case 10: //:::::::::: quartier :::::::::::
                //:::::::::: Récupérer la ville.
                ArrayList infoVille = (ArrayList) sid.getArrayEtapeInfo().get(listSize-5);
                infoVille.add(update.getMessage().getText());
                message.setText(Kiakou.requestQuartier());
                sid.getArrayEtapeInfo().add(new ArrayList<>());
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
                ArrayList infoLocalisation = (ArrayList) sid.getArrayEtapeInfo().get(listSize-4);
                infoLocalisation.add(update.getMessage().getText());
                String text = "Cliquer sur le bouton en bas pour saisir vos horaire";
                message = sendInlineKeyBoardMessage(""+update.getMessage().getChatId(),"saisir heure","saisirHeure.com", text);
                sid.getArrayEtapeInfo().add(new ArrayList<>());
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
        // TODO Auto-generated method stub
        return "5148846345:AAGTo5uKUOcqhRnMvn1X1gWWdHid4P1pjtE";
    }
}