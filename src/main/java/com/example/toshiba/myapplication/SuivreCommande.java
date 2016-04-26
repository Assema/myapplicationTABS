package com.example.toshiba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SuivreCommande extends AppCompatActivity {

    ListView listView;
    CutomAdapter3 cutomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivre_commande);
        listView = (ListView) findViewById(R.id.listView3);
        cutomAdapter = new CutomAdapter3(this, getCommandeList());

        listView.setAdapter(cutomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_commande, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.retour2) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //Toast.makeText(this,"message", Toast.LENGTH_SHORT).show();
        }



        return super.onOptionsItemSelected(item);
    }

    public List<Commande> getCommandeList()
    {

        List<Commande> commandeList = new ArrayList<Commande>();
        Commande commande=new Commande();
        //1er produit
        commande.setNumCmd(1);

        commande.setEtat("En cours");
        commandeList.add(commande);


        commande=new Commande();
        //1er produit
        commande.setNumCmd(2);

        commande.setEtat("En préparation");
        commandeList.add(commande);

        commande=new Commande();
        //1er produit
        commande.setNumCmd(3);

        commande.setEtat("En préparation");
        commandeList.add(commande);

        commande=new Commande();
        //1er produit
        commande.setNumCmd(4);

        commande.setEtat("Envoyée");
        commandeList.add(commande);

        commande=new Commande();
        //1er produit
        commande.setNumCmd(5);

        commande.setEtat("En cours");
        commandeList.add(commande);
        return commandeList;




    }
}
