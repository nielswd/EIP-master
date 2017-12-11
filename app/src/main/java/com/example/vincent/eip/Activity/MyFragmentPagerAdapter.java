package com.example.vincent.eip.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.vincent.eip.Fragments.CallRequestFragment;
import com.example.vincent.eip.Fragments.PaymentFragment;
import com.example.vincent.eip.Fragments.RequestFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private Fragment mCurrentFragment;
    private CallRequestFragment callRequest= new CallRequestFragment();
    private RequestFragment request= new RequestFragment();
    private PaymentFragment payment= new PaymentFragment();
    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return callRequest;
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return request;
            case 2: // Fragment # 1 - This will show SecondFragment
                return payment;
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }
}
