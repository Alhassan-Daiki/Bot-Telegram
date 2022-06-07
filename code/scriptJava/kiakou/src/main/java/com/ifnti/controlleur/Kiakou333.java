package com.ifnti.controlleur;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Kiakou333 extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // TODO Auto-generated method stub
        System.out.println("\n\nhasMassage= "+update.hasMessage());
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Bonjour");
            
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }/*
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("Hello")){
                    Long chat = update.getMessage().getChatId();
                    SendMessage sm = sendInlineKeyBoardMessage(""+chat);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(update.hasCallbackQuery()){
            try {
                SendMessage sm = new SendMessage();
                sm.setText(update.getCallbackQuery().getData());
                sm.setChatId(""+update.getCallbackQuery().getMessage().getChatId());
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        */
    }

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return "Bot-telegrame-projet";
    }

    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return "5148846345:AAGTo5uKUOcqhRnMvn1X1gWWdHid4P1pjtE";
    }
    
    /**
     * amk : processus d'enregistrement d'une commande.
     */
    public void enregistrerUnePersonne(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Créer une nouvelle insatnce de SendMessage
            SendMessage message = new SendMessage();
            // definir l'id du chat
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Entrer votre nom");
        }
    }
}
