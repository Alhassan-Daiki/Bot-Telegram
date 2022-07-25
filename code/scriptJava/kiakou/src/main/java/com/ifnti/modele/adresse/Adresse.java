package com.ifnti.modele.adresse;

import com.ifnti.controlleur.Kiakou;

public class Adresse {

    String mNum;

    Quartier mQuartier;
    
    LieuDeReference mLieuDeReference;
    
    public static class AdresseBuilder {

        String mNum;

        Quartier quartier;
        
        LieuDeReference lieuDeReference;

        public AdresseBuilder withQuartier(Quartier pQuartier) {
            this.quartier = pQuartier;
            return this;
        }

        public AdresseBuilder withLieuDeReference(LieuDeReference pLieuDeReference) {
            this.lieuDeReference = pLieuDeReference;
            return this;
        }

        public Adresse build() {
            Adresse adresse = new Adresse();
            adresse.mQuartier = this.quartier;
            adresse.mLieuDeReference = this.lieuDeReference;
            adresse.mNum = Kiakou.adDAO.create(adresse);
            return adresse;
 
        }
    }

    public Adresse(){

    }

    public Adresse(String pNum, Quartier pQuartier, LieuDeReference pLieuDeReference) {
        this.mNum = pNum;
        this.mQuartier = pQuartier;
        this.mLieuDeReference = pLieuDeReference;
    }

    public String getmNum() {
        return mNum;
    }

    public Quartier getMQuartier() {
        return this.mQuartier;
    }

    public LieuDeReference getMLieuDeReference() {
        return this.mLieuDeReference;
    }

    @Override
    public String toString() {
        return "Adresse [lieuDeReference=" + mLieuDeReference + ", mNum=" + mNum + ", quartier=" + mQuartier + "]";
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public Quartier getmQuartier() {
        return mQuartier;
    }

    public void setmQuartier(Quartier mQuartier) {
        this.mQuartier = mQuartier;
    }

    public LieuDeReference getmLieuDeReference() {
        return mLieuDeReference;
    }

    public void setmLieuDeReference(LieuDeReference mLieuDeReference) {
        this.mLieuDeReference = mLieuDeReference;
    }
}
