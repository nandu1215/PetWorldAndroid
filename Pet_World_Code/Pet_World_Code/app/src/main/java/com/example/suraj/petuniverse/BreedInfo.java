package com.example.suraj.petuniverse;

import android.graphics.Bitmap;

/**
 * Created by Ramya on 3/6/2016.
 */
public class BreedInfo {
    String breedName;
    String breedDetails;
    String breedPhoto;
    Bitmap breedBmp;

    public Bitmap getBreedBmp() {
        return breedBmp;
    }

    public void setBreedBmp(Bitmap breedBmp) {
        this.breedBmp = breedBmp;
    }

    public String getBreedName() {
        return breedName;
    }

    public String getBreedDetails() {
        return breedDetails;
    }

    public String getBreedPhoto() {
        return breedPhoto;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public void setBreedDetails(String breedDetails) {
        this.breedDetails = breedDetails;
    }

    public void setBreedPhoto(String breedPhoto) {
        this.breedPhoto = breedPhoto;
    }
}
