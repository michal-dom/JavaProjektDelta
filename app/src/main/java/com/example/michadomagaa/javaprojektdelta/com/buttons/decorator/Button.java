package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

/**
 * Created by macfr on 08.06.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
    protected Activity activity;
    protected String name = null;

    public Button(Context c, RelativeLayout rl, String s, Activity a){
        this.context = c;
        this.relativeLayout = rl;
        this.name = s;
        this.activity = a;
    }

    private TextView setLetter(){
        if(context != null){
            TextView tv = new TextView(context);
            tv.setTextColor(Color.WHITE);
            tv.setText(name.substring(0,1));
            tv.setTextSize(40);
            tv.setGravity(Gravity.CENTER);
            tv.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0, 20, 0, 0);// left, top, right, bottom;
            tv.setLayoutParams(params);
            return tv;
        }
        return null;
    }

    private TextView setTitle(){
        if(context != null){
            TextView tv = new TextView(context);
            tv.setTextColor(Color.WHITE);
            tv.setText(name);
            tv.setTextSize(12);
            tv.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0, 160, 0, 0);// left, top, right, bottom;
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

    @Override
    public Activity getActivity() { return activity; }


}