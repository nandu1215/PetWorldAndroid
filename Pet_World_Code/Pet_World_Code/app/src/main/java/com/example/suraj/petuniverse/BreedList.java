package com.example.suraj.petuniverse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.firebase.client.Firebase;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BreedList extends AppCompatActivity  {

    ArrayList<BreedInfo> breedInfo = new ArrayList<BreedInfo>();
    ImageView imgView;
    ListView breedListView;
    private static final String TAG = "BreedList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String breed = bundle.getString(Constants.BREED);
        String animal = bundle.getString(Constants.ANIMAL);
        if(breed.equals(Constants.BREED))
        {
            String JSONText ="" ;
            InputStream ins = getResources().openRawResource(R.raw.animals_1);
            InputStreamReader reader = new InputStreamReader(ins);
            BufferedReader buffer = new BufferedReader(reader);
            String s = "";
            try {
                while ((s = buffer.readLine()) != null) {
                    JSONText += s;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("BreedList","JSONText:"+JSONText.toString());
            JSONParser parsedData = new JSONParser(JSONText.toString());
            try {
                breedInfo = parsedData.getBreedInfo(animal);
//                Log.e("BreedList", "breedInfo is:"+breedInfo.getBreedName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("BreedList", "JSONParser");
        }
        if(breedInfo.size() > 0)
        {
            breedListView = (ListView) findViewById(R.id.custom_list_view);
            breedListView.setAdapter(new CustomAdapterBreed(getApplicationContext(),R.layout.breed_custom_row,breedInfo));
        }
        breedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: ");
                String breedName = breedInfo.get(i).getBreedName();
                String breedDescription = breedInfo.get(i).getBreedDetails();
                Log.e(TAG, "onItemClick: "+1 );
                Bitmap bmp = breedInfo.get(i).getBreedBmp();
                Log.e(TAG, "onItemClick: "+"BmpLoaded" );

                String filename = "bitmap.png";
                try {
                    //Write file

                    FileOutputStream stream = openFileOutput(filename, Context.MODE_PRIVATE);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    //Cleanup
                    stream.close();
//                    bmp.recycle();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent breedDetails = new Intent(getApplicationContext(),BreedDetails.class);
                breedDetails.putExtra(Constants.BREED_NAME,breedName);
                breedDetails.putExtra(Constants.BREED_DESC,breedDescription);
                breedDetails.putExtra(Constants.BREED_IMAGE,filename);
                startActivity(breedDetails);
            }
        });
//        imgView = (ImageView) findViewById(R.id.imageView8);
//        DownloadImageAsyncTask imageAsyncTask = new DownloadImageAsyncTask(BreedList.this);
//        imageAsyncTask.execute("askdj");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

//    @Override
//    public void getDownloadedImageBitMap(Bitmap bm) {
//        Log.e(TAG, "getDownloadedImageBitMap:Entered " );
//        imgView.setImageBitmap(bm);
//    }

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
