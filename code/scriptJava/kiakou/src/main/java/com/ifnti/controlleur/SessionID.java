package com.ifnti.controlleur;


import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.objects.Contact;

import org.telegram.telegrambots.meta.api.objects.User;


/*
 * SessionID est une classe qui nous permet
 * de donner une mémoire au bot.
 * :::::Attributs:::::
 * Etape == Où j'en suis dans la discution ?
 * ChatId == Je discute avec qui. 
 */

public class SessionID {
    static ArrayList<SessionID> listeSession = new ArrayList<SessionID>();
    private ArrayList<Object> listeEtapeInfo = new ArrayList<Object>();
    
    private int[][] listeSousEtape = {{0}, {0}, {0}, {0}};
    public static int ijour = 0;
    static String sousEtapeInfo = "";
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

    public void resetListeSousEtape(){
        for (int[] elt : this.listeSousEtape){
            elt[0] = 0;
        }
    }
    public int getSousEtapeValue(int position){
        return listeSousEtape[position][0];
    }
    
    public void setSousEtapeValue(int position, int value){
        listeSousEtape[position][0] = value;
        
    }

    public void incrementSousEtape(int position){
        listeSousEtape[position][0]++;
        
    }

    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    
    public void setEtape(int etape){
        this.etape = etape;
    }

    public int getEtape(){
        return this.etape;
    }
    
    public ArrayList<Object> getArrayEtapeInfo(){
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