package com.example.toshiba.myapplication;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item =menu.findItem(R.id.spinner);
        spinner= (Spinner) item.getActionView();
        String [] values={"Vetements","Chaussures","Accessoires","Sacs","Cosmetiques"};
        ArrayAdapter adapter= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                 categorie= spinner.getSelectedItem().toString();
                System.out.println("la categorie que j recupere est "+categorie);
                if(i==0) {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie,"Homme"));
                    listView.setAdapter(cutomAdapter);
                }
                if(i==1)
                {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie,"Femme"));
                    listView.setAdapter(cutomAdapter);
                }
                if(i==2)
                {
                    cutomAdapter = new CutomAdapter(getActivity(), getListFiltre(getProduitList(), categorie,"Enfant"));
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


            return super.onOptionsItemSelected(item);
        }
    }



    public List<Produit> getListFiltre( List<Produit> liste,String categorie,String Type)

    {
        List<Produit> filtre = new ArrayList<Produit>();
        for (int i = 0; i < liste.size(); i++) {
            if (categorie.toUpperCase().contains(liste.get(i).getCategorie().toUpperCase()) && Type.toUpperCase().contains(liste.get(i).getTypeConso().toUpperCase()) ) {
                filtre.add(liste.get(i));
            }
        }
        return filtre;
    }


    public List<Produit> getProduitList( )
    {
        List<Produit> list=new ArrayList<Produit>();
        List<Produit>  listFiltre=new ArrayList<Produit>();
        ///1er produit
        Vetement v1=new Vetement();
        v1.setCategorie("Vetements");
        v1.setNom("Haut primark");
        v1.setCover1(R.drawable.ic_primark_1225_971062_1_zoom);
        v1.setPrix(3000);
        v1.setTypeConso("Femme");
        list.add(v1);
        ////2eme produi
        Vetement v2=new Vetement();
        v2.setCategorie("Vetements");
        v2.setNom("chemise à carreaux");
        v2.setCover1(R.drawable.ic_autre_1156_289952_1_zoom);
        v2.setPrix(2000);
        v2.setTypeConso("Femme");
        list.add(v2);
        ////3eme produit
        Vetement v3=new Vetement();
        v3.setCategorie("Vetements");
        v3.setNom("Robe Zara");
        v3.setCover1(R.drawable.ic_autre_3065_535344_1_zoom);
        v3.setPrix(4000);
        v3.setTypeConso("Femme");
        list.add(v3);
        //////4eme produit
        Vetement v4=new Vetement();
        v4.setCategorie("Vetements");
        v4.setNom("Veste classique");
        v4.setCover1(R.drawable.ic_autre_8969_543344_1_zoom);
        v4.setPrix(7000);
        v4.setTypeConso("Femme");
        list.add(v4);
        //////5eme produit
        Vetement v5=new Vetement();
        v5.setCategorie("Vetements");
        v5.setNom("Pantalon classique");
        v5.setCover1(R.drawable.ic_autre_9437_781344_1_zoom);
        v5.setPrix(7000);
        v5.setTypeConso("Femme");
        list.add(v5);

        Vetement v6=new Vetement();
        v6.setCategorie("Vetements");
        v6.setNom("Pantalon_Chino_Regular");
        v6.setCover1(R.drawable.ic_pantalon_chino_regular_twill_leger__rose_corail_homme_td569_10_lpr2);
        v6.setPrix(8000);
        v6.setMarque("chino");
        v6.setMatiere("coton");
        v6.setTypeConso("Homme");
        list.add(v6);

        Vetement v7=new Vetement();
        v7.setCategorie("Vetements");
        v7.setNom("Pantalon_Regular_Toucher");
        v7.setCover1(R.drawable.ic_pantalon_regular_toucher_doux_longueur_us_32_noir_homme_dw511_8_pr1);
        v7.setPrix(9560);
        v7.setMarque("ZARA");
        v7.setMatiere("coton");
        v7.setTypeConso("Homme");
        list.add(v7);

        Vetement v8=new Vetement();
        v8.setCategorie("Vetements");
        v8.setNom("Tee_Shirt_Moucheté");
        v8.setCover1(R.drawable.ic_tee_shirt_mouchete_en_jersey__bleu_marine_homme_tq187_3_lpr2);
        v8.setPrix(3400);
        v8.setMarque("chino");
        v8.setMatiere("Jersey");
        v8.setTypeConso("Homme");
        list.add(v8);

        Vetement v9=new Vetement();
        v9.setCategorie("Vetements");
        v9.setNom("Tee_shirt_raye");
        v9.setCover1(R.drawable.ic_tee_shirt_raye_jersey__beige_homme_to319_8_lpr2);
        v9.setPrix(8000);
        v9.setMarque("chino");
        v9.setMatiere("Jersey");
        v9.setTypeConso("Homme");
        list.add(v9);

        Vetement v10=new Vetement();
        v10.setCategorie("Vetements");
        v10.setNom("Tee_shirt_raye");
        v10.setCover1(R.drawable.ic_pantalon_chino_regular_twill_leger__rose_corail_homme_td569_10_lpr2);
        v10.setPrix(8000);
        v10.setMarque("chino");
        v10.setMatiere("Jersey");
        v10.setTypeConso("Homme");
        list.add(v10);




        Vetement v11=new Vetement();
        v11.setCategorie("Vetements");
        v11.setNom("veste_enfant");
        v11.setCover1(R.drawable.ic_324512710_0_pr_1_324512710_e9db7628_ab90_4f4e_83b7_e5c93421d69b);
        v11.setPrix(4000);
        v11.setMarque("Zara kids");
        v11.setMatiere("coton");
        v11.setTypeConso("Enfant");
        list.add(v11);

        Vetement v12=new Vetement();
        v12.setCategorie("Vetements");
        v12.setNom("Veste_petite_fille");
        v12.setCover1(R.drawable.veste_fille);
        v12.setPrix(2000);
        v12.setMarque("Chico");
        v12.setMatiere("Laine");
        v12.setTypeConso("Enfant");
        list.add(v12);

        Vetement v13=new Vetement();
        v13.setCategorie("Vetements");
        v13.setNom("Tee_Shirt_Moucheté");
        v13.setCover1(R.drawable.ic_tee_shirt_mouchete_en_jersey__bleu_marine_homme_tq187_3_lpr2);
        v13.setPrix(3400);
        v13.setMarque("chino");
        v13.setMatiere("Jersey");
        v13.setTypeConso("Enfant");
        list.add(v13);

        Vetement v14=new Vetement();
        v14.setCategorie("Vetements");
        v14.setNom("Tee_shirt_raye");
        v14.setCover1(R.drawable.ic_tee_shirt_raye_jersey__beige_homme_to319_8_lpr2);
        v14.setPrix(8000);
        v14.setMarque("chino");
        v14.setMatiere("Jersey");
        v14.setTypeConso("Enfant");
        list.add(v14);

        Vetement v15=new Vetement();
        v15.setCategorie("Vetements");
        v15.setNom("Tee_shirt_raye");
        v15.setCover1(R.drawable.ic_pantalon_chino_regular_twill_leger__rose_corail_homme_td569_10_lpr2);
        v15.setPrix(8000);
        v15.setMarque("chino");
        v15.setMatiere("Jersey");
        v15.setTypeConso("Enfant");
        list.add(v15);

///////////////////////////////////////////// chaussure//////////////////////////////////////

        Chaussure c1=new Chaussure();
        c1.setCategorie("Chaussures");
        c1.setNom("Chaussure_dain");
        c1.setCover1(R.drawable.ic_chaussure_femme_noire);
        c1.setMarque("Nina");
        c1.setTypeConso("Femme");
        c1.setPrix(3200);
        list.add(c1);

        Chaussure c2=new Chaussure();
        c2.setCategorie("Chaussures");
        c2.setNom("Ballerine");
        c2.setCover1(R.drawable.ic_ballerine_femme_rose);
        c2.setMarque("Nina");
        c2.setTypeConso("Femme");
        c2.setPrix(3000);
        list.add(c2);

        Chaussure c3=new Chaussure();
        c3.setCategorie("Chaussures");
        c3.setNom("Sandales rouges ");
        c3.setCover1(R.drawable.ic_chaussure_femme_rouge);
        c3.setMarque("Mango");
        c3.setTypeConso("Femme");
        c3.setPrix(6000);
        list.add(c3);

        Chaussure c4=new Chaussure();
        c4.setCategorie("Chaussures");
        c4.setNom("Bottines beiges");
        c4.setCover1(R.drawable.ic_bottine_marron_7500);
        c4.setMarque("Bershka");
        c4.setTypeConso("Femme");
        c4.setPrix(7500);
        list.add(c4);

        Chaussure c5=new Chaussure();
        c5.setCategorie("Chaussures");
        c5.setNom("Cavaliere cuir marron");
        c5.setCover1(R.drawable.ic_cavaliere_femme_marron);
        c5.setMarque("Mango");
        c5.setTypeConso("Femme");
        c5.setPrix(11000);
        list.add(c5);



        Chaussure c6=new Chaussure();
        c6.setCategorie("Chaussures");
        c6.setNom("baskette");
        c6.setCover1(R.drawable.ic_chaussure_homme_bleu);
        c6.setMarque("Nike");
        c6.setTypeConso("Homme");
        c6.setPrix(13000);
        list.add(c6);

        Chaussure c7=new Chaussure();
        c7.setCategorie("Chaussures");
        c7.setNom("Chaussure marron");
        c7.setCover1(R.drawable.ic_chaussure_homme_marron);
        c7.setMarque("Marque");
        c7.setTypeConso("Homme");
        c7.setPrix(7000);
        list.add(c7);

        Chaussure c8=new Chaussure();
        c8.setCategorie("Chaussures");
        c8.setNom("Baskette_adidas nouvelle generation ");
        c8.setCover1(R.drawable.ic_baskette_homme_verte);
        c8.setMarque("Adidas");
        c8.setTypeConso("Homme");
        c8.setPrix(20000);
        list.add(c8);

        Chaussure c9=new Chaussure();
        c9.setCategorie("Chaussures");
        c9.setNom("Godasse homme");
        c9.setCover1(R.drawable.ic_godasse_homme);
        c9.setMarque("Hugo");
        c9.setTypeConso("Homme");
        c9.setPrix(15000);
        list.add(c9);

        Chaussure c10=new Chaussure();
        c10.setCategorie("Chaussures");
        c10.setNom("Chaussure classique");
        c10.setCover1(R.drawable.ic_chaussure_homme_grise);
        c10.setMarque("Mongo homme");
        c10.setTypeConso("Homme");
        c10.setPrix(9952);
        list.add(c10);



        Chaussure c11=new Chaussure();
        c11.setCategorie("Chaussures");
        c11.setNom("Chaussure enfant rose");
        c11.setCover1(R.drawable.ic_chaussure_enfant_rose);
        c11.setMarque("Kid's");
        c11.setTypeConso("Enfant");
        c11.setPrix(4500);
        list.add(c11);

        Chaussure c12=new Chaussure();
        c12.setCategorie("Chaussures");
        c12.setNom("Chaussure classique fillette");
        c12.setCover1(R.drawable.ic_chaussure_enfant_blanche);
        c12.setMarque("Zara Kid's");
        c12.setTypeConso("Enfant");
        c12.setPrix(7000);
        list.add(c12);

        Chaussure c13=new Chaussure();
        c13.setCategorie("Chaussures");
        c13.setNom("Baskette_enfant ");
        c13.setCover1(R.drawable.ic_baskette_enfant_bleu);
        c13.setMarque("Adidas");
        c13.setTypeConso("Enfant");
        c13.setPrix(3500);
        list.add(c13);

        Chaussure c14=new Chaussure();
        c14.setCategorie("Chaussures");
        c14.setNom("Adidas pour petite fille");
        c14.setCover1(R.drawable.ic_baskete_enfant_rose);
        c14.setMarque("Adidas");
        c14.setTypeConso("Enfant");
        c14.setPrix(7200);
        list.add(c14);

        Chaussure c15=new Chaussure();
        c15.setCategorie("Chaussures");
        c15.setNom("Converse enfant");
        c15.setCover1(R.drawable.ic_chaussure_homme_grise);
        c15.setMarque("All star");
        c15.setTypeConso("Enfant");
        c15.setPrix(2400);
        list.add(c15);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Accesoires a1=new Accesoires();
        a1.setCategorie("Accessoires");
        a1.setNom("Lunettes femme");
        a1.setCover1(R.drawable.ic_superdry);
        a1.setPrix(9000);
        a1.setMarque("SuperDry");
        a1.setTypeConso("Femme");
        list.add(a1);


        Accesoires a2=new Accesoires();
        a2.setCategorie("Accessoires");
        a2.setNom("Lunettes de solei femme");
        a2.setCover1(R.drawable.ic_persol);
        a2.setPrix(13000);
        a2.setMarque("Persol");
        a2.setTypeConso("Femme");
        list.add(a2);

        Accesoires a3=new Accesoires();
        a3.setCategorie("Accessoires");
        a3.setNom("Bracelet pendantif");
        a3.setCover1(R.drawable.ic_bracelet_pendantif);
        a3.setPrix(4500);
        a3.setMarque("Bvulgari");
        a3.setTypeConso("Femme");
        list.add(a3);

        Accesoires a4=new Accesoires();
        a4.setCategorie("Accessoires");
        a4.setNom("Chapeau été");
        a4.setCover1(R.drawable.ic_chapeau_paille);
        a4.setPrix(2700);
        a4.setMarque("Zara");
        a4.setTypeConso("Femme");
        list.add(a4);

        Accesoires a5=new Accesoires();
        a5.setCategorie("Accessoires");
        a5.setNom("Ceinture cuir");
        a5.setCover1(R.drawable.ic_ceinture_cuir);
        a5.setPrix(4200);
        a5.setMarque("Bershka");
        a5.setTypeConso("Femme");
        list.add(a5);



        Accesoires a6=new Accesoires();
        a6.setCategorie("Accessoires");
        a6.setNom("Ceinture Cuir");
        a6.setCover1(R.drawable.ic_ceinture_homme);
        a6.setPrix(6000);
        a6.setMarque("D&G");
        a6.setTypeConso("Homme");
        list.add(a6);


        Accesoires a7=new Accesoires();
        a7.setCategorie("Accessoires");
        a7.setNom("Lunettes hommes");
        a7.setCover1(R.drawable.ic_lunettes_accessoire_homme);
        a7.setPrix(16000);
        a7.setMarque("Persol");
        a7.setTypeConso("Homme");
        list.add(a7);

        Accesoires a8=new Accesoires();
        a8.setCategorie("Accessoires");
        a8.setNom("Echarppe hiver");
        a8.setCover1(R.drawable.ic_echarppe);
        a8.setPrix(6200);
        a8.setMarque("Celio");
        a8.setTypeConso("Homme");
        list.add(a8);

        Accesoires a9=new Accesoires();
        a9.setCategorie("Accessoires");
        a9.setNom("Bracelet tressé pour homme");
        a9.setCover1(R.drawable.ic_bracelet_homme);
        a9.setPrix(2700);
        a9.setMarque("Zara");
        a9.setTypeConso("Homme");
        list.add(a9);

        Accesoires a10=new Accesoires();
        a10.setCategorie("Accessoires");
        a10.setNom("Portefeuille");
        a10.setCover1(R.drawable.ic_portfeuil_homme);
        a10.setPrix(4200);
        a10.setMarque("Celio");
        a10.setTypeConso("Homme");
        list.add(a10);



        ///////////////////////////////////////////////////////////////////////////////////////////////////////////


        Sacs s1=new Sacs();
        s1.setCategorie("Sacs");
        s1.setNom("Sac été");
        s1.setCover1(R.drawable.ic_sac_beige);
        s1.setPrix(9600);
        s1.setMarque("Zara");
        s1.setTypeConso("Femme");
        list.add(s1);

        Sacs s2=new Sacs();
        s2.setCategorie("Sacs");
        s2.setNom("Sac à dos pour femme");
        s2.setCover1(R.drawable.ic_sac_a__dos__femme);
        s2.setPrix(8200);
        s2.setMarque("Mongo");
        s2.setTypeConso("Femme");
        list.add(s2);

        Sacs s3=new Sacs();
        s3.setCategorie("Sacs");
        s3.setNom("Sac glassé");
        s3.setCover1(R.drawable.ic_sac_glass_femme);
        s3.setPrix(5500);
        s3.setMarque("Zara");
        s3.setTypeConso("Femme");
        list.add(s3);


        Sacs s4=new Sacs();
        s4.setCategorie("Sacs");
        s4.setNom("Pochette pour femme");
        s4.setCover1(R.drawable.ic_sac_noire);
        s4.setPrix(3700);
        s4.setMarque("Zara");
        s4.setTypeConso("Femme");
        list.add(s4);


        Sacs s5=new Sacs();
        s5.setCategorie("Sacs");
        s5.setNom("Sac cartable pour femme");
        s5.setCover1(R.drawable.ic_sac_cartable_femme);
        s5.setPrix(11000);
        s5.setMarque("Bershka");
        s5.setTypeConso("Femme");
        list.add(s5);


        Sacs s6=new Sacs();
        s6.setCategorie("Sacs");
        s6.setNom("Cartable aventurier pour homme");
        s6.setCover1(R.drawable.ic_sac_homme1);
        s6.setPrix(8000);
        s6.setMarque("Zara homme");
        s6.setTypeConso("Homme");
        list.add(s6);

        Sacs s7=new Sacs();
        s7.setCategorie("Sacs");
        s7.setNom("Sac cartable pour homme");
        s7.setCover1(R.drawable.ic_sac_homme2);
        s7.setPrix(7600);
        s7.setMarque("Zara homme");
        s7.setTypeConso("Homme");
        list.add(s7);


        Sacs s8=new Sacs();
        s8.setCategorie("Sacs");
        s8.setNom("Sac aventurier");
        s8.setCover1(R.drawable.ic_sac_homme3);
        s8.setPrix(6000);
        s8.setMarque("Zara homme");
        s8.setTypeConso("Homme");
        list.add(s8);


        Sacs s9=new Sacs();
        s9.setCategorie("Sacs");
        s9.setNom("Sac cuir pour homme");
        s9.setCover1(R.drawable.ic_sac_homme4);
        s9.setPrix(12300);
        s9.setMarque("Zara homme");
        s9.setTypeConso("Homme");
        list.add(s9);


        Sacs s10=new Sacs();
        s10.setCategorie("Sacs");
        s10.setNom("Sac à dos ");
        s10.setCover1(R.drawable.ic_sac_homme5);
        s10.setPrix(8200);
        s10.setMarque("Zara homme");
        s10.setTypeConso("Homme");
        list.add(s10);

        Sacs s11=new Sacs();
        s11.setCategorie("Sacs");
        s11.setNom("Sac pailles été");
        s11.setCover1(R.drawable.ic_sac_fille1);
        s11.setPrix(3000);
        s11.setMarque("Zara");
        s11.setTypeConso("Enfant");
        list.add(s11);

        Sacs s12=new Sacs();
        s12.setCategorie("Sacs");
        s12.setNom("Sac à dos barbie");
        s12.setCover1(R.drawable.ic_sac_fille3);
        s12.setPrix(2600);
        s12.setMarque("Nina");
        s12.setTypeConso("Enfant");
        list.add(s12);


        Sacs s13=new Sacs();
        s13.setCategorie("Sacs");
        s13.setNom("Cartable à fleurs ");
        s13.setCover1(R.drawable.ic_sac_enfant_4);
        s13.setPrix(3800);
        s13.setMarque("Zara Kids");
        s13.setTypeConso("Enfant");
        list.add(s13);

        Sacs s14=new Sacs();
        s14.setCategorie("Sacs");
        s14.setNom("Pochette Fulla");
        s14.setCover1(R.drawable.ic_sac_enfant5);
        s14.setPrix(2200);
        s14.setMarque("Zara Kids");
        s14.setTypeConso("Enfant");
        list.add(s14);


        Sacs s15=new Sacs();
        s15.setCategorie("Sacs");
        s15.setNom("Cartable écolière");
        s15.setCover1(R.drawable.ic_sac_fille2);
        s15.setPrix(4300);
        s15.setMarque("Zara Kids");
        s15.setTypeConso("Enfant");
        list.add(s15);


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
