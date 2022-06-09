package com.ifnti.modele.service ;

import com.ifnti.controlleur.Kiakou;

public class Personne {
    private String mNum;

    private String mNom;

    private String mPrenom;

    private String mContact;

    public Personne(String mNom, String mPrenom, String mContact) {
        this.mNom = mNom;
        this.mPrenom = mPrenom;
        this.mContact = mContact;
        this.mNum = Kiakou.pDao.create(this);
    }



    public String getmNum() {
        return mNum;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmPrenom() {
        return mPrenom;
    }

    public void setmPrenom(String mPrenom) {
        this.mPrenom = mPrenom;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    @Override
    public String toString() {
        return "Personne{" + "mNom=" + mNom + ", mPrenom=" + mPrenom + ", mContact=" + mContact + "}";
    }



}
