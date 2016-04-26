package com.example.toshiba.myapplication;

import android.content.Context;
import android.util.SparseBooleanArray;
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
    private SparseBooleanArray mSelectedItemsIds;


    public CutomAdapter2(Context context, List<Produit> produitList) {
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        ProduitList = produitList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Produit> getProduitList() {
        return ProduitList;
    }

    public void setProduitList(List<Produit> produitList) {
        ProduitList = produitList;
    }

    public SparseBooleanArray getmSelectedItemsIds() {
        return mSelectedItemsIds;
    }

    public void setmSelectedItemsIds(SparseBooleanArray mSelectedItemsIds) {
        this.mSelectedItemsIds = mSelectedItemsIds;
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


    public void remove(Produit object) {
        ProduitList.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.liste_panier2, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView nom = (TextView) convertView.findViewById(R.id.Nom);
        TextView prix= (TextView) convertView.findViewById(R.id.Prix);
        coverIcon.setImageResource(ProduitList.get(position).getCover1());
        nom.setText(ProduitList.get(position).getNom());
        String pri=Float.toString(ProduitList.get(position).getPrix());
        prix.setText(pri+" DA");


        if(ProduitList.get(position).getCategorie()=="Vetements" || ProduitList.get(position).getCategorie()=="Sacs"|| ProduitList.get(position).getCategorie()=="Chaussures")
        {
            TextView Couleur =(TextView) convertView.findViewById(R.id.editText);
            Couleur.setVisibility(View.VISIBLE);
            TextView coul =(TextView) convertView.findViewById(R.id.Quantity);
            coul.setVisibility(View.VISIBLE);
            coul.setText("Couleur : ");
            Couleur.setText("Rouge");
        }
         if(ProduitList.get(position).getCategorie()=="Vetements")
         {
             TextView Taille =(TextView) convertView.findViewById(R.id.tail);
             Taille.setVisibility(View.VISIBLE);
             TextView Tail=(TextView) convertView.findViewById(R.id.Taille);
             Tail.setVisibility(View.VISIBLE);
             Tail.setText("Taille : ");
             Taille.setText("S");
         }
        if(ProduitList.get(position).getCategorie()=="Chaussures")
        {
            TextView Taille =(TextView) convertView.findViewById(R.id.tail);
            Taille.setVisibility(View.VISIBLE);
            TextView Tail=(TextView) convertView.findViewById(R.id.Taille);
            Tail.setVisibility(View.VISIBLE);
            Tail.setText("Pointure  : ");
            Taille.setText("37");


        }

        return convertView;
    }
}
