package com.hoangphuong2.salemanager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hoangphuong2.salemanager.data.preferences.Preferences;
import com.hoangphuong2.salemanager.ui.control.FragmentNavigator;
import com.hoangphuong2.salemanager.ui.fragment.FragmentMain;
import com.hoangphuong2.salemanager.ui.fragment.FragmentStart;
import com.hoangphuong2.salemanager.util.Tag;

public class SplashScreen extends AppCompatActivity {
    private FragmentNavigator fragmentNavigator;
    private Fragment fragment;
    private Preferences preferences;
    private String ownerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initValue();
        initFragment();
    }

    private void initValue() {
        preferences = new Preferences(getApplicationContext(),Preferences.SALE_MANAGER_PREFERENCES);
        ownerName = preferences.getStringValue(preferences.SALE_MANAGER_OWNER_NAME);
    }

    public FragmentNavigator getFragmentNavigator() {
        return fragmentNavigator;
    }

    public Preferences getPreferences(){
        return preferences;
    }

    private void initFragment() {
        fragmentNavigator = new FragmentNavigator(getSupportFragmentManager(),
                R.id.frameContainer, R.anim.slide_in_left, R.anim.slide_in_right,
                R.anim.slide_in_right, R.anim.slide_out_right);
        if(ownerName!=null){
            fragment = new FragmentMain();
            Log.d(Tag.SplashScreen,"Name is config,go to Main");
        }else {
            fragment = new FragmentStart();
            Log.d(Tag.SplashScreen,"Name is not config,go to Start");
        }
        fragmentNavigator.setRootFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if(fragmentNavigator.getActiveFragment() instanceof FragmentMain ||
                fragmentNavigator.getActiveFragment() instanceof FragmentStart){
            exitApp();
        }
    }

    private void exitApp(){
        this.finish();
        System.exit(0);
    }
}
