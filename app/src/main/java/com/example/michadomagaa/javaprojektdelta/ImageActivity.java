package com.example.michadomagaa.javaprojektdelta;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.michadomagaa.javaprojektdelta.com.buttons.builder.Bar;
import com.example.michadomagaa.javaprojektdelta.com.buttons.builder.ButtonsBuilder;
import com.example.michadomagaa.javaprojektdelta.com.buttons.decorator.Button;
import com.example.michadomagaa.javaprojektdelta.com.buttons.decorator.Item;
import com.example.michadomagaa.javaprojektdelta.com.buttons.decorator.OnClickItemDecorator;
import com.example.michadomagaa.javaprojektdelta.com.filter.mvc.FilterController;

public class ImageActivity extends AppCompatActivity {

    private ImageView iv;
    private RelativeLayout edit_bt;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        String name = getIntent().getStringExtra("name");
        String path = getIntent().getStringExtra("path");
        tv1 = (TextView) findViewById(R.id.name_view);
        tv1.setText(name);

        final Bitmap bitmap = Imaging.decodeImage(path+"/"+name);
        iv = (ImageView) findViewById(R.id.image_image);
        iv.setImageBitmap(bitmap);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        buttonInit();

        FilterController controller = new FilterController(this);
        controller.initSeekBars();
    }
    private void buttonInit(){
        String names1[] = {"KONTRAST", "NASYCENIE", "ODCIEN", "JASNOSC"};
        String names2[] = {"OBROC", "WYSLIJ", "USTAW", "INFO"};

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Context c = getApplicationContext();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_image);

        ButtonsBuilder builder = new ButtonsBuilder();
        Bar left = builder.initLeftBar(width, height, c, rl);
        Bar right = builder.initRightBar(width, height, c, rl);

        //do tego momentu same relative layouty

        //ponizej wypelniam tekstem i dodaje klikalnosc

        for (int i = 0; i < right.getIdList().size(); i++){
            RelativeLayout tmp_rl = (RelativeLayout) findViewById(right.getIdList().get(i));
            Item tmp_btn = new OnClickItemDecorator(new Button(c, tmp_rl, names1[i], this));
            tmp_btn.completeLayout();
        }

        for (int i = 0; i < left.getIdList().size(); i++){
            RelativeLayout tmp_rl = (RelativeLayout) findViewById(left.getIdList().get(i));
            Item tmp_btn = new OnClickItemDecorator(new Button(c, tmp_rl, names2[i], this));
            tmp_btn.completeLayout();
        }
    }
}
