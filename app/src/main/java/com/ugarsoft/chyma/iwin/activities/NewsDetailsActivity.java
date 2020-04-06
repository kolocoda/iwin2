package com.ugarsoft.chyma.iwin.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang.WordUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class NewsDetailsActivity extends AppCompatActivity {

    private TextView date;
    private TextView title;
    private TextView postBy;
    private WebView content;
    private ImageView newsImage;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        setupViews();
        loadContent();
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
        content = (WebView) findViewById(R.id.content);
        newsImage = (ImageView) findViewById(R.id.newsImage);
        newsImage.setVisibility(View.GONE);

        FontUtil.setDefaultTypeFace(NewsDetailsActivity.this, date,postBy);
        FontUtil.setNewsBoldTypeFace(NewsDetailsActivity.this, title);
        setTitle("Energy News");
    }

    private void loadContent(){
        String check = Pref.getStringValue(NewsDetailsActivity.this, Constant.TOGGLE.name(), "");
        if(check.equals(Constant.POWER_NEWS.name())) {
            PowerNews news = Pref.getNewsContent(NewsDetailsActivity.this);

            date.setText(dateFormat.format(news.getPostDate()));
            title.setText(news.getPostTitle());
            postBy.setText(WordUtils.capitalize(news.getPostBy()));
            String imgurl = news.getPostImage();
            if (imgurl != null && !imgurl.isEmpty()){
               // new PictureUtil.PictureLoader(newsImage, imgurl).execute();
                Picasso.with(NewsDetailsActivity.this).load(imgurl)
                        .placeholder(R.drawable.ic_menu_news)
                        .into(newsImage);
                newsImage.setVisibility(View.VISIBLE);
            }

            content.getSettings().setJavaScriptEnabled(true);
            content.getSettings().setBuiltInZoomControls(true);
            content.getSettings().setDefaultTextEncodingName("utf-8");
            content.setWebChromeClient(new WebChromeClient());
            content.loadData(news.getPostContent(), "text/html; charset=utf-8", "UTF-8");
        }

    }


}
