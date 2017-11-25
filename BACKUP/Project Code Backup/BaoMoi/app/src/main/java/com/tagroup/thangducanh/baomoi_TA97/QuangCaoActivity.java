package com.tagroup.thangducanh.baomoi_TA97;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;



/**
 * Main Activity. Inflates main activity xml and implements RewardedVideoAdListener.
 */
public class QuangCaoActivity extends Activity implements RewardedVideoAdListener {
    private   String quangcaoid = "ca-app-pub-9334661269367396/8043118195";

    private   String ungdungid = "ca-app-pub-9334661269367396~3275072909";

    private   long thoigianchay = 14;
    private   int kethucgame = 1;

    private int tinhdiem;
    private TextView tinhdiemText;
    private RewardedVideoAd NhanVideoADs;

    private long thoigiancho;
    private CountDownTimer CDT_chay;
    private boolean ketthucgame;
    private boolean tamdunggame;

    private Button btnThuLai;
    private Button hienthivd;
    private Button btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quang_cao);

        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, ungdungid);

        NhanVideoADs = MobileAds.getRewardedVideoAdInstance(this);
        NhanVideoADs.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        // Create the "retry" button, which tries to show an interstitial between game plays.
        btnThuLai = findViewById(R.id.retry_button);
        btnThuLai.setVisibility(View.INVISIBLE);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        // Create the "show" button, which shows a rewarded video if one is loaded.
        hienthivd = findViewById(R.id.watch_video);
        hienthivd.setVisibility(View.INVISIBLE);
        hienthivd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedVideo();
            }
        });

        // Display current coin count to user.
        tinhdiemText = findViewById(R.id.coin_count_text);
        tinhdiem = 0;
        tinhdiemText.setText("Số lần xem: " + tinhdiem);

        startGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseGame();
        NhanVideoADs.pause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!ketthucgame && tamdunggame) {
            resumeGame();
        }
        NhanVideoADs.resume(this);
    }

    private void pauseGame() {
        CDT_chay.cancel();
        tamdunggame = true;
    }

    private void resumeGame() {
        createTimer(thoigiancho);
        tamdunggame = false;
    }

    private void loadRewardedVideoAd() {
        if (!NhanVideoADs.isLoaded()) {
            NhanVideoADs.loadAd(quangcaoid, new AdRequest.Builder().build());
        }
    }

    private void addCoins(int coins) {
        tinhdiem = tinhdiem + coins;
        tinhdiemText.setText("Số lần xem: " + tinhdiem);
    }

    private void startGame() {
        // Hide the retry button, load the ad, and start the timer.
        btnThuLai.setVisibility(View.INVISIBLE);
        hienthivd.setVisibility(View.INVISIBLE);
        loadRewardedVideoAd();
        createTimer(thoigianchay);
        tamdunggame = false;
        ketthucgame = false;
    }

    // Create the game timer, which counts down to the end of the level
    // and shows the "retry" button.
    private void createTimer(long time) {
        final TextView textView = findViewById(R.id.timer);
        if (CDT_chay != null) {
            CDT_chay.cancel();
        }
        CDT_chay = new CountDownTimer(time * 1000, 50) {
            @Override
            public void onTick(long millisUnitFinished) {
                thoigiancho = ((millisUnitFinished / 1000) + 1);
                textView.setText("Hãy đợi: " + thoigiancho+"s để xem");
            }

            @Override
            public void onFinish() {
                if (NhanVideoADs.isLoaded()) {
                    hienthivd.setVisibility(View.VISIBLE);
                }
                textView.setText("Hãy click vào XEM VIDEO !");
                addCoins(kethucgame);
                btnThuLai.setVisibility(View.VISIBLE);
                ketthucgame = true;
            }
        };
        CDT_chay.start();
    }

    private void showRewardedVideo() {
        hienthivd.setVisibility(View.INVISIBLE);
        if (NhanVideoADs.isLoaded()) {
            NhanVideoADs.show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
        // Preload the next video ad.
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this,
                String.format(" onRewarded! currency: %s amount: %d", reward.getType(),
                        reward.getAmount()),
                Toast.LENGTH_SHORT).show();
        addCoins(reward.getAmount());
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "Cám ơn bạn đã xem video", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "Không thể tải video", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onBackPressed() {
        finish();
    }
}
