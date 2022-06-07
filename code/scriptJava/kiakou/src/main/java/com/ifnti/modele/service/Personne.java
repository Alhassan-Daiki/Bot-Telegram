package com.ifnti.modele.service ;

public class Personne {
    
    private String mNum;

    private String mNom;

    private String mPrenom;

    private String mContact;

    public Personne(String pNom, String pPrenom, String pContact){
        this.mNom = pNom;
        this.mPrenom = pPrenom;
        this.mContact = pContact;
    }

    public String getmNom() {
        return mNom;
    }

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
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
        return "Personne{" + "mNom=" + mNom + ", mPrenom=" + mPrenom + ", mContact=" + mContact + '}';
    }

}
