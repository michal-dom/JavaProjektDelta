package com.example.michadomagaa.javaprojektdelta;

/**
 * Created by Marcin on 24.05.2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class Imaging {

    public Imaging(){};

    public static Bitmap decodeImage(String path){
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 1;

        File file = new File(path);
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return bitmap;
    }
}
