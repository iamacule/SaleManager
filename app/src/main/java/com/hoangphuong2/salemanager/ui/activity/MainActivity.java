package com.hoangphuong2.salemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.ui.adapter.PagerAdapter;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.DataUtil;

/**
 * Created by MrAn on 12-May-16.
 */
public class MainActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private FloatingActionButton addButton;
    private final int SELECTED = 1;
    private final int UNSELECTED = 0;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setOnClick() {
        OnSingleClickListener onSingleClickListener = new OnSingleClickListener(500) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.fab:
                        switch (DataUtil.current_fragment) {
                            case DataUtil.PRIVATE:
                                intent = new Intent(MainActivity.this, AddPrivateActivity.class);
                                startActivity(intent);
                                break;
                            case DataUtil.COMPANY:

                                break;
                            case DataUtil.BILL:

                                break;
                        }
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

    public void setVisibleAddButton(boolean isShow) {
        if (isShow) {
            if (addButton.getVisibility() == View.GONE) {
                addButton.startAnimation(AnimationUtil.slideInBottom(this));
                addButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (addButton.getVisibility() == View.VISIBLE) {
                addButton.startAnimation(AnimationUtil.slideOutBottom(this));
                addButton.setVisibility(View.GONE);
            }
        }
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
                DataUtil.current_fragment = tab.getPosition();
                setIcon(SELECTED, tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setIcon(UNSELECTED, tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setIcon(int id, TabLayout.Tab tab) {
        if (id == SELECTED) {
            switch (tab.getPosition()) {
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
        } else {
            switch (tab.getPosition()) {
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

    @Override
    public void identifyLayout() {
        initLayout();
        initToolBar();
    }

    @Override
    public void identityValue() {
        initTabLayout();
    }

    @Override
    public void identityAction() {
        setOnClick();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
