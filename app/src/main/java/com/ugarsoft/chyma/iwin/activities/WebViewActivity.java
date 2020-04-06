package com.ugarsoft.chyma.iwin.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ugarsoft.chyma.iwin.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tips);
        setupViews();
    }

    private void setupViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(10f);
        progressBar = (ProgressBar) findViewById(R.id.progBar);
        progressBar.setMax(100);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                WebViewActivity.this.setTitle("Loading...");
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                WebViewActivity.this.setTitle(R.string.payment_channel);
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }


        });
        webView.setWebChromeClient(new MyWebClient());
        webView.loadUrl(this.getIntent().getDataString());
        progressBar.setProgress(0);
    }

    private  class MyWebClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            WebViewActivity.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);

        }


    }

    public void setValue(int progress){
        this.progressBar.setProgress(progress);
    }
}
