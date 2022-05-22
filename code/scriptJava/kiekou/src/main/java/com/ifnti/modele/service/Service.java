package com.ifnti.modele.service ;

public class Service {
    private String mDesignation;

    private String mDescription;

    private int mNote;


    public Service() {
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
    
}
