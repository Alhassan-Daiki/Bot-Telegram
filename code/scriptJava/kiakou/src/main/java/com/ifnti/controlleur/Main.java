package com.ifnti.controlleur;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.ifnti.modele.service.Personne;

/**
 *
 * @author amk
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new KiakouBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        //:::::::::: Instance de personne.
        /*Personne personne1 = new Personne.PersonneBuilder()
                                        .withFristName("KKKKK")
                                        .withLastName("Ali")
                                        .withContact("9999999")
                                        .build();
        System.out.println(personne1);*/
    }
}
