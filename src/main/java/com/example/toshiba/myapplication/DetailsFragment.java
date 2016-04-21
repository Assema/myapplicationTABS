package com.example.toshiba.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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
            TextView textCategorie = (TextView) v.findViewById(R.id.textCategorie);
            TextView textPrix = (TextView) v.findViewById(R.id.PrixText);
            ((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);
            coverImage.setImageResource(produit.getCover1());
            textTitle.setText( produit.getNom());
            textCategorie.setText(produit.getCategorie());
            

        }


       /* Button ajouter=(Button)v.findViewById(R.id.ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







            }
        });*/


        return v;


    }


}

