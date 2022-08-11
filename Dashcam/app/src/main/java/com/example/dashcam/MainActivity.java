package com.example.dashcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    public LinearLayout imgmain;

    public void take(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));


    }

    public void views() {
        imgmain = findViewById(R.id.nxtarw);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        views();
    }
}