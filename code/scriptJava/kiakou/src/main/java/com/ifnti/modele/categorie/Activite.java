package com.ifnti.modele.categorie ;

public class Activite {
    
    private String mNum;

    private String mNom;

    private Categorie mCategorie;

    public Activite(String pNom) {
        this.mNom = pNom;
        this.mNum = "";
    }

    public Activite(String pNum, String pNom, Categorie pCategorie) {
        this.mNum = pNum;
        this.mNom = pNom;
        this.mCategorie = pCategorie;
    }

    public Categorie getmCategorie() {
        return mCategorie;
    }

    public void setmCategorie(Categorie mCategorie) {
        this.mCategorie = mCategorie;
    }


    public String getMNom() {
        return this.mNom;
    }

    public void setMNom(final String pNom) {
        this.mNom = pNom;
    }


    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    @Override
    public String toString() {
        return "Activite [mCategorie=" + mCategorie + ", mNom=" + mNom + ", mNum=" + mNum + "]";
    }

}
