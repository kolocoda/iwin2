package com.ugarsoft.chyma.iwin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;

public class AlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.example.chyma.iwin.alarm";

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver","Alarm is triggered");
        if(WebServiceImpl.deviceIsConnected(context)) {
            Intent i = new Intent(context, NewsRefreshService.class);
            context.startService(i);
        }

    }
}
