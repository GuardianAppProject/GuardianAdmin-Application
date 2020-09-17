package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guardian.guardianadmin_v1.PasswordManager.AsteriskPasswordTransformationMethod;
import com.guardian.guardianadmin_v1.PasswordManager.DoNothingTransformationMethod;
import com.guardian.guardianadmin_v1.Transmissions.LoginRegisterWorker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        TextView messageText = findViewById(R.id.messageTextSignIn);
        messageText.setText("");


        final EditText edittext = (EditText) findViewById(R.id.password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        final ImageButton showPasswordBtn = findViewById(R.id.passwordLock);
        signInProgress = findViewById(R.id.signInProgress);

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

        Button signUp = (Button) findViewById(R.id.signInUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();

            }
        });


        final Button loginButton = findViewById(R.id.SignUpButt);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginResult = "asd";
                onSignInClick(v);
                try {
                    signInProgress.setVisibility(View.VISIBLE);
                    sleep(250);
                    signInProgress.setVisibility(View.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!isLoginResultValid()) return;
//                saveToken(loginResult.substring(25));
                write(loginResult.substring(37));
                Toast.makeText(SignInActivity.this, loginResult.substring(37), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignInActivity.this, MainListActivity.class);
                startActivity(i);
                finish();
            }
        });


        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    loginButton.performClick();
                }
                return false;
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
            MainActivity.setToken(toWrite);
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