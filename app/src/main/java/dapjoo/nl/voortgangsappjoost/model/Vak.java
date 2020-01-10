package dapjoo.nl.voortgangsappjoost.model;

import java.io.Serializable;

public class Vak implements Serializable {

    private String naam;
    private float cijfer;
    private boolean behaald;
    private String notitie;
    private boolean keuzevak;
    private int schooljaar;
    private int ec;


    public Vak(String naam, float cijfer, boolean behaald, String notitie, boolean keuzevak, int schooljaar, int ec) {
        this.naam = naam;
        this.cijfer = cijfer;
        this.behaald = behaald;
        this.notitie = notitie;
        this.keuzevak = keuzevak;
        this.schooljaar = schooljaar;
        this.ec = ec;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public float getCijfer() {
        return cijfer;
    }

    public void setCijfer(float cijfer) {
        this.cijfer = cijfer;
    }

    public boolean isBehaald() {
        return behaald;
    }

    public void setBehaald(boolean behaald) {
        this.behaald = behaald;
    }

    public String getNotitie() {
        return notitie;
    }

    public void setNotitie(String notitie) {
        this.notitie = notitie;
    }

    public boolean isKeuzevak() {
        return keuzevak;
    }

    public void setKeuzevak(boolean keuzevak) {
        this.keuzevak = keuzevak;
    }

    public int getSchooljaar() {
        return schooljaar;
    }

    public void setSchooljaar(int schooljaar) {
        this.schooljaar = schooljaar;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }
}