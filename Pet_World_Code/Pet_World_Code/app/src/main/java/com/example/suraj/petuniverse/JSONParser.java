package com.example.suraj.petuniverse;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ramya on 3/6/2016.
 */
public class JSONParser {

    String inputJSON;
    ArrayList<BreedInfo> breedInfo = new ArrayList<BreedInfo>();

    public JSONParser(String inputJSON) {
        this.inputJSON = inputJSON;
    }

    public String getInputJSON() {
        return inputJSON;
    }

    public ArrayList<BreedInfo> getBreedInfo(String animal) throws JSONException {
        JSONObject parsedObj = new JSONObject(getInputJSON());
        Log.e("JSONParser","parsedObj");
//        JSONArray petObj = parsedObj.getJSONArray("Dogs");
//        Log.e("JSONParser","petObj.length"+petObj.length());
        JSONArray jsonArray = parsedObj.getJSONArray(animal);
        String breed = "";
        if(animal.equals(Constants.DOG))
        {
            breed =  jsonArray.getJSONObject(0).getString(Constants.BREED);
        }
        else if(animal.equals(Constants.CAT))
        {
            breed = jsonArray.getJSONObject(1).getString(Constants.BREED);
        }


        Log.e("JSONParser","brees is:"+breed);
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d("Type","Entered");
            BreedInfo oneBreed = new BreedInfo();
            oneBreed.setBreedName(jsonArray.getJSONObject(i).getString(Constants.BREED));
            oneBreed.setBreedDetails(jsonArray.getJSONObject(i).getString(Constants.DESCRIPTION));
            oneBreed.setBreedPhoto(jsonArray.getJSONObject(i).getString(Constants.IMAGE));
            breedInfo.add(oneBreed);
//            obj.getString("breed");
//            Log.d("Type",obj.toString(i) );
        }
        return breedInfo;
    }



    public void setInputJSON(String inputJSON) {
        this.inputJSON = inputJSON;
    }

    public void setBreedInfo(ArrayList<BreedInfo> breedInfo) {
        this.breedInfo = breedInfo;
    }
}
