package com.example.suraj.petuniverse;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

//Resources res = getResources();
//        Bitmap src = BitmapFactory.decodeResource(res, R.mipmap.dogss);
//        RoundedBitmapDrawable dr =
//                RoundedBitmapDrawableFactory.create(res, src);
//        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
public class RoundImage {

   public RoundedBitmapDrawable circleImage(Resources res, int pet ){
       BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
       options.inSampleSize = 5;
       Bitmap src = BitmapFactory.decodeResource(res,pet,options);
       RoundedBitmapDrawable dr =
               RoundedBitmapDrawableFactory.create(res, src);
       dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
    return dr;
   }

}
