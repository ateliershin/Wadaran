package com.wadaran.wadaran;

import android.graphics.Bitmap;

/**
 * Created by erikotsuda on 6/29/15.
 */

public class Restaurant{
    private int id;
    private String name;
    private String address;
    private String area;
    private String tel;
    private double lat;
    private double lon;
    private float rank;
    private String comment;
    private int isGk;
    private int isDt;
    private int isGj;
    private Bitmap image;

    public void setId(int n){ this.id = n;}
    public int getId(){ return this.id;}
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return this.name;
    }
    public void setAddress(String n){
        this.address = n;
    }
    public String getAddress(){
        return this.address;
    }
    public void setArea(String n){
        this.area = n;
    }
    public String getArea(){
        return this.area;
    }
    public void setTel(String n){
        this.tel = n;
    }
    public String getTel(){
        return this.tel;
    }
    public void setLat(double n){
        this.lat = n;
    }
    public double getLat(){
        return this.lat;
    }
    public void setLon(double n){
        this.lon = n;
    }
    public double getLon(){
        return this.lon;
    }
    public void setRank(float i){
        this.rank = i;
    }
    public float getRank(){
        return this.rank;
    }
    public void setComment(String c){
        this.comment = c;
    }
    public String getComment(){
        return this.comment;
    }
    public void setGk(int b){
        this.isGk = b;
    }
    public int getGk(){
        return this.isGk;
    }
    public void setDt(int b){
        this.isDt = b;
    }
    public int getDt(){
        return this.isDt;
    }
    public void setGj(int b){
        this.isGj = b;
    }
    public int getGj(){
        return this.isGj;
    }
    public Bitmap getImage(){
        return this.image;
    }
    public void setImage(Bitmap image){
        this.image = image;
    }
}
