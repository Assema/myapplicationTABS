package com.example.toshiba.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CutomAdapter2 extends BaseAdapter
{
    private Context context;
    private List<Produit> ProduitList;


    public CutomAdapter2(Context context, List<Produit> produitList) {
        this.context = context;
        ProduitList = produitList;
    }


    @Override
    public int getCount() {
        return ProduitList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.liste_panier2, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView nom = (TextView) convertView.findViewById(R.id.nom);
        TextView categorie = (TextView) convertView.findViewById(R.id.categorie);
        TextView prix= (TextView) convertView.findViewById(R.id.prix);
        coverIcon.setImageResource(ProduitList.get(position).getCover1());
        nom.setText(ProduitList.get(position).getNom());
        categorie.setText(ProduitList.get(position).getCategorie());

        String pri=Float.toString(ProduitList.get(position).getPrix());
        prix.setText(pri+" DA");
        return convertView;
    }
}
