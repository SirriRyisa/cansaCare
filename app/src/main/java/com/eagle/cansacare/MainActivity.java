package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backbtn = findViewById(R.id.slideViewBackBtn);
        nextbtn = findViewById(R.id.slideViewNextBtn);
        skipbtn = findViewById(R.id.slideViewSkipBtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getItem(0) > 0) {
                    mSlideViewPager.setCurrentItem(getItem(-1), true);
                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getItem(0) < 3) {
                    // If not on the last page, move to the next page
                    mSlideViewPager.setCurrentItem(getItem(1), true);
                } else {
                    // If on the last page, start the next activity
                    Intent i = new Intent(MainActivity.this, GetStarted.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, GetStarted.class);
                startActivity(i);
                finish();

            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position) {

        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.divider_color, getApplicationContext().getTheme()));
            }

            mDotLayout.addView(dots[i]);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.primary_color, getApplicationContext().getTheme()));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override

        public void onPageSelected(int position) {

            setUpindicator(position);


            if (position > 0) {

                backbtn.setVisibility(View.VISIBLE);

            }else {

                backbtn.setVisibility(View.INVISIBLE);

            }

        }

        @Override

        public void onPageScrollStateChanged(int state) {


        }

    };


    private int getItem(int i) {
        return mSlideViewPager.getCurrentItem() + i;
    }
}