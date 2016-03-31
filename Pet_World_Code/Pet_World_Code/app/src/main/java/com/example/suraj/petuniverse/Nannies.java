package com.example.suraj.petuniverse;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Nannies extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG =1; ;
    String imgDecodableString;
    String selectSpinnerValue;
    List<MarketData> marketForumItems;
    static long  count = 0;
    Firebase fireBaseRef;
    String itemDescription;
    String titleValue;
    private int hot_number = 0;
    static int  notifyCount =0;
    private TextView ui_hot = null;
    NotificationCompat.Builder notification;
    ArrayAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nannies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pet Sitter");
        Firebase.setAndroidContext(this);
        fireBaseRef = new Firebase(Constants.FIREBASE_LINK);
        //Can add Forum Item with a Image -- Later Enhancement
        marketForumItems = new ArrayList<MarketData>();

//      myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, marketForumItems);
        ListView myListView = (ListView) findViewById(R.id.custom_list_view_nannies);
        TextView textView = (TextView) findViewById(R.id.nanniesTitle);

        //To Intialize Custom Font
//        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/black_jack.ttf");
//        textView.setTypeface(font);


        myAdapter = new CustomAdapter(this, R.layout.market_pet_custom_row, marketForumItems);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String marketItemValue = String.valueOf(parent.getItemAtPosition(position));
                        Intent displayForumItem = new Intent(getApplicationContext(),DisplayForumItem.class);
                        displayForumItem.putExtra(Constants.IMAGE,marketForumItems.get(position).getImagePath());
                        displayForumItem.putExtra(Constants.DESCRIPTION,marketForumItems.get(position).getDescription());

                        //Decoding the base64 Image to Displayable Image File
//                                byte[] imageAsBytes = Base64.decode(marketItem.getImagePath().toString(), Base64.DEFAULT);
//                                Bitmap decodedImageBitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//                                photoView.setImageBitmap(decodedImageBitMap);)
                        startActivity(displayForumItem);
                        Toast.makeText(getApplicationContext(), marketForumItems.get(position).toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        //Retrieving details from Forum
//        fireBaseRef.child().;
        fireBaseRef.child("pets/Nanny").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Getting Object from FireBase through DataSnapShot
                int childCount=0;
                MarketData marketItem = new MarketData("","","","");
                String description= "",imagePath = "",marketCategory= "",title ="";
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                        childCount++;
                        if(childCount == 1) {
                            description = postSnapshot.getValue().toString();
                            marketItem.setDescription(description);
                        }
                        else if(childCount == 2) {
                            imagePath = postSnapshot.getValue().toString();
                            marketItem.setImagePath(imagePath);
                        }
                        else if(childCount == 3) {
                            marketCategory = postSnapshot.getValue().toString();
                            marketItem.setMarketCategory(marketCategory);
                        }
                        else if(childCount == 4) {
                            title = postSnapshot.getValue().toString();
                            marketItem.setTitleValue(title);
                        }
//                            Log.e("MarketPet", "Post Snapshot value is " + snapShotValue);
                    }

                //count = dataSnapshot.getChildrenCount();
                Log.e("GetKey",dataSnapshot.getKey());
                count = Integer.parseInt(dataSnapshot.getKey());
                myAdapter.notifyDataSetChanged();
                marketForumItems.add(marketItem);
                notifyCount++;
                updateHotCount(notifyCount);
                createNotification(title, description);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

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



    //ALERT DIALOG BOX-----------------------------------------------------------------------------
    public void displayAlert(View view) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.prompts, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setTitle("Add New Pet Sitter");
        final EditText input = (EditText) promptView.findViewById(R.id.userInput);
        final EditText title = (EditText) promptView.findViewById(R.id.title);

