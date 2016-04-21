package com.example.toshiba.myapplication;

import java.io.Serializable;

/**
 * Created by TOSHIBA on 27/03/2016.
 */
public class Produit implements Serializable
{
    private String Nom ;
    private String marque;
    private String categorie;
    private String TypeConso;
    private float prix;
    private int cover1 ;
    private int cover2;

    public Produit() {
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTypeConso() {
        return TypeConso;
    }

    public void setTypeConso(String typeConso) {
        TypeConso = typeConso;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }



    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCover1() {
        return cover1;
    }

    public void setCover1(int cover1) {
        this.cover1 = cover1;
    }

    public int getCover2() {
        return cover2;
    }

    public void setCover2(int cover2) {
        this.cover2 = cover2;
    }
}
