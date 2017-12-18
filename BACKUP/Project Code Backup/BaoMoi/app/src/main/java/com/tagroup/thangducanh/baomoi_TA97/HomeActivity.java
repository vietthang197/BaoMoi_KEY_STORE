package com.tagroup.thangducanh.baomoi_TA97;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnTheGioi;
    private Button btnTrangChu;
    private Button btnThoiSu;
    private Button btnKinhDoanh;
    private Button btnQuangCao;
    private Button btnGiaiTri;
    private Button btnTheThao;
    private Button btnPhapLuat;
    private Button btnGiaoDuc;
    private Button btnSucKhoe;
    private Button btnGiaDinh;
    private Button btnDuLich;
    private Button btnKhoaHoc;
    private Button btnSoHoa;
    private Button btnXe;
    private Button btnCongDong;
    private Button btnTamSu;
    private Button btnCuoi;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-6698655251090750~7707011583");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        initLayout();
        initAction();
    }

    private void initAction() {
        btnTheGioi.setOnClickListener(this);
        btnTrangChu.setOnClickListener(this);
        btnThoiSu.setOnClickListener(this);
        btnKinhDoanh.setOnClickListener(this);
        btnQuangCao.setOnClickListener(this);
        btnGiaiTri.setOnClickListener(this);
        btnTheThao.setOnClickListener(this);
        btnPhapLuat.setOnClickListener(this);
        btnGiaoDuc.setOnClickListener(this);
        btnSucKhoe.setOnClickListener(this);
        btnGiaDinh.setOnClickListener(this);
        btnDuLich.setOnClickListener(this);
        btnKhoaHoc.setOnClickListener(this);
        btnSoHoa.setOnClickListener(this);
        btnXe.setOnClickListener(this);
        btnCongDong.setOnClickListener(this);
        btnTamSu.setOnClickListener(this);
        btnCuoi.setOnClickListener(this);

    }

    private void initLayout() {
        btnTheGioi = findViewById(R.id.btnTheGioi);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        btnThoiSu = findViewById(R.id.btnThoiSu);
        btnKinhDoanh = findViewById(R.id.btnKinhDoanh);
        btnQuangCao = findViewById(R.id.btnQuangCao);
        btnGiaiTri = findViewById(R.id.btnGiaiTri);
        btnTheThao = findViewById(R.id.btnTheThao);
        btnPhapLuat = findViewById(R.id.btnPhapLuat);
        btnGiaoDuc = findViewById(R.id.btnGiaoDuc);
        btnSucKhoe = findViewById(R.id.btnSucKhoe);
        btnGiaDinh = findViewById(R.id.btnGiaDinh);
        btnDuLich = findViewById(R.id.btnDuLich);
        btnKhoaHoc = findViewById(R.id.btnKhoaHoc);
        btnSoHoa = findViewById(R.id.btnSoHoa);
        btnXe = findViewById(R.id.btnXe);
        btnCongDong = findViewById(R.id.btnCongDong);
        btnTamSu = findViewById(R.id.btnTamSu);
        btnCuoi = findViewById(R.id.btnCuoi);

    }

    @Override
    public void onClick(View v) {
        if(v == btnTheGioi){
            Intent intentTheGioi = new Intent(HomeActivity.this,MainActivity.class);
            intentTheGioi.putExtra("urlDocBao","https://vnexpress.net/rss/the-gioi.rss");
            intentTheGioi.putExtra("tieude","-- Bản tin thế giới --");
            startActivity(intentTheGioi);
        }
        if(v == btnTrangChu){
            Intent intentTrangChu = new Intent(HomeActivity.this,MainActivity.class);
            intentTrangChu.putExtra("urlDocBao","https://vnexpress.net/rss/tin-moi-nhat.rss");
            intentTrangChu.putExtra("tieude","-- Tin mới nhất --");
            startActivity(intentTrangChu);
        }
        if(v == btnThoiSu){
            Intent intentThoiSu= new Intent(HomeActivity.this,MainActivity.class);
            intentThoiSu.putExtra("urlDocBao","https://vnexpress.net/rss/thoi-su.rss");
            intentThoiSu.putExtra("tieude","-- Bản tin thời sự --");
            startActivity(intentThoiSu);
        }
        if(v == btnKinhDoanh){
            Intent intentKinhDoanh = new Intent(HomeActivity.this,MainActivity.class);
            intentKinhDoanh.putExtra("urlDocBao","https://vnexpress.net/rss/kinh-doanh.rss");
            intentKinhDoanh.putExtra("tieude","-- Kinh doanh --");
            startActivity(intentKinhDoanh);
        }
        if(v == btnQuangCao){
            Intent intentQuangCao = new Intent(HomeActivity.this,QuangCaoActivity.class);
            startActivity(intentQuangCao);
        }
        if(v == btnGiaiTri){
            Intent intentGiaiTri = new Intent(HomeActivity.this,MainActivity.class);
            intentGiaiTri.putExtra("urlDocBao","https://vnexpress.net/rss/giai-tri.rss");
            intentGiaiTri.putExtra("tieude","-- Giải trí --");
            startActivity(intentGiaiTri);
        }
        if(v == btnTheThao){
            Intent intentTheThao = new Intent(HomeActivity.this,MainActivity.class);
            intentTheThao.putExtra("urlDocBao","https://vnexpress.net/rss/the-thao.rss");
            intentTheThao.putExtra("tieude","-- Thể thao --");
            startActivity(intentTheThao);
        }
        if(v == btnPhapLuat){
            Intent intentPhapLuat = new Intent(HomeActivity.this,MainActivity.class);
            intentPhapLuat.putExtra("urlDocBao","https://vnexpress.net/rss/phap-luat.rss");
            intentPhapLuat.putExtra("tieude","-- Pháp luật --");
            startActivity(intentPhapLuat);
        }
        if(v == btnGiaoDuc){
            Intent intentGiaoDuc = new Intent(HomeActivity.this,MainActivity.class);
            intentGiaoDuc.putExtra("urlDocBao","https://vnexpress.net/rss/giao-duc.rss");
            intentGiaoDuc.putExtra("tieude","-- Giáo dục --");
            startActivity(intentGiaoDuc);
        }
        if(v == btnSucKhoe){
            Intent intentSucKhoe = new Intent(HomeActivity.this,MainActivity.class);
            intentSucKhoe.putExtra("urlDocBao","https://vnexpress.net/rss/suc-khoe.rss");
            intentSucKhoe.putExtra("tieude","-- Sức khỏe --");
            startActivity(intentSucKhoe);
        }
        if(v == btnGiaDinh){
            Intent intentGiaDinh = new Intent(HomeActivity.this,MainActivity.class);
            intentGiaDinh.putExtra("urlDocBao","https://vnexpress.net/rss/gia-dinh.rss");
            intentGiaDinh.putExtra("tieude","-- Gia Đình --");
            startActivity(intentGiaDinh);
        }
        if(v == btnDuLich){
            Intent intentDuLich = new Intent(HomeActivity.this,MainActivity.class);
            intentDuLich.putExtra("urlDocBao","https://vnexpress.net/rss/du-lich.rss");
            intentDuLich.putExtra("tieude","-- Du Lịch --");
            startActivity(intentDuLich);
        }
        if(v == btnKhoaHoc){
            Intent intentKhoaHoc = new Intent(HomeActivity.this,MainActivity.class);
            intentKhoaHoc.putExtra("urlDocBao","https://vnexpress.net/rss/khoa-hoc.rss");
            intentKhoaHoc.putExtra("tieude","-- Khoa Học --");
            startActivity(intentKhoaHoc);
        }
        if(v == btnSoHoa){
            Intent intentSoHoa = new Intent(HomeActivity.this,MainActivity.class);
            intentSoHoa.putExtra("urlDocBao","https://vnexpress.net/rss/so-hoa.rss");
            intentSoHoa.putExtra("tieude","-- Số hóa --");
            startActivity(intentSoHoa);
        }
        if(v == btnXe){
            Intent intentXe= new Intent(HomeActivity.this,MainActivity.class);
            intentXe.putExtra("urlDocBao","https://vnexpress.net/rss/oto-xe-may.rss");
            intentXe.putExtra("tieude","-- Xe và đời sống --");
            startActivity(intentXe);
        }
        if(v == btnCongDong){
            Intent intentCongDong= new Intent(HomeActivity.this,MainActivity.class);
            intentCongDong.putExtra("urlDocBao","https://vnexpress.net/rss/cong-dong.rss");
            intentCongDong.putExtra("tieude","-- Cộng đồng --");
            startActivity(intentCongDong);
        }
        if(v == btnTamSu){
            Intent intentTamSu= new Intent(HomeActivity.this,MainActivity.class);
            intentTamSu.putExtra("urlDocBao","https://vnexpress.net/rss/tam-su.rss");
            intentTamSu.putExtra("tieude","-- Tâm sự --");
            startActivity(intentTamSu);
        }
        if(v == btnCuoi){
            Intent intentCuoi= new Intent(HomeActivity.this,MainActivity.class);
            intentCuoi.putExtra("urlDocBao","https://vnexpress.net/rss/cuoi.rss");
            intentCuoi.putExtra("tieude","-- Cười --");
            startActivity(intentCuoi);
        }

    }
}
