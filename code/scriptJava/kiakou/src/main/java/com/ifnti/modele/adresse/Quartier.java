package com.ifnti.modele.adresse ;

public class Quartier {

    private String mNum;

    private String mNom;

    private Ville mVille;


    public Quartier(String mNom, Ville mVille) {
        this.mNom = mNom;
        this.mVille = mVille;
        this.mNum = "";
    }

    public Quartier(String mNum, String mNom, Ville mVille) {
        this.mNum = mNum;
        this.mNom = mNom;
        this.mVille = mVille;
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

    public Ville getMVille() {
        return mVille;
    }

    public void setMVille(Ville mVille) {
        this.mVille = mVille;
    }

    @Override
    public String toString() {
        return "Quartier [mNom=" + mNom + ", mNum=" + mNum + ", mVille=" + mVille + "]";
    }

}
