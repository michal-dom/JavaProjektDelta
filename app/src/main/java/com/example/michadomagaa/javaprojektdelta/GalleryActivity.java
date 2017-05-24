package com.example.michadomagaa.javaprojektdelta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private String currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = (GridView) findViewById(R.id.grid_gallery);
        gridViewAdapter = new GridViewAdapter(this, R.layout.gallery_grid_item, getData());

        gridView.setAdapter(gridViewAdapter);

        final int size = gridView.getChildCount();
        Log.e("size", size+"");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                ImageItems item = (ImageItems) parent.getItemAtPosition(i);
                setMainImage(item.getTitle(), item.getImage(), item.getSize());
                currentImage = item.getTitle();
            }
        });

        RelativeLayout e_bt = (RelativeLayout) findViewById(R.id.gallery_edit);

        e_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, ImageActivity.class);
                intent.putExtra("name", currentImage);
                intent.putExtra("path", getFolderPath()+"/Camera");
                startActivity(intent);
            }
        });

    }
    private String getFolderPath(){
        File dcimFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        return dcimFile.getPath();
    }

    private void setMainImage(String p, Bitmap b, long s){
        ImageView iv = (ImageView) findViewById(R.id.gallery_main_img);
        iv.setImageBitmap(b);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView tv1 = (TextView) findViewById(R.id.gallery_main_name);
        tv1.setText(p);

        TextView tv2 = (TextView) findViewById(R.id.gallery_main_size);
        tv2.setText(((float) s/1024)+" kB");


    }

    private ArrayList<ImageItems> getData(){
        final ArrayList<ImageItems> imageItemes = new ArrayList<>();
        File f = new File(getFolderPath()+"/Camera");
        File[] files = f.listFiles();

        setMainImage(files[0].getName(),Imaging.decodeImage(f.getPath() + "/" + files[0].getName()), files[0].length());
        currentImage = files[0].getName();
        for(int i = 0; i < files.length; i++){
            final String imagePath = f.getPath() + "/" + files[i].getName();
            Bitmap bitmap = Imaging.decodeImage(imagePath);

            imageItemes.add(new ImageItems(bitmap, files[i].getName(), files[i].length()));
        }
        return imageItemes;
    }
}

