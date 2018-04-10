package com.example.android.vigilant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button button_continue = (Button) findViewById(R.id.button_continue);

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
//            when sign up button is clicked it takes you to SignUpBasicInfo
            public void onClick(View v) {
                Intent GotoSignUpBasic = new Intent(WelcomeActivity.this, ContactsActivity.class);
                WelcomeActivity.this.startActivity(GotoSignUpBasic);
    }


});}}
