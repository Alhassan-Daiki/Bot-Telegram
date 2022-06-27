package com.ifnti.modele.adresse ;

public class LieuDeReference {

    private String mNum;

    private String mNom;

    private Quartier mQuartier;
    
    public LieuDeReference(String mNom, Quartier mQuartier) {
        this.mNom = mNom;
        this.mQuartier = mQuartier;
        this.mNum = "";

    }

    public LieuDeReference(String mNum, String mNom, Quartier mQuartier) {
        this.mNum = mNum;
        this.mNom = mNom;
        this.mQuartier = mQuartier;
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }

    public Quartier getMQuartier() {
        return mQuartier;
    }

    public void setMQuartier(Quartier mQuartier) {
        this.mQuartier = mQuartier;
    }

    public String getMNom() {
        return this.mNom;
    }

    public void setMNom(final String value) {
        this.mNom = value;
    }

}
