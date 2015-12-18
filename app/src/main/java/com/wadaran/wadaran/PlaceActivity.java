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

import java.util.ArrayList;
import java.util.HashMap;


public class PlaceActivity extends Activity {
    String button_name;
    String tmpStr = "ほげ";
    RestaurantDB rDb;

    @Override
    protected void onResume(){
        super.onResume();
        createContent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        createContent();
    }

    public void createContent(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // インテントを取得
        Intent intent = getIntent();
        // インテントに保存されたデータを取得
        button_name = intent.getStringExtra("button_name");

        rDb = (RestaurantDB) this.getApplication();
        final HashMap<String, ArrayList<Integer>> restaurantMap = rDb.getRestaurantListBySituation(button_name);
        System.out.println(button_name);
        if (button_name.equals("合コン")){
            adapter.add("どこで合コンする？");
            for(int i = 0; i < rDb.areaList.size(); i++){
                String tmpPlace = rDb.areaList.get(i);
                int num = restaurantMap.get(tmpPlace).size();
                if (num != 0) {
                    adapter.add(tmpPlace + " : " + num);
                }
            }
        } else if(button_name.equals("デート♡")){
            adapter.add("どこでデートする？");
            for(int i = 0; i < rDb.areaList.size(); i++){
                String tmpPlace = rDb.areaList.get(i);
                int num = restaurantMap.get(tmpPlace).size();
                if (num != 0) {
                    adapter.add(tmpPlace + " : " + num);
                }
            }
        } else {
            adapter.add("どこで接待する？");
            for(int i = 0; i < rDb.areaList.size(); i++){
                String tmpPlace = rDb.areaList.get(i);
                int num = restaurantMap.get(tmpPlace).size();
                if (num != 0) {
                    adapter.add(tmpPlace + " : " + num);
                }
            }
        }
        // Spinnerの設定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner selectSpinner = (Spinner) findViewById(R.id.place_spinner);
        selectSpinner.setAdapter(adapter);
        //selectSpinner.setPrompt(button_name);
        Intent intent2 = new Intent(this, RestaurantListActivity.class);

        selectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Intent intent2 = new Intent(this, RestaurantListActivity.class);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                if (position != 0) {
                    TextView textView = (TextView) view;
                    StringBuilder sb = new StringBuilder();
                    sb.append("parent=").append(parent.getClass().getSimpleName())
                            .append(" position=").append(position).append(" id=").append(id)
                            .append(" textView.getText()=").append(textView.getText());
                    Toast.makeText(getApplicationContext(), sb.toString(),
                            Toast.LENGTH_SHORT).show();
                    //goRestaurantListPage(view, textView.getText().toString());
                    Intent intent2 = new Intent(PlaceActivity.this, RestaurantListActivity.class);
                    String tmpStrings[] = textView.getText().toString().split(" : ",0);
                    intent2.putExtra("place_name", tmpStrings[0]);
                    intent2.putExtra("situation", button_name);
                    intent2.putIntegerArrayListExtra("idList", restaurantMap.get(tmpStrings[0]));
                    startActivity(intent2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place, menu);
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

    public void goLocationPage(View view){
        Button btn = (Button)view;
        //button_name = (String) btn.getText();

        Intent intent = new Intent(this, NowLocationActivity.class);
        //intent.putExtra("button_name",button_name);
        startActivity(intent);
    }

    public void goRestaurantListPage(View view, String place){
        Button btn = (Button)view;
        //button_name = (String) btn.getText();

        Intent intent = new Intent(this, RestaurantListActivity.class);
        intent.putExtra("place_name",place);
        startActivity(intent);
    }
}
