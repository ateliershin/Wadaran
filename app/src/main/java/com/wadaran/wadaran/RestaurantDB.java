package com.wadaran.wadaran;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by erikotsuda on 6/23/15.
 */
public class RestaurantDB extends Application{

    ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    ArrayList<String> areaList = new ArrayList<String>();
    //ArrayList<int> a = new ArrayList<int>();

    public HashMap<String, ArrayList<Integer>> getRestaurantListBySituation(String situation){
        int size = restaurantList.size();
        HashMap<String, ArrayList<Integer>> restaurantMap = new HashMap<String, ArrayList<Integer>>();

        for(int i = 0; i < areaList.size(); i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            restaurantMap.put(areaList.get(i),tmp);
        }

        for(int i = 0; i < size; i++){
            switch (situation){
                case "合コン":
                    if(restaurantList.get(i).getGk() == 1){
                        String area = restaurantList.get(i).getArea();
                        //restaurantMap.get("六本木").add(1);
                        restaurantMap.get(area).add(restaurantList.get(i).getId());
                    }
                    break;
                case "デート♡":
                    if(restaurantList.get(i).getDt() == 1){
                        String area = restaurantList.get(i).getArea();
                        restaurantMap.get(area).add(restaurantList.get(i).getId());
                    }
                    break;
                case "外国人接待":
                    if(restaurantList.get(i).getGj() == 1){
                        String area = restaurantList.get(i).getArea();
                        restaurantMap.get(area).add(restaurantList.get(i).getId());
                    }
                    break;
            }
        }
        return restaurantMap;
    }

    public void loadRestaurantSet(){
        if (restaurantList.size() > 0){
            restaurantList.clear();
        }

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try{
            inputStream = getAssets().open("restaurant_list.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s = bufferedReader.readLine()) != null){
                Restaurant r = new Restaurant();
                String[] restaurantInfo = s.split("\t");
                r.setId(Integer.parseInt(restaurantInfo[0]));
                r.setName(restaurantInfo[1]);
                r.setArea(restaurantInfo[2]);

                if(areaList.size() != 0) {
                    for (int i = 0; i < areaList.size(); i++) {
                        if (restaurantInfo[2].equals(areaList.get(i))) {
                            //areaList.add(restaurantInfo[2]);
                            break;
                        }
                        if(i == areaList.size() - 1) {
                            areaList.add(restaurantInfo[2]);
                        }
                    }
                }else{
                    areaList.add(restaurantInfo[2]);
                }

                r.setAddress(restaurantInfo[3]);
                r.setTel(restaurantInfo[4]);
                r.setLat(Double.parseDouble(restaurantInfo[5]));
                r.setLon(Double.parseDouble(restaurantInfo[6]));
                r.setRank(Float.parseFloat(restaurantInfo[7]));
                r.setComment(restaurantInfo[8]);
                r.setDt(Integer.parseInt(restaurantInfo[9]));
                r.setGk(Integer.parseInt(restaurantInfo[10]));
                r.setGj(Integer.parseInt(restaurantInfo[11]));
                r.setImage(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
                restaurantList.add(r);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null ) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

