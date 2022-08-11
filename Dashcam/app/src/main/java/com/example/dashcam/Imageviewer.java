package com.example.dashcam;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Imageviewer extends AppCompatActivity {
    private static  final int REQUEST_LOCATION=1;
    ImageView imageView;
    TextView Date;
    TextView a, b, c;
    public int mInterval = 3000; // 3 seconds by default, can be changed later
    public Handler mHandler;
    String latitude,longitude,speed;
    LocationManager locationManager;

    private String MEDIA_PATH = Environment.getExternalStorageDirectory().toString() + "/Pictures/Dashcam/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageviewer);
        getSupportActionBar().hide();
        Date = findViewById(R.id.date);
        Intent i = getIntent();
        String url = i.getStringExtra("picname");
        String fileName = url;
        File f = new File(MEDIA_PATH, fileName);
        imageView = findViewById(R.id.shwimg);
        imageView.setImageURI(Uri.fromFile(f));
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
        a = findViewById(R.id.lat);
        b = findViewById(R.id.longg);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),

                        Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
                {
                }
                else
                {
                    Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                    if (LocationGps !=null)
                    {
                        double lat=LocationGps.getLatitude();
                        double longi=LocationGps.getLongitude();
                        double speedi=LocationGps.getSpeed()*18/5;

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);



                        a.setText(latitude);
                        b.setText(longitude);

                    }
                    else if (LocationNetwork !=null)
                    {
                        double lat=LocationNetwork.getLatitude();
                        double longi=LocationNetwork.getLongitude();

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);
                        a.setText(latitude);
                        b.setText(longitude);

                    }
                    else if (LocationPassive !=null)
                    {
                        double lat=LocationPassive.getLatitude();
                        double longi=LocationPassive.getLongitude();

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);
                        a.setText(latitude);
                        b.setText(longitude);
                    }



                }

            }










        }

