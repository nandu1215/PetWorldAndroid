package com.example.suraj.petuniverse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.AsyncTask;

import com.example.suraj.petuniverse.CustomAdapterBreed.ViewHolder;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Ramya on 3/7/2016.
 */
public class DownloadImageAsyncTask extends AsyncTask<CustomAdapterBreed.ViewHolder,Integer,CustomAdapterBreed.ViewHolder>{
    private BitMapImageDownloadInterface downloadInterface;
    Bitmap bmp;
    CustomAdapterBreed.ViewHolder viewHolder;

    public DownloadImageAsyncTask(BitMapImageDownloadInterface downloadInterface) {
        this.downloadInterface = downloadInterface;
    }




    @Override
    protected ViewHolder doInBackground(ViewHolder... viewHolders) {
        try {
            ViewHolder viewHolder = viewHolders[0];
            Bitmap bmp;
//            URL url = new URL("http://icons.iconarchive.com/icons/crountch/one-piece-jolly-roger/72/Luffys-flag-2-icon.png");
            URL url  = new URL(viewHolder.imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
          options.inSampleSize = 5;
//            options.inBitmap = true;
            Bitmap roughBitmap = BitmapFactory.decodeStream(input,null,options);

//            // save width and height
//            int   inWidth = options.outWidth;
//            int inHeight = options.outHeight;
//            int dstWidth = 2;
//            int dstHeight = 2;
//            // decode full image pre-resized
////            in = new FileInputStream(pathOfInputImage);
//            options = new BitmapFactory.Options();
//            // calc rought re-size (this is no exact resize)
//            options.inSampleSize = Math.max(inWidth/dstWidth, inHeight/dstHeight);
//            // decode full image
//            Bitmap roughBitmap = BitmapFactory.decodeStream(input, null, options);

            // calc exact destination size
//            Matrix m = new Matrix();
//            RectF inRect = new RectF(0, 0, roughBitmap.getWidth(), roughBitmap.getHeight());
//            RectF outRect = new RectF(0, 0, 2, 2);
//            m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
//            float[] values = new float[9];
//            m.getValues(values);
//
//            // resize bitmap
//            Bitmap resizedBitmap = Bitmap.createScaledBitmap(roughBitmap, (int) (roughBitmap.getWidth() * values[0]), (int) (roughBitmap.getHeight() * values[4]), true);
            viewHolder.bmp = roughBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return viewHolders[0];
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        downloadInterface.getDownloadedImageBitMap(bmp);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(CustomAdapterBreed.ViewHolder viewHolder) {
        super.onPostExecute(viewHolder);
        //Bitmap smallerSize = getResizedBitmap(viewHolder.bmp,50);
        viewHolder.breed.setBreedBmp(viewHolder.bmp);
        viewHolder.imageView.setImageBitmap(viewHolder.bmp);
        viewHolder.textView.setText(viewHolder.text.toString());
        //downloadInterface.getDownloadedImageBitMap(bmp);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
