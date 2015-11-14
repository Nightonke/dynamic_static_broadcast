package com.nightonke.ex_05;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button sendDynamicBroadcastButton;
    private Button sendStaticBroadcastButton;
    private EditText editText;

    private boolean registered = false;

    private MyDynamicBroadcastReceiver myDynamicBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = (Button)findViewById(R.id.register);
        sendDynamicBroadcastButton = (Button)findViewById(R.id.send);
        sendStaticBroadcastButton = (Button)findViewById(R.id.send2);
        editText = (EditText)findViewById(R.id.edit);

        myDynamicBroadcastReceiver = new MyDynamicBroadcastReceiver();

        sendDynamicBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send a dynamic broadcast
                // it can be received when registered
                Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                intent.putExtra("MESSAGE", editText.getText().toString());
                sendBroadcast(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter dynamic_filter = new IntentFilter();
                dynamic_filter.addAction("android.appwidget.action.APPWIDGET_UPDATE");
                if (registered) {
                    unregisterReceiver(myDynamicBroadcastReceiver);
                    registerButton.setText("Register");
                } else {
                    registerReceiver(myDynamicBroadcastReceiver, dynamic_filter);
                    registerButton.setText("Unregister");
                }
                registered = !registered;
            }
        });

        sendStaticBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyStaticBroadcastReceiver.class);
                intent.putExtra("MESSAGE", editText.getText().toString());
                sendBroadcast(intent);
            }
        });

    }
}
