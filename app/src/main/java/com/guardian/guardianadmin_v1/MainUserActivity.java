package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class MainUserActivity extends AppCompatActivity {
    private TextView usernameText,phoneNumberText,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;
    private static String[] data = new String[15];
    private static boolean isDataReady;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUserActivity.this, MainListActivity.class);
                startActivity(i);
                finish();
            }
        });

        usernameText = findViewById(R.id.usernameTextView);
        phoneNumberText = findViewById(R.id.phoneNumberTextView);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);
        textView5 = findViewById(R.id.text5);
        textView6 = findViewById(R.id.text6);
        textView7 = findViewById(R.id.text7);
        textView8 = findViewById(R.id.text8);
        textView9 = findViewById(R.id.text9);
        textView10 = findViewById(R.id.text10);
        textView11 = findViewById(R.id.text11);

        String number = data[2];
        phoneNumberText.setText(number);
        String username = data[3];
        usernameText.setText(username);
        String safety = data[4];
        textView1.setText(safety);
        String speed = data[5];
        textView2.setText(speed);
        String nonstop = data[6];
        textView3.setText(nonstop);
        String vibration = data[7];
        textView4.setText(vibration);
        String sleep = data[8];
        textView5.setText(sleep);
        String acceleration = data[9];
        textView6.setText(acceleration);
        String time = data[10];
        textView7.setText(time);
        String danger_zone = data[11];
        textView8.setText(danger_zone);
        String weather = data[12];
        textView9.setText(weather);
        String road_type = data[13];
        textView10.setText(road_type);
        String traffic = data[14];
        textView11.setText(traffic);




        Button date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersianDatePickerDialog picker = new PersianDatePickerDialog(MainUserActivity.this)
                        .setPositiveButtonString("تایید")
                        .setNegativeButton("بستن")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setMinYear(1300)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
//                        .setInitDate(initDate)
                        .setActionTextColor(Color.GRAY)
//                        .setTypeFace(new Typeface())
                        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                        .setShowInBottomSheet(true)
                        .setListener(new Listener() {
                            @Override
                            public void onDateSelected(PersianCalendar persianCalendar) {

                                Toast.makeText(MainUserActivity.this, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });
    }

    public static void updateUserData(String[] newData){
        isDataReady = true;
        data = newData;
        //inja ans.split dare az onvar miad, chizmiz haro tike tike mikonim set mikonim roye textbox haye xml

    }
}