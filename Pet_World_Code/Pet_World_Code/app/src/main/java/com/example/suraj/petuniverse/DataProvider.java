package com.example.suraj.petuniverse;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by suraj on 3/18/2016.
 */
public class DataProvider {

    public static HashMap<String,List< String>> getInfo(String JSONText,String animal) throws JSONException
    {
        JSONObject parsedObj = new JSONObject(JSONText);
        Log.e("JSONParser", "parsedObj");
//        JSONArray petObj = parsedObj.getJSONArray("Dogs");
//        Log.e("JSONParser","petObj.length"+petObj.length());
        JSONArray jsonArray = null;
        if(animal.equals(Constants.DOG))
            jsonArray = parsedObj.getJSONArray("Dogs");
        else if(animal.equals(Constants.BIRD))
            jsonArray = parsedObj.getJSONArray("Birds");
        else if(animal.equals(Constants.CAT))
            jsonArray = parsedObj.getJSONArray("Cats");
        else if(animal.equals(Constants.FISH))
            jsonArray = parsedObj.getJSONArray("Fish");
        //String breed = "";
        HashMap<String, List<String>> HealthDetails = new HashMap<String, List<String>>();
        Log.e("DataProvider", "jsonArray.getJSONObject(0)" + jsonArray.getJSONObject(0).keys());
        Log.e("DataProvider","Value is:"+jsonArray.getJSONObject(0).get(jsonArray.getJSONObject(0).names().getString(0)));
        int count = 0;
        for(int i=0;i<jsonArray.length();i++) {
//          Log.e("DataProvider", "Key is:" + key);
            Log.e("DataProvider", "Value is:" + jsonArray.getJSONObject(count).get(jsonArray.getJSONObject(count).names().getString(0)));
            String Value = jsonArray.getJSONObject(count).get(jsonArray.getJSONObject(count).names().getString(0)).toString();
            List<String> Obj = new ArrayList<String>();
            String Key =jsonArray.getJSONObject(i).names().toString();
//            Key.replaceAll("\\[","");
//            Key.replaceAll("\\]","");
//            Key.replaceAll("\"","");

            Key = Key.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","");

            Log.e("Key","Key is:"+Key);
//            Key.replaceAll("")
                Obj.add(jsonArray.getJSONObject(i).get(jsonArray.getJSONObject(i).names().getString(0)).toString());
                HealthDetails.put(Key,Obj );
            //HealthDetails.put(jsonArray.getJSONObject(count).names().toString(), jsonArray.getJSONObject(count).get(jsonArray.getJSONObject(count).names().getString(0)).toString());
            count++;
        }
////        for(int i = 0; i < jsonArray.length(); i++) {
////            Iterator iterator = jsonArray.getJSONObject(i).keys();
////            count =0;
////            while (iterator.hasNext()) {
////                String key = (String) iterator.next();
////                count++;
////                Log.e("DataProvider", "Key is:" + key);
////                Log.e("DataProvider", "Value is:" + jsonArray.getJSONObject(i).get(jsonArray.getJSONObject(count).names().getString(count)));
////                String Value = jsonArray.getJSONObject(i).get(jsonArray.getJSONObject(count).names().getString(count)).toString();
////                List<String> Obj = new ArrayList<String>();
////                Obj.add(jsonArray.getJSONObject(i).get(jsonArray.getJSONObject(i).names().getString(i)).toString());
////                HealthDetails.put(key,Obj );
//////            JSONObject issue = jsonArray.getJSONObject(key);
////
////                //  get id from  issue
//////            String _pubKey = issue.optString("id");
////            }
////        }
////        if(animal.equals(SyncStateContract.Constants.DOG))
////        {
////            breed =  jsonArray.getJSONObject(0).getString(SyncStateContract.Constants.BREED);
////        }
////        else if(animal.equals(SyncStateContract.Constants.CAT))
////        {
////            breed = jsonArray.getJSONObject(1).getString(Constants.BREED);
////        }
//
//
//
//
//        HashMap<String, List<String>> HealthDetails = new HashMap<String, List<String>>();
//        List<String> Grooming = new ArrayList<String>();
//        Grooming.add("Grooming practice:\n Some veterinarians recommend adding a few teaspoons to a dog’s food to help boost their appetite. Other apple cider vinegar enthusiasts recommend applying it directly to a dog’s skin to help soothe itchy hot spots which makes pet grooming convenient.");
//        Grooming.add("Grooming practice:\n Aromatherapy oils, like basil, lemon, cinnamon, cedar, lavender, and pennyroyal are the natural repellent for ticks. For removing ticks, prepare a mixture of any three oils. Mix one drop each of the three oils chosen and pure almond oil. Stir it well, and soak a cloth in it. Wrap this cloth on your pet and apply it on the skin.");
//
//        List<String> Infection = new ArrayList<String>();
//        Infection.add("Skin Infection\n Remedy: Chamomile tea uses the natural disinfecting effects of the chamomile plant to settle upset doggy tummies. It is recommended for colic, gas, and anxiety. It can also alleviate minor skin irritations. Just chill in the fridge and spray onto the affected area on the dog's raw skin. Your dog should feel an immediate soothing effect as the chilled tea kills the yeast and/or bacteria on the skin. A warm (not hot) tea bag can also be used for soothing infected or irritated eyes.");
//        Infection.add("Yest Infection\n Remedy: Deliciously plain yogurt is a healthy treat for your dog. Just as with humans, the live acidophilus in the yogurt keeps the good bacteria in your dog's intestines in balance, so that bad bacteria is swiftly knocked out. If your dog is on antibiotics, a little yogurt will also help keep yeast infections at bay (a common side-effect of antibiotic treatment). You can also give your dog acidophilus pills -- wrapping the pills in bacon is strictly optional.");
//
//        List<String> Weakness = new ArrayList<String>();
//        Weakness.add("Hypoglycemia\n Remedy: This is low blood sugar, which is the opposite of diabetes. It can make your dog weak and lead to seizures. Treatment will depend on what's causing the hypoglycemia. Short-term therapy may include giving corn syrup orally or intravenous glucose.");
//        Weakness.add("Diarrhea\n Remedy: Give the dog a small meal of cooked chicken and white rice. It will be the dog's diet until the stool consistency returns to normal. If the diarrhea continues for more than 24 hours or you start seeing bloody stools or mucus-coated stools, call your vet immediately.");
//
//        List<String> Bugs = new ArrayList<String>();
//        Bugs.add("Flea alert:\n Remedy: Dilute 2-3 drops of your chosen oil in 1-3 tablespoons of water. Some people use the oil undiluted, but I personally feel it should always be diluted, even if it’s only by a little. Next, pick out a bandana to be the flea collar-I think a bandana is preferable because you can take it on and off and your dog’s collar won’t smell. It’s always fun to get creative with patterns and colors here. If you go up to ½ teaspoon you can use up to 5 drops of the liquid. Using an eyedropper or other similar means, apply 5-10 drops of the mixture to the bandana and rub the sides of the fabric together, and then tie it about your dog’s neck in a snazzy way. Reapply oil mixture to the collar once a week. In conjunction with this, 1 or 2 drops of oil diluted with at least 1 tablespoon of olive oil can be placed at the base of your dog’s tail.");
//        Bugs.add("Tick alert:\n Remedy: One of the natural repellents that a lot of people have success with is rose geranium oil, which can be applied to your dog's collar. Do NOT use rose geranium oil on your cat, though. Cats can have a bad reaction to essential oils, primarily because they spend a lot of time grooming, which means that anything on their skin goes into their mouth. With ticks, the best thing you might do is to check your pet a few times a day when you are in an area that has ticks, and remove them promptly. Proper technique is important for removing ticks, so make sure that you consult a veterinarian before doing it yourself if you are not completely sure of how to do it.");

//        HealthDetails.put("Grooming", Grooming);
//        HealthDetails.put("Infection",Infection);
//        HealthDetails.put("Weakness",Weakness);
//        HealthDetails.put("Bugs",Bugs);

        return HealthDetails;
    }
}
