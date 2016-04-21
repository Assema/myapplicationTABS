package com.example.toshiba.myapplication;

import java.util.List;

public class Sacs extends Produit
{
    private List<String> couleurs;

    public Sacs() {
    }


    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }
}
