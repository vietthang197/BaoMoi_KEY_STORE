package com.tagroup.thangducanh.baomoi_TA97;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Main2Activity extends AppCompatActivity {

    private WebView webView;
    private AdView mAdView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-9334661269367396~3275072909");
        mAdView3 = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
