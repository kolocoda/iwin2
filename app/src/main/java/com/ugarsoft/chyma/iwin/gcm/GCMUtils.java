package com.ugarsoft.chyma.iwin.gcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.AnnouncementsDetailsActivity;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.json.JSONException;
import org.json.JSONObject;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by Chyma on 5/21/2016.
 */
public class GCMUtils {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static boolean checkPlayServices(Context context, Activity activity) {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(context);
        // When Play services not found in device
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                // Show Error dialog to install Play services
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                MessageUtil.showAlert(context,
                        "This device doesn't support Play services, App will not work normally");

            }
            return false;
        } else {
           // MessageUtil.showAlert(context,"This device supports Play services, App will work normally");
        }
        return true;
    }

    public static void sendNotification(String message, Context con) {
        Intent intent = new Intent(con, AnnouncementsDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(con, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        try {
            JSONObject jsonObj = new JSONObject(message);// Remove this line later
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(con)
                    .setSmallIcon(R.drawable.smallicon)
                    .setContentTitle(jsonObj.getString("postTitle"))
                    .setContentText(jsonObj.getString("postContent"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) con.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
