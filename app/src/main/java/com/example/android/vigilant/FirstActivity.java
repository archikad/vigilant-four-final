package com.example.android.vigilant;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.security.Permission;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * Created by Claire on 4/3/2018.
 */


public class FirstActivity extends AppCompatActivity {
    final int PERMISSION_REQUEST_CODE = 111;
    private Button second_button;
    private Button first_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button first_button = (Button) findViewById(R.id.first_button);
        Button second_button = (Button) findViewById(R.id.second_button);
        Button third_button = (Button) findViewById(R.id.third_button);
        Button more_button = (Button) findViewById(R.id.more_button);
        second_button.setEnabled(false);

        final ArrayList<String> contacts = getIntent().getStringArrayListExtra("CONTACTS");

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            second_button.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        }
        second_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkPermission(Manifest.permission.SEND_SMS)) {


                    // TODO GET THIS INTENT RECIEVER TO WORK



                    //String[] contacts = getIntent().getExtras().getStringArray("CONTACTS");

                    //String[] contacts = new String[] {"3345", "5554", "5556"};


                    for(int i = 0; i < contacts.size(); i++) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(contacts.get(i), null, "Please call for help or pick me up now. I think I'm in danger.", null, null);
                    }

                } else {
                    Toast.makeText(FirstActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }

            }
        });


        first_button.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            first_button.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        }

        first_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    /*String SENT = "SMS_SENT";

                    String DELIVERED = "SMS_DELIVERED";

                    PendingIntent sentPI = PendingIntent.getBroadcast(FirstActivity.this, 0, new Intent(SENT), 0);

                    PendingIntent deliveredPI = PendingIntent.getBroadcast(FirstActivity.this, 0, new Intent(DELIVERED), 0);



                    //--- When the SMS has been sent --

                    registerReceiver(new BroadcastReceiver()

                    {
                        public void onReceive(Context arg0, Intent arg1)
                        {
                            switch (getResultCode())
                            {
                                case Activity.RESULT_OK:
                                    Toast.makeText(getBaseContext(), "SMS sent",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                    } , new IntentFilter(SENT));
                    //delivered//*/

                if (checkPermission(Manifest.permission.SEND_SMS)) {

                    ArrayList<String> contacts = getIntent().getStringArrayListExtra("CONTACTS");

                    for(int i = 0; i < contacts.size(); i++) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(contacts.get(i), null, "I'm in a weird situation. Stand by.", null, null);
                    }
                } else {
                    Toast.makeText(FirstActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }

            }
        });

        third_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "911";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }

        });

        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(FirstActivity.this, HomeActivity.class);
                FirstActivity.this.startActivity(gotohome);

                Intent sendcontacts = new Intent(FirstActivity.this, HomeActivity.class);
                sendcontacts.putStringArrayListExtra("CONTACTSLIST", contacts);
                FirstActivity.this.startActivity(sendcontacts);
            }
        });



    }




    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    second_button.setEnabled(true);
                    first_button.setEnabled(true);

                }
                return;
            }
        }
    }}






// ...







