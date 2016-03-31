package com.example.suraj.petuniverse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ramya on 3/6/2016.
 */
public class CustomAdapter extends ArrayAdapter{

    private final List<MarketData> marketItemList;

    static class ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView;
    }

    public CustomAdapter(Context context, int resource, List<MarketData> marketItemList) {
        super(context, resource, marketItemList);
        this.marketItemList = marketItemList;
    }

    // getDropDownView returns the view for the dropdown. We use the same view
    // between getView and getDropDownView.
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MarketData marketItem = marketItemList.get(position);

        View row = null;
        ViewHolder holder;
        try {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.market_pet_custom_row, null);

                holder = new ViewHolder();
                holder.imageView = (ImageView) row.findViewById(R.id.rowImage1);
                holder.textView = (TextView) row.findViewById(R.id.rowText1);
//                holder.textView2 = (TextView) row.findViewById(R.id.rowText2);
                row.setTag(holder);
            } else {
                row = convertView;
                holder = (ViewHolder) row.getTag();
            }
            //Set the Market Category
            holder.textView.setText(marketItem.getTitleValue());
            // Set the text
//            holder.textView2.setText(marketItem.getDescription());

            // Set the image
            try {

                byte[] imageAsBytes = Base64.decode(marketItem.getImagePath().toString(), Base64.DEFAULT);
                Bitmap decodedImageBitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                holder.imageView.setImageBitmap(decodedImageBitMap);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return row;
        }
        catch(java.lang.NullPointerException nullValue)
        {
            Log.d("CustomAdapter", "Invalid Input" + nullValue.toString());
        }
        catch (Exception e) {
            Log.d("CustomAdapter", "Raised Generic Exception" + e.toString());
            //   Toast.makeText(getApplicationContext(), "Please Enter All Values", Toast.LENGTH_LONG).show();
        }
        return row;
    }

}
