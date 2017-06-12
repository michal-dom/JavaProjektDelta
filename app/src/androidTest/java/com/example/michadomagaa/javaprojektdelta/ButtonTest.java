package com.example.michadomagaa.javaprojektdelta;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.michadomagaa.javaprojektdelta.com.buttons.decorator.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Michał Domagała on 2017-06-12.
 */
@RunWith(AndroidJUnit4.class)
public class ButtonTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void smallTest() throws Exception {

        MainActivity activity = rule.getActivity();
        Context context = activity;
        RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.activity_main);

        Button button = new Button(context, relativeLayout, "test_string", activity);

        assertNotNull(button);

    }
}
