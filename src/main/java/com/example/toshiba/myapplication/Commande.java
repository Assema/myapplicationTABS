package com.example.toshiba.myapplication;

import java.util.Date;


public class Commande {
    private int NumCmd;
    private Date DateCmd;
    private String Etat;

    public void setNumCmd(int numCmd) {
        NumCmd = numCmd;
    }

    public void setDateCmd(Date dateCmd) {
        DateCmd = dateCmd;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public int getNumCmd() {

        return NumCmd;
    }

    public Date getDateCmd() {
        return DateCmd;
    }

    public String getEtat() {
        return Etat;
    }
}
