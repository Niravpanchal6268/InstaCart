package com.example.instacart.Auth.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.instacart.Auth.Customer.CartF.CartFragment;
import com.example.instacart.Auth.Customer.CategoriesF.CategoriesFragment;
import com.example.instacart.Auth.Customer.HomeF.HomeFragment;
import com.example.instacart.Auth.Customer.ProfileF.ProfileFragment;
import com.example.instacart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDashbordAcitivity extends AppCompatActivity {
        FrameLayout frameLayout;
        BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashbord_acitivity);

        frameLayout=findViewById(R.id.framelayout_id);
        bottomNavigationView=findViewById(R.id.bottonnavigation_id);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment=null;
                switch (item.getItemId())
                {
                    case R.id.home_botton_m_id:
                        fragment=new HomeFragment();
                        break;
                    case R.id.categories_bottom_m_id:
                        fragment=new CategoriesFragment();
                        break;
                    case R.id.cart_bottom_m_id:
                        fragment=new CartFragment();
                        break;
                    case R.id.profile_bottom_m_id:
                        fragment=new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,fragment).commit();
                return true;


            }
        });




    }
}