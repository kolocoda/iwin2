package com.ugarsoft.chyma.iwin.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.base.HomeActivity;
import com.ugarsoft.chyma.iwin.activities.base.PowerStatsActivity;

import java.util.List;

/**
 * Created by Chyma on 5/18/2016.
 */
public class Notifications {

    public static void notify(Context context, String title, List<String> messages, Intent intent){

        Log.i("TAG", "Start Notification");
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setNumber(messages.size());
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker(messages.get(0));
        mBuilder.setSmallIcon(R.drawable.icon);
        mBuilder.setSound(defaultSoundUri);

        if(messages.size() > 1){
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(messages.size() + " New Stories");

        }else {
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(messages.get(0));

        }


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HomeActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);
        nm.notify(2, mBuilder.build());

    }

    public static void notifyReport(Context context, String title, List<String> messages, Intent intent){

        Log.i("TAG", "Start Notification");
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setNumber(messages.size());
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker(messages.get(0));
        mBuilder.setSmallIcon(R.drawable.icon);
        mBuilder.setSound(defaultSoundUri);

        if(messages.size() > 1){
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(messages.size() + " New Reports");

        }else {
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(messages.get(0));

        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(PowerStatsActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);
        nm.notify(0, mBuilder.build());

    }
}
