package com.ifnti.modele.service ;

import com.ifnti.controlleur.Kiakou;

public class Personne {
    
    private String mNum;

    private String mNom;

    private String mPrenom;
    
    private String mContact;

    public static class PersonneBuilder {
        private String mNum = "indefinie";
        private String mNom = "indefinie";
        private String mPrenom = "indefinie";
        private String mContact = "indefinie";

        public PersonneBuilder withFristName(String pNom){
            this.mNom = pNom;
            return this;
        }

        public PersonneBuilder withLastName(String pPrenom){
            this.mPrenom = pPrenom;
            return this;
        }

        public PersonneBuilder withContact(String pContact){
            this.mContact = pContact;
            return this;
        }

        public Personne build(){
            Personne personne = new Personne();
            personne.mNom = mNom;
            personne.mPrenom = mPrenom;
            personne.mContact = mContact;
            personne.mNum = Kiakou.pDAO.create(personne);
            return personne;
        }
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
