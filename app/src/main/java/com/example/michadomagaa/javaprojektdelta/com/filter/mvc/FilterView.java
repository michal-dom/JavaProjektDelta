package com.example.michadomagaa.javaprojektdelta.com.filter.mvc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.michadomagaa.javaprojektdelta.R;

/**
 * Created by Michał Domagała on 2017-06-09.
 */

public class FilterView {

    Activity activity;
    public FilterView(Activity a){
        activity = a;
    }

    public void placeBitmap(Bitmap b){
        ImageView iv = (ImageView) activity.findViewById(R.id.image_image);
        iv.setImageBitmap(b);
    }

    public Bitmap getBitmap(){
        ImageView iv = (ImageView) activity.findViewById(R.id.image_image);
        return ((BitmapDrawable) iv.getDrawable()).getBitmap();
    }
}
