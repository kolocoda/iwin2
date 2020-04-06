package com.ugarsoft.chyma.iwin.activities;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.utils.CompatUtil;
import com.ugarsoft.chyma.iwin.utils.FontUtil;

public class AboutUsActivity extends AppCompatActivity {

    TextView text;
    private boolean shouldAnimate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setupViews();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (CompatUtil.hasLolipop() && shouldAnimate) {
            toolbar.setAlpha(0f);
            toolbar.animate().translationZBy(40f).alphaBy(1f).setDuration(1500).start();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        text = (TextView) findViewById(R.id.text);
        FontUtil.setDefaultTypeFace(this, text);

    }
    String description = "Energy Centre is a product of the Round table for Growth and Development of power (RODEP). This application streamline the activities of the Independent energy watch initiative (IWIN), availing users the opportunity to track the happenings of the power sector on the go.\n" +
            "\n" +
            "Version 1.0.5";
}
