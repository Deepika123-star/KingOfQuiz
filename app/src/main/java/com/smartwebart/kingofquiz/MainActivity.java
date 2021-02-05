package com.smartwebart.kingofquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartwebart.kingofquiz.adapter.PreliminaryAdapter;
import com.smartwebart.kingofquiz.add.InterstitialAdsActivityActivity;
import com.smartwebart.kingofquiz.contact.ContactUsActivity;
import com.smartwebart.kingofquiz.model.NewSeriesModel;
import com.smartwebart.kingofquiz.model.Prelimanary_model;
import com.smartwebart.kingofquiz.retrofit.UtilMethods;
import com.smartwebart.kingofquiz.retrofit.mCallBackResponse;
import com.smartwebart.kingofquiz.setting.SetttingActivity;
import com.smartwebart.kingofquiz.utils.Urls;
import com.smartwebart.kingofquiz.utils.UsefullMethods;
import com.smartwebart.kingofquiz.viewmodel.ExamsViewModel;
import com.smartwebart.kingofquiz.webview.WebViewActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private static final int MODE_DARK = 0;
    private static final int MODE_LIGHT = 1;
    RecyclerView recycler_item;
    ArrayList<Prelimanary_model> content_data;
    private ExamsViewModel mViewModel;
    private List<NewSeriesModel>list;
    private static final String TAG = "MainActivity";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {

                case R.id.howtoplay:{
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra(WebViewActivity.DATA, Urls.TERMS_CONDITION);
                    intent.putExtra(WebViewActivity.TITLE, "How to Play");
                    startActivity(intent);
                    break;
                }
                case R.id.help:
                    Intent setting=new Intent(MainActivity.this, ContactUsActivity.class);
                    startActivity(setting);
                    break;
                case  R.id.navigationMenu:
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.openDrawer(GravityCompat.START);

                    break;
            }

            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setDarkMode(getWindow());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        recycler_item=findViewById(R.id.recycler_item);
        mAdView = findViewById(R.id.adView);
         mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });



       /* MobileAds.initialize(this,getResources().getString(R.string.banner_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        getSubject();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        bottomNavigationView.setSelectedItemId(R.id.setting);

        //handling floating action menu
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });
getSubject();
       //getTopicsObserver();
        /*AdapterMainSubject adapterMainSubject=new AdapterMainSubject(MainActivity.this);
        recycler_item.setAdapter(adapterMainSubject);*/
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void getSubject() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(MainActivity.this)) {
            UtilMethods.INSTANCE.getSubjects(MainActivity.this,new mCallBackResponse() {
                @Override
                public void success(String from, String message) {
                    Type type=new TypeToken<List<NewSeriesModel>>(){}.getType();
                    list=new Gson().fromJson(message,type);
                    setAdapter(list);

                }
                @Override
                public void fail(String from) {
                    UsefullMethods.showMessage(MainActivity.this, SweetAlertDialog.ERROR_TYPE, "Error", from, "OK", () -> {

                    });

                }

            });


        } else {

            UtilMethods.INSTANCE.internetNotAvailableMessage(MainActivity.this);
        }
    }

    private void setAdapter(List<NewSeriesModel> list) {
        PreliminaryAdapter mAdapter = new PreliminaryAdapter(list,MainActivity.this);
        recycler_item.setAdapter(mAdapter);

    }

 /*   private void getTopicsObserver() {
        Observer observer = new Observer() {
            @Override
            public void onChanged(@Nullable Object object) {
                List<SubjectModel> propertyBookingList = (List<SubjectModel>) object;
                add_topic_data(propertyBookingList);

            }
        };
        mViewModel.getSubjects(MainActivity.this).observe(MainActivity.this, observer);
    }*/

    private void add_topic_data() {
        Log.d("Arraylist AVlue====",""+list.toString());
        PreliminaryAdapter mAdapter = new PreliminaryAdapter(list,MainActivity.this);
        recycler_item.setAdapter(mAdapter);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            // Handle the camera action
        if (id == R.id.nav_gallery) {
            Intent setting=new Intent(MainActivity.this, ContactUsActivity.class);
            startActivity(setting);
        } else if (id == R.id.nav_slideshow) {
            Intent setting=new Intent(MainActivity.this, SetttingActivity.class);
            startActivity(setting);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.DATA, Urls.TERMS_CONDITION);
            intent.putExtra(WebViewActivity.TITLE, "How to Play");
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_dark_mode) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click
           /* DarkModePrefManager darkModePrefManager = new DarkModePrefManager(this);
            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();*/

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //create a seperate class file, if required in multiple activities
//    public void setDarkMode(Window window){
//        if(new DarkModePrefManager(this).isNightMode()){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            changeStatusBar(MODE_DARK,window);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            changeStatusBar(MODE_LIGHT,window);
//        }
//    }
    public void changeStatusBar(int mode, Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            //Light mode
            if(mode==MODE_LIGHT){
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }

    public void testAdd(View view) {
        Intent intent = new Intent(MainActivity.this, InterstitialAdsActivityActivity.class);
        startActivity(intent);
    }
}


