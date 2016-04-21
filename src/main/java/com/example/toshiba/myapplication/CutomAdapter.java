package com.example.toshiba.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CutomAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Produit> ProduitList;
    // Pour le fitre
    private ValueFilter  valueFilter;
    private List<Produit> mFilterList;
    private List<Produit> listFilter;


    public CutomAdapter(Context context, List<Produit> ProduitList) {
        this.context = context;
        this.ProduitList = ProduitList;
        this.mFilterList = ProduitList;

        // créer une copie de bookList pour le filtre

    }

    public List<Produit> getListFilter() {
        return listFilter;
    }

    public void setListFilter(List<Produit> listFilter) {
        this.listFilter = listFilter;
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

    public ValueFilter getValueFilter() {
        return valueFilter;
    }

    public void setValueFilter(ValueFilter valueFilter) {
        this.valueFilter = valueFilter;
    }

    public List<Produit> getmFilterList() {
        return mFilterList;
    }

    public void setmFilterList(List<Produit> mFilterList) {
        this.mFilterList = mFilterList;
    }

    @Override
    public int getCount() {
        return ProduitList.size();
    }

    @Override
    public Object getItem(int position) {
        return ProduitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.liste_items, null);
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
        // Récupérer la liste des auteurs
        // Séparer la liste des auteurs par une virgule


        return convertView;
    }

    /* Implementation du filtre
       On doit redéfinir la méthode getFilter()
    */
    @Override
    public Filter getFilter() {
        // La méthode retourne un objet de type Filter
        if(valueFilter==null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    // Une nouvelle classe de type Filter est définie
    // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

    private class ValueFilter extends Filter {

        // cette méthode effecute le filtre sur la liste des livres
        // une copie de cette liste mFilterList est utilisée

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
             Produit produit;
              listFilter = new ArrayList<Produit>();
            FilterResults filterResults = new FilterResults();
            // véirifer si le texte n'est pas vide
            if (constraint != null && constraint.length() > 0) {
                for (int i = 0; i < mFilterList.size(); i++) {
                    produit = mFilterList.get(i);
                    // Utiliser le titre et l'éditeur  comme filtre
                    if ((produit.getCategorie().toUpperCase().contains(constraint.toString().toUpperCase()))
                            || (produit.getTypeConso().toUpperCase().contains(constraint.toString().toUpperCase()))) {
                        listFilter.add(produit);
                    }
                }
                filterResults.count = listFilter.size();
                filterResults.values = listFilter;


            } else {
                filterResults.count = mFilterList.size();
                filterResults.values = mFilterList;
            }
            return filterResults;
        }

        // Cette méthode permet d'afficher la nouvelle listView en appelant notifyDataSetChanged()
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ProduitList = (List<Produit>) results.values;
            notifyDataSetChanged();

        }
    }
}
