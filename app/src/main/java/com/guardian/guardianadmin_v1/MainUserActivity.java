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
        TextView safetyStatus = findViewById(R.id.safetyStatus);

        String number = data[2];
        phoneNumberText.setText(number);
        String username = data[3];
        usernameText.setText(username);
        String safety = data[4];
        if(isNumeric(safety)) {
            textView1.setText((Math.round(Double.parseDouble(safety) * 100.0) / 100.0) + "%");
            safetyStatus.setText(EncodeDecode.calculateStatusAlgorithm(Math.round(Double.parseDouble(safety))));
        } else {
            textView1.setText(safety);
        }

        String speed = data[5];
        if(isNumeric(speed)) {
            textView2.setText(EncodeDecode.speedDecode(Double.parseDouble(speed)));
        } else {
            textView2.setText(speed);
        }
        String nonstop = data[6];
        if(isNumeric(nonstop)) {
            textView3.setText(EncodeDecode.withoutStopDecode(Double.parseDouble(nonstop)));
        } else {
            textView3.setText(nonstop);
        }
        String vibration = data[7];
        if(isNumeric(vibration)) {
            textView4.setText(EncodeDecode.vibrationDecode(Double.parseDouble(vibration)));
        } else {
            textView4.setText(vibration);
        }
        String sleep = data[8];
        if(isNumeric(sleep)) {
            textView5.setText(EncodeDecode.sleepDecode(Double.parseDouble(sleep)));
        } else {
            textView5.setText(sleep);
        }
        String acceleration = data[9];
        if(isNumeric(acceleration)) {
            textView6.setText(EncodeDecode.sleepDecode(Double.parseDouble(acceleration)));
        } else {
            textView6.setText(acceleration);
        }
        String time = data[10];
        if(isNumeric(time)) {
            textView7.setText(EncodeDecode.timeDecode(Double.parseDouble(time)));
        } else {
            textView7.setText(time);
        }
        String danger_zone = data[11];
        if(isNumeric(danger_zone)) {
            textView8.setText(EncodeDecode.nearCitiesDecode(Double.parseDouble(danger_zone)));
        } else {
            textView8.setText(danger_zone);
        }
        String weather = data[12];
        if(isNumeric(weather)) {
            textView9.setText(EncodeDecode.weatherDecode(Double.parseDouble(weather)));
        } else {
            textView9.setText(weather);
        }
        String road_type = data[13];
        if(isNumeric(road_type)) {
            textView10.setText(EncodeDecode.roadTypeDecode(Double.parseDouble(road_type)));
        } else {
            textView10.setText(road_type);
        }
        String traffic = data[14];
        if(isNumeric(traffic)) {
            textView11.setText(EncodeDecode.monthDecode(Double.parseDouble(traffic)));
        } else {
            textView11.setText(traffic);
        }


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

                                // Gregorian
                                JalaliCalendar.jalaliToGregorian(new JalaliCalendar.YearMonthDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));

                                System.err.println(persianCalendar.getPersianMonthName());
                                System.err.println("===========:::");
                                System.err.println(primeCalendar.toCivil().getMonth() + "-" + primeCalendar.toCivil().getDayOfMonth() + "-" + primeCalendar.toCivil().getYear());
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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MainUserActivity.this, MainListActivity.class);
        startActivity(i);
        finish();
    }
}