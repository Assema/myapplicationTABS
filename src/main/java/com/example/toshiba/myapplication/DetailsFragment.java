package com.example.toshiba.myapplication;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by pc on 04/03/2016.
 */
public class DetailsFragment extends Fragment {


    ArrayAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail,null);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Produit produit = (Produit) bundle.getSerializable("produit");
            ImageView coverImage = (ImageView) v.findViewById(R.id.coverImage);
            TextView textPrix = (TextView) v.findViewById(R.id.PrixText);
            TextView textNom = (TextView) v.findViewById(R.id.NomText);


            TextView textView2=(TextView) v.findViewById(R.id.textView2);

            TextView textView3=(TextView) v.findViewById(R.id.textView3);
            ((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);
            coverImage.setImageResource(produit.getCover2());
            textPrix.setText(String.valueOf(produit.getPrix()));
            textNom.setText(produit.getNom());




            Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner3);
            if((!produit.getCategorie().equals("Accessoires")) && (!produit.getCategorie().equals("Cosmetiques")) ) {

                String[] values2 = {"Noir", "Rouge", "Marron", "Bleu", "Vert", "Orange", "Rose"};
                adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, values2);
                spinner2.setAdapter(adapter);
                textView3.setText("COULEUR");
            }
            else
            {
                spinner2.setVisibility(View.INVISIBLE);
            }
            Spinner spinner = (Spinner) v.findViewById(R.id.spinner2);

            if (produit.getCategorie().equals("Vetements")) {

                String[] values = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"};
                adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, values);
                spinner.setAdapter(adapter);

                textView2.setText("TAILLE");
            }
            else if (produit.getCategorie().equals("Chaussures")) {

                String[] values = {"32", "34","36","38","40","42","44"};
                adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, values);
                spinner.setAdapter(adapter);

                textView2.setText("POINTURE");
            }
            else {
                spinner.setVisibility(View.INVISIBLE);


            }



        }

        Button ajouter=(Button)v.findViewById(R.id.Ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "LE PRODUIT A ETE AJOUTE AU PANIER", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }


}
