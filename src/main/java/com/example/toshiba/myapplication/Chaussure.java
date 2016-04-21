package com.example.toshiba.myapplication;

import java.util.List;

public class Chaussure extends Produit{

    private List<String> pointures;
    private List<String> couleurs;

    public Chaussure() {
    }

    public List<String> getPointures() {
        return pointures;
    }

    public void setPointures(List<String> pointures) {
        this.pointures = pointures;
    }

    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }
}
