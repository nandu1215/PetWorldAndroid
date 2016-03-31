package com.example.suraj.petuniverse;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://resplendent-heat-9984.firebaseio.com/");
        ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
        //Timer for a couple of seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        }, 2000);

    }
}

