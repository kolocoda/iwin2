package com.ugarsoft.chyma.iwin.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.FAQ;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;

import org.apache.commons.lang.WordUtils;

import java.text.SimpleDateFormat;

public class FAQsDetailsActivity extends AppCompatActivity {

    private TextView date;
    private TextView title;
    private TextView postBy;
    private WebView content;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_details);
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

        FontUtil.setDefaultTypeFace(FAQsDetailsActivity.this, date,title,postBy);

    }

    private void loadContent(){
        String check = Pref.getStringValue(FAQsDetailsActivity.this, Constant.TOGGLE.name(), "");
        if(check.equals(Constant.FAQ.name())) {
            setTitle("FAQ");
            
            FAQ news = Pref.getFAQFromSharedPrefs(FAQsDetailsActivity.this);

            date.setText(dateFormat.format(news.getPostDate()));
            title.setText(news.getPostTitle());
            postBy.setText(WordUtils.capitalize(news.getPostBy()));

            content.getSettings().setJavaScriptEnabled(true);
            content.getSettings().setBuiltInZoomControls(true);
            content.getSettings().setDefaultTextEncodingName("utf-8");
            content.setWebChromeClient(new WebChromeClient());
            content.loadData(news.getPostContent(), "text/html; charset=utf-8", "UTF-8");
        }
    }
}
