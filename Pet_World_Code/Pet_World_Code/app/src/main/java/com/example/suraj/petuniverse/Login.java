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

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText loginusername;
    EditText loginpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(),"Please Login",Toast.LENGTH_LONG).show();






    }
    public void Login(View view){


        loginusername=(EditText) findViewById(R.id.EmailLoginID);

        loginpassword=(EditText) findViewById(R.id.PasswordLoginID);

        Firebase ref = new Firebase("https://resplendent-heat-9984.firebaseio.com/");
        ref.authWithPassword(loginusername.getText().toString(), loginpassword.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(getApplicationContext(), "You have been Authenticated", Toast.LENGTH_LONG).show();
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void link(View view){
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }
}