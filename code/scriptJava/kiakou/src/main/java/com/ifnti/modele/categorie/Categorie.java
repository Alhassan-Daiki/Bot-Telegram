package com.ifnti.modele.categorie ;

public class Categorie {

    private String mNum;

    private String mNom;

    public Categorie(String mNom) {
        this.mNom = mNom;
        this.mNum = "";

    }

    public Categorie(String mNum, String mNom) {
        this.mNum = mNum;
        this.mNom = mNom;
    }

    public void setMNom(final String value) {
        this.mNom = value;
    }

    public String getMNom() {
        return this.mNom;
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }

    @Override
    public String toString() {
        return "Categorie [mNom=" + mNom + ", mNum=" + mNum + "]";
    }

}
