package com.example.instacart.Auth.Customer.HomeF;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instacart.R;


public class SliderViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slid1, R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    public SliderViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return images.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custome_slider_layout, null);
        ImageView imageView = view.findViewById(R.id.slider_single_image_id);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
