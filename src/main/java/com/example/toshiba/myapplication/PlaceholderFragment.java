package com.example.toshiba.myapplication;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment

{
    CutomAdapter cutomAdapter ;
    CutomAdapter cutomAdapter2;
    Spinner spinner;
    String categorie;
    ListView listView;
    int i;
    List<Produit> listeH=new ArrayList<Produit>();
    List<Produit> listeF=new ArrayList<Produit>();
    List<Produit> listeE=new ArrayList<Produit>();
    SwitchCompat switchAB;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_main,null); //on récupère le fragment qui existe deja
        listView = (ListView) v.findViewById(R.id.listView);
        setHasOptionsMenu(true);
        Bundle bundle=new Bundle();
        bundle=getArguments();


        cutomAdapter = new CutomAdapter(getActivity(),getProduitList());
        i= bundle.getInt("pos");

        if(i==0)
        {

            cutomAdapter.getFilter().filter("Homme");






        }
        if(i==1)
        {
            cutomAdapter.getFilter().filter("Femme");


        }
        if(i==2)
        {
            cutomAdapter.getFilter().filter("Enfant");


        }

        listView.setAdapter(cutomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showView((Produit) cutomAdapter.getItem(position));
            }
        });
        return v;
    }




    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item =menu.findItem(R.id.spinner);
        spinner= (Spinner) item.getActionView();

        MenuItem item2 =menu.findItem(R.id.myswitch);

        switchAB = (SwitchCompat) menu.findItem(R.id.myswitch)
                .getActionView().findViewById(R.id.switchForActionBar);
        final MenuItem notif=menu.findItem(R.id.notification);
        if(notif.getTitle().equals("ON"))
        {
            notif.setIcon(R.drawable.ic_action_social_notifications);
        }
        else
        {
            notif.setIcon(R.drawable.ic_action_social_notifications_off);
        }




        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getActivity(), "Notifications activées", Toast.LENGTH_SHORT)
                            .show();
                    notif.setIcon(R.drawable.ic_action_social_notifications);
                    notif.setTitle("ON");
                } else {
                    Toast.makeText(getActivity(), "Notifications desactivées", Toast.LENGTH_SHORT)
                            .show();
                    notif.setIcon(R.drawable.ic_action_social_notifications_off);
                    notif.setTitle("OFF");
                }
            }
        });

        String [] values={"Tous","Vetements","Chaussures","Accessoires","Sacs","Cosmetiques"};
        ArrayAdapter adapter= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                categorie = spinner.getSelectedItem().toString();
                System.out.println("la categorie que j recupere est " + categorie);
                if (i == 0) {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie, "Homme"));
                    listView.setAdapter(cutomAdapter);
                }
                if (i == 1) {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie, "Femme"));
                    listView.setAdapter(cutomAdapter);
                }
                if (i == 2) {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie, "Enfant"));
                    listView.setAdapter(cutomAdapter);
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
            {
                return true;
            }

            if(id==R.id.panier)

            {
                Intent intent=new Intent(this.getActivity(),PanierActivity.class);
                startActivity(intent);
            }
            if(id==R.id.notification)
            {

                if (item.getTitle().equals("ON")) {

                    Toast.makeText(getActivity(), "Les notifications sont activées ", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Toast.makeText(getActivity(), "Les notifications sont désactivées", Toast.LENGTH_SHORT).show();
                }


            }

            if(id==R.id.SuivreCommande)
            {
                Intent intent =new Intent(this.getActivity(),SuivreCommande.class);
                startActivity(intent);
            }

            return super.onOptionsItemSelected(item);
        }
    }



    public List<Produit> getListFiltre( List<Produit> liste,String categorie,String Type)

    {

        List<Produit> filtre = new ArrayList<Produit>();
        if(categorie=="Tous")
        {
            for (int i = 0; i < liste.size(); i++)
            {
                if(Type.toUpperCase().contains(liste.get(i).getTypeConso().toUpperCase()))
                {
                    filtre.add(liste.get(i));

                }

            }
            return filtre;
        }
        else {
            for (int i = 0; i < liste.size(); i++) {
                if (categorie.toUpperCase().contains(liste.get(i).getCategorie().toUpperCase()) && Type.toUpperCase().contains(liste.get(i).getTypeConso().toUpperCase())) {
                    filtre.add(liste.get(i));
                }
            }
            return filtre;
        }
    }


    public List<Produit> getProduitList( )
    {
        List<Produit> list=new ArrayList<Produit>();
        List<Produit>  listFiltre=new ArrayList<Produit>();
        ///1er produit
        Vetement v1=new Vetement();
        v1.setCategorie("Vetements");
        v1.setNom("ROBE DE VILLE");
        v1.setCover1(R.drawable.ic_robe_ville);
        v1.setCover2(R.drawable.ic_robe_ville_cover);
        v1.setPrix(3000);
        v1.setTypeConso("Femme");
        list.add(v1);
        ////2eme produi
        Vetement v2=new Vetement();
        v2.setCategorie("Vetements");
        v2.setNom("JUPE PLISSEE");
        v2.setCover1(R.drawable.ic_jupe_pilisse);
        v2.setCover2(R.drawable.ic_jupe_pilisse_cover);
        v2.setPrix(2000);
        v2.setTypeConso("Femme");
        list.add(v2);
        ////3eme produit
        Vetement v3=new Vetement();
        v3.setCategorie("Vetements");
        v3.setNom("CHEMISIER ZARA");
        v3.setCover1(R.drawable.ic_chemisier_zara);
        v3.setCover2(R.drawable.ic_chemisier_zara_cover);
        v3.setPrix(4000);
        v3.setTypeConso("Femme");
        list.add(v3);
        //////4eme produit
        Vetement v4=new Vetement();
        v4.setCategorie("Vetements");
        v4.setNom("PANTALLON CLASSIQUE CAROTTE");
        v4.setCover1(R.drawable.ic_pantallon_classique_carrote);
        v4.setCover2(R.drawable.ic_pantallon_classique_carrote_cover);
        v4.setPrix(7000);
        v4.setTypeConso("Femme");
        list.add(v4);
        //////5eme produit
        Vetement v5=new Vetement();
        v5.setCategorie("Vetements");
        v5.setNom("JEAN BLEU");
        v5.setCover1(R.drawable.ic_jean_bleu);
        v5.setCover2(R.drawable.ic_jean_bleu_cover);
        v5.setPrix(7000);
        v5.setTypeConso("Femme");
        list.add(v5);
//////////////////////////////HOMMES
        Vetement v6 = new Vetement();
        v6.setCategorie("Vetements");
        v6.setNom("PULL EN LAINE");
        v6.setCover1(R.drawable.ic_pull_en_laine);
        v6.setCover2(R.drawable.ic_pull_en_laine_cover);
        v6.setPrix(8000);
        v6.setMarque("chino");
        v6.setMatiere("coton");
        v6.setTypeConso("Homme");
        list.add(v6);

        Vetement v7 = new Vetement();
        v7.setCategorie("Vetements");
        v7.setNom("SWEAT A CAPUCHE");
        v7.setCover1(R.drawable.ic_sweat_a_capuch);
        v7.setCover2(R.drawable.ic_sweat_a_capuche_cover);
        v7.setPrix(9560);
        v7.setMarque("ZARA");
        v7.setMatiere("coton");
        v7.setTypeConso("Homme");
        list.add(v7);

        Vetement v8 = new Vetement();
        v8.setCategorie("Vetements");
        v8.setNom("PANTALLON CLASSIQUE");
        v8.setCover1(R.drawable.ic_pantallon_classique);
        v8.setCover2(R.drawable.ic_pantallon_classique_cover);
        v8.setPrix(3400);
        v8.setMarque("chino");
        v8.setMatiere("Jersey");
        v8.setTypeConso("Homme");
        list.add(v8);

        Vetement v9 = new Vetement();
        v9.setCategorie("Vetements");
        v9.setNom("CHEMISIER A CARREAUX");
        v9.setCover1(R.drawable.ic_chemisier_a_carreaux);
        v9.setCover2(R.drawable.ic_chemisier_a_carreaux_cover);
        v9.setPrix(8000);
        v9.setMarque("chino");
        v9.setMatiere("Jersey");
        v9.setTypeConso("Homme");
        list.add(v9);

        Vetement v10 = new Vetement();
        v10.setCategorie("Vetements");
        v10.setNom("PANTALLON FASHION");
        v10.setCover1(R.drawable.ic_pantallon_fashion);
        v10.setCover2(R.drawable.ic_pantallon_fashion_cover);
        v10.setPrix(8000);
        v10.setMarque("chino");
        v10.setMatiere("Jersey");
        v10.setTypeConso("Homme");
        list.add(v10);
/////////ENAFANTS

        Vetement v11=new Vetement();
        v11.setCategorie("Vetements");
        v11.setNom("ROBE A FLEURS");
        v11.setCover1(R.drawable.ic_robe_a_fleurs_2);
        v11.setCover2(R.drawable.ic_robe_a_fleurs);
        v11.setPrix(4000);
        v11.setMarque("Zara kids");
        v11.setMatiere("coton");
        v11.setTypeConso("Enfant");
        list.add(v11);

        Vetement v12 = new Vetement();
        v12.setCategorie("Vetements");
        v12.setNom("VESTE_PETIT_GARCON");
        v12.setCover1(R.drawable.ic_veste_enfant1);
        v12.setCover2(R.drawable.ic_veste_enfant1_cover);
        v12.setPrix(2000);
        v12.setMarque("Chico");
        v12.setMatiere("Laine");
        v12.setTypeConso("Enfant");
        list.add(v12);

        Vetement v13 = new Vetement();
        v13.setCategorie("Vetements");
        v13.setNom("GILET PETITE FILLE");
        v13.setCover1(R.drawable.ic_gilet_petite_fille);
        v13.setCover2(R.drawable.ic_gilet_petite_fille_cover);
        v13.setPrix(3400);
        v13.setMarque("chino");
        v13.setMatiere("Jersey");
        v13.setTypeConso("Enfant");
        list.add(v13);

        Vetement v14 = new Vetement();
        v14.setCategorie("Vetements");
        v14.setNom("PANTALON PETIT GARCON");
        v14.setCover1(R.drawable.ic_pantalon_garcon);
        v14.setCover2(R.drawable.ic_pantalon_garcon_cover);
        v14.setPrix(8000);
        v14.setMarque("chino");
        v14.setMatiere("Jersey");
        v14.setTypeConso("Enfant");
        list.add(v14);

        Vetement v15 = new Vetement();
        v15.setCategorie("Vetements");
        v15.setNom("JUPE FILLETTE");
        v15.setCover1(R.drawable.ic_jupe_fillette);
        v15.setCover2(R.drawable.ic_jupe_fillette_cover);
        v15.setPrix(8000);
        v15.setMarque("chino");
        v15.setMatiere("Jersey");
        v15.setTypeConso("Enfant");
        list.add(v15);

///////////////////////////////////////////// chaussure//////////////////////////////////////

        Chaussure c1=new Chaussure();
        c1.setCategorie("Chaussures");
        c1.setNom("ESCARPINS");
        c1.setCover1(R.drawable.ic_escarpin);
        c1.setCover2(R.drawable.ic_escarpin_cover);
        c1.setMarque("Nina");
        c1.setTypeConso("Femme");
        c1.setPrix(3200);
        list.add(c1);

        Chaussure c2 = new Chaussure();
        c2.setCategorie("Chaussures");
        c2.setNom("SANDALES ETE");
        c2.setCover1(R.drawable.ic_cendalles_ete);
        c2.setCover2(R.drawable.ic_cendalles_ete_cover);
        c2.setMarque("Nina");
        c2.setTypeConso("Femme");
        c2.setPrix(3000);
        list.add(c2);

        Chaussure c3 = new Chaussure();
        c3.setCategorie("Chaussures");
        c3.setNom("BASKETS NIKE ");
        c3.setCover1(R.drawable.ic_basket_nike);
        c3.setCover2(R.drawable.ic_basket_nike_cover);
        c3.setMarque("NIKE");
        c3.setTypeConso("Femme");
        c3.setPrix(6000);
        list.add(c3);

        Chaussure c4 = new Chaussure();
        c4.setCategorie("Chaussures");
        c4.setNom("VANS ");
        c4.setCover1(R.drawable.ic_vans);
        c4.setCover2(R.drawable.ic_vans_cover);
        c4.setMarque("Bershka");
        c4.setTypeConso("Femme");
        c4.setPrix(7500);
        list.add(c4);

        Chaussure c5 = new Chaussure();
        c5.setCategorie("Chaussures");
        c5.setNom("BALLERINE");
        c5.setCover1(R.drawable.ic_ballerine);
        c5.setCover2(R.drawable.ic_ballerine_cover);
        c5.setMarque("Mango");
        c5.setTypeConso("Femme");
        c5.setPrix(11000);
        list.add(c5);

///////////////////////HOMMES
        Chaussure c6=new Chaussure();
        c6.setCategorie("Chaussures");
        c6.setNom("CHAUSSURES CLASSIQUES");
        c6.setCover1(R.drawable.ic_chaussures_classiques);
        c6.setCover2(R.drawable.ic_chaussures_classiques_cover);
        c6.setMarque("");
        c6.setTypeConso("Homme");
        c6.setPrix(13000);
        list.add(c6);

        Chaussure c7 = new Chaussure();
        c7.setCategorie("Chaussures");
        c7.setNom("TIMBERLAND");
        c7.setCover1(R.drawable.ic_timberland);
        c7.setCover2(R.drawable.ic_timberland_cover);
        c7.setMarque("Marque");
        c7.setTypeConso("Homme");
        c7.setPrix(7000);
        list.add(c7);

        Chaussure c8 = new Chaussure();
        c8.setCategorie("Chaussures");
        c8.setNom("MOCASSIN");
        c8.setCover1(R.drawable.ic_mocassin);
        c8.setCover2(R.drawable.ic_mocassin_cover);
        c8.setMarque("");
        c8.setTypeConso("Homme");
        c8.setPrix(20000);
        list.add(c8);

        Chaussure c9 = new Chaussure();
        c9.setCategorie("Chaussures");
        c9.setNom("VANS");
        c9.setCover1(R.drawable.ic_vans_homme);
        c9.setCover2(R.drawable.ic_vans_homme_cover);
        c9.setMarque("Hugo");
        c9.setTypeConso("Homme");
        c9.setPrix(15000);
        list.add(c9);

        Chaussure c10 =new Chaussure();
        c10.setCategorie("Chaussures");
        c10.setNom("R MAX");
        c10.setCover1(R.drawable.ic_r_max);
        c10.setCover2(R.drawable.ic_r_max_cover);
        c10.setMarque("");
        c10.setTypeConso("Homme");
        c10.setPrix(9952);
        list.add(c10);
///////////////////////////ENFANTS

        Chaussure c11=new Chaussure();
        c11.setCategorie("Chaussures");
        c11.setNom("BOUTILLON FILLETTE");
        c11.setCover1(R.drawable.ic_boutillon_fiellette);
        c11.setCover2(R.drawable.ic_boutillon_fiellette_cover);
        c11.setMarque("Kid's");
        c11.setTypeConso("Enfant");
        c11.setPrix(4500);
        list.add(c11);

        Chaussure c12 =new Chaussure();
        c12.setCategorie("Chaussures");
        c12.setNom("BASKET FILLETTE");
        c12.setCover1(R.drawable.ic_basket_fillette);
        c12.setCover2(R.drawable.ic_basket_fillette_cover);
        c12.setMarque("Zara Kid's");
        c12.setTypeConso("Enfant");
        c12.setPrix(7000);
        list.add(c12);

        Chaussure c13 =new Chaussure();
        c13.setCategorie("Chaussures");
        c13.setNom("SANDALES FILLETTE");
        c13.setCover1(R.drawable.ic_sandales_fillette);
        c13.setCover2(R.drawable.ic_sandales_fillette_cover);
        c13.setMarque("Adidas");
        c13.setTypeConso("Enfant");
        c13.setPrix(3500);
        list.add(c13);

        Chaussure c14 =new Chaussure();
        c14.setCategorie("Chaussures");
        c14.setNom("BASKET PETIT GARCON");
        c14.setCover1(R.drawable.ic_basket_ptit_garcon);
        c14.setCover2(R.drawable.ic_basket_ptit_garcon_cover);
        c14.setMarque("Adidas");
        c14.setTypeConso("Enfant");
        c14.setPrix(7200);
        list.add(c14);

        Chaussure c15 =new Chaussure();
        c15.setCategorie("Chaussures");
        c15.setNom("BALLERINE FILLETTE");
        c15.setCover1(R.drawable.ic_ballerine_fillette);
        c15.setCover2(R.drawable.ic_ballerine_fillette_cover);
        c15.setMarque("All star");
        c15.setTypeConso("Enfant");
        c15.setPrix(2400);
        list.add(c15);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Accesoires a1=new Accesoires();
        a1.setCategorie("Accessoires");
        a1.setNom("BRACELET ARGENT");
        a1.setCover1(R.drawable.ic_bracelet_argent);
        a1.setCover2(R.drawable.ic_bracelet_argent_cover);
        a1.setPrix(9000);
        a1.setMarque("SuperDry");
        a1.setTypeConso("Femme");
        list.add(a1);


        Accesoires a2=new Accesoires();
        a2.setCategorie("Accessoires");
        a2.setNom("MONTRE DOREE");
        a2.setCover1(R.drawable.ic_montre_femme_cover);
        a2.setCover2(R.drawable.ic_montre_femme);
        a2.setPrix(13000);
        a2.setMarque("Persol");
        a2.setTypeConso("Femme");
        list.add(a2);

        Accesoires a3 =new Accesoires();
        a3.setCategorie("Accessoires");
        a3.setNom("ECHARPE BARIOLEE");
        a3.setCover1(R.drawable.ic_echarpe_femme);
        a3.setCover2(R.drawable.ic_echarpe_femme);
        a3.setPrix(4500);
        a3.setMarque("Bvulgari");
        a3.setTypeConso("Femme");
        list.add(a3);

        Accesoires a4 =new Accesoires();
        a4.setCategorie("Accessoires");
        a4.setNom("LUNETTE DE SOLEIL RAY BAN");
        a4.setCover1(R.drawable.ic_lunette_femme);
        a4.setCover2(R.drawable.ic_lunette_femme_cover);
        a4.setPrix(2700);
        a4.setMarque("Zara");
        a4.setTypeConso("Femme");
        list.add(a4);

        Accesoires a5 =new Accesoires();
        a5.setCategorie("Accessoires");
        a5.setNom("CHAPEAU");
        a5.setCover1(R.drawable.ic_chapeau);
        a5.setCover2(R.drawable.ic_chapeau_cover);
        a5.setPrix(4200);
        a5.setMarque("Bershka");
        a5.setTypeConso("Femme");
        list.add(a5);

////////////////////////////HOMMES
        Accesoires a6=new Accesoires();
        a6.setCategorie("Accessoires");
        a6.setNom("CHAPEAU");
        a6.setCover1(R.drawable.ic_chapeau_homme);
        a6.setCover2(R.drawable.ic_chapeau_homme_cover);
        a6.setPrix(6000);
        a6.setMarque("D&G");
        a6.setTypeConso("Homme");
        list.add(a6);


        Accesoires a7=new Accesoires();
        a7.setCategorie("Accessoires");
        a7.setNom("Lunettes hommes");
        a7.setCover1(R.drawable.ic_lunette_homme);
        a7.setCover2(R.drawable.ic_lunette_homme_cover);
        a7.setPrix(16000);
        a7.setMarque("Persol");
        a7.setTypeConso("Homme");
        list.add(a7);

        Accesoires a8 =new Accesoires();
        a8.setCategorie("Accessoires");
        a8.setNom("ceinture en cuir");
        a8.setCover1(R.drawable.ic_ceinture_en_cuir);
        a8.setCover2(R.drawable.ic_ceinture_en_cuir_cover);
        a8.setPrix(6200);
        a8.setMarque("");
        a8.setTypeConso("Homme");
        list.add(a8);

        Accesoires a9 =new Accesoires();
        a9.setCategorie("Accessoires");
        a9.setNom("ECHARPE D'HIVERS");
        a9.setCover1(R.drawable.ic_echarpe_homme);
        a9.setCover2(R.drawable.ic_echarpe_homme);
        a9.setPrix(2700);
        a9.setMarque("Zara");
        a9.setTypeConso("Homme");
        list.add(a9);

        Accesoires a10=new Accesoires();
        a10.setCategorie("Accessoires");
        a10.setNom("BONNET");
        a10.setCover1(R.drawable.ic_bonnet);
        a10.setCover2(R.drawable.ic_bonnet_cover);
        a10.setPrix(4200);
        a10.setMarque("Celio");
        a10.setTypeConso("Homme");
        list.add(a10);
////////////////////////////Enfants

        Accesoires a11=new Accesoires();
        a11.setCategorie("Accessoires");
        a11.setNom("PETITES BARRETTES POUR FILLETTES");
        a11.setCover1(R.drawable.ic_barrette_fillette);
        a11.setCover2(R.drawable.ic_barrette_fillette_cover);
        a11.setPrix(600);
        a11.setMarque("");
        a11.setTypeConso("Enfant");
        list.add(a11);


        Accesoires a12=new Accesoires();
        a12.setCategorie("Accessoires");
        a12.setNom("ELASTIQUE POUR CHEVEUX");
        a12.setCover1(R.drawable.ic_elastique_cheveux);
        a12.setCover2(R.drawable.ic_elastique_cheveux_cover);
        a12.setPrix(160);
        a12.setMarque("");
        a12.setTypeConso("Enfant");
        list.add(a12);

        Accesoires a13 =new Accesoires();
        a13.setCategorie("Accessoires");
        a13.setNom("BANDEAU FILLETTE");
        a13.setCover1(R.drawable.ic_bandeau);
        a13.setCover2(R.drawable.ic_bandeau_cover);
        a13.setPrix(200);
        a13.setMarque("");
        a13.setTypeConso("Enfant");
        list.add(a13);

        Accesoires a14 =new Accesoires();
        a14.setCategorie("Accessoires");
        a14.setNom("BOB PETIT GARCON");
        a14.setCover1(R.drawable.ic_bob);
        a14.setCover2(R.drawable.ic_bob_cover);
        a14.setPrix(2700);
        a14.setMarque("Zara");
        a14.setTypeConso("Enfant");
        list.add(a14);

        Accesoires a15=new Accesoires();
        a15.setCategorie("Accessoires");
        a15.setNom("BRACELETS");
        a15.setCover1(R.drawable.ic_bracelets);
        a15.setCover2(R.drawable.ic_bracelets_cover);
        a15.setPrix(4200);
        a15.setMarque("");
        a15.setTypeConso("Enfant");
        list.add(a15);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////


        Sacs s1=new Sacs();
        s1.setCategorie("Sacs");
        s1.setNom("SAC A MAIN LOUIS VUITTON");
        s1.setCover1(R.drawable.ic_luis_vuitton);
        s1.setCover2(R.drawable.ic_luis_vuitton_cover);
        s1.setPrix(9600);
        s1.setMarque("louis vuitton");
        s1.setTypeConso("Femme");
        list.add(s1);

        Sacs s2=new Sacs();
        s2.setCategorie("Sacs");
        s2.setNom("SAC A MAIN CHANEL");
        s2.setCover1(R.drawable.ic_chanel);
        s2.setCover2(R.drawable.ic_chanel_cover);
        s2.setPrix(8200);
        s2.setMarque("chanel");
        s2.setTypeConso("Femme");
        list.add(s2);

        Sacs s3=new Sacs();
        s3.setCategorie("Sacs");
        s3.setNom("SAC A MAIN MICHAEL KORS");
        s3.setCover1(R.drawable.ic_michael_kors);
        s3.setCover2(R.drawable.ic_michael_kors_cover);
        s3.setPrix(5500);
        s3.setMarque("MICHAEL KORS");
        s3.setTypeConso("Femme");
        list.add(s3);


        Sacs s4=new Sacs();
        s4.setCategorie("Sacs");
        s4.setNom("SAC A DOS ");
        s4.setCover1(R.drawable.ic_sac_a_dos_femme);
        s4.setCover2(R.drawable.ic_sac_a_dos_femme_cover);
        s4.setPrix(3700);
        s4.setMarque("Zara");
        s4.setTypeConso("Femme");
        list.add(s4);


        Sacs s5=new Sacs();
        s5.setCategorie("Sacs");
        s5.setNom("POCHETTE POUR FEMME");
        s5.setCover1(R.drawable.ic_pochette_femme);
        s5.setCover2(R.drawable.ic_pochette_femme_cover);
        s5.setPrix(1100);
        s5.setMarque("Bershka");
        s5.setTypeConso("Femme");
        list.add(s5);
/////////////////////////////////////////HOMMES

        Sacs s6=new Sacs();
        s6.setCategorie("Sacs");
        s6.setNom("CARTABLE DE TRAVAIL");
        s6.setCover1(R.drawable.ic_cartable_homme);
        s6.setCover2(R.drawable.ic_cartable_homme_cover);
        s6.setPrix(8000);
        s6.setMarque("Zara homme");
        s6.setTypeConso("Homme");
        list.add(s6);

        Sacs s7=new Sacs();
        s7.setCategorie("Sacs");
        s7.setNom("POCHETTE HOMME");
        s7.setCover1(R.drawable.ic_pochette_homme);
        s7.setCover2(R.drawable.ic_pochette_homme_cover);
        s7.setPrix(7600);
        s7.setMarque("Zara homme");
        s7.setTypeConso("Homme");
        list.add(s7);


        Sacs s8=new Sacs();
        s8.setCategorie("Sacs");
        s8.setNom("PORTEFEUILLE HOMME");
        s8.setCover1(R.drawable.ic_portfeuille_homme);
        s8.setCover2(R.drawable.ic_portfeuille_homme_cover);
        s8.setPrix(6000);
        s8.setMarque("Zara homme");
        s8.setTypeConso("Homme");
        list.add(s8);


        Sacs s9=new Sacs();
        s9.setCategorie("Sacs");
        s9.setNom("SAC A DOS EN DIN");
        s9.setCover1(R.drawable.ic_sac_a_dos_din_homme);
        s9.setCover2(R.drawable.ic_sac_a_dos_din_homme_cover);
        s9.setPrix(12300);
        s9.setMarque("Zara homme");
        s9.setTypeConso("Homme");
        list.add(s9);


        Sacs s10= new Sacs();
        s10.setCategorie("Sacs");
        s10.setNom("SAC A DOS SPORT ");
        s10.setCover1(R.drawable.ic_sac_a_dos_homme1);
        s10.setCover2(R.drawable.ic_sac_a_dos_homme1_cover);
        s10.setPrix(8200);
        s10.setMarque("Zara homme");
        s10.setTypeConso("Homme");
        list.add(s10);
///////////////////////////////////////ENFANT
        Sacs s11= new Sacs();
        s11.setCategorie("Sacs");
        s11.setNom("CARTABLE ECOLIER");
        s11.setCover1(R.drawable.ic_cartable_ecole);
        s11.setCover2(R.drawable.ic_cartable_ecole_cover);
        s11.setPrix(3000);
        s11.setMarque("Zara");
        s11.setTypeConso("Enfant");
        list.add(s11);

        Sacs s12= new Sacs();
        s12.setCategorie("Sacs");
        s12.setNom("SAC A JOUETS");
        s12.setCover1(R.drawable.ic_sac_a_jouets_);
        s12.setCover2(R.drawable.ic_sac_a_jouets_cover);
        s12.setPrix(2600);
        s12.setMarque("Nina");
        s12.setTypeConso("Enfant");
        list.add(s12);


        Sacs s13 = new Sacs();
        s13.setCategorie("Sacs");
        s13.setNom("SAC EN JEAN ");
        s13.setCover1(R.drawable.ic_sac_en_jean);
        s13.setCover2(R.drawable.ic_sac_en_jean_cover);
        s13.setPrix(3800);
        s13.setMarque("Zara Kids");
        s13.setTypeConso("Enfant");
        list.add(s13);

        Sacs s14= new Sacs();
        s14.setCategorie("Sacs");
        s14.setNom("SAC POUR LE GOUTER");
        s14.setCover1(R.drawable.ic_sac_pour_le_gouter);
        s14.setCover2(R.drawable.ic_sac_pour_le_gouter_cover);
        s14.setPrix(2200);
        s14.setMarque("Zara Kids");
        s14.setTypeConso("Enfant");
        list.add(s14);


        Sacs s15 = new Sacs();
        s15.setCategorie("Sacs");
        s15.setNom("SACOCHE FILLETTE");
        s15.setCover1(R.drawable.ic_sacoche_fillette);
        s15.setCover2(R.drawable.ic_sacoche_fillette_cover);
        s15.setPrix(4300);
        s15.setMarque("Zara Kids");
        s15.setTypeConso("Enfant");
        list.add(s15);
/////////////////////COSMETIQUE

        Cosmetique m1= new Cosmetique();
        m1.setCategorie("COSMETIQUE");
        m1.setNom("CREME BB LIGHT GARNIER");
        m1.setCover1(R.drawable.ic_creme_bb_light_garnier);
        m1.setCover2(R.drawable.ic_creme_bb_light_garnier_cover);
        m1.setPrix(1500);
        m1.setMarque("");
        m1.setTypeConso("Femme");
        list.add(m1);

        Cosmetique m2= new Cosmetique();
        m2.setCategorie("COSMETIQUE");
        m2.setNom("PARFUM POUR FEMME");
        m2.setCover1(R.drawable.ic_parfum1);
        m2.setCover2(R.drawable.ic_parfum1_cover);
        m2.setPrix(3000);
        m2.setMarque("");
        m2.setTypeConso("Femme");
        list.add(m2);


        Cosmetique m3= new Cosmetique();
        m3.setCategorie("COSMETIQUE");
        m3.setNom("PARFUM");
        m3.setCover1(R.drawable.ic_parfum2);
        m3.setCover2(R.drawable.ic_parfum2_cover);
        m3.setPrix(5000);
        m3.setMarque("");
        m3.setTypeConso("Femme");
        list.add(m3);

        Cosmetique m4= new Cosmetique();
        m4.setCategorie("COSMETIQUE");
        m4.setNom("SHAMPOING PANTENE");
        m4.setCover1(R.drawable.ic_shampooing_pantene);
        m4.setCover2(R.drawable.ic_shampooing_pantene_cover);
        m4.setPrix(1000);
        m4.setMarque("");
        m4.setTypeConso("Femme");
        list.add(m4);


        Cosmetique m5= new Cosmetique();
        m5.setCategorie("COSMETIQUE");
        m5.setNom("TEINTURE POUR CHEVEUX");
        m5.setCover1(R.drawable.ic_teinture_cheveux_excellence_loreal);
        m5.setCover2(R.drawable.ic_teinture_cheveux_excellence_loreal_cover);
        m5.setPrix(2000);
        m5.setMarque("Zara");
        m5.setTypeConso("Femme");
        list.add(m5);

        ///////////////HOMMES
        Cosmetique m6= new Cosmetique();
        m6.setCategorie("COSMETIQUE");
        m6.setNom("CREME APRES RASAGE NIVEA");
        m6.setCover1(R.drawable.ic_creme_apres_rasag);
        m6.setCover2(R.drawable.ic_creme_apres_rasage_cover);
        m6.setPrix(3000);
        m6.setMarque("");
        m6.setTypeConso("Homme");
        list.add(m6);

        Cosmetique m7= new Cosmetique();
        m7.setCategorie("COSMETIQUE");
        m7.setNom("DEODORANT AXE");
        m7.setCover1(R.drawable.ic_deodorant_axe);
        m7.setCover2(R.drawable.ic_deodorant_axe_cover);
        m7.setPrix(1000);
        m7.setMarque("Zara");
        m7.setTypeConso("Homme");
        list.add(m7);

        Cosmetique m8= new Cosmetique();
        m8.setCategorie("COSMETIQUE");
        m8.setNom("PARFUM HOGO BOSS");
        m8.setCover1(R.drawable.ic_parfum_hugo_boss);
        m8.setCover2(R.drawable.ic_parfum_hugo_boss_cover);
        m8.setPrix(7000);
        m8.setMarque("");
        m8.setTypeConso("Homme");
        list.add(m8);

        Cosmetique m9= new Cosmetique();
        m9.setCategorie("COSMETIQUE");
        m9.setNom("PARFUM LACOST");
        m9.setCover1(R.drawable.ic_parfum_lacost);
        m9.setCover2(R.drawable.ic_parfum_lacost_cover);
        m9.setPrix(8000);
        m9.setMarque("Zara");
        m9.setTypeConso("Homme");
        list.add(m9);

        Cosmetique m10= new Cosmetique();
        m10.setCategorie("COSMETIQUE");
        m10.setNom("SHAMPOING HOMME ELSEVE");
        m10.setCover1(R.drawable.ic_shampooing_homme_elseve);
        m10.setCover2(R.drawable.ic_shampooing_homme_elseve_cover);
        m10.setPrix(3000);
        m10.setMarque("Zara");
        m10.setTypeConso("Homme");
        list.add(m10);

///////////////////////////////////////////ENFANT
        Cosmetique m11= new Cosmetique();
        m11.setCategorie("COSMETIQUE");
        m11.setNom("EAU DE TOILETTE BEBE");
        m11.setCover1(R.drawable.ic_eau_de_toilette_bb);
        m11.setCover2(R.drawable.ic_eau_de_toilette_bb_cover);
        m11.setPrix(1000);
        m11.setMarque("");
        m11.setTypeConso("Enfant");
        list.add(m11);

        Cosmetique m12= new Cosmetique();
        m12.setCategorie("COSMETIQUE");
        m12.setNom("EAU DE TOILETTE CATTIER");
        m12.setCover1(R.drawable.ic_eau_de_toilette_cattier_cover);
        m12.setCover2(R.drawable.ic_eau_de_toilette_cattier);
        m12.setPrix(3000);
        m12.setMarque("");
        m12.setTypeConso("Enfant");
        list.add(m12);

        Cosmetique m13= new Cosmetique();
        m13.setCategorie("COSMETIQUE");
        m13.setNom("LAIT HYDRATANT NIVEA");
        m13.setCover1(R.drawable.ic_lait_hydratant_nivea);
        m13.setCover2(R.drawable.ic_lait_hydratant_nivea_cover);
        m13.setPrix(2000);
        m13.setMarque("Zara");
        m13.setTypeConso("Enfant");
        list.add(m13);

        Cosmetique m14= new Cosmetique();
        m14.setCategorie("COSMETIQUE");
        m14.setNom("SHAMPOING BEBE MIXA");
        m14.setCover1(R.drawable.ic_shampoing_bebe_mixa);
        m14.setCover2(R.drawable.ic_shampoing_bebe_mixa_cover);
        m14.setPrix(2300);
        m14.setMarque("");
        m14.setTypeConso("Enfant");
        list.add(m14);

        Cosmetique m15= new Cosmetique();
        m15.setCategorie("COSMETIQUE");
        m15.setNom("TALC POUR BEBE CADUM");
        m15.setCover1(R.drawable.ic_talc_bb_cadum);
        m15.setCover2(R.drawable.ic_talc_bb_cadum_cover);
        m15.setPrix(3000);
        m15.setMarque("Zara");
        m15.setTypeConso("Enfant");
        list.add(m15);

        return list;
    }

    public boolean isTwoPane() {

        View v  = getActivity().findViewById(R.id.frameLayout);
        return (v!=null);

    }

    public void showView (Produit produit) {
        if (isTwoPane()) {
            DetailsFragment detailFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("produit", produit);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);

            ft.commit();

        } else {
            Intent intent = new Intent(getActivity(), Details2Activity.class);
            intent.putExtra("produit", produit);
            startActivity(intent);
        }

    }
}
