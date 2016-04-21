package com.example.toshiba.myapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Details2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Configuration config = getResources().getConfiguration();
        if ((config.orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
                && (config.smallestScreenWidthDp >= 600)) {
            finish();
        } else {
            setContentView(R.layout.activity_details2);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            Produit produit = (Produit) getIntent().getSerializableExtra("produit");
            DetailsFragment detailFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("produit", produit);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);
            ft.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_retour)

        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        if(id==R.id.panier)

        {
            Intent intent=new Intent(this,PanierActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}

