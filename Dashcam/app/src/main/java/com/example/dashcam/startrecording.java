package com.example.dashcam;


import static com.example.dashcam.R.drawable.mic_off_white_btn;
import static com.example.dashcam.R.drawable.mic_on_white_btn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;

import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class startrecording extends AppCompatActivity implements LocationListener, ImageAnalysis.Analyzer, View.OnClickListener {


    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    LocationManager locationManager;
    PreviewView previewView;
    LinearLayout nom;
    private ImageCapture imageCapture;
    private VideoCapture videoCapture;
    private ImageView bRecord;
    private ImageView rr;
    private LinearLayout bCapture;
    private boolean paused = true;
    String locationText = "";
    LinearLayout Mute;
    String locationLatitude = "";
    String locationLongitude = "";
    String speed = " ";
    String timemax = "5000";
    ImageView MUTEEE;
    String result;
    TextView pos;
    TextView para;
    public int mInterval = 3000; // 3 seconds by default, can be changed later
    public Handler mHandler;
    TextView Date;
    TextView showLocation;
    TextView showLocation2, showspeed;
    String micpos;
    int width;
    int height;
    Chronometer myChronometer;
    long u = 1;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    LinearLayout one, two;


    public void takeme(View view) {
        switch (view.getId()) {

            case R.id.LL2:
                Intent intentt = new Intent(getApplicationContext(), Myrecordings.class);
                startActivity(intentt);
                break;
            case R.id.LL1:
                Intent intentttt = new Intent(getApplicationContext(), Appsettings.class);
                intentttt.putExtra("lastActivityName", "startrecording");
                startActivity(intentttt);
                break;

        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startrecording);
        getSupportActionBar().hide();
        para = findViewById(R.id.param);
        previewView = findViewById(R.id.previewView);
        bCapture = findViewById(R.id.bcapture);
        bRecord = findViewById(R.id.record);
        Date = findViewById(R.id.duration);
        showLocation = findViewById(R.id.lati);
        showLocation2 = findViewById(R.id.longi);
        showspeed = findViewById(R.id.spd);
        rr = findViewById(R.id.white);
        Mute = findViewById(R.id.mic);
        nom = findViewById(R.id.LL1);
        MUTEEE = findViewById(R.id.pic);
        pos = findViewById(R.id.mrec);
        one = findViewById(R.id.LL1);
        two = findViewById(R.id.LL2);
    myChronometer = (Chronometer)findViewById(R.id.chronometer);
        myChronometer.setVisibility(View.GONE);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        para.setText(s1);
        if (s1.isEmpty()) {

            para.setText("mps");
        }
        if (s1 != null && s1.equals("km/h")) {
            u = (long) 3.6;
        }
        SharedPreferences ssh = getSharedPreferences("MysSharedPref", MODE_PRIVATE);
        String s2 = ssh.getString("name2", "");
        if (s2 != null && s2.equals("2 minutes")) {
            timemax = "120000";
        }
        if (s2 != null && s2.equals("5 minutes")) {
            timemax = "300000";
        }
        if (s2 != null && s2.equals("30 minutes")) {
            timemax = "300000";
        }
        if (s2.isEmpty()) {

            timemax = "120000";


        }


        SharedPreferences sssh = getSharedPreferences("MyssSharedPref", MODE_PRIVATE);
        String s3 = sssh.getString("name3", "");
        if (s3 != null && s3.equals("640*480")) {
            width = 640;
            height = 480;

        }
        if (s3 != null && s3.equals("1280*720")) {
            width = 1440;
            height = 1080;
            ;
        }
        if (s3 != null && s3.equals("1920*1080")) {

            width = 1280;
            height = 720;
        }

        SharedPreferences ssssh = getSharedPreferences("MysssSharedPref", MODE_PRIVATE);
        String s4 = ssssh.getString("name4", "");
        if (s3 != null && s3.equals("1920*1080")) {

            width = 1280;
            height = 720;
        }


        if (s4 != null && s4.equals("On app launch")) {
            showspeed.postDelayed(new Runnable() {  //delay button
                public void run() {
                    bRecord.setImageResource(R.drawable.play);
                    rr.setImageResource(R.drawable.ic_red_icon);
                    recordVideo();
                    myChronometer.setBase(SystemClock.elapsedRealtime());
                    myChronometer.setVisibility(View.VISIBLE);
                    myChronometer.start();
                    paused = false;
                }
            }, 100);


            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @SuppressLint("RestrictedApi")
                @Override
                public void run() {
                    bRecord.setImageResource(R.drawable.pause);
                    rr.setImageResource(R.drawable.ic_white_icon);
                    videoCapture.stopRecording();

                    myChronometer.stop();
                    myChronometer.setVisibility(View.GONE);

                    paused = true;
                }

            }, Long.parseLong(String.valueOf(timemax)));
            Handler handler2 = new Handler();
            handler2.postDelayed(() -> {
                mHandler = new Handler();
            }, Long.parseLong(timemax));


        }


        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMicrophoneMute(false);
        Mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioManager.isMicrophoneMute() == false) {
                    audioManager.setMicrophoneMute(true);
                    MUTEEE.setImageResource(mic_off_white_btn);
                    micpos = "Mic off";
                    pos.setText(micpos);


                } else {
                    audioManager.setMicrophoneMute(false);
                    MUTEEE.setImageResource(mic_on_white_btn);
                    micpos = "Mic on";
                    pos.setText(micpos);
                }


            }
        });

//importing date
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);


        bRecord.setTag("start recording"); // Set the initial text of the button

        bCapture.setOnClickListener(this);
        bRecord.setOnClickListener(this);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, getExecutor());

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                mHandler = new Handler();
                startRepeatingTask();
            }
        }, 5000); //5 seconds


        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {


            try {
                getLocation(); //this function can change value of mInterval.

                if (locationText.toString() == "") {

                } else {

                    showLocation.setText(locationLatitude.toString());
                    showLocation2.setText(locationLongitude.toString());
                    showspeed.setText(speed);
                }
            } finally {

                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        locationText = location.getLatitude() + "," + location.getLongitude();
        locationLatitude = location.getLatitude() + "";
        locationLongitude = location.getLongitude() + "";
        speed = String.format("%.1f", location.getSpeed() * u) + " ";

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


// this code is responsible for taking pictures and capturing videos

    Executor getExecutor() {
        return ContextCompat.getMainExecutor(this);
    }

    @SuppressLint("RestrictedApi")
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();
        Preview preview = new Preview.Builder()
                .build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        // Image capture use case
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        // Video capture use case


        videoCapture = new VideoCapture.Builder()
                .setVideoFrameRate(30).
                setTargetResolution(new Size(width, height))
                .build();


        // Image analysis use case
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(getExecutor(), this);

        //bind to lifecycle:
        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageCapture, videoCapture);
    }


    @Override
    public void analyze(@NonNull ImageProxy image) {
        // image processing here for the current frame
        Log.d("TAG", "analyze: got the frame at: " + image.getImageInfo().getTimestamp());
        image.close();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bcapture:
                capturePhoto();
                break;
            case R.id.record:

                if (paused == true) {
                    bRecord.setImageResource(R.drawable.play);
                    rr.setImageResource(R.drawable.ic_red_icon);
                    recordVideo();
                    paused = false;
                    myChronometer.setVisibility(View.VISIBLE);
                    myChronometer.setBase(SystemClock.elapsedRealtime());
                    myChronometer.start();

                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @SuppressLint("RestrictedApi")

                        @Override
                        public void run() {
                            videoCapture.stopRecording();
                            bRecord.setImageResource(R.drawable.pause);
                            rr.setImageResource(R.drawable.ic_white_icon);
                            myChronometer.stop();
                            myChronometer.setVisibility(View.GONE);
                            paused = true;
                        }
                    }, Long.parseLong(String.valueOf(timemax)));
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new

                                                 Runnable() {
                                                     public void run() {
                                                         mHandler = new Handler();
                                                         startRepeatingTask();
                                                     }
                                                 }, Long.parseLong(timemax));
                } else {

                    bRecord.setImageResource(R.drawable.pause);
                    rr.setImageResource(R.drawable.ic_white_icon);
                    videoCapture.stopRecording();
                    myChronometer.stop();
                    myChronometer.setVisibility(View.GONE);
                    paused = true;


                }

        }
    }


    @SuppressLint("RestrictedApi")
    private void recordVideo() {
        if (videoCapture != null) {
            long timestamp = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Movies/" + "dashcamrec/");


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            videoCapture.startRecording(


                    new VideoCapture.OutputFileOptions.Builder(
                            getContentResolver(),
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                    ).build(),
                    getExecutor(),


                    new VideoCapture.OnVideoSavedCallback() {
                        @Override
                        public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {

                            Toast.makeText(getApplicationContext(), "Video has been saved successfully.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                            Toast.makeText(getApplicationContext(), "Error saving video: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }


    private void capturePhoto() {
        long timestamp = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/" + "Dashcam/");


        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(
                        getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).build(),
                getExecutor(),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Toast.makeText(getApplicationContext(), "Photo has been saved successfully.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(getApplicationContext(), "Error saving photo: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public void onRestart() {
        super.onRestart();

    }
}
