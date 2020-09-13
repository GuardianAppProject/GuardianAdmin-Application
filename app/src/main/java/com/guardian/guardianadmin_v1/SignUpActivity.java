package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.guardian.PasswordManager.AsteriskPasswordTransformationMethod;
import com.guardian.PasswordManager.DoNothingTransformationMethod;
import com.guardian.guardianadmin_v1.Transmissions.LoginRegisterWorker;

import static java.lang.Thread.sleep;

public class SignUpActivity extends AppCompatActivity {


    LinearLayout signUpProgress;
    private boolean hidePassword = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText edittext = (EditText) findViewById(R.id.password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        final ImageButton showPasswordBtn = findViewById(R.id.passwordLock);
        showPasswordBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hidePassword) {
                    edittext.setTransformationMethod(new DoNothingTransformationMethod());
                    showPasswordBtn.setBackgroundResource(R.drawable.padlock1);
                    hidePassword = false;
                } else {
                    edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                    showPasswordBtn.setBackgroundResource(R.drawable.padlock2);
                    hidePassword = true;
                }

            }
        });


        final Button signUp = (Button) findViewById(R.id.SignUpButt);
        signUpProgress = findViewById(R.id.signUpProgress);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                registerResult = "asd";
                onSignUpClick(v);
                try {
                    signUpProgress.setVisibility(View.VISIBLE);
                    sleep(250);
                    signUpProgress.setVisibility(View.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!isRegisterResultValid()) return;

                Intent i = new Intent(SignUpActivity.this, MainListActivity.class);
                startActivity(i);
                finish(); // baade signup moafagh koja bere?
            }
        });

    }

    private void onSignUpClick(View v){
        EditText enteredUN = (EditText) findViewById(R.id.username);
        EditText enteredPW = (EditText) findViewById(R.id.password);
        EditText enteredNum = (EditText) findViewById(R.id.phoneNum);

        String username = enteredUN.getText().toString();
        String password = enteredPW.getText().toString();
        String phone = enteredNum.getText().toString();

        LoginRegisterWorker registerWorker = new LoginRegisterWorker(this);
        registerWorker.execute("register",username,password,phone);
        try {
            signUpProgress.setVisibility(View.VISIBLE);
            sleep(500);
            signUpProgress.setVisibility(View.INVISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String registerResult = "asd";

    public static void setRegisterResult(String result){
        registerResult  = result;
    }

    private boolean isRegisterResultValid(){
        return registerResult.contains("register complete");
    }
}