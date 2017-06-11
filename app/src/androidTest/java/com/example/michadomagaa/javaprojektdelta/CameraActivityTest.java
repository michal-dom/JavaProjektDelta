package com.example.michadomagaa.javaprojektdelta;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.ActivityCompat;
import android.view.TextureView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Michał Domagała on 2017-06-12.
 */
@RunWith(AndroidJUnit4.class)
public class CameraActivityTest {
    @Rule
    public ActivityTestRule<CameraActivity> rule = new ActivityTestRule<>(CameraActivity.class);

    @Test
    public void smallTest() throws Exception {
        CameraActivity activity = rule.getActivity();


        TextureView view = (TextureView) activity.findViewById(R.id.texture);
        assertNotNull(view);
        assertNotNull(view.getSurfaceTextureListener());

    }
}
