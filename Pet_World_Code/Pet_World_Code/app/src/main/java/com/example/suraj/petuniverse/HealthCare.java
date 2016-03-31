package com.example.suraj.petuniverse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthCare extends AppCompatActivity {
    HashMap<String, List<String>> Healthcare_category;
    List<String> Healthcare_list;
    ExpandableListView Exp_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HealthcareAdapter adapter;

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Bundle bundle = getIntent().getExtras();
        String breed = bundle.getString(Constants.BREED);
        String animal = bundle.getString(Constants.ANIMAL);
        String selectedAnimal="";
        if(animal.equals(Constants.DOG))
        {
            selectedAnimal = Constants.DOG;
        }
        else if(animal.equals(Constants.CAT))
        {
            selectedAnimal = Constants.DOG;
        }
        else if(animal.equals(Constants.FISH))
        {
            selectedAnimal = Constants.FISH;
        }
        else if(animal.equals(Constants.BIRD))
        {
            selectedAnimal = Constants.BIRD;
        }

        String JSONText ="" ;
        InputStream ins = getResources().openRawResource(R.raw.healthcare);
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
        Exp_list = (ExpandableListView)findViewById(R.id.exp_list);
        try {
            Healthcare_category = DataProvider.getInfo(JSONText,selectedAnimal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Healthcare_list = new ArrayList<String>(Healthcare_category.keySet());
        adapter = new HealthcareAdapter(this, Healthcare_category,Healthcare_list);
        Exp_list.setAdapter(adapter);
    }

}


