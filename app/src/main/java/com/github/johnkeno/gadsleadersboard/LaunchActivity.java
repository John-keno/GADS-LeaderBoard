package com.github.johnkeno.gadsleadersboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_main);
        ImageView imageView = findViewById(R.id.start_screen);

        checkInternetValidity(imageView);
        setLaunchScreen(imageView);








    }

    private void checkInternetValidity(ImageView view) {
        if (!isConnected(LaunchActivity.this)){
            Snackbar.make(view,"No Internet Connection. Click Screen to retry", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view,"No Internet Connection. Click Screen to retry", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    setLaunchScreen((ImageView) view);
                }

            });
        }
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
    private void setLaunchScreen(ImageView view) {
        if(isConnected(LaunchActivity.this)){
            Snackbar.make(view,"Connecting", Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
            Thread thread = new Thread(){
                public void run(){
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {

                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }; thread.start();

        }
        else {
            checkInternetValidity(view);
        }

    }
}