package com.example.suraj.petuniverse;

/**
 * Created by Ramya on 3/6/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

import static com.example.suraj.petuniverse.R.mipmap.ic_launcher;


/**
 * Created by Ramya on 3/6/2016.
 */
public class CustomAdapterBreed extends ArrayAdapter implements BitMapImageDownloadInterface {

    private final List<BreedInfo> breedList;



    static class ViewHolder {
        TextView textView;
        ImageView imageView;
        String text;
        Bitmap bmp;
        String imageUrl;
        BreedInfo breed;
    }
    ViewHolder holder;
    @Override
    public void getDownloadedImageBitMap(Bitmap ins) {

        holder.imageView.setImageBitmap(ins);


    }
    public CustomAdapterBreed(Context context, int resource, List<BreedInfo> breedList) {
        super(context, resource, breedList);
        this.breedList = breedList;
    }

    // getDropDownView returns the view for the dropdown. We use the same view
    // between getView and getDropDownView.
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BreedInfo breed = breedList.get(position);

        View row = null;
//        ViewHolder holder;
        try {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.breed_custom_row, null);

                holder = new ViewHolder();
                holder.imageView = (ImageView) row.findViewById(R.id.rowImage3);
                holder.textView = (TextView) row.findViewById(R.id.rowText3);
                holder.text = "";
                holder.breed = breed;

                row.setTag(holder);
            } else {
                row = convertView;
                holder = (ViewHolder) row.getTag();
            }

            holder.text = breed.getBreedName();
            holder.imageUrl = breed.getBreedPhoto();

            // Set the image
            try {

                //Code with HTTP request to Update UI -- Ramya TBD
                DownloadImageAsyncTask imageAsyncTask = new DownloadImageAsyncTask(CustomAdapterBreed.this);
                imageAsyncTask.execute(holder);
                //breed.setBreedBmp(holder.bmp);
               // holder.imageView.setImageResource(R.drawable.camera_icon);

            } catch (Exception e) {
                e.printStackTrace();
            }
            // Set the text
//            holder.textView.setText(breed.getBreedName());

            return row;
        }
        catch(java.lang.NullPointerException nullValue)
        {
            Log.d("CustomAdapterBreed", "Invalid Input" + nullValue.toString());
        }
        catch (Exception e) {
            Log.d("CustomAdapterBreed", "Raised Generic Exception" + e.toString());
            //   Toast.makeText(getApplicationContext(), "Please Enter All Values", Toast.LENGTH_LONG).show();
        }
        return row;
    }

}

