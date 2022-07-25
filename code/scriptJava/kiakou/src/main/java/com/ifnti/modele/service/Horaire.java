package com.ifnti.modele.service ;

import com.ifnti.controlleur.Kiakou;

public class Horaire {
    private int mHeure_debut_matinee;

    private int mHeure_fin_matinee;

    private int mHeure_debut_soiree;

    private int mHeure_fin_soiree;

    private int mMinute_debut_matinee;

    private int mMinute_fin_matinee;

    private int mMinute_debut_soiree;

    private int mMinute_fin_soiree;

    private int mH24;

    private String mNum;
    

    public Horaire(int mHeure_debut_matinee, int mHeure_fin_matinee, int mHeure_debut_soiree, int mHeure_fin_soiree,
            int mMinute_debut_matinee, int mMinute_fin_matinee, int mMinute_debut_soiree, int mMinute_fin_soiree,
            int mH24) {
        this.mHeure_debut_matinee = mHeure_debut_matinee;
        this.mHeure_fin_matinee = mHeure_fin_matinee;
        this.mHeure_debut_soiree = mHeure_debut_soiree;
        this.mHeure_fin_soiree = mHeure_fin_soiree;
        this.mMinute_debut_matinee = mMinute_debut_matinee;
        this.mMinute_fin_matinee = mMinute_fin_matinee;
        this.mMinute_debut_soiree = mMinute_debut_soiree;
        this.mMinute_fin_soiree = mMinute_fin_soiree;
        this.mH24 = mH24;
        this.mNum = Kiakou.hDao.create(this);
    }

    public Horaire(int mHeure_debut_matinee, int mHeure_fin_matinee, int mHeure_debut_soiree, int mHeure_fin_soiree,
            int mMinute_debut_matinee, int mMinute_fin_matinee, int mMinute_debut_soiree, int mMinute_fin_soiree,
            int mH24, String numHoraire) {
        this.mHeure_debut_matinee = mHeure_debut_matinee;
        this.mHeure_fin_matinee = mHeure_fin_matinee;
        this.mHeure_debut_soiree = mHeure_debut_soiree;
        this.mHeure_fin_soiree = mHeure_fin_soiree;
        this.mMinute_debut_matinee = mMinute_debut_matinee;
        this.mMinute_fin_matinee = mMinute_fin_matinee;
        this.mMinute_debut_soiree = mMinute_debut_soiree;
        this.mMinute_fin_soiree = mMinute_fin_soiree;
        this.mH24 = mH24;
        this.mNum = numHoraire;
    }

    public String getNum() {
        return mNum;
    }

    public void setNum(String numHoraire) {
        this.mNum = numHoraire;
    }

    public int getmH24() {
        return mH24;
    }

    public void setmH24(int mH24) {
        this.mH24 = mH24;
    }

    public int getmHeure_debut_matinee() {
        return mHeure_debut_matinee;
    }

    public void setmHeure_debut_matinee(int mHeure_debut_matinee) {
        this.mHeure_debut_matinee = mHeure_debut_matinee;
    }

    public int getmHeure_fin_matinee() {
        return mHeure_fin_matinee;
    }

    public void setmHeure_fin_matinee(int mHeure_fin_matinee) {
        this.mHeure_fin_matinee = mHeure_fin_matinee;
    }

    public int getmHeure_debut_soiree() {
        return mHeure_debut_soiree;
    }

    public void setmHeure_debut_soiree(int mHeure_debut_soiree) {
        this.mHeure_debut_soiree = mHeure_debut_soiree;
    }

    public int getmHeure_fin_soiree() {
        return mHeure_fin_soiree;
    }

    public void setmHeure_fin_soiree(int mHeure_fin_soiree) {
        this.mHeure_fin_soiree = mHeure_fin_soiree;
    }

    public int getmMinute_debut_matinee() {
        return mMinute_debut_matinee;
    }

    public void setmMinute_debut_matinee(int mMinute_debut_matinee) {
        this.mMinute_debut_matinee = mMinute_debut_matinee;
    }

    public int getmMinute_fin_matinee() {
        return mMinute_fin_matinee;
    }

    public void setmMinute_fin_matinee(int mMinute_fin_matinee) {
        this.mMinute_fin_matinee = mMinute_fin_matinee;
    }

    public int getmMinute_debut_soiree() {
        return mMinute_debut_soiree;
    }

    public void setmMinute_debut_soiree(int mMinute_debut_soiree) {
        this.mMinute_debut_soiree = mMinute_debut_soiree;
    }

    public int getmMinute_fin_soiree() {
        return mMinute_fin_soiree;
    }

    public void setmMinute_fin_soiree(int mMinute_fin_soiree) {
        this.mMinute_fin_soiree = mMinute_fin_soiree;
    }

    @Override
    public String toString() {
        return "Horaire [mH24=" + mH24 + ", mHeure_debut_matinee=" + mHeure_debut_matinee + ", mHeure_debut_soiree="
                + mHeure_debut_soiree + ", mHeure_fin_matinee=" + mHeure_fin_matinee + ", mHeure_fin_soiree="
                + mHeure_fin_soiree + ", mMinute_debut_matinee=" + mMinute_debut_matinee + ", mMinute_debut_soiree="
                + mMinute_debut_soiree + ", mMinute_fin_matinee=" + mMinute_fin_matinee + ", mMinute_fin_soiree="
                + mMinute_fin_soiree + "]";
    }

    
}
