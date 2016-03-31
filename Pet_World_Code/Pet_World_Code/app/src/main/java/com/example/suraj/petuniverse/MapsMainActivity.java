package com.example.suraj.petuniverse;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsMainActivity extends FragmentActivity implements LocationListener {

    GoogleMap mGoogleMap;
    Spinner mSprPlaceType;

    String[] mPlaceType = null;
    String[] mPlaceTypeName = null;

    double mLatitude = 0;
    double mLongitude = 0;

    TextView radiustext;
    String radiusTextString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);

        //setting textView content
        Intent getFromMain = getIntent();
        String type1 = getFromMain.getExtras().getString("PLACE_TYPE");

        TextView txt=(TextView) findViewById(R.id.typeValue);

        if (type1.equals("pet_store")){
            txt.setText("Pet Marts");
        }
       else{
            txt.setText("Veterinary Hospitals");
        }





        // Array of place types
        mPlaceType = getResources().getStringArray(R.array.place_type);

        // Array of place type names
        mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);

        // Creating an array adapter with an array of Place types
        // to populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);

        // Getting reference to the Spinner
        mSprPlaceType = (Spinner) findViewById(R.id.spr_place_type);

        // Setting adapter on Spinner to set place types
        mSprPlaceType.setAdapter(adapter);

        Button btnFind;

        // Getting reference to Find Button
        btnFind = (Button) findViewById(R.id.btn_find);

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else { // Google Play Services are available

            // Getting reference to the SupportMapFragment
            SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting Google Map
            mGoogleMap = fragment.getMap();

            Intent getFromMain1 = getIntent();
            String maptype = getFromMain1.getExtras().getString("MAPPING_TYPE");
            Toast.makeText(getApplicationContext(),maptype,Toast.LENGTH_SHORT).show();


                         //   mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            if(maptype.equals("Normal")){
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
            else  if(maptype.equals("Terrain")){
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            }
            else  if(maptype.equals("Satellite")){
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
            else {
                 mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
            // mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);




            // Enabling MyLocation in Google Map
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location From GPS
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }

            locationManager.requestLocationUpdates(provider, 20000, 0, this);

            // Setting click event lister for the find button
            btnFind.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MapsMainActivity.this, "buttonclicked", Toast.LENGTH_SHORT).show();

                     radiustext = (TextView) findViewById(R.id.radiusText);
                     radiusTextString=radiustext.getText().toString();
                    if(radiusTextString=="Radius"){radiusTextString="5000";}

                    int selectedPosition = mSprPlaceType.getSelectedItemPosition();
                    String type = mPlaceType[selectedPosition];

                    //type="pet_store";

                    StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                    sb.append("location="+mLatitude+","+mLongitude);
                    sb.append("&radius="+radiusTextString);
                    sb.append("&types="+type);
                    sb.append("&sensor=true");
                    sb.append("&key=AIzaSyDUzbWX3-ZfjF8oV6Q12AYjkud0SfaCnnc");
//AIzaSyCGSuCngvCkEIFTjB-nG5zDPefHk69XpE8
                    // Creating a new non-ui thread task to download json data
                    PlacesTask placesTask = new PlacesTask();

                    // Invokes the "doInBackground()" method of the class PlaceTask
                    placesTask.execute(sb.toString());

                }
            });

        }

    }
    public void milesToMeters(Double miles){

        miles=miles*1609.34;
    }
    public void metersToMiles(){


    }
    public void plus(View view){
        radiustext=(TextView)findViewById(R.id.radiusText);
        radiusTextString=radiustext.getText().toString();
      //  Integer.parseInt(radiusTextString);
        Toast.makeText(getApplicationContext(),radiusTextString,Toast.LENGTH_LONG).show();
        if(radiusTextString.equals("radius")){
        Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();
        radiustext.setText("5000");
    }
    else if(Integer.parseInt(radiusTextString)+500<=50000){
        int x=Integer.parseInt(radiusTextString)+500;
       radiustext.setText(""+x);
        }


    }
    public void minus(View view){

        radiustext=(TextView)findViewById(R.id.radiusText);
        radiusTextString=radiustext.getText().toString();
        //  Integer.parseInt(radiusTextString);
        if(radiusTextString.equals("radius")){
            radiustext.setText("5000");
        }
        else if(Integer.parseInt(radiusTextString)-500>=0){
            int x=Integer.parseInt(radiusTextString)-500;
            radiustext.setText(""+x);
        }

    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("excep downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    /** A class, to download Google Places */
    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result){
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParseTask
            parserTask.execute(result);
        }

    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try{
                jObject = new JSONObject(jsonData[0]);

                /** Getting the parsed data as a List construct */
                places = placeJsonParser.parse(jObject);

            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list){

            // Clears all the existing markers
            mGoogleMap.clear();

            for(int i=0;i<list.size();i++){

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);

                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");

                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");

                LatLng latLng = new LatLng(lat, lng);

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                //This will be displayed on taping the marker
                markerOptions.title(name + " : " + vicinity);

                // Placing a marker on the touched position
                mGoogleMap.addMarker(markerOptions);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        LatLng latLng = new LatLng(mLatitude, mLongitude);


        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}