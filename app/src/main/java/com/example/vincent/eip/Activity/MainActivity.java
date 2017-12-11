package com.example.vincent.eip.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.vincent.eip.Activity.devices.ListDevicesActivity;
import com.example.vincent.eip.Activity.messages.ListMessagesActivity;
import com.example.vincent.eip.Fragments.CallRequestFragment;
import com.example.vincent.eip.Fragments.infoevent.InfoEventsFragment;
import com.example.vincent.eip.Fragments.openinghours.OpeningHoursFragment;
import com.example.vincent.eip.Fragments.PaymentFragment;
import com.example.vincent.eip.Fragments.RequestFragment;
import com.example.vincent.eip.Fragments.touristicplaces.TouristicPlacesFragment;
import com.example.vincent.eip.Fragments.transports.TransportsFragment;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.OnTaskCompleted;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.R;
import com.example.vincent.eip.Network.SendData;
import com.github.clans.fab.FloatingActionMenu;
import com.layer_net.stepindicator.StepIndicator;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    SendData data = new SendData();

    Toolbar mToolbar;

    private TabLayout tabLayout;
    private ViewPager tabsViewPager;

    private StepIndicator mRequestTabs;
    private ViewPager mRequestViewPager;

    private FloatingActionMenu menuServices;
//    private FloatingActionButton fab1;
//    private FloatingActionButton fab2;
//    private FloatingActionButton fab3;

    private android.support.design.widget.FloatingActionButton fab;
//    private BottomSheetBehavior mBottomSheetBehavior;

    private CallRequestFragment mCallRequestFragment = new CallRequestFragment();
    private RequestFragment mRequestFragment = new RequestFragment();
    private PaymentFragment mPaymentFragment = new PaymentFragment();

    private ViewPagerAdapter mAdapterViewPager;

    private RelativeLayout mTopRelative;
//    private FrameLayout mFrame;

    private AppBarLayout appBarLayout;
    private boolean isOpen = true;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);


         //final View bottomSheet = findViewById( R.id.bottom_sheet );

        mTopRelative = (RelativeLayout) findViewById(R.id.top_relative);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
//        mFrame = (FrameLayout) findViewById(R.id.frame);
//        mFrame.setVisibility(View.GONE);

//        mBottomSheetBehavior = new UserLockBottomSheetBehavior();
//        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//        mBottomSheetBehavior.setPeekHeight(0);
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    initBootSHeet();

        final GlobalClass global=(GlobalClass) getApplication();
        data.getAllSectors(MainActivity.this, global.userInfos, new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
            }
                });
        menuServices = (FloatingActionMenu) findViewById(R.id.menu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SendData data = new SendData();
//                GlobalClass global=(GlobalClass) MainActivity.this.getApplication();
//                UserClientInfo clientInfo = global.userInfos;
//               data.getInfoEvent(MainActivity.this, clientInfo);
//                MessageRetrofit testMessages = new MessageRetrofit();
//                            testMessages.login(MainActivity.this, clientInfo);
                if (isOpen){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    appBarLayout.setExpanded(false, true);
                    isOpen = false;
                } else {
                    appBarLayout.setExpanded(true, true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    isOpen = true;
                }
            }
        });
//        fab1 = (FloatingActionButton) findViewById(R.id.menu_item1);
//        fab2 = (FloatingActionButton) findViewById(R.id.menu_item2);
//        fab3 = (FloatingActionButton) findViewById(R.id.menu_item3);
//
//
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                appBarLayout.setExpanded(false, true);
//
//            }
//        });
//
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            menuServices.close(true);
//                final GlobalClass global=(GlobalClass) MainActivity.this.getApplication();
//                data.getServicesFromSector(MainActivity.this, global.userInfos, "entretien", new OnTaskCompleted() {
//                    @Override
//                    public void onTaskCompleted() {
////                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                        mCallRequestFragment.showDataCleaning(global.listServices, new CallbackRequestClick() {
//                            @Override
//                            public void OnRequestClick() {
//                                mRequestViewPager.setCurrentItem(1, true);
//                            }
//                        });
//                    }});
//            }});
//
//
//        fab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final GlobalClass global=(GlobalClass) MainActivity.this.getApplication();
//                data.getServicesFromSector(MainActivity.this, global.userInfos, "cuisine", new OnTaskCompleted() {
//                    @Override
//                    public void onTaskCompleted() {
////                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                        mCallRequestFragment.showDataFood(global.listServices, new CallbackRequestClick() {
//                            @Override
//                            public void OnRequestClick() {
//
//                            }
//                        });
//                    }});
//            }});


        tabsViewPager = (ViewPager) findViewById(R.id.tabsviewpager);
        setupTabsViewPager(tabsViewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(tabsViewPager);

        //mRequestViewPager = (ViewPager) findViewById(R.id.requestviewpager);
        //setupRequestViewPager(mRequestViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initBootSHeet(){
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        ImageView services = (ImageView) llBottomSheet.findViewById(R.id.img_services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SectorActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

// init the bottom sheet behavior
         bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

// change the state of the bottom sheet
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

// set the peek height
        bottomSheetBehavior.setPeekHeight(340);

// set hideable or not


// set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void setupTabsViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OpeningHoursFragment(), "Opening Hours");
        adapter.addFragment(new InfoEventsFragment(), "Events");
        adapter.addFragment(new TouristicPlacesFragment(), "Touristic Places");
        adapter.addFragment(new TransportsFragment(), "Transports");
        viewPager.arrowScroll(View.FOCUS_LEFT);
        viewPager.arrowScroll(View.FOCUS_RIGHT);
        viewPager.setAdapter(adapter);
    }

    private void setupRequestViewPager(ViewPager viewPager) {
        mAdapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapterViewPager.addFragment(mCallRequestFragment, "Choose your request");
        mAdapterViewPager.addFragment(mRequestFragment, "Select quantities");
        mAdapterViewPager.addFragment(mPaymentFragment, "Pay and Valid");
        viewPager.arrowScroll(View.FOCUS_LEFT);
        viewPager.arrowScroll(View.FOCUS_RIGHT);
        viewPager.setAdapter(mAdapterViewPager);
        mRequestTabs= (StepIndicator) findViewById(R.id.step_indicator);
        mRequestTabs.setupWithViewPager(viewPager);
        mRequestTabs.setClickable(false);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
//        }else if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//            if (mRequestViewPager.getCurrentItem() == 0) {
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            } else {
//                mRequestViewPager.setCurrentItem(mRequestViewPager.getCurrentItem() - 1, true);
//            }
//        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, ListRequestsActivity.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, ListMessagesActivity.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.domo) {
            Intent intent = new Intent(MainActivity.this, ListDevicesActivity.class);
            MainActivity.this.startActivity(intent);
        }
        else if (id == R.id.nav_send) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
