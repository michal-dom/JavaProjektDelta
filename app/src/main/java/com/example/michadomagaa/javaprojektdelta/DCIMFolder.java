package com.example.michadomagaa.javaprojektdelta;

import android.os.Environment;

import java.io.File;

/**
 * Created by Marcin on 01.06.2017.
 */

public class DCIMFolder extends File {

    private static DCIMFolder instance;
    private DCIMFolder(){
        super(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
    }

    public static boolean foldExist(){
        File tmpFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"");
        return tmpFile.exists();
    }

    public static DCIMFolder getInstance(){
            if(instance == null)
                instance = new DCIMFolder();
            return instance;
    }

}
