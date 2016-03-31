package com.example.suraj.petuniverse;

/**
 * Created by Ramya on 2/20/2016.
 */
public class MarketData {
    public String description;
    public String marketCategory;
    public String imagePath;
    public  String titleValue;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public MarketData(String description, String imagePath, String marketCategory, String titleValue) {
        this.description = description;
        this.imagePath = imagePath;
        this.marketCategory = marketCategory;
        this.titleValue = titleValue;
    }

    public String getDescription() {
        return this.description;
    }
    public String getMarketCategory(){
        return marketCategory;
    }
    public String getImagePath(){
        return imagePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setMarketCategory(String marketCategory) {
        this.marketCategory = marketCategory;
    }
}
