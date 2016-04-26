package com.example.toshiba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  ImageView image=(ImageView)findViewById(R.id.imageView);
      //  image.setImageResource(R.drawable.ic_logo2);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_panier, menu);
        final MenuItem notif=menu.findItem(R.id.notification);

        if(notif.getTitle().equals("ON"))
        {
            notif.setIcon(R.drawable.ic_action_social_notifications);
        }
        else
        {
            notif.setIcon(R.drawable.ic_action_social_notifications_off);
        }


        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_retour)

        {
            Intent intent=new Intent(this,PanierActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
