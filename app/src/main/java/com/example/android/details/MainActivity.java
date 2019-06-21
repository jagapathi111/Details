package com.example.android.details;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.media.browse.MediaBrowser.EXTRA_PAGE;
import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Breakfast","we are in oncreate haha clicklistener");


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

    }


    public enum CustomPagerEnum {

        Breakfast(R.string.breakfast, R.layout.view_breakfast),
        Lunch(R.string.lunch, R.layout.view_lunch),
        Dinner(R.string.Dinner, R.layout.view_dinner);

        private int mTitleResId;
        private int mLayoutResId;

        CustomPagerEnum(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getLayoutResId() {
            return mLayoutResId;
        }

    }

}

