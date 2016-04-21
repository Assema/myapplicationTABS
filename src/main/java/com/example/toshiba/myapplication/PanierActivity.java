package com.example.toshiba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView list=(ListView)findViewById(R.id.listView2);
        CutomAdapter2 cutom=new CutomAdapter2(this,getListePanier());
        list.setAdapter(cutom);
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_panier, menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         int id=item.getItemId();

        if(id==R.id.action_retour)

        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
     return true;
    }


    public List<Produit> getListePanier()
    {
        List<Produit> list=new ArrayList<Produit>();

        Vetement v2=new Vetement();
        v2.setCategorie("Vetements");
        v2.setNom("chemise à carreaux");
        v2.setCover1(R.drawable.ic_autre_1156_289952_1_zoom);
        v2.setPrix(2000);
        v2.setTypeConso("Femme");
        list.add(v2);

        Vetement v11=new Vetement();
        v11.setCategorie("Vetements");
        v11.setNom("veste_enfant");
        v11.setCover1(R.drawable.ic_324512710_0_pr_1_324512710_e9db7628_ab90_4f4e_83b7_e5c93421d69b);
        v11.setPrix(4000);
        v11.setMarque("Zara kids");
        v11.setMatiere("coton");
        v11.setTypeConso("Enfant");
        list.add(v11);

        Chaussure c5=new Chaussure();
        c5.setCategorie("Chaussures");
        c5.setNom("Cavaliere cuir marron");
        c5.setCover1(R.drawable.ic_cavaliere_femme_marron);
        c5.setMarque("Mango");
        c5.setTypeConso("Femme");
        c5.setPrix(11000);
        list.add(c5);

        return list;

    }
}
