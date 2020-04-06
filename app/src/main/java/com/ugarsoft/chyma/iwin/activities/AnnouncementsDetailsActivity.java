package com.ugarsoft.chyma.iwin.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.apache.commons.lang.WordUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import me.leolin.shortcutbadger.ShortcutBadger;

public class AnnouncementsDetailsActivity extends AppCompatActivity {

    private TextView date;
    private TextView title;
    private TextView postBy;
    private TextView content;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_details);
        setupViews();
        loadContent();
        clearBadgeCount();
    }

    private void clearBadgeCount() {
        int count = 0;
        Pref.saveToSharedPreference(this, Constant.NOTIFICATION_COUNT.name(), count);
        int badgeCount = count;
        ShortcutBadger.applyCount(this, badgeCount);
        Log.i("Ann Details Activity","Cleared badge count");
    }

    private void setupViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(10f);

        date = (TextView) findViewById(R.id.date);
        title = (TextView) findViewById(R.id.title);
        postBy = (TextView) findViewById(R.id.postBy);
        content = (TextView) findViewById(R.id.content);

        FontUtil.setDefaultTypeFace(AnnouncementsDetailsActivity.this, date,title,postBy,content);

    }

    private void loadContent(){

            setTitle("Announcement");
            
            Announcement announcement = Pref.getAnnouncementContent(AnnouncementsDetailsActivity.this);
            if(announcement != null) {
                date.setText(dateFormat.format(announcement.getPostDate()));
                title.setText(announcement.getPostTitle());
                postBy.setText(WordUtils.capitalize(announcement.getPostBy()));
                content.setText(Html.fromHtml(announcement.getPostContent()));
            }

        new MarkAnnouncement(announcement).execute();
    }

    private class MarkAnnouncement extends AsyncTask<Void, Void, Void> {

        Announcement news;
        DBService dbService;
        public MarkAnnouncement(Announcement news){
            this.news = news;
        }

        @Override
        protected void onPreExecute() {
            dbService = DBService.getInstance(AnnouncementsDetailsActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
                QueryBuilder<Announcement, Long> qb = dao.queryBuilder();
                qb.where().eq("postId", news.getPostId());
                Announcement announcement = qb.queryForFirst();

                announcement.setRead(true);
                dao.update(announcement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
