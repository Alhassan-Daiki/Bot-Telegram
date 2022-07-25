package com.ifnti.modele.service ;

import com.ifnti.controlleur.Kiakou;

public class HoraireParJour {
    
    private String jour;
    private Service service;
    private Horaire horaires;

    public HoraireParJour(String jour, Service service, Horaire horaires, String inutile) {
        this.jour = jour;
        this.service = service;
        this.horaires = horaires;
    }

    public HoraireParJour(String jour, Service service, Horaire horaires) {
        this.jour = jour;
        this.service = service;
        this.horaires = horaires;
        Kiakou.hpjDao.create(this);
    }

    public Horaire getHoraires() {
        return horaires;
    }

    public void setHoraires(Horaire horaires) {
        this.horaires = horaires;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getJour() {
        return this.jour;
    }

    public void setJour(String value) {
        this.jour = value;
    }

    @Override
    public String toString() {
        return "HoraireParJour [horaires=" + horaires + ", jour=" + jour + ", service=" + service + "]";
    }

}
