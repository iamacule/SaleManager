package com.hoangphuong2.salemanager.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.ui.adapter.PagerAdapter;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;

/**
 * Created by MrAn on 12-May-16.
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private FloatingActionButton addButton;
    private final int SELECTED = 1;
    private final int UNSELECTED = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        initToolBar();
        initTabLayout();
        setOnClick();
    }

    private void setOnClick() {
        OnSingleClickListener onSingleClickListener = new OnSingleClickListener(500) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()){
                    case R.id.fab:
                        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }
            }
        };
        addButton.setOnClickListener(onSingleClickListener);
    }

    private void initLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        addButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.private_)).setIcon(R.drawable.private_select));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.company)).setIcon(R.drawable.company_none_select));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.bill)).setIcon(R.drawable.bill_none_select));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                setIcon(SELECTED,tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setIcon(UNSELECTED,tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setIcon(int id,TabLayout.Tab tab){
        if (id==SELECTED){
            switch (tab.getPosition()){
                case 0:
                    tab.setIcon(R.drawable.private_select);
                    break;
                case 1:
                    tab.setIcon(R.drawable.company_select);
                    break;
                case 2:
                    tab.setIcon(R.drawable.bill_select);
                    break;
            }
        }else {
            switch (tab.getPosition()){
                case 0:
                    tab.setIcon(R.drawable.private_none_select);
                    break;
                case 1:
                    tab.setIcon(R.drawable.company_none_select);
                    break;
                case 2:
                    tab.setIcon(R.drawable.bill_none_select);
                    break;
            }
        }
    }

    private void initToolBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
