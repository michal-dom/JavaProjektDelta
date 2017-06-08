package com.example.michadomagaa.javaprojektdelta.com.buttons.builder;

/**
 * Created by macfr on 08.06.2017.
 */
import android.content.Context;
import android.widget.RelativeLayout;


public interface Item {

    public void setRelativeView(int w, int h, Context c, RelativeLayout rl,int m);
    public Integer getId();

}
