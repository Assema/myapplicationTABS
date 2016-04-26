package com.example.toshiba.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by TOSHIBA on 28/03/2016.
 */
public class DetailsFragment extends android.app.Fragment

{
    Commande commande=new Commande();
    Produit produit;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_detail,null);
        Bundle bundle = getArguments();
        if (bundle != null) {
            produit = (Produit) bundle.getSerializable("produit");
            ImageView coverImage = (ImageView) v.findViewById(R.id.coverImage);
            TextView textTitle = (TextView) v.findViewById(R.id.NomText);
            // TextView textCategorie = (TextView) v.findViewById(R.id.textCategorie);
            TextView textPrix = (TextView) v.findViewById(R.id.PrixText);
            //((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);
            coverImage.setImageResource(produit.getCover2());
            textTitle.setText( produit.getNom());
            //textCategorie.setText(produit.getCategorie());

            Spinner taille=(Spinner)v.findViewById(R.id.spinner5);
            String [] values={"XS","S","M","L","XL","XXL","XXXL"};
            ArrayAdapter adapter= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values);
            taille.setAdapter(adapter);
            if(produit.getCategorie()!="Vetements"){taille.setVisibility(v.GONE);
                TextView text1=(TextView)v.findViewById(R.id.textView6);
                text1.setVisibility(v.GONE);
            }

            Spinner pointure=(Spinner)v.findViewById(R.id.spinner6);
            String [] values1={"32","33","34","35","36","37","38","39","40","41","42","43"};
            ArrayAdapter adapter1= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values1);
            pointure.setAdapter(adapter1);
            if(produit.getCategorie()!="Chaussures"){pointure.setVisibility(v.GONE);
                TextView text2=(TextView)v.findViewById(R.id.textView7);
                text2.setVisibility(v.GONE);
            }

            Spinner couleur=(Spinner)v.findViewById(R.id.spinner7);
            String [] values2={"Rouge","Noir","Marron","Vert","Gris","Bleu","Blanc","beige"};
            ArrayAdapter adapter2= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values2);
            couleur.setAdapter(adapter2);
            if((produit.getCategorie()=="Accessoires")|| (produit.getCategorie()=="Cosmetique")){couleur.setVisibility(v.GONE);
                TextView text3=(TextView)v.findViewById(R.id.textView8);
                text3.setVisibility(v.GONE);
            }

            Spinner matiere=(Spinner)v.findViewById(R.id.spinner8);
            String [] values3={"Coton","Satin","Mousline","Toile","","cachemir","jean","veloure","Din"};
            ArrayAdapter adapter3= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,values3);
            matiere.setAdapter(adapter3);
            if(produit.getCategorie()!="Vetements"){matiere.setVisibility(v.GONE);
                TextView text4=(TextView)v.findViewById(R.id.textView9);
                text4.setVisibility(v.GONE);
            }


            ImageButton image=(ImageButton)v.findViewById(R.id.Ajouter);
            image.setImageResource(R.drawable.ic_panier);



        }

        ImageButton ajouter=(ImageButton)v.findViewById(R.id.Ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "LE PRODUIT A ETE AJOUTE AU PANIER", Toast.LENGTH_SHORT).show();

            }
        });



        return v;


    }


    public void ajouter(View v)
    {
        Toast text=new Toast(this.getActivity());
        text.makeText(this.getActivity(),"UN PRODUIT AJOUTE AU PANIER", Toast.LENGTH_SHORT);
        text.show();

    }






}

