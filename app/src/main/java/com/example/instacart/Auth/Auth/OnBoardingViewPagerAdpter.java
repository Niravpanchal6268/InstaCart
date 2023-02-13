package com.example.instacart.Auth.Auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.instacart.R;

public class OnBoardingViewPagerAdpter extends PagerAdapter {

    Context context;
    int Images[] = {
            R.drawable.ons_welcome,
            R.drawable.ons_second,
            R.drawable.ons_third
    };
    int HeadingText[]
            = {
            R.string.welcome_ons_heading1,
            R.string.ons_secound_heading2,
            R.string.ons_third_heading3

    };
    int SubHeadingText[]={
            R.string.welcome_ons_subheading1,
            R.string.ons_secound_subheading2,
            R.string.ons_third_subheading3
    };
    int Descriptions[]={

            R.string.welcome_ons_description1,
            R.string.welcome_ons_description1,
            R.string.welcome_ons_description1

    };

    public OnBoardingViewPagerAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return HeadingText.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
       return view ==(RelativeLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.onboardsinglelayout,container,false);

        ImageView sliderImage=view.findViewById(R.id.onboarding_image_id);
        TextView headingText=view.findViewById(R.id.onboarding_headingtext_id);
        TextView subheadingText=view.findViewById(R.id.onboarding_subheadingtext_id);
        TextView Description=view.findViewById(R.id.onboading_description_id);

        sliderImage.setImageResource(Images[position]);
        headingText.setText(HeadingText[position]);
        subheadingText.setText(SubHeadingText[position]);
        Description.setText(Descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((RelativeLayout)object);

    }

}
