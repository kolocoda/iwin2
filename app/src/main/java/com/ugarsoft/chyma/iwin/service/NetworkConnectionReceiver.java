package com.ugarsoft.chyma.iwin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;

public class NetworkConnectionReceiver extends BroadcastReceiver {
    public NetworkConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NetworkReceiver","Network has Changed");
        if(WebServiceImpl.deviceIsConnected(context)) {
            Log.i("NetworkReceiver","Network is on");
            Intent i = new Intent(context, NewsRefreshService.class);
            context.startService(i);
        }
    }
}
