package com.sumit.skcreditappmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class TermConView extends AppCompatActivity {
    private String term_url = "https://www.google.co.in";
    private WebView wv_terms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_con_view);

        getSupportActionBar().hide();
        wv_terms = (WebView)findViewById(R.id.wv_terms);
        wv_terms.setWebChromeClient(new WebChromeClient());
        wv_terms.loadUrl(term_url);
        //enable java Script
        wv_terms.getSettings().setJavaScriptEnabled(true);
        wv_terms.getSettings().setSupportZoom(true);
    }
}