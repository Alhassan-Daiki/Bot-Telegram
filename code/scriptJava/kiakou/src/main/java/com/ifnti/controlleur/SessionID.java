package com.ifnti.controlleur;


import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.objects.Contact;

import org.telegram.telegrambots.meta.api.objects.User;

import com.ifnti.modele.service.Personne.PersonneBuilder;

/*
 * SessionID est une classe qui nous permet
 * de donner une mémoire au bot.
 * :::::Attributs:::::
 * Etape == Où j'en suis dans la discution ?
 * ChatId == Je discute avec qui. 
 */

public class SessionID {
    static ArrayList<SessionID> listeSession = new ArrayList<SessionID>();
    private ArrayList<PersonneBuilder> listeEtapeInfo = new ArrayList<PersonneBuilder>();
    private int[][] listeSousEtape = {{0}, {0}, {0}};

    private Long chatId;
    private User user;
    private Contact contact;
    private int etape;

    public SessionID(Long chatId, User user) {
        this.chatId = chatId;
        this.setUser(user);
        this.etape = 0 ;
        SessionID.listeSession.add(this);
    }

    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public void resetEtape(){
        this.etape = 0;
    }
    public void incrementEtape(){
        this.etape += 1;
    }

    public int getEtape(){
        return this.etape;
    }
    
    public void resetEtapeInfo(){
        this.listeEtapeInfo = new ArrayList<PersonneBuilder>(); 
    }

    public ArrayList<PersonneBuilder> getArrayEtapeInfo(){
        return this.listeEtapeInfo;
    }

    public static int findChatId(Long chatId){
        for (SessionID s : SessionID.listeSession) {
            //System.out.println("s.ch: "+s.chatId+"\n cid: "+chatId);
            if (s.chatId.equals(chatId)){
                //System.out.println("ok");
                return SessionID.listeSession.indexOf(s);
            }
        }
        return -1;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int[][] getListeSousEtape() {
        return listeSousEtape;
    }

    public void setListeSousEtape(int[][] listeSousEtape) {
        this.listeSousEtape = listeSousEtape;
    }
   
}