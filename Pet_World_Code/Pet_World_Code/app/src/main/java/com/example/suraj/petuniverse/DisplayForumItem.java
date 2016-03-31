package com.example.suraj.petuniverse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class DisplayForumItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView photoView = (ImageView) findViewById(R.id.imageView7);
        TextView description = (TextView) findViewById(R.id.textView6);

        //Get Description of Forun Item and Encode64 Image String
        Bundle bundle = getIntent().getExtras();

        //Decoding the base64 Image to Displayable Image File
        String encodeImage = bundle.getString(Constants.IMAGE);
        byte[] imageAsBytes = Base64.decode(encodeImage, Base64.DEFAULT);
        Bitmap decodedImageBitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        photoView.setImageBitmap(decodedImageBitMap);

        description.setText(bundle.getString(Constants.DESCRIPTION));
//        FloatingActionButton  fab = (FloatingActionButton) findViewById(R.id.fab);
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
