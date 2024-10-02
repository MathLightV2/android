package com.example.examen;

public class Langage {
    private String nom;
    private String dateUn;
    private String dateDeux;
    private String dateTrois;

    public Langage ( String nom, String dateUn , String dateDeux , String dateTrois){

        this.nom = nom;
        this.dateUn = dateUn;
        this.dateDeux = dateDeux;
        this.dateTrois = dateTrois;

    }

    public String getNom() {
        return nom;
    }

    public String getDateUn() {
        return dateUn;
    }

    public String getDateDeux() {
        return dateDeux;
    }

    public String getDateTrois() {
        return dateTrois;
    }
}
