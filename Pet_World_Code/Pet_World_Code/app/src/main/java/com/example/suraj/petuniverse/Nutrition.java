package com.example.suraj.petuniverse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Nutrition extends AppCompatActivity {
    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        WebView webView = (WebView) findViewById(R.id.webView2);
        webView.getSettings().setJavaScriptEnabled(true);
        Bundle bundle = getIntent().getExtras();
        String breed = bundle.getString(Constants.BREED);
        String animal = bundle.getString(Constants.ANIMAL);
        String selectedAnimal="";
        if(animal.equals(Constants.DOG)){
            webView.loadUrl("https://www.cesarsway.com/dog-care/nutrition/dog-nutrition-a-to-z");
        }
        else if(animal.equals(Constants.CAT)){
            webView.loadUrl("http://www.catinfo.org/");
        }
        else if(animal.equals(Constants.BIRD)){
            webView.loadUrl("http://www.peteducation.com/article.cfm?c=15+1835&aid=2844");
        }
        else if(animal.equals(Constants.FISH)){
            webView.loadUrl("http://www.americanaquariumproducts.com/Quality_Fish_Food.html");
        }
//        webView.loadUrl("http://www.google.com");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    public void home(View view){

        startActivity(new Intent(this,MainActivity.class));

    }
    public void share(View view){

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try Pet World for Android!");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I'm using Pet World for Android and I recommend it. Download the app from Playstore");

        Intent chooserIntent = Intent.createChooser(shareIntent, "Share with");
        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }
    public void logout(View view){
        Firebase ref = new Firebase(Constants.FIREBASE_LINK);
        Firebase.setAndroidContext(this);
        ref.unauth();
        startActivity(new Intent(getApplicationContext(), Splash.class));
    }
}
