package com.ifnti.controlleur;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



public class Main {

    public static void main(String[] args) {
       
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new KiakouBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        //%24h24%24h24%24h24%24h24%24h24%24h24%24h24
        //%24h24%24h24%10:30;14:00%24h24%24h24%24h24%10:30;%14:30
        //%24h24%24h24%24h24%24h24%24h24%7:00;12:00%15:00;12:00
        //Kiakou.saveHour("%24h24%24h24%24h24%24h24%24h24%7:00;12:00%15:00;12:00", Kiakou.sDAO.findById("SER000005"));
        //Kiakou.saveHour("%non%24h24%24h24%24h24%24h24%24h24%24h24%24h24", Kiakou.sDAO.findById("SER000005"));
        //System.out.println(Kiakou.showResultatSeacheService(Kiakou.sDAO.getAll()));
        //Kiakou.sDAO.getAll();
    }
}
