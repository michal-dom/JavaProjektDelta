package com.example.michadomagaa.javaprojektdelta.com.filter.mvc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.michadomagaa.javaprojektdelta.R;

/**
 * Created by Michał Domagała on 2017-06-09.
 */

public class FilterController {

    private FilterModel model;
    private SeekBar seekContrast, seekHue, seekBrightness, seekSaturation;
    private Activity activity;
    private int brightness_controller = 50;
    private int contrast_controller = 50;
    private int saturation_controller = 50;
    private int hue_controller = 50;
    private ImageView imageView;
    private final Bitmap startBitmap;
    private Bitmap editBitmap;
    private FilterView view;

    public FilterController(Activity a){
        activity = a;
        imageView = (ImageView) a.findViewById(R.id.image_image);
        view = new FilterView(activity);
        startBitmap = view.getBitmap();
    }

    SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            brightness_controller = seekBrightness.getProgress();
            contrast_controller = seekContrast.getProgress();
            hue_controller = seekHue.getProgress();
            saturation_controller = seekSaturation.getProgress();
            view.placeBitmap(onChangeAdjust(startBitmap,
                    brightness_controller,
                    contrast_controller,
                    saturation_controller,
                    hue_controller));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    };

    public void initSeekBars(){
        seekBrightness = (SeekBar) activity.findViewById(R.id.brightness_seekbar);
        seekBrightness.setOnSeekBarChangeListener(barChangeListener);
        seekContrast = (SeekBar) activity.findViewById(R.id.contrast_seekbar);
        seekContrast.setOnSeekBarChangeListener(barChangeListener);
        seekHue = (SeekBar) activity.findViewById(R.id.hue_seekbar);
        seekHue.setOnSeekBarChangeListener(barChangeListener);
        seekSaturation = (SeekBar) activity.findViewById(R.id.saturation_seekbar);
        seekSaturation.setOnSeekBarChangeListener(barChangeListener);
    }

    public static Bitmap onChangeAdjust(Bitmap bit, int b, int c, int h, int s){
        int br = (b*2) - 100;
        int con = (c*2) - 100;
        int hue = (int) (h*3.6) - 180;
        int sat = (s*2) - 100;

        FilterModel model = new FilterModel();
        Paint paint = new Paint();
        Bitmap bm = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(),bit.getConfig());
        paint.setColorFilter(model.adjustColor(br, con, sat, hue));
        Canvas canvas = new Canvas(bm);
        canvas.drawBitmap(bit, 0, 0, paint);

        return bm;
    }

}
