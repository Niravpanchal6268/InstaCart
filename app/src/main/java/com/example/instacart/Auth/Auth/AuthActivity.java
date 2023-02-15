package com.example.instacart.Auth.Auth;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.instacart.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class AuthActivity extends AppCompatActivity {

    TextView textView;
    SignInUpFragmetnsPagerAdpter signInUpFragmetnsPagerAdpter;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem signin_tab, singup_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        textView = findViewById(R.id.auth_text_welcome_id);
        tabLayout = findViewById(R.id.SingIn_Up_tablayout_id);
        signin_tab = findViewById(R.id.SignIn_tab_id);
        singup_tab = findViewById(R.id.SignUp_tab_id);
        viewPager = findViewById(R.id.viewpageer_L_S_id);

        signInUpFragmetnsPagerAdpter = new SignInUpFragmetnsPagerAdpter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(signInUpFragmetnsPagerAdpter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==0||tab.getPosition()==1)
                {
                    viewPager.setCurrentItem(tab.getPosition());
                }
//                switch (tab.getPosition()) {
//                    case 0:
//                        viewPager.setCurrentItem(tab.getPosition());
//                        break;
//
//                    case 1:
//                        viewPager.setCurrentItem(tab.getPosition());
//                        break;
//
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}