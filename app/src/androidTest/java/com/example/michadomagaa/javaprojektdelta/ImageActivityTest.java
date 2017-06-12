package com.example.michadomagaa.javaprojektdelta;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Michał Domagała on 2017-06-12.
 */
@RunWith(AndroidJUnit4.class)
public class ImageActivityTest {



    @Rule
    public ActivityTestRule<CameraActivity> rule = new ActivityTestRule<>(CameraActivity.class);

    @Test
    public void smallTest() throws Exception {
        CameraActivity activity = rule.getActivity();
        SeekBar seekBar = (SeekBar) activity.findViewById(R.id.contrast_seekbar);
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        assertNotNull(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        assertNotNull((height));
        assertNotNull(metrics);
    }

    @Test
    public void onCreate() throws Exception {


    }
}