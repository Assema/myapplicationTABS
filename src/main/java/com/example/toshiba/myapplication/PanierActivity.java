package com.example.toshiba.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView totale=(TextView)findViewById(R.id.tot);
        totale.setText("17 000 DA");
        ImageButton image=(ImageButton)findViewById(R.id.imageButton);
        image.setImageResource(R.drawable.ic_icone);
        final ListView list=(ListView)findViewById(R.id.listView2);
        final  CutomAdapter2 cutom=new CutomAdapter2(this,getListePanier());
        list.setAdapter(cutom);
        list.setLongClickable(true);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {



                AlertDialog.Builder alert = new AlertDialog.Builder(
                        PanierActivity.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Etes vous sur de vouloir supprimer cette commande!");
                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        List<Produit> listS=new ArrayList<Produit>();
                        listS=cutom.getProduitList();
                        listS.remove(position);
                        CutomAdapter2 cutom=new CutomAdapter2(PanierActivity.this,listS);
                        list.setAdapter(cutom);
                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_panier, menu);
        final MenuItem notif=menu.findItem(R.id.notification);
        if(notif.getTitle().equals("ON"))
        {
            notif.setIcon(R.drawable.ic_action_social_notifications);
        }
        else
        {
            notif.setIcon(R.drawable.ic_action_social_notifications_off);
        }

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
        if(id==R.id.SuivreCommande)

        {
            Intent intent=new Intent(this,SuivreCommande.class);
            startActivity(intent);
        }
        if(id==R.id.notification)
        {
            if (item.getTitle().equals("ON")) {

                Toast.makeText(this, "Les notifications sont activées ", Toast.LENGTH_SHORT).show();
            }
            else
            {

                Toast.makeText(this, "Les notifications sont désactivées", Toast.LENGTH_SHORT).show();
            }

        }
        return true;
    }



    public  void validerCommande(View v)

    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }



    public List<Produit> getListePanier()
    {
        List<Produit> list=new ArrayList<Produit>();


        ///1er produit
        Vetement v1=new Vetement();
        v1.setCategorie("Vetements");
        v1.setNom("ROBE DE VILLE");
        v1.setCover1(R.drawable.ic_robe_ville);
        v1.setCover2(R.drawable.ic_robe_ville_cover);
        v1.setPrix(3000);
        v1.setTypeConso("Femme");
        list.add(v1);

        Chaussure c7= new Chaussure();
        c7.setCategorie("Chaussures");
        c7.setNom("TIMBERLAND");
        c7.setCover1(R.drawable.ic_timberland);
        c7.setCover2(R.drawable.ic_timberland_cover);
        c7.setMarque("Marque");
        c7.setTypeConso("Homme");
        c7.setPrix(7000);
        list.add(c7);

        Accesoires a2=new Accesoires();
        a2.setCategorie("Accessoires");
        a2.setNom("MONTRE DOREE");
        a2.setCover1(R.drawable.ic_montre_femme_cover);
        a2.setCover2(R.drawable.ic_montre_femme);
        a2.setPrix(13000);
        a2.setMarque("Persol");
        a2.setTypeConso("Femme");
        list.add(a2);

        Accesoires a9 =new Accesoires();
        a9.setCategorie("Accessoires");
        a9.setNom("ECHARPE D'HIVERS");
        a9.setCover1(R.drawable.ic_echarpe_homme);
        a9.setCover2(R.drawable.ic_echarpe_homme);
        a9.setPrix(2700);
        a9.setMarque("Zara");
        a9.setTypeConso("Homme");
        list.add(a9);

        Sacs s4=new Sacs();
        s4.setCategorie("Sacs");
        s4.setNom("SAC A DOS ");
        s4.setCover1(R.drawable.ic_sac_a_dos_femme);
        s4.setCover2(R.drawable.ic_sac_a_dos_femme_cover);
        s4.setPrix(3700);
        s4.setMarque("Zara");
        s4.setTypeConso("Femme");
        list.add(s4);

        return list;


    }
}
