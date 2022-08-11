package com.example.dashcam;

import static com.example.dashcam.startrecording.hasPermissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Home extends AppCompatActivity {
    LinearLayout one, two, three, four;
    int PERMISSION_ALL = 1;
    LinearLayout Mute;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };


    public void select(View view) {
        switch (view.getId()) {
            case R.id.L1:
                Intent intent = new Intent(Home.this, startrecording.class);
                startActivity(intent);
                break;
            case R.id.L2:
                Intent intentt = new Intent(getApplicationContext(), Myrecordings.class);
                startActivity(intentt);
                break;
            case R.id.L3:
                Intent intenttt = new Intent(getApplicationContext(), Snapshots.class);
                startActivity(intenttt);
                break;
            case R.id.L4:
                Intent intentttt = new Intent(getApplicationContext(), Appsettings.class);

                intentttt.putExtra("lastActivityName2", "Home");
                startActivity(intentttt);
                break;

        }

    }

    public void views() {
        one = findViewById(R.id.L1);
        two = findViewById(R.id.L2);
        three = findViewById(R.id.L3);
        four = findViewById(R.id.L4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        views();
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


    }
}