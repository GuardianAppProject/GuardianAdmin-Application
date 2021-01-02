package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        TextView link = (TextView) findViewById(R.id.websiteText);
        String linkText = "<a href='http://guardianapp.ir'>وبسایت گاردین</a>";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());
//        // 2) How to place email address
        TextView email = (TextView) findViewById(R.id.emailText);
        String emailText = "<a href=\"mailto:support@guardianapp.ir\">ایمیل پشتیبانی</a>";
        email.setText(Html.fromHtml(emailText));
        email.setMovementMethod(LinkMovementMethod.getInstance());


        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Support.this, MainListActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Support.this, MainListActivity.class);
        startActivity(i);
        finish();
    }
}