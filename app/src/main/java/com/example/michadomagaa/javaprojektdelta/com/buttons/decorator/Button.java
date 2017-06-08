package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

/**
 * Created by macfr on 08.06.2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Michał Domagała on 2017-05-31.
 */

public class Button implements Item {
    RelativeLayout relativeLayout = null;
    Context context = null;
    protected String name = null;

    public Button(Context c, RelativeLayout rl, String s){
        this.context = c;
        this.relativeLayout = rl;
        this.name = s;
    }

    private TextView setLetter(){
        if(context != null){
            TextView tv = new TextView(context);
            tv.setTextColor(Color.BLACK);
            tv.setText(name.substring(0,1));
            tv.setTextSize(40);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(40, 5, 5, 5);// left, top, right, bottom;
            //tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(params);
            return tv;
        }
        return null;
    }

    private TextView setTitle(){
        if(context != null){
            TextView tv = new TextView(context);
            tv.setTextColor(Color.BLACK);
            tv.setText(name);
            tv.setTextSize(12);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 130, 5, 5);// left, top, right, bottom;
            tv.setLayoutParams(params);
            return tv;
        }
        return null;
    }


    @Override
    public void completeLayout() {
        relativeLayout.addView(setLetter());
        relativeLayout.addView(setTitle());
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    @Override
    public String getName() {
        return name;
    }
}