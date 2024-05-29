package com.example.batteryalerts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView status;
    Button action;
    BatteryReceiver batteryReceiver;
//    BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
//            status.setText(String.valueOf(level));
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        status=findViewById(R.id.status);
        action=findViewById(R.id.action);
        batteryReceiver=new BatteryReceiver(status);
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity.this.registerReceiver(batteryLevelReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryReceiver);
    }
}