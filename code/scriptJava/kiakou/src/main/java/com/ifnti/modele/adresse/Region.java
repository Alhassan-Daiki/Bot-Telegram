package com.ifnti.modele.adresse ;

public class Region {

    private String mNum;

    private String mNom;

    public Region(String mNom) {
        this.mNom = mNom;
        this.mNum = "";

    }

    public Region(String mNum, String mNom) {
        this.mNum = mNum;
        this.mNom = mNom;
    }

    public String getMNom() {
        return this.mNom;
    }

    public void setMNom(final String pNom) {
        this.mNom = pNom;
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }
}
