package com.wadaran.wadaran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class SituationActivity extends Activity {

    String button_name;
    RestaurantDB rDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        rDb = (RestaurantDB) this.getApplication();
        rDb.loadRestaurantSet();

        String rName = rDb.restaurantList.get(1).getName();
        String tmp = rDb.areaList.get(0);
        //Toast.makeText(SituationActivity.this, tmp, Toast.LENGTH_LONG).show();

        //HashMap<String, int[]> testmap = new HashMap<String, int[]>();
        //int i[] = {1,3,5};

        //testmap.put("六本木", i);
        //Toast.makeText(SituationActivity.this, testmap.get("六本木")[1], Toast.LENGTH_LONG).show();
    }

    public void selectGokon(View view){
        goNext(view);
    }

    public void selectDate(View view){
        goNext(view);
    }

    public void selectGaikokujin(View view){
        goNext(view);
    }

    public void goNext(View view){
        Button btn = (Button)view;
        button_name = (String) btn.getText();

        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("button_name",button_name);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_situation, menu);
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
