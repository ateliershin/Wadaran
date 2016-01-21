package com.wadaran.wadaran;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private WebView wv;
    private Button btn;
    private ViewPager _viewPager = null;

    //これっす
    //これっす
    //2016/01/07はお腹がすきました
    //あああ
    //さらだがすきです
    //これから送別会
    //あああ


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn = (Button) findViewById(R.id.telButton);
        btn.setOnClickListener(this);

        _viewPager = (ViewPager) findViewById(R.id.viewpager_restaurant);
        PagerAdapter mPagerAdapter = new CustomPagerAdapterRestaurantPic(this);
        _viewPager.setAdapter(mPagerAdapter);

        wv = (WebView)findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://chart.apis.google.com/chart?cht=r&chxt=x,y&chds=0,5&chco=FF0000&chd=t:4.5,3.5,2.3,2.6,4.5,4.5&chls=2&chm=B,FF000020,0,0,0&chxl=1:|0|1|2|3|4|5|0:|%E3%82%B3%E3%82%B9%E3%83%91|%E6%9A%97%E3%81%95|%E5%80%8B%E5%AE%A4|%E3%82%B5%E3%83%BC%E3%83%93%E3%82%B9|%E5%92%8C%E9%A2%A8&chs=170x170");

    }

    public void selectBtn1(View view){
        Intent intent = new Intent(this, FoodPicturesActivity.class);
        startActivity(intent);
        Log.d("pictures", "MapActivity - foodBtn1");
    }
    @Override
    public void onClick(View v) {
        // ダイアルボタン押下時
        if (v.getId() == R.id.telButton) {
            // インテントの生成
            Intent intent = new Intent("android.intent.action.DIAL",
                    Uri.parse("tel:1234567890"));
            // 次のアクティビティの起動
            startActivity(intent);
        }
        /*
        else if(v.getId() == R.id.foodBtn1){
            Intent intent = new Intent(this, FoodPicturesActivity.class);
            startActivity(intent);
            Log.d("pictures", "MapActivity - foodBtn1");
        }
        else if(v.getId() == R.id.foodBtn2){
            Intent intent = new Intent(this, FoodPicturesActivity.class);
            startActivity(intent);
            Log.d("pictures", "MapActivity - foodBtn1");
        }
        else if(v.getId() == R.id.foodBtn3){
            Intent intent = new Intent(this, FoodPicturesActivity.class);
            startActivity(intent);
            Log.d("pictures", "MapActivity - foodBtn1");
        }
        */
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
