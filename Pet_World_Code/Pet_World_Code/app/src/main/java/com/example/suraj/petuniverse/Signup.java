package com.example.suraj.petuniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class Signup extends AppCompatActivity {

    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(getApplicationContext(),"Please Register",Toast.LENGTH_LONG).show();



    }


    public void Signup(View view){

        username=(EditText) findViewById(R.id.usernameID);

        password=(EditText) findViewById(R.id.passwordID);



        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://resplendent-heat-9984.firebaseio.com/");
        ref.createUser(username.getText().toString(),password.getText().toString() , new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                Toast.makeText(getApplicationContext(),"You have registered",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));


            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }
}