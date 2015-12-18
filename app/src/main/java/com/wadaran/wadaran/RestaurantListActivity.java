package com.wadaran.wadaran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RestaurantListActivity extends Activity {

    String situation;
    String placeName;
    ArrayList<Integer> restaurantIdList;
    RestaurantDB rDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        rDb = (RestaurantDB) this.getApplication();
        TextView situationText = (TextView)findViewById(R.id.situation);
        TextView placeText = (TextView) findViewById(R.id.place);
        TextView numRestaurantText = (TextView) findViewById(R.id.numberOfRestaurants);

        Intent intent = getIntent();
        situation = intent.getStringExtra("situation");
        placeName = intent.getStringExtra("place_name");
        restaurantIdList = intent.getIntegerArrayListExtra("idList");
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

        for(int i = 0; i < restaurantIdList.size(); i++){
            restaurants.add(rDb.restaurantList.get(restaurantIdList.get(i) - 1));
        }

        situationText.setText(situation);
        placeText.setText(placeName);
        numRestaurantText.setText(restaurantIdList.size() + "件");

        RestaurantAdapter rAdapter = new RestaurantAdapter(this, 0, restaurants);
        // ListViewにAdapterを設置
        final ListView myListView = (ListView) findViewById(R.id.restaurantListView);
        //myListView.setEmptyView(findViewById(R.id.empty));
        myListView.setAdapter(rAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView name = (TextView) view.findViewById(R.id.name);
                Toast.makeText(RestaurantListActivity.this, name.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                goNext();
            }
        });

    }

    public void goNext(){
        Intent intentNext = new Intent(this, MapsActivity.class);
        //intent.putExtra("place_name",place);
        startActivity(intentNext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_list, menu);
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


    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView tel;
        TextView comment;
        RatingBar ratingBar;

        public ViewHolder(View view){
            this.image = (ImageView) view.findViewById(R.id.image);
            this.name = (TextView) view.findViewById(R.id.name);
            this.tel = (TextView) view.findViewById(R.id.tel);
            this.comment = (TextView) view.findViewById(R.id.comment);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        }
    }

    public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        private LayoutInflater layoutInflater;

        public RestaurantAdapter(Context context, int viewResourceId, ArrayList<Restaurant> restaurants) {
            super(context, viewResourceId, restaurants);
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder viewHolder;

            // 再利用できるViewがなかったらLayoutInflaterを使ってrow.xmlをViewにする
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.row, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // そのViewにデータをセットする
            Restaurant restaurant = (Restaurant) getItem(position);

            viewHolder.image.setImageBitmap(restaurant.getImage());
            viewHolder.name.setText(restaurant.getName());
            viewHolder.tel.setText(restaurant.getTel());
            viewHolder.comment.setText(restaurant.getComment());
            viewHolder.ratingBar.setRating(restaurant.getRank());

            // そのViewを返す
            return convertView;
        }
    }


}
