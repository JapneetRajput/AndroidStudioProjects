package com.example.batteryalerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {

    TextView status;

    BatteryReceiver(TextView status){
        this.status=status;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int percentage = intent.getIntExtra("level",0);
        status.setText(percentage+"%");
    }
}
