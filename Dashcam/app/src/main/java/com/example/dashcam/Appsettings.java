package com.example.dashcam;

import static com.example.dashcam.R.id.mps;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Appsettings extends AppCompatActivity {
    LinearLayout L1, L2, L3, L4, L5;
    Dialog mdialog1, mdialog2, mdialog3, mdialog4, mdialog5;
    boolean m = true;
    Intent intent;
    TextView Unit;
    String val;
    TextView textView1;
    TextView resol;
    TextView time;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    SharedPreferences ssharedPreferences;
    SharedPreferences.Editor myEdit1;
    SharedPreferences sssharedPreferences;
    SharedPreferences.Editor myEdit2;
    SharedPreferences ssssharedPreferences;
    SharedPreferences.Editor myEdit3;
    RadioButton Btn1, Btn2;
    SharedPreferences settings;
    SharedPreferences.Editor editorun;
    String getName;
    Intent intent8;
    SharedPreferences settings2;
    SharedPreferences.Editor editorun2;
    SharedPreferences settings3;
    SharedPreferences.Editor editorun3;
    SharedPreferences settings4;
    SharedPreferences.Editor editorun4;


    public void dcim(View view) {
        switch (view.getId()) {
            case R.id.SU:
                mdialog1.setContentView(R.layout.customdialouge);
                mdialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                mdialog1.show();

                RadioGroup rg = mdialog1.findViewById(R.id.radiogroup1);
                RadioButton btn1 = mdialog1.findViewById(R.id.k);
                RadioButton btn2 = mdialog1.findViewById(mps);
                TextView txt = mdialog1.findViewById(R.id.can);
                SharedPreferences getun = getSharedPreferences("YOUR_PREF_NAME", 0);
                int snowDensity = getun.getInt("SNOW_DENSITY", 2);
                if (snowDensity == 2) {

                    btn2.setChecked(true);
                }

                if (snowDensity == 1) {
                    btn1.setChecked(true);
                } else if (snowDensity == 0) {
                    btn2.setChecked(true);
                }
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog1.dismiss();
                    }
                });
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.k:
                                settings = getSharedPreferences("YOUR_PREF_NAME", 0);
                                editorun = settings.edit();
                                editorun.putInt("SNOW_DENSITY", 1);
                                editorun.commit();
                                val = "km/h";
                                sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                myEdit = sharedPreferences.edit();
                                myEdit.putString("name", val);
                                myEdit.apply();
                                Unit.setText(val);
                                break;
                            case R.id.mps:
                                settings = getSharedPreferences("YOUR_PREF_NAME", 0);
                                editorun = settings.edit();
                                editorun.putInt("SNOW_DENSITY", 0);
                                editorun.commit();

                                val = "mps";
                                sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                myEdit = sharedPreferences.edit();
                                myEdit.putString("name", val);
                                myEdit.apply();
                                Unit.setText(val);
                                mdialog1.dismiss();

                        }
                    }
                });
                break;

            case R.id.Vf:
                mdialog3.setContentView(R.layout.customthree);
                mdialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mdialog3.show();
                RadioGroup rg3 = mdialog3.findViewById(R.id.radiog3);
                RadioButton min1 = mdialog3.findViewById(R.id.tmin);
                RadioButton min2 = mdialog3.findViewById(R.id.fmin);
                RadioButton min3 = mdialog3.findViewById(R.id.tnmin);
                SharedPreferences getunn = getSharedPreferences("YOUR_PREF_NAME2", 0);
                int snowDensity2 = getunn.getInt("SNOW_DENSITY2", 3);
                Log.d("opo", "dcim: " + snowDensity2);
                if (snowDensity2 == 3) {
                    min1.setChecked(true);
                } else if (snowDensity2 == 1) {
                    min1.setChecked(true);
                } else if (snowDensity2 == 0) {
                    min2.setChecked(true);
                } else if (snowDensity2 == 2) {
                    min3.setChecked(true);
                }
                TextView txt3 = mdialog3.findViewById(R.id.can);
                txt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog3.dismiss();
                    }
                });
                rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.tmin:
                                settings2 = getSharedPreferences("YOUR_PREF_NAME2", 0);
                                editorun2 = settings2.edit();
                                editorun2.putInt("SNOW_DENSITY2", 1);
                                editorun2.commit();
                                val = "2 minutes";
                                ssharedPreferences = getSharedPreferences("MysSharedPref", MODE_PRIVATE);
                                myEdit1 = ssharedPreferences.edit();
                                myEdit1.putString("name2", val);
                                myEdit1.apply();
                                time.setText(val);
                                mdialog3.dismiss();

                                break;
                            case R.id.tnmin:
                                settings2 = getSharedPreferences("YOUR_PREF_NAME2", 0);
                                editorun2 = settings2.edit();
                                editorun2.putInt("SNOW_DENSITY2", 2);
                                editorun2.commit();
                                val = "30 minutes";
                                ssharedPreferences = getSharedPreferences("MysSharedPref", MODE_PRIVATE);
                                myEdit1 = ssharedPreferences.edit();
                                myEdit1.putString("name2", val);
                                myEdit1.apply();
                                time.setText(val);
                                mdialog3.dismiss();
                                break;
                            case R.id.fmin:
                                settings2 = getSharedPreferences("YOUR_PREF_NAME2", 0);
                                editorun2 = settings2.edit();
                                editorun2.putInt("SNOW_DENSITY2", 0);
                                editorun2.commit();
                                val = "5 minutes";
                                ssharedPreferences = getSharedPreferences("MysSharedPref", MODE_PRIVATE);
                                myEdit1 = ssharedPreferences.edit();
                                myEdit1.putString("name2", val);
                                myEdit1.apply();

                                time.setText(val);
                                mdialog3.dismiss();

                                break;


                        }
                    }
                });


                break;
            case R.id.Vr:
                mdialog4.setContentView(R.layout.customfour);
                mdialog4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mdialog4.show();
                RadioGroup rg2 = mdialog4.findViewById(R.id.r2);
                RadioButton radio1 = mdialog4.findViewById(R.id.first);
                RadioButton radio2 = mdialog4.findViewById(R.id.second);
                RadioButton radio3 = mdialog4.findViewById(R.id.third);
                SharedPreferences getunnn = getSharedPreferences("YOUR_PREF_NAME3", 0);
                int snowDensity3 = getunnn.getInt("SNOW_DENSITY3", 3);
                if (snowDensity3 == 3) {
                    radio3.setChecked(true);
                } else if (snowDensity3 == 1) {
                    radio1.setChecked(true);
                } else if (snowDensity3 == 0) {
                    radio2.setChecked(true);
                } else if (snowDensity3 == 2) {
                    radio3.setChecked(true);
                }


                TextView txt4 = mdialog4.findViewById(R.id.cancel);
                txt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog4.dismiss();
                    }
                });
                rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.first:
                                settings3 = getSharedPreferences("YOUR_PREF_NAME3", 0);
                                editorun3 = settings3.edit();
                                editorun3.putInt("SNOW_DENSITY3", 1);
                                editorun3.commit();
                                val = "1920*1080";
                                sssharedPreferences = getSharedPreferences("MyssSharedPref", MODE_PRIVATE);
                                myEdit2 = sssharedPreferences.edit();
                                myEdit2.putString("name3", val);
                                myEdit2.apply();
                                resol.setText(val);
                                mdialog4.dismiss();
                                break;
                            case R.id.second:
                                settings3 = getSharedPreferences("YOUR_PREF_NAME3", 0);
                                editorun3 = settings3.edit();
                                editorun3.putInt("SNOW_DENSITY3", 0);
                                editorun3.commit();
                                val = "1280*720";
                                sssharedPreferences = getSharedPreferences("MyssSharedPref", MODE_PRIVATE);
                                myEdit2 = sssharedPreferences.edit();
                                myEdit2.putString("name3", val);
                                myEdit2.apply();
                                resol.setText(val);
                                mdialog4.dismiss();
                                break;
                            case R.id.third:
                                settings3 = getSharedPreferences("YOUR_PREF_NAME3", 0);
                                editorun3 = settings3.edit();
                                editorun3.putInt("SNOW_DENSITY3", 2);
                                editorun3.commit();
                                val = "640*480";
                                sssharedPreferences = getSharedPreferences("MyssSharedPref", MODE_PRIVATE);
                                myEdit2 = sssharedPreferences.edit();
                                myEdit2.putString("name3", val);
                                myEdit2.apply();
                                resol.setText(val);
                                mdialog4.dismiss();
                                break;
                        }
                    }
                });


                break;
            case R.id.Rm:
                mdialog5.setContentView(R.layout.customfive);
                mdialog5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mdialog5.show();

                RadioGroup rg5 = mdialog5.findViewById(R.id.R6);
                RadioButton rD5 = mdialog5.findViewById(R.id.man);
                RadioButton rD6 = mdialog5.findViewById(R.id.aut);
                SharedPreferences getunnnn = getSharedPreferences("YOUR_PREF_NAME4", 0);
                int snowDensity4 = getunnnn.getInt("SNOW_DENSITY4", 3);
                if (snowDensity4 == 3) {
                    rD5.setChecked(true);
                } else if (snowDensity4 == 1) {
                    rD5.setChecked(true);
                } else if (snowDensity4 == 0) {
                    rD6.setChecked(true);
                }
                TextView txtcan = mdialog5.findViewById(R.id.cncl);
                txtcan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog5.dismiss();
                    }
                });

                rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.man:
                                settings4 = getSharedPreferences("YOUR_PREF_NAME4", 0);
                                editorun4 = settings4.edit();
                                editorun4.putInt("SNOW_DENSITY4", 1);
                                editorun4.commit();
                                val = "Manual";
                                ssssharedPreferences = getSharedPreferences("MysssSharedPref", MODE_PRIVATE);
                                myEdit3 = ssssharedPreferences.edit();
                                myEdit3.putString("name4", val);
                                myEdit3.apply();
                                textView1.setText(val);
                                mdialog5.dismiss();
                                break;
                            case R.id.aut:
                                settings4 = getSharedPreferences("YOUR_PREF_NAME4", 0);
                                editorun4 = settings4.edit();
                                editorun4.putInt("SNOW_DENSITY4", 0);
                                editorun4.commit();
                                val = "On app launch";
                                ssssharedPreferences = getSharedPreferences("MysssSharedPref", MODE_PRIVATE);
                                myEdit3 = ssssharedPreferences.edit();
                                myEdit3.putString("name4", val);
                                myEdit3.apply();

                                textView1.setText(val);

                                mdialog5.dismiss();
                                break;

                        }
                    }
                });
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Log.d("vvv", "onBackPressed: " + getName);
        if (getName != null && getName.equals("startrecording")) {

            startActivity(new Intent(getApplicationContext(), startrecording.class));

        }
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appsettings);
        getSupportActionBar().hide();
        L1 = findViewById(R.id.SU);
        L2 = findViewById(R.id.pl);
        L3 = findViewById(R.id.Vf);
        L4 = findViewById(R.id.Vr);
        L5 = findViewById(R.id.Rm);
        intent8 = getIntent();
        getName = intent8.getStringExtra("lastActivityName");
        ImageView imageView = findViewById(R.id.pre);
        mdialog1 = new Dialog(this);
        mdialog2 = new Dialog(this);
        mdialog3 = new Dialog(this);
        mdialog4 = new Dialog(this);
        mdialog5 = new Dialog(this);
        textView1 = findViewById(R.id.textdes);
        resol = findViewById(R.id.res);
        time = findViewById(R.id.duration);
        Unit = findViewById(R.id.unit);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        Unit.setText(s1);
        if (s1.isEmpty()) {
            Unit.setText("mps");
        }
        SharedPreferences ssh = getSharedPreferences("MysSharedPref", MODE_PRIVATE);
        String s2 = ssh.getString("name2", "");
        time.setText(s2);
        if (s2.isEmpty()) {

            time.setText("2 minute");
        }
        SharedPreferences sssh = getSharedPreferences("MyssSharedPref", MODE_PRIVATE);
        String s3 = sssh.getString("name3", "");
        resol.setText(s3);
        if (s3.isEmpty()) {

            resol.setText("1600*720");
        }

        SharedPreferences ssssh = getSharedPreferences("MysssSharedPref", MODE_PRIVATE);
        String s4 = ssssh.getString("name4", "");
        textView1.setText(s4);
        if (s4.isEmpty()) {

            textView1.setText("Manaual");
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

L2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Software Update");
            String shareMessage = "\nLets use this amazing application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share Via"));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }



});
    }

}
