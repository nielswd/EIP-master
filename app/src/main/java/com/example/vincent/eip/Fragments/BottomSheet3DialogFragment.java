package com.example.vincent.eip.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.eip.Activity.ViewPagerAdapter;
import com.example.vincent.eip.R;
import com.layer_net.stepindicator.StepIndicator;

/**
 * Created by iNfecteD on 27/02/2017.
 */

public class BottomSheet3DialogFragment extends BottomSheetDialogFragment {
    private StepIndicator mRequestTabs;
    private ViewPager mRequestViewPager;

    private BottomSheetBehavior mBottomSheetBehavior;

    private CallRequestFragment mCallRequestFragment = new CallRequestFragment();
    private RequestFragment mRequestFragment = new RequestFragment();
    private PaymentFragment mPaymentFragment = new PaymentFragment();

    private ViewPagerAdapter mAdapterViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottomsheet, container, false);

        mRequestViewPager = (ViewPager) rootView.findViewById(R.id.requestVp);
        setupRequestViewPager(mRequestViewPager, rootView);


        return rootView;
    }
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);


    }

    private void setupRequestViewPager(ViewPager viewPager, View mainView) {
        mAdapterViewPager = new ViewPagerAdapter(getFragmentManager());
        mAdapterViewPager.addFragment(new CallRequestFragment(), "Choose your request");
//        mAdapterViewPager.addFragment(mRequestFragment, "Select quantities");
//        mAdapterViewPager.addFragment(mPaymentFragment, "Pay and Valid");
        viewPager.arrowScroll(View.FOCUS_LEFT);
        viewPager.arrowScroll(View.FOCUS_RIGHT);
        viewPager.setAdapter(mAdapterViewPager);
//        mRequestTabs= (StepIndicator) mainView.findViewById(R.id.step_indicator);
//        mRequestTabs.setupWithViewPager(viewPager);
//        mRequestTabs.setClickable(false);
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });
    }

    public void click_fab3(){
        setupRequestViewPager(mRequestViewPager, getView());
    }
}