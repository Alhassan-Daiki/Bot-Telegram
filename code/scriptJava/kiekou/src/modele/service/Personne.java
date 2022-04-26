package modele.service ;

public class Personne {
    private String mNom;

    private String mPrenom;

    private String mContact;

    public String getmNom() {
        return mNom;
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
