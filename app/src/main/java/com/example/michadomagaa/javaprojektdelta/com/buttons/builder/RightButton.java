package com.example.michadomagaa.javaprojektdelta.com.buttons.builder;

/**
 * Created by macfr on 08.06.2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;



public class RightButton implements Item {

    private RelativeLayout relativeLayout;

    @Override
    public void setRelativeView(int w, int h, Context c, RelativeLayout rl, int m) {
        relativeLayout = new RelativeLayout(c);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(w*0.2), (int)(h*0.172));
        relativeLayout.setBackgroundColor(0x88000000);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int margin = (m*(int)(h*0.172))+(int)(h*0.05);
        params.setMargins(0, margin, 0, 0);
        relativeLayout.setLayoutParams(params);
        relativeLayout.setId(200+m);
        rl.addView(relativeLayout);
    }

    @Override
    public Integer getId() {
        return relativeLayout.getId();
    }
}