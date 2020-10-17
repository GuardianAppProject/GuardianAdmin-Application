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
import android.widget.Toast;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class MainUserActivity extends AppCompatActivity {

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

    public static void updateUserData(String[] data){
        //inja ans.split dare az onvar miad, chizmiz haro tike tike mikonim set mikonim roye textbox haye xml
    }
}