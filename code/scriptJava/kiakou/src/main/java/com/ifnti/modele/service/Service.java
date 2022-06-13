package com.ifnti.modele.service ;

import java.util.ArrayList;

public class Service {
    private String mDesignation;

    private String mDescription;

    private ArrayList<Integer> mNotes;

    private int mMeanNote;

    private Personne mPersonne;

    public class ServiceBuilder{
        private String mDesignation;
        private String mDescription;
        private Personne mPersonne;
        
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

        public Service build(){
            Service service = new Service();
            service.mDesignation = mDesignation;
            service.mDescription = mDescription;
            service.mPersonne = mPersonne;
            return service;
        }
    }


    public Personne getmPersonne() {
        return mPersonne;
    }

    public void setmPersonne(Personne pPersonne) {
        this.mPersonne = pPersonne;
    }

    public String getMDesignation() {
        return this.mDesignation;
    }

    public void setMDesignation(String pDesignation) {
        this.mDesignation = pDesignation;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String pDescription) {
        this.mDescription = pDescription;
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
