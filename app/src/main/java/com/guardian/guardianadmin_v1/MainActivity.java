package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int TIME_OUT = 2500; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(2600, 2600) {

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
}