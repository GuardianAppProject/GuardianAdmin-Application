package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.guardian.PasswordManager.AsteriskPasswordTransformationMethod;
import com.guardian.PasswordManager.DoNothingTransformationMethod;

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

    }
}