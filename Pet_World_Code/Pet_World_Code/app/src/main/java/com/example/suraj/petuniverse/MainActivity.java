package com.example.suraj.petuniverse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.app.NavUtils;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Getting The Resources
    Resources res ;
    //Using Instance of roundImage class
    RoundImage r;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;



    String choice="Hybrid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Making Images Roind-------------------------------------------
        //Getting The Resources
        res = getResources();
        //Using Instance of roundImage class
        r= new RoundImage();
//        //Getting The Resources
//        Resources res = getResources();
//        //Using Instance of roundImage class
//        RoundImage r= new RoundImage();
        //Setting Images with rounded Images
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView2.setImageDrawable(r.circleImage(res, R.mipmap.dogss));

        //On Selection goes to a common page for all breeds
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petDetailsIntent = new Intent(getApplicationContext(),PetDetails.class);
                petDetailsIntent.putExtra(Constants.SELECTED_PET,Constants.DOG);
                startActivity(petDetailsIntent);
            }
        });


        imageView3=(ImageView) findViewById(R.id.imageView3);
        imageView3.setImageDrawable(r.circleImage(res, R.mipmap.cat));

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petDetailsIntent = new Intent(getApplicationContext(),PetDetails.class);
                petDetailsIntent.putExtra(Constants.SELECTED_PET,Constants.CAT);
                startActivity(petDetailsIntent);
            }
        });

        imageView4=(ImageView) findViewById(R.id.imageView4);
        imageView4.setImageDrawable(r.circleImage(res, R.mipmap.fish));

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petDetailsIntent = new Intent(getApplicationContext(),PetDetails.class);
                petDetailsIntent.putExtra(Constants.SELECTED_PET,Constants.FISH);
                startActivity(petDetailsIntent);
            }
        });

        imageView5=(ImageView) findViewById(R.id.imageView5);
        imageView5.setImageDrawable(r.circleImage(res, R.mipmap.bird));

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petDetailsIntent = new Intent(getApplicationContext(),PetDetails.class);
                petDetailsIntent.putExtra(Constants.SELECTED_PET,Constants.BIRD);
                startActivity(petDetailsIntent);
            }
        });

//        Button invite = (Button) findViewById(R.id.inviteButton);
//        invite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try Pet World for Android!");
//                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I'm using Pet World for Android and I recommend it. Click here: http://www.petworld.com");
//
//                Intent chooserIntent = Intent.createChooser(shareIntent, "Share with");
//                chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(chooserIntent);
//            }
//        });
        // End of Setting Images with Rounded Images------------------------------

    }

//    TextView myTextView = (TextView)findViewById(R.id.textView2);
//
//    Typeface myTypeface = Typeface.createFromAsset(getAssets(), "assets/future.ttf");
//
//    myTextView.setTypeface(myTypeface);

    public void dogGreyScale(View view){
        //  imageView2=(ImageView) findViewById(R.id.imageView2);


        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView2.setColorFilter(filter);

        //changing all other images back to original

        matrix.setSaturation(1);
        filter = new ColorMatrixColorFilter(matrix);
        imageView3.setColorFilter(filter);
        imageView4.setColorFilter(filter);
        imageView5.setColorFilter(filter);

    }
    public void catGreyScale(View view){


        // imageView3=(ImageView) findViewById(R.id.imageView3);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView3.setColorFilter(filter);

        //changing all other images back to original
        matrix.setSaturation(1);
        filter = new ColorMatrixColorFilter(matrix);
        imageView2.setColorFilter(filter);
        imageView4.setColorFilter(filter);
        imageView5.setColorFilter(filter);
    }
    public void fishGreyScale(View view){


        //  imageView4=(ImageView) findViewById(R.id.imageView4);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView4.setColorFilter(filter);

        //changing all other images back to original
        matrix.setSaturation(1);
        filter = new ColorMatrixColorFilter(matrix);
        imageView2.setColorFilter(filter);
        imageView3.setColorFilter(filter);
        imageView5.setColorFilter(filter);

    }
    public void birdGreyScale(View view){


        // imageView5=(ImageView) findViewById(R.id.imageView5);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView5.setColorFilter(filter);

        //changing all other images back to original
        matrix.setSaturation(1);
        filter = new ColorMatrixColorFilter(matrix);
        imageView2.setColorFilter(filter);
        imageView3.setColorFilter(filter);
        imageView4.setColorFilter(filter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent MarketPetPage = new Intent(this,MarketPet.class);

            startActivity(MarketPetPage);


        } else if (id == R.id.nav_gallery) {
            Intent PetMartPage= new Intent(this,Nannies.class);
            startActivity(PetMartPage);

        } else if (id == R.id.nav_slideshow) {

            startActivity(new Intent(this,Peta.class));

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this,VideoPlayActivity.class));


        }
//        else if (id == R.id.nav_forum) {
//            startActivity(new Intent(this,.class));
//        }
        else if (id == R.id.nav_pet_marts) {
            AlertDialog levelDialog;
            //start
            final CharSequence[] items = {"Normal","Terrain","Satellite","Hybrid"};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select The Map Type");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {

                    switch (item) {
                        case 0:
                            // Your code when first option seletced
                            choice ="Normal";
                            Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            // Your code when 2nd  option seletced
                            choice ="Terrain";
                            break;
                        case 2:
                            // Your code when 3rd option seletced
                            choice ="Satellite";
                            break;
                        case 3:
                            // Your code when 4th  option seletced
                            choice ="Hybrid";
                            break;

                    }
                  //  dialog.dismiss();
                }
            });


            builder
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                             Toast.makeText(getApplicationContext(),"Ok Clicked",Toast.LENGTH_SHORT).show();

                            Intent sendToMap = new Intent(getApplicationContext(), MapsMainActivity.class);
                            sendToMap.putExtra("PLACE_TYPE", "pet_store");
                            Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
                            sendToMap.putExtra("MAPPING_TYPE",choice);

//                            Intent sendToMap1 = new Intent(getApplicationContext(), MapsMainActivity.class);
//                            sendToMap1.putExtra("MAP_TYPE",choice);
                            startActivity(sendToMap);
                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Cancel CLicked", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });





            levelDialog = builder.create();
            levelDialog.show();
            //end





           // startActivity(new Intent(this,MapsMainActivity.class));


        } else if (id == R.id.nav_veterinary) {
//            Intent sendToMap = new Intent(this, MapsMainActivity.class);
//            sendToMap.putExtra("PLACE_TYPE", "veterinary_hospital");
//            startActivity(sendToMap);
            AlertDialog levelDialog;
            //start
            final CharSequence[] items = {"Normal","Terrain","Satellite","Hybrid"};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select The Map Type");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {

                    switch (item) {
                        case 0:
                            // Your code when first option seletced
                            choice ="Normal";
                            Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            // Your code when 2nd  option seletced
                            choice ="Terrain";
                            break;
                        case 2:
                            // Your code when 3rd option seletced
                            choice ="Satellite";
                            break;
                        case 3:
                            // Your code when 4th  option seletced
                            choice ="Hybrid";
                            break;

                    }
                    //  dialog.dismiss();
                }
            });


            builder
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getApplicationContext(),"Ok Clicked",Toast.LENGTH_SHORT).show();

                            Intent sendToMap = new Intent(getApplicationContext(), MapsMainActivity.class);
                            sendToMap.putExtra("PLACE_TYPE", "veterinary_hospital");
                            Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
                            sendToMap.putExtra("MAPPING_TYPE",choice);

//                            Intent sendToMap1 = new Intent(getApplicationContext(), MapsMainActivity.class);
//                            sendToMap1.putExtra("MAP_TYPE",choice);
                            startActivity(sendToMap);
                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Cancel CLicked", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });





            levelDialog = builder.create();
            levelDialog.show();
            //end





            // startActivity(new Intent(this,MapsMainActivity.class));
           // startActivity(new Intent(this,PetMart.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void logout(View view){
//        Firebase ref = new Firebase(Constants.FIREBASE_LINK);
//        Firebase.setAndroidContext(this);
//        ref.unauth();
//        startActivity(new Intent(getApplicationContext(), Splash.class));
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