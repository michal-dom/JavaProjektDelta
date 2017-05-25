package com.example.michadomagaa.javaprojektdelta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.main_gallery);
        tv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        GalleryActivity.class);
                intent.putExtra("root", "root");
                startActivity(intent);
            }
        });

        tv2 = (TextView) findViewById(R.id.main_camera);
        tv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        GalleryActivity.class);
                intent.putExtra("root", "root");
                startActivity(intent);
            }
        });
    }



}
