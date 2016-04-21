package com.example.toshiba.myapplication;

import java.util.List;

public class Commande {

    List<Produit> ListeProduit;
    String etat;
    float totale;


    public Commande() {

    }

    public List<Produit> getListeProduit() {
        return ListeProduit;
    }

    public void setListeProduit(List<Produit> listeProduit) {
        ListeProduit = listeProduit;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
