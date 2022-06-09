package com.ifnti.modele.service;

import com.ifnti.controlleur.Kiakou;

public class Service {

    private String mNum;
    private String mDesignation;
    private String mDescription;
    private int mNote;
    private Personne mPersonne;
    
    public Service(String pDesignation, String pDescription) {
        this.mDesignation = pDesignation;
        this.mDescription = pDescription;
        this.mNum = Kiakou.sDao.create(this);
    }
     

    public Personne getmPersonne() {
        return mPersonne;
    }


    public void setmPersonne(Personne mPersonne) {
        this.mPersonne = mPersonne;
    }


    public String getMDesignation() {
        return this.mDesignation;
    }

    public void setMDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getMNote() {
        return this.mNote;
    }

    public void setMNote(int mNote) {
        this.mNote = mNote;
    }
    
    public String toString(){
        return "une instance";
    }


    public String getmNum() {
        return mNum;
    }
}
