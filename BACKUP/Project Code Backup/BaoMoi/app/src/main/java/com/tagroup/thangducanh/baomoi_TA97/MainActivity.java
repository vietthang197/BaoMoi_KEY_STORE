package com.tagroup.thangducanh.baomoi_TA97;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter customAdapter;
    private ArrayList<DocBao> docBaos;
    private TextView tvTieude;
    private AdView mAdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intents = getIntent();
        final String urls = intents.getStringExtra("urlDocBao");
        String tieude = intents.getStringExtra("tieude");
        tvTieude = findViewById(R.id.tvTieude);
        tvTieude.setText(tieude);
        listView = findViewById(R.id.listView);
        docBaos = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute(urls);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("link",docBaos.get(position).getLink());
                startActivity(intent);
            }
        });

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-9334661269367396~3275072909");
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);
    }
    class ReadData extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            NodeList nodeListPubDate = document.getElementsByTagName("pubDate");
            String hinhanh ="";
            for(int i=0;i<nodeList.getLength();i++){
                String pubdate = nodeListPubDate.item(i+1).getTextContent();
                String cdata = nodeListDescription.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher  = p.matcher(cdata);
                if(matcher.find()){
                    hinhanh =  matcher.group(1);
                    Log.d("hinhanh",hinhanh);
                }else{
                    Pattern p2 = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher2  = p2.matcher(cdata);
                   if(matcher2.find()){
                       hinhanh = matcher2.group(1);
                   }
                }
                Element element = (Element) nodeList.item(i);
                String title = parser.getValue(element,"title");
                String link =  parser.getValue(element,"link");
                docBaos.add(new DocBao(title,link,hinhanh,pubdate));

            }
            customAdapter = new CustomAdapter(MainActivity.this,android.R.layout.simple_list_item_1,docBaos);
            listView.setAdapter(customAdapter);
            super.onPostExecute(s);
        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
