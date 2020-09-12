package com.github.johnkeno.gadsleadersboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.google.android.material.snackbar.Snackbar;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_main);
        ImageView imageView = findViewById(R.id.start_screen);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnected(LaunchActivity.this)){
                    Snackbar.make(view,"No Internet Connection. Please check", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                }
                else{
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    finish();
                }


            }

        });
    }

    private boolean isConnected(LaunchActivity launchActivity) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) launchActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);

        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }
}