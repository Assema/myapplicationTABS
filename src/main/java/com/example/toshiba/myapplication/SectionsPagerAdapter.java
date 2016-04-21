package com.example.toshiba.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by TOSHIBA on 16/03/2016.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter // une classe abstraite qui nous permet de lancer le fragment, on recupere la position au niveau de l'activité grace a un BUNDLE

{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) //elle va lancer le fragment et lui donner la position ,le fragment va recuperer la position a partir du bundle et afficher le textview en fonction de la position
    {

        PlaceholderFragment placeholderFragment=new PlaceholderFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("pos",position); //mettre la position du fragment dans le bundle
        placeholderFragment.setArguments(bundle); //passer le bundle au fragment
        return placeholderFragment;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOMME";
            case 1:
                return "FEMME";
            case 2:
                return "ENFANT";
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
