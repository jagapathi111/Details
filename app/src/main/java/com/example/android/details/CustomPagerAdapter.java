package com.example.android.details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//public class CustomPagerAdapter extends PagerAdapter {

/*    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        MainActivity.CustomPagerEnum customPagerEnum = MainActivity.CustomPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
         ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
        collection.addView(layout);

        return layout;



    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
//        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return MainActivity.CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        MainActivity.CustomPagerEnum customPagerEnum = MainActivity.CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    } */

//}