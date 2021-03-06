package com.wadaran.wadaran;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by erikotsuda on 12/1/15.
 */
public class CustomPagerAdapterFoodPic extends PagerAdapter {
    public final static int N = 5;
    private LayoutInflater _inflater = null;
    private int picturePos = 0;

    public CustomPagerAdapterFoodPic(Context c) {
        super();
        _inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomPagerAdapterFoodPic(Context c, int position) {
        super();
        _inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        picturePos = position;
        Log.d("picture","picture "+picturePos);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = (LinearLayout) _inflater.inflate(R.layout.activity_picture_page, null);
        //int brt = 255*position/N;
        //layout.setBackgroundColor(Color.rgb(brt, brt, brt));//適当に色をセット(しなくていい)
        ImageView img = (ImageView) layout.findViewById(R.id.img_scroll);
        int rsrc[] = { R.drawable.img00, R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04 };
        //int tmphoge = R.drawable.img04;
        int pos = position + picturePos - 1;
        img.setImageResource(rsrc[pos]);
        /*
        if(picturePos != 0){
            Log.d("picture", "picturePos != 0");
            img.setImageResource(rsrc[picturePos-1]);
            picturePos = 0;
        }else{
            img.setImageResource(rsrc[position]);
        }
        */
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return N;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
