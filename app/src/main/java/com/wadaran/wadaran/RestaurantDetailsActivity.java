package com.wadaran.wadaran;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RestaurantDetailsActivity extends Activity {

    private WebView wv;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        wv = (WebView)findViewById(R.id.webView);

        wv.setWebViewClient(new WebViewClient());

        /*{
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ActionBar actionBar = getActionBar();
                actionBar.setSubtitle(wv.getTitle());
            }
        });
        */
        wv.loadUrl("http://chart.apis.google.com/chart?cht=r&chxt=x,y&chds=0,5&chco=FF0000&chd=t:4.5,3.5,2.3,2.6,4.5,4.5&chls=2&chm=B,FF000020,0,0,0&chxl=1:|0|1|2|3|4|5|0:|%E3%82%B3%E3%82%B9%E3%83%91|%E6%9A%97%E3%81%95|%E5%80%8B%E5%AE%A4|%E3%82%B5%E3%83%BC%E3%83%93%E3%82%B9|%E5%92%8C%E9%A2%A8&chs=210x210");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
