package com.ifnti.controlleur;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.ifnti.modele.categorie.*;

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
        
        
        //System.out.println(Kiakou.requestTownByRegion("REG000001"));
    }
}
