package com.example.vig.sih_mota;

import com.google.firebase.database.Exclude;

public class Download {
    private String mName;
    private String mImageUrl;
    private String mKey;
    private String category;
    private String price;

    public Download() {
        //empty constructor needed
    }


    public Download(String name, String imageUrl, String s,  String p) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        category = s;
        price = p;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String p) {
        price = p;
    }

    public String getCategory(){return category;}

    public void setCategory(String s) {category = s;}

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
