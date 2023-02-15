package com.example.instacart.Auth.Auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.instacart.R;
import com.google.android.material.button.MaterialButton;

public class OnBoardingMainActivity extends AppCompatActivity {

    ViewPager OnbviewPage;
    LinearLayout mdotlayout;
    MaterialButton next_btn, back_btn;
    OnBoardingViewPagerAdpter onBoardingViewPagerAdpter;
    TextView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardingmain);

        OnbviewPage = findViewById(R.id.onboarding_sliderViewPager_id);
        mdotlayout = findViewById(R.id.mDot_layout);
        next_btn = findViewById(R.id.onboarding_next_btn_id);
        back_btn = findViewById(R.id.onboarding_previous_btn_id);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0) {
                    OnbviewPage.setCurrentItem(getitem(-1), true);
                }
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) < 2) {
                    OnbviewPage.setCurrentItem(getitem(1), true);

                } else {
                    Toast.makeText(OnBoardingMainActivity.this, "main", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(OnBoardingMainActivity.this, AuthActivity.class));
                    startActivity(new Intent(OnBoardingMainActivity.this,AuthActivity.class));
//                    finish();
                }

            }
        });

        onBoardingViewPagerAdpter = new OnBoardingViewPagerAdpter(this);
        OnbviewPage.setAdapter(onBoardingViewPagerAdpter);
        setUPIndicator(0);
        OnbviewPage.addOnPageChangeListener(onPageChangeListener);
    }

    public void setUPIndicator(int position) {
        dots = new TextView[3];
        mdotlayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            }
            mdotlayout.addView(dots[i]);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
        }

    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUPIndicator(position);
            if (position > 0) {
                back_btn.setVisibility(View.VISIBLE);
            } else {
                back_btn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {
        return OnbviewPage.getCurrentItem() + i;
    }
}