package com.ifnti.controlleur;

import java.time.LocalTime;
import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;

public class SessionID {
    static ArrayList<SessionID> listeSession = new ArrayList<SessionID>();
    private ArrayList<ArrayList> listeEtapeInfo = new ArrayList<ArrayList>();
    private Long chatId;
    private User user;
    private Contact contact;
    private int startSession;
    private int endSession;
    private int etape;

    public SessionID(Long chatId, User user) {
        this.chatId = chatId;
        this.setUser(user);
        this.setStartSession();
        this.setEndSession();
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
    
    public ArrayList getEtapeInfo(ArrayList etapeInfos){
        return this.listeEtapeInfo ;
    }

    public void resetEtapeInfo(){
        this.listeEtapeInfo = new ArrayList<ArrayList>(); 
    }
    public void addArrayEtapeInfo(ArrayList etapeInfos){
        this.listeEtapeInfo.add(etapeInfos);
    }

    public ArrayList getArrayEtapeInfo(){
        return this.listeEtapeInfo;
    }

    public boolean sessionActive(){
        //System.out.println("\nDebut: "+this.startSession+" Fin: "+this.endSession);
        if (LocalTime.now().getSecond()<this.endSession){
            return true;
        }
        return false;
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

    public int getEndSession() {
        return this.endSession;
    }

    public void setEndSession() {
        this.endSession = 120;
        this.endSession += this.startSession; 
    }

    public int getStartSession() {
        return this.startSession;
    }

    public void setStartSession() {
        this.startSession = LocalTime.now().getSecond();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
}