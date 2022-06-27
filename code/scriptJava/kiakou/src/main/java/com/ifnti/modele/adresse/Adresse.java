package com.ifnti.modele.adresse;

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
            this.mNum = "";
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

    public Quartier getQuartier() {
        return this.mQuartier;
    }

    public LieuDeReference getLieuDeReference() {
        return this.mLieuDeReference;
    }

    @Override
    public String toString() {
        return "Adresse [lieuDeReference=" + mLieuDeReference + ", mNum=" + mNum + ", quartier=" + mQuartier + "]";
    }
}
