package com.example.vincent.eip.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Network.CustomPagerAdapter;
import com.example.vincent.eip.Interfaces.OnSectorClick;
import com.example.vincent.eip.Interfaces.OnTaskCompleted;
import com.example.vincent.eip.Network.SectorsMainPageAdapter;
import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 17/02/2017.
 */

public class HotelSheetActivity extends AppCompatActivity {
    boolean is_click = false;
    SendData data = new SendData();
    private int choix = 0;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    int[] mResources = {
            R.mipmap.image_hotel,
            R.mipmap.miniature
    };

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collaspingmainmenu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);
        tabLayout.setScrollPosition(0,0,true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final GlobalClass global=(GlobalClass) getApplication();
        // specify an adapter (see also next example)
        mAdapter = new SectorsMainPageAdapter(global.listSectors, new OnSectorClick() {
            @Override
            public void OnSectorClick(String idSector) {
                data.getServicesFromSector(HotelSheetActivity.this, global.userInfos, idSector, new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted() {
                        Intent myIntent = new Intent(HotelSheetActivity.this, ServiceActivity.class);
                        HotelSheetActivity.this.startActivity(myIntent);
                    }
                });
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
