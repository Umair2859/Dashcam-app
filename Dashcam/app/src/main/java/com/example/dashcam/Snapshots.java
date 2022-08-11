package com.example.dashcam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dashcam.Adapter.Myadapter;
import com.example.dashcam.model.snaps;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Snapshots extends AppCompatActivity {
    RecyclerView recyclerView;
    Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapshots);
        getSupportActionBar().hide();

ImageView imageView=findViewById(R.id.pre);
      TextView  text=findViewById(R.id.textView3);
        recyclerView = findViewById(R.id.ss);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myadapter = new Myadapter(Snapshots.this, getdata());

        recyclerView.setAdapter(myadapter);
        if (getdata().size() == 0)
        {
            text.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            text.setVisibility(View.GONE);


        }


imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
});
    }

    private ArrayList<snaps> getdata() {
        ArrayList<snaps> snapss = new ArrayList<>();
        File file = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Dashcam/");
        snaps s;
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {

                File file1 = files[i];
                s = new snaps();
                s.setName(file1.getName());
                s.setUri(file1.getAbsolutePath());
                s.setDate(String.valueOf(file1.lastModified()));
                long l=file1.length();
                long kbsize=l/1024;
                if(kbsize<1024) {
                    s.setSize(String.valueOf(kbsize)+" kbs");
                }
                else {
                    long inmbs=kbsize/1024;
                    s.setSize(String.valueOf(inmbs)+" mbs");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                s.setDate(String.valueOf(sdf.format(file1.lastModified())));
                snapss.add(s);
            }

        }
        return snapss;


    }


}
