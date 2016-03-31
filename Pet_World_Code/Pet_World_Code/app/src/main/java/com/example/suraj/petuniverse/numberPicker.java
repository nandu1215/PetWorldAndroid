package com.example.suraj.petuniverse;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

public class numberPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        NumberPicker numberPicker=(NumberPicker) findViewById(R.id.numberPicker);
//        numberPicker.setMaxValue(1000);
//        numberPicker.setMinValue(0);
//        numberPicker.setWrapSelectorWheel(true);
//
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
//
//                Toast.makeText(getApplicationContext(), "Value was: " + Integer.toString(i) + " is now: " + Integer.toString(i2), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    public void alertBox(View view){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.promptpicker, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setTitle("Market Your Pet");


        NumberPicker numberPicker=(NumberPicker) promptView.findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(1500);
        numberPicker.setWrapSelectorWheel(true);

        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 5;
                return "" + temp;
            }
        };
        numberPicker.setFormatter(formatter);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {

                Toast.makeText(getApplicationContext(), "Value was: " + Integer.toString(i) + " is now: " + Integer.toString(i2), Toast.LENGTH_SHORT).show();

            }
        });

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Cancel CLicked", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

//         create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

}
