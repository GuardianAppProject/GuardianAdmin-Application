package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.guardian.guardianadmin_v1.Transmissions.LogoutWorker;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccount.this, MainListActivity.class);
                startActivity(i);
                finish();

            }
        });

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                onClickLogout();
            }
        });
    }

    private void onClickLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.logout_alert, null);

        Button logoutYes = view.findViewById(R.id.logoutYesButton);
        logoutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutWorker logoutWorker = new LogoutWorker(MyAccount.this);
                logoutWorker.execute("logout",getToken());

                Intent i = new Intent(MyAccount.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button logoutNo = view.findViewById(R.id.logoutNoBtn);
        logoutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        builder.setView(view);
        builder.show();


    }

    public String getToken() {
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