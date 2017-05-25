package com.example.michadomagaa.javaprojektdelta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    private ImageView iv;
    private int brightness_controller = 50;
    private int contrast_controller = 50;
    private int saturation_controller = 50;
    private int hue_controller = 50;
    private SeekBar seekContrast, seekHue, seekBrightness, seekSaturation;
    private RelativeLayout brightness_bt, contrast_bt, saturation_bt, hue_bt, edit_bt;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        String name = getIntent().getStringExtra("name");
        String path = getIntent().getStringExtra("path");
        tv1 = (TextView) findViewById(R.id.name_view);
        tv1.setText(name);
        //Toast.makeText(getApplicationContext(), path+"/"+name, Toast.LENGTH_LONG).show();

        final Bitmap bitmap = Imaging.decodeImage(path+"/"+name);
        iv = (ImageView) findViewById(R.id.image_image);
        iv.setImageBitmap(bitmap);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        buttonInit();
        seekBarInit();
    }
    private void buttonInit(){
        brightness_bt = (RelativeLayout) findViewById(R.id.brightness_rl);
        contrast_bt = (RelativeLayout) findViewById(R.id.contrast_rl);
        saturation_bt = (RelativeLayout) findViewById(R.id.saturation_rl);
        hue_bt = (RelativeLayout) findViewById(R.id.hue_rl);
        edit_bt = (RelativeLayout) findViewById(R.id.edit_rl);

        brightness_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBrightness.setVisibility(View.VISIBLE);
                seekContrast.setVisibility(View.INVISIBLE);
                seekSaturation.setVisibility(View.INVISIBLE);
                seekHue.setVisibility(View.INVISIBLE);
            }
        });


        contrast_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBrightness.setVisibility(View.INVISIBLE);
                seekContrast.setVisibility(View.VISIBLE);
                seekSaturation.setVisibility(View.INVISIBLE);
                seekHue.setVisibility(View.INVISIBLE);
            }
        });

        saturation_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBrightness.setVisibility(View.INVISIBLE);
                seekContrast.setVisibility(View.INVISIBLE);
                seekSaturation.setVisibility(View.VISIBLE);
                seekHue.setVisibility(View.INVISIBLE);

            }
        });

        hue_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBrightness.setVisibility(View.INVISIBLE);
                seekContrast.setVisibility(View.INVISIBLE);
                seekSaturation.setVisibility(View.INVISIBLE);
                seekHue.setVisibility(View.VISIBLE);
            }
        });

        edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matrix matrix = new Matrix();

                matrix.postRotate(90);
                final Bitmap editBitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(editBitmap,editBitmap.getWidth(),editBitmap.getHeight(),true);
                Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                iv.setImageBitmap(rotatedBitmap);

            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    private void seekBarInit(){
        final Bitmap editBitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
        SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                brightness_controller = seekBrightness.getProgress();
                contrast_controller = seekContrast.getProgress();
                hue_controller = seekHue.getProgress();
                saturation_controller = seekSaturation.getProgress();

                iv.setImageBitmap(onChangeAdjust(editBitmap,
                        brightness_controller,
                        contrast_controller,
                        saturation_controller,
                        hue_controller));
                //Log.e("test", this.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };

        seekBrightness = (SeekBar) findViewById(R.id.brightness_seekbar);
        seekBrightness.setOnSeekBarChangeListener(barChangeListener);
        seekContrast = (SeekBar) findViewById(R.id.contrast_seekbar);
        seekContrast.setOnSeekBarChangeListener(barChangeListener);
        seekHue = (SeekBar) findViewById(R.id.hue_seekbar);
        seekHue.setOnSeekBarChangeListener(barChangeListener);
        seekSaturation = (SeekBar) findViewById(R.id.saturation_seekbar);
        seekSaturation.setOnSeekBarChangeListener(barChangeListener);

    }

    public static Bitmap onChangeAdjust(Bitmap bit, int b, int c, int h, int s){
        int br = (b*2) - 100;
        int con = (c*2) - 100;
        int hue = (int) (h*3.6) - 180;
        int sat = (s*2) - 100;

        ColorFilterGenerator generator = new ColorFilterGenerator();

        Paint paint = new Paint();
        Bitmap bm = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(),bit.getConfig());

        paint.setColorFilter(generator.adjustColor(br, con, sat, hue));
        Canvas canvas = new Canvas(bm);

        canvas.drawBitmap(bit, 0, 0, paint);

        return bm;
    }
}
