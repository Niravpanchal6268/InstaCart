package com.example.instacart.Auth.Customer.HomeF;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.instacart.Auth.Customer.HomeF.Categories.CategoriesAdpter;
import com.example.instacart.Auth.Customer.HomeF.Categories.CategoriesModel;
import com.example.instacart.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView categoriesRecyclerView;
    CategoriesAdpter categoriesAdpter;
    ViewPager viewPager;
    SliderViewPagerAdapter sliderViewPagerAdapter;
    LinearLayout sliderDotpanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoriesRecyclerView = view.findViewById(R.id.home_tops_product_recyclerView_id);
        //viewpageobj


        //categories firebase to app
        FirebaseRecyclerOptions<CategoriesModel> options =
                new FirebaseRecyclerOptions.Builder<CategoriesModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories"), CategoriesModel.class)
                        .build();
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        categoriesAdpter = new CategoriesAdpter(options);
        categoriesRecyclerView.setAdapter(categoriesAdpter);

        //slider code offline
        viewPager = view.findViewById(R.id.slider_viewpage_id);
        sliderViewPagerAdapter = new SliderViewPagerAdapter(getContext());

        viewPager.setAdapter(sliderViewPagerAdapter);

        sliderDotpanel = view.findViewById(R.id.slider_dots_id);

        dotscount = sliderViewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

            sliderDotpanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        categoriesAdpter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoriesAdpter.stopListening();

    }
}