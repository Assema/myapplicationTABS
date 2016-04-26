package com.example.toshiba.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CutomAdapter3 extends BaseAdapter {
    private Context context;
    private List<Commande> commandeList;
    // Pour le fitre

    private List<Commande> mFilterList;


    public CutomAdapter3(Context context, List<Commande> commandeList) {
        this.context = context;
        this.commandeList = commandeList;
        // créer une copie de produitList pour le filtre
        this.mFilterList = commandeList;
    }

    @Override
    public int getCount() {
        return commandeList.size();
    }

    @Override
    public Object getItem(int position) {
        return commandeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<Commande> getListFilter() {
        return commandeList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.lis_item3, null);
        }

        TextView textNumero = (TextView) convertView.findViewById(R.id.numCmd);


        TextView textEtat = (TextView) convertView.findViewById(R.id.etatCmd);
        int numero= commandeList.get(position).getNumCmd();

        textNumero.setText(String.valueOf(numero) );


        textEtat.setText(commandeList.get(position).getEtat());



    /* Implementation du filtre
       On doit redéfinir la méthode getFilter()
    */

        // Une nouvelle classe de type Filter est définie
        // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

        return convertView;
    }
}

