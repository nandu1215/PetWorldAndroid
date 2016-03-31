package com.example.suraj.petuniverse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.firebase.client.Firebase;

public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        WebView browser = (WebView) findViewById(R.id.webView);
//        browser.loadUrl("");
        String frameVideo = "<html>" +
                "<body>Pet Training videos<br><br/>" +
                "<iframe width='320' height='315'' src='https://www.youtube.com/embed/m9KQegi4r8k' frameborder='0' allowfullscreen><br/></iframe>" +
                "<iframe width='320' height='315'' src='https://www.youtube.com/embed/Id0IyTaAuwI' frameborder='0' allowfullscreen><br/></iframe>"+
                "<iframe width='320' height='315'' src='https://www.youtube.com/embed/2aRbsRuhVog' frameborder='0' allowfullscreen><br/></iframe>"+
                "<iframe width='320' height='315'' src='https://www.youtube.com/embed/D4kPZ25IMn0' frameborder='0' allowfullscreen><br/></iframe>"+
                "</body>" +
                "</html>";

        WebView displayVideo = (WebView) findViewById(R.id.webView);
        displayVideo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayVideo.loadData(frameVideo, "text/html", "utf-8");
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