//SPINNER------------------------------------------
        final Spinner mSpinner= (Spinner) promptView
                .findViewById(R.id.spinner);
        final String array_spinner[];
        array_spinner=new String[2];

        array_spinner[0]="Select An Option";
        array_spinner[1]="Nanny";
        Log.e("MarketPet","ItemDescription is "+itemDescription);


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSpinnerValue = array_spinner[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//END OF SPINNER--------------------------------------------
        // reference UI elements from my_dialog_layout in similar fashion
        //mSpinner.setOnItemSelectedListener(new OnSpinnerItemClicked());

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        // editTextMainScreen.setText(input.getText());

                        // Forum Items will be based on Count value, When new Item is added we get the last count Value and update new Item
                        //with Count+1 value
                        itemDescription = input.getText().toString();
                        titleValue = title.getText().toString();
                        count++;
//                      Log.e("MarketPet", "Last Item in the List:" + marketForumItems.get(marketForumItems.size() - 1).toString());
                        Log.e("MarketPet", "count and ItemDescription is " + count);
                        Log.e("MarketPet", "ItemDescription is" + itemDescription);
                        //marketForumItems.add(itemDescription);

                        // Encoding Image to a base64 Format
                        String imageEncodedBase64 = "";
                        if (!imgDecodableString.isEmpty()) {
                            Log.e("MarketPet", "Uploaded Image Path is" + imgDecodableString);
                            Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            imageEncodedBase64 = Base64.encodeToString(b, Base64.DEFAULT);

                        }

//                        Log.e("MarketPet", "imageEncodedBase64 is :" + imageEncodedBase64);
                        MarketData marketItem = new MarketData(itemDescription, imageEncodedBase64, selectSpinnerValue,titleValue);


                        Firebase petRefCategory = fireBaseRef.child("pets").child(selectSpinnerValue).child(Long.toString(count));
                        petRefCategory.setValue(marketItem);
                        Toast.makeText(getApplicationContext(), "Yes CLicked", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), input.getText().toString(), Toast.LENGTH_LONG).show();
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

//END OF ALERT DIALOG BOX------------------------------------------------------------------------

//    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void uploadPhoto(View view){
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 5;
                Bitmap bitmapPhotoView = BitmapFactory.decodeFile(imgDecodableString, options);
                Log.e("MarketPet","ImageDecodableString:"+imgDecodableString);
                Log.e("MarketPet","BitMapImage:"+bitmapPhotoView);
                cursor.close();
                //FOR SETTING  THE IMAGE
                // ImageView imgView = (ImageView) findViewById(R.id.imageView6);
                // Set the Image in ImageView after decoding the String
                // imgView.setImageBitmap(BitmapFactory
                //  .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        final View menu_hotlist = menu.findItem(R.id.menu_hotlist1).getActionView();
        ui_hot = (TextView) menu_hotlist.findViewById(R.id.hotlist_hot);
        updateHotCount(hot_number);
        new MyMenuItemStuffListener(menu_hotlist, "Show hot message") {
            @Override
            public void onClick(View v) {
//                onHotlistSelected();
               // count++;
                //updateHotCount(count);
                Log.e("MainActivity", "ItemSelected");
            }
        };
        return super.onCreateOptionsMenu(menu);

//        return true;
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
    public void createNotification(String titleValue,String itemDescription)
    {
        notification=new NotificationCompat.Builder(getApplicationContext());
        notification.setAutoCancel(true);
        //Building the notification
        notification.setSmallIcon(R.drawable.app_icon);
        notification.setTicker("New Pet Alert ");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(titleValue);
        notification.setContentText(itemDescription);
        Log.e("MarketPet","CreateNotification");
        //calling mainacticity on click of intent
        Intent in = new Intent(getApplicationContext(), MarketPet.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Building notifiation issues or sending
        NotificationManager nm=(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        Random r = new Random();
//        int uniqueID= r.nextInt(20000-10000+1)+10000;
        int uniqueID =12345;
        nm.notify(uniqueID,notification.build());
        notification.setAutoCancel(true);
    }
    public void updateHotCount(final int new_hot_number) {
        hot_number = new_hot_number;
        if (ui_hot == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (new_hot_number == 0)
                    ui_hot.setVisibility(View.INVISIBLE);
                else {
                    ui_hot.setVisibility(View.VISIBLE);
                    ui_hot.setText(Integer.toString(new_hot_number));
                }
            }
        });
    }
    static abstract class MyMenuItemStuffListener implements View.OnClickListener, View.OnLongClickListener {
        private String hint;
        private View view;

        MyMenuItemStuffListener(View view, String hint) {
            this.view = view;
            this.hint = hint;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override abstract public void onClick(View v);

        @Override public boolean onLongClick(View v) {
            final int[] screenPos = new int[2];
            final Rect displayFrame = new Rect();
            view.getLocationOnScreen(screenPos);
            view.getWindowVisibleDisplayFrame(displayFrame);
            final Context context = view.getContext();
            final int width = view.getWidth();
            final int height = view.getHeight();
            final int midy = screenPos[1] + height / 2;
            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            Toast cheatSheet = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
            if (midy < displayFrame.height()) {
                cheatSheet.setGravity(Gravity.TOP | Gravity.RIGHT,
                        screenWidth - screenPos[0] - width / 2, height);
            } else {
                cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
            }
            cheatSheet.show();
            return true;
        }
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
