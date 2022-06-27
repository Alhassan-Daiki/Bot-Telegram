package com.ifnti.modele.service ;

import java.util.ArrayList;

import com.ifnti.modele.adresse.Adresse;
import com.ifnti.modele.categorie.Activite;

public class Service {

    private String mNum;

    private String mDesignation;

    private String mDescription;

    private ArrayList<Integer> mNotes;

    private int mMeanNote;

    private Personne mPersonne;

    private Activite mActivite;

    private Adresse mAdresse;

    public Service() {
    }

    public Service(String mNum, String mDesignation, String mDescription, Personne mPersonne, Activite mActivite,
            Adresse mAdresse) {
        this.mNum = mNum;
        this.mDesignation = mDesignation;
        this.mDescription = mDescription;
        this.mPersonne = mPersonne;
        this.mActivite = mActivite;
        this.mAdresse = mAdresse;
    }

    public static class ServiceBuilder{
        private String mDesignation = "indefinie";
        private String mDescription = "indefinie";
        private Personne mPersonne = null;
        private Activite mActivite = null;
        private Adresse mAdresse = null;
        
        public ServiceBuilder withDescription(String pDesignation){
            this.mDesignation = pDesignation;
            return this;
        }
        public ServiceBuilder withDesignation(String pDescription){
            this.mDescription = pDescription;
            return this;
        }

        public ServiceBuilder withPersonne(Personne pPersonne){
            this.mPersonne = pPersonne;
            return this;
        }

        public ServiceBuilder withActivite(Activite pActivite){
            this.mActivite = pActivite;
            return this;
        }

        public ServiceBuilder withAdresse(Adresse pAdresse){
            this.mAdresse = pAdresse;
            return this;
        }

        public Service build(){
            Service service = new Service();
            service.mDesignation = mDesignation;
            service.mDescription = mDescription;
            service.mPersonne = mPersonne;
            service.mActivite = mActivite;
            service.mAdresse = mAdresse;
            return service;
        }
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }

    public String getMDesignation() {
        return mDesignation;
    }

    public void setMDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

    public String getMDescription() {
        return mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Personne getMPersonne() {
        return mPersonne;
    }

    public void setMPersonne(Personne mPersonne) {
        this.mPersonne = mPersonne;
    }


    public Activite getMActivite() {
        return mActivite;
    }

    public void setMActivite(Activite mActivite) {
        this.mActivite = mActivite;
    }

    public Adresse getMAdresse() {
        return mAdresse;
    }

    public void setMAdresse(Adresse mAdresse) {
        this.mAdresse = mAdresse;
    }

    public ArrayList<Integer> getMNotes() {
        return this.mNotes;
    }

    public void scaleMNote(Integer pNote) {
        //:::::: Nous ne gardons pas toute les note on garde le 42 note .
        if (mNotes.size()>42){
            mNotes.remove(0);
        }
        this.mNotes.add(pNote);
        calculateMeanNote();
    }
    
    public void calculateMeanNote(){
        int mean = 0;
        for (Integer note : mNotes) {
            mean += note;
        }
        mean = mean/mNotes.size();
        this.mMeanNote = mean;
    }

    public int getmMeanNote() {
        return mMeanNote;
    }
}
