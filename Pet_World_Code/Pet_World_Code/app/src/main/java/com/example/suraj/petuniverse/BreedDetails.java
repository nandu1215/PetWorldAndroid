package com.example.suraj.petuniverse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.io.FileInputStream;

public class BreedDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent detailsIntent = getIntent();
        TextView breedName = (TextView) findViewById(R.id.textView7);
        TextView breedDesc = (TextView) findViewById(R.id.textView8);
        ImageView breedImage = (ImageView) findViewById(R.id.imageView9);

        breedName.setText(detailsIntent.getExtras().getString(Constants.BREED_NAME));
        breedDesc.setMovementMethod(new ScrollingMovementMethod());
        breedDesc.setText(detailsIntent.getExtras().getString(Constants.BREED_DESC));
        String filename =  detailsIntent.getStringExtra(Constants.BREED_IMAGE);
        Bitmap breedImageBitmap = null;
        try {
            FileInputStream is = this.openFileInput(filename);
            breedImageBitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        breedImage.setImageBitmap(breedImageBitmap);




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
