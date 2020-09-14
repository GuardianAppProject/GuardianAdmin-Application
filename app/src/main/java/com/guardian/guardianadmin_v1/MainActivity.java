package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.guardian.guardianadmin_v1.Transmissions.TokenChecker;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static int TIME_OUT = 2500; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button retryButton = findViewById(R.id.retryButton);

        retryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!GPSAndInternetChecker.check(MainActivity.this)) {
                    retryButton.setVisibility(View.VISIBLE);
                } else {

                    retryButton.setVisibility(View.INVISIBLE);
                    startApp();
                }
            }
        });

        if(!GPSAndInternetChecker.check(MainActivity.this)) {
            retryButton.setVisibility(View.VISIBLE);

        }

        if(retryButton.getVisibility()==View.INVISIBLE) {
            startApp();
        }



    }


    private void startApp(){

        TokenChecker.beginCheck(read(),this);

        new CountDownTimer(2360, 2360) {

            @Override
            public void onTick(long millisUntilFinished) {

            }


            public void onFinish() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, TIME_OUT);

                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

        }.start();
    }

    public String read(){
        //reading text from file
        String string = "";
        try {
            FileInputStream fileIn=openFileInput("tokenFile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[10000];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                string +=readstring;
            }
            InputRead.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
}