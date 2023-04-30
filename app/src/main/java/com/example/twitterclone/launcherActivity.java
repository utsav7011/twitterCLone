package com.example.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class launcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                PreferenceManager.getDefaultSharedPreferences(launcherActivity.this).edit().putBoolean("isUserLogged", true).apply();
                if (PreferenceManager.getDefaultSharedPreferences(
                        launcherActivity.this).getBoolean("isUserLogged", false)){
                    Intent intent;
                    intent = new Intent(launcherActivity.this, homeActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent;
                    intent = new Intent(launcherActivity.this, MainActivity.class);
                    startActivity(intent);
                }



            }
        }, 1200);

    }
}