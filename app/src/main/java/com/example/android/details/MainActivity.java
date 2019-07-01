package com.example.android.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.android.details.Breakfast.BreakfastActivity;
import com.example.android.details.Dinner.DinnerActivity;
import com.example.android.details.Lunch.LunchActivity;

public class MainActivity extends AppCompatActivity {


    CardView cardview1, cardview2, cardview3;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardview1 = findViewById(R.id.cardview1);
        cardview2 = findViewById(R.id.cardview2);
        cardview3 = findViewById(R.id.cardview3);

        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, BreakfastActivity.class);
                startActivity(intent);

            }
        });

        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LunchActivity.class);
                startActivity(intent);

            }
        });

        cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DinnerActivity.class);
                startActivity(intent);

            }
        });


    }



}

