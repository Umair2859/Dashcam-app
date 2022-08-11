package com.example.dashcam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashcam.Adapter.Myadapter2;
import com.example.dashcam.model.videos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Myrecordings extends AppCompatActivity  {
TextView text;
    RecyclerView recyclerView;
    Myadapter2 adapt;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecordings);
        getSupportActionBar().hide();
        text=findViewById(R.id.textView3);
ImageView imageView=findViewById(R.id.pre);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
});
        recyclerView = findViewById(R.id.vs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapt = new Myadapter2(Myrecordings.this, getdata());

        recyclerView.setAdapter(adapt);
        if (getdata().size() == 0)
        {
           text.setVisibility(View.VISIBLE);
           recyclerView.setVisibility(View.GONE);
        }
else {
            text.setVisibility(View.GONE);


        }

    }

    private ArrayList<videos> getdata() {
        ArrayList<videos> svid = new ArrayList<>();
        File file = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Movies/dashcamrec/");
        videos v;
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {

                File file1 = files[i];
                v = new videos();
                v.setName(file1.getName());
                v.setUri(file1.getAbsolutePath());
                long l=file1.length();
                long kbsize=l/1024;
                if(kbsize<1024) {
                    v.setSize(String.valueOf(kbsize)+" kbs");
                }
                else {
                    long inmbs=kbsize/1024;
                    v.setSize(String.valueOf(inmbs)+" mbs");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                v.setDate(String.valueOf(sdf.format(file1.lastModified())));

                svid.add(v);

            }

        }
        return svid;


    }



    }

