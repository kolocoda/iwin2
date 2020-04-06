package com.ugarsoft.chyma.iwin.gcm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.google.android.gms.gcm.GcmListenerService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class GCMReceiver extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString(GCMConstant.MSG_KEY);
        String notificationType = "";
        try {
            JSONObject jsonObj = new JSONObject(message);// Remove this line later
            notificationType = jsonObj.getString("notifType ");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        if(notificationType.equals(GCMConstant.NOTIFICATION_ANNOUNCEMENT)){
            try {
                JSONObject jsonObj = new JSONObject(message);// Remove this line later
                String title = jsonObj.getString("postTitle");
                String content = jsonObj.getString("postContent");
                String postBy = jsonObj.getString("postBy");
                String date = jsonObj.getString("postDate");

                Announcement announcement = new Announcement();
                announcement.setPostTitle(title);
                announcement.setPostContent(content);
                announcement.setPostDate(new Date().getTime());
                announcement.setPostBy(postBy);


                Pref.saveAnnouncementContent(this, announcement);
                int count = Pref.getIntegerValue(this, Constant.NOTIFICATION_COUNT.name(), 0);
                count += 1;
                Pref.saveToSharedPreference(this, Constant.NOTIFICATION_COUNT.name(), count);
                int badgeCount = count;
                ShortcutBadger.applyCount(this, badgeCount);
                Pref.saveToSharedPreference(this, Constant.NEW_NOTIF.name(), true);
                Log.i("GCM Receiver","Saved to shared prefs and badge");
                Long id = getLatestAnnouncementid();
                new AnnoucementLoader(id).execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent newAnnouncement = new Intent(GCMConstant.NEW_ANNOUNCEMENT);
            LocalBroadcastManager.getInstance(this).sendBroadcast(newAnnouncement);
            Log.i("GCM Reciever","Notification Broadcast Sent");
        }


        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        GCMUtils.sendNotification(message, this);
        // [END_EXCLUDE]
    }

    private Long getLatestAnnouncementid() {
        DBService dbService;
        dbService = DBService.getInstance(GCMReceiver.this);
        List<Announcement> announcements = new ArrayList<>();
        try {
            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy("postId", false);
            announcements.addAll(queryBuilder.query());
            if (!announcements.isEmpty()) {
                return announcements.get(0).getPostId();
            } else {
                return 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private class AnnoucementLoader extends AsyncTask<Void, Void, Boolean> {

        DBService dbService = DBService.getInstance(GCMReceiver.this);
        Long id;
        public AnnoucementLoader(Long id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
                Log.i("Announce Background","Getting announcement");
                responseObject = WebServiceImpl.getAnnouncementAfter(id);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<Announcement> faqs = responseObject.getAnnouncementList();
                    for (Announcement announcement : faqs) {
                        try {
                            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
                            dao.create(announcement);
                        } catch (Exception e) {
                            Log.e("GCMRECEIVER", e.getMessage());
                        }
                    }
                }

            return null;
        }
    }


}
