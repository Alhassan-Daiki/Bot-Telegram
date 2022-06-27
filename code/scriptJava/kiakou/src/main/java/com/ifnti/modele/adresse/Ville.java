package com.ifnti.modele.adresse ;

public class Ville {

    private String mNum;

    private String mNom;

    private Region mRegion;

    public Ville(String mNom, Region mRegion) {
        this.mNom = mNom;
        this.mRegion = mRegion;
        this.mNum = "";
    }

    public Ville(String mNum, String mNom, Region mRegion) {
        this.mNum = mNum;
        this.mNom = mNom;
        this.mRegion = mRegion;
    }

    public Region getMRegion() {
        return mRegion;
    }

    public void setMRegion(Region mRegion) {
        this.mRegion = mRegion;
    }

    public String getMNom() {
        return this.mNom;
    }

    public void setMNom(final String value) {
        this.mNom = value;
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }

}
