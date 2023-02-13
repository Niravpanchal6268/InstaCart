package com.example.instacart.Auth.Auth;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SignInUpFragmetnsPagerAdpter extends FragmentPagerAdapter {
    int tabcout;

    public SignInUpFragmetnsPagerAdpter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcout = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                return new SignINFragment();
            case 1:

                return new SignUPFragment();

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return tabcout;
    }
}
