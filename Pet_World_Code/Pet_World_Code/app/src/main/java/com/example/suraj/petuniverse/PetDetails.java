package com.example.suraj.petuniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

public class PetDetails extends AppCompatActivity {
    private static final String TAG = "PetDetails";
    private static String selectedPet = "";
    private ArrayAdapter<String> petDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.custom_list_view);


        Bundle bundle = getIntent().getExtras();
        selectedPet = bundle.getString(Constants.SELECTED_PET);
        Log.e(TAG, "onCreate: Selected Item is before loop :" + selectedPet);
        if(selectedPet.equals(Constants.DOG))
        {
            Log.e(TAG, "onCreate: Selected Item is :Dog" );
            String listOfPetDetailsInfo[] = {"Breeds","Health care","Nutrition","Training"};
            petDetailsList = new ArrayAdapter<String>(this,R.layout.pet_details_custom_row,R.id.rowText,listOfPetDetailsInfo);
            listView.setAdapter(petDetailsList);

        }
        else if(selectedPet.equals(Constants.CAT))
        {
            Log.e(TAG, "onCreate: Selected Item is :Cat" );
            String listOfPetDetailsInfo[] = {"Breeds","Health care","Nutrition","Training"};
            petDetailsList = new ArrayAdapter<String>(this,R.layout.pet_details_custom_row,R.id.rowText,listOfPetDetailsInfo);
            listView.setAdapter(petDetailsList);
        }
        else if(selectedPet.equals(Constants.FISH))
        {
            Log.e(TAG, "onCreate: Selected Item is :Fish" );
            String listOfPetDetailsInfo[] = {"Types","Health care","Nutrition","Training"};
            petDetailsList = new ArrayAdapter<String>(this,R.layout.pet_details_custom_row,R.id.rowText,listOfPetDetailsInfo);
            listView.setAdapter(petDetailsList);
        }
        else if(selectedPet.equals(Constants.BIRD))
        {
            Log.e(TAG, "onCreate: Selected Item is :Bird" );
            String listOfPetDetailsInfo[] = {"Types","Health care","Nutrition","Training"};
            petDetailsList = new ArrayAdapter<String>(this,R.layout.pet_details_custom_row,R.id.rowText,listOfPetDetailsInfo);
            listView.setAdapter(petDetailsList);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onCreate: Selected Item is before loop :" + selectedPet);
                Log.e(TAG, "onCreate: position :" + position);
                Intent petInfoElaborate = new Intent(getApplicationContext(),BreedList.class);
                if(position == 0)
                {
                    petInfoElaborate.putExtra(Constants.BREED,Constants.BREED);
                    petInfoElaborate.putExtra(Constants.ANIMAL,selectedPet);
                    startActivity(petInfoElaborate);
                }
                else if(position == 1)
                {
                    Intent dogHealthIntent = new Intent(getApplicationContext(),HealthCare.class);
                    dogHealthIntent.putExtra(Constants.ANIMAL,selectedPet);
                    startActivity(dogHealthIntent);
                }
                else if(position == 2)
                {
                    Intent nutriIntent = new Intent(getApplicationContext(),Nutrition.class);
                    nutriIntent.putExtra(Constants.ANIMAL,selectedPet);
                    startActivity(nutriIntent);
                }
                else if(position == 3)
                {
                    startActivity(new Intent(getApplicationContext(),VideoPlayActivity.class));
                }
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onCreate: Selected Item is before loop :" + selectedPet);
                Log.e(TAG, "onCreate: position :" + position);
                Intent petInfoElaborate = new Intent(getApplicationContext(),BreedList.class);
                if(position == 0)
                {
                    petInfoElaborate.putExtra(Constants.BREED,Constants.BREED);
                }
                startActivity(petInfoElaborate);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
