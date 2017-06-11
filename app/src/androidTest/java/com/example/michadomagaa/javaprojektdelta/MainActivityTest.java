package com.example.michadomagaa.javaprojektdelta;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.io.Resources;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Michał Domagała on 2017-06-12.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void smallTest() throws Exception {
        MainActivity activity = rule.getActivity();
        TextView view1 = (TextView) activity.findViewById(R.id.main_gallery);
        assertNotNull(view1);

        TextView view2 = (TextView) activity.findViewById(R.id.main_camera);
        assertNotNull(view2);
    }


}

