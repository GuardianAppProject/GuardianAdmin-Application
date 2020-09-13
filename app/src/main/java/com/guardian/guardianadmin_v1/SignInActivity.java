package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.guardian.PasswordManager.AsteriskPasswordTransformationMethod;
import com.guardian.PasswordManager.DoNothingTransformationMethod;
import com.guardian.guardianadmin_v1.Transmissions.LoginRegisterWorker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Thread.sleep;

public class SignInActivity extends AppCompatActivity {

    LinearLayout signInProgress;
    private boolean hidePassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText edittext = (EditText) findViewById(R.id.password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        final ImageButton showPasswordBtn = findViewById(R.id.passwordLock);
        signInProgress = findViewById(R.id.signUpProgress);

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
    }

    protected void onSignInClick(View v){
        EditText enteredUN = (EditText) findViewById(R.id.username);
        EditText enteredPW = (EditText) findViewById(R.id.password);
        String username = enteredUN.getText().toString();
        String password = enteredPW.getText().toString();
        LoginRegisterWorker loginWorker = new LoginRegisterWorker(this);
        loginWorker.execute("login",username,password);
        try {
            signInProgress.setVisibility(View.VISIBLE);
            sleep(500);
            signInProgress.setVisibility(View.INVISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String loginResult = "asd";

    public static void setLoginResult(String result){
        loginResult  = result;
    }

    private boolean isLoginResultValid(){
        return loginResult.contains("login complete");
    }

    //=================

    public void write(String toWrite) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("tokenFile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(toWrite);
            outputWriter.close();

            //display file saved message

        } catch (Exception e) {
            e.printStackTrace();
        }
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