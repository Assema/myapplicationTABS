package com.example.toshiba.myapplication;

import java.util.List;

public class Vetement extends Produit

{
    private String matiere;
    private List<String> couleurs;
    private List<String> tailles;

    public Vetement() {
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }

    public List<String> getTailles() {
        return tailles;
    }

    public void setTailles(List<String> tailles) {
        this.tailles = tailles;
    }
}
