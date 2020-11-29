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

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.common.operators.CalendarField;

import java.util.Arrays;
import java.util.Calendar;

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
        /*textView2.setText(EncodeDecode.speedDecode(Double.parseDouble(speed)));
        String nonstop = data[6];
        textView3.setText(EncodeDecode.withoutStopDecode(Double.parseDouble(nonstop)));
        String vibration = data[7];
        textView4.setText(EncodeDecode.vibrationDecode(Double.parseDouble(vibration)));
        String sleep = data[8];
        textView5.setText(EncodeDecode.sleepDecode(Double.parseDouble(sleep)));
        String acceleration = data[9];
        textView6.setText(EncodeDecode.sleepDecode(Double.parseDouble(acceleration)));
        String time = data[10];
        textView7.setText(EncodeDecode.timeDecode(Double.parseDouble(time)));
        String danger_zone = data[11];
        textView8.setText(EncodeDecode.nearCitiesDecode(Double.parseDouble(danger_zone)));
        String weather = data[12];
        textView9.setText(EncodeDecode.weatherDecode(Double.parseDouble(weather)));
        String road_type = data[13];
        textView10.setText(EncodeDecode.roadTypeDecode(Double.parseDouble(road_type)));
        String traffic = data[14];
        textView11.setText(EncodeDecode.monthDecode(Double.parseDouble(traffic)));*/




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
                                PrimeCalendar primeCalendar = new com.aminography.primecalendar.persian.PersianCalendar();
                                primeCalendar.set(Calendar.DAY_OF_MONTH,persianCalendar.getPersianDay());
                                primeCalendar.set(Calendar.MONTH,persianCalendar.getPersianMonth());
                                primeCalendar.set(Calendar.YEAR,persianCalendar.getPersianYear());
                                System.err.println(persianCalendar.getPersianMonthName());
                                System.err.println("===========:::");
                                System.err.println(primeCalendar.toCivil());
                                System.err.println(primeCalendar.getShortDateString());


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