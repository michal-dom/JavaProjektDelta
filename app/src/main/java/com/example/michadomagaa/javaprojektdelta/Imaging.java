package com.example.michadomagaa.javaprojektdelta;

/**
 * Created by Marcin on 24.05.2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Imaging {

    public Imaging(){}

    public static Bitmap decodeImage(String path){
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 1;

        File file = new File(path);
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return decodeFile(file) ;
    }
    private static Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
}

