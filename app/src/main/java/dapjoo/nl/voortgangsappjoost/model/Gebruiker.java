package dapjoo.nl.voortgangsappjoost.model;

import java.io.Serializable;

public class Gebruiker implements Serializable {

    private String naam;
    private int leeftijd;

    public Gebruiker(String naam, int leeftijd) {
        this.naam = naam;
        this.leeftijd = leeftijd;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

}
