package com.hoangphuong2.salemanager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hoangphuong2.salemanager.ui.fragment.FragmentBill;
import com.hoangphuong2.salemanager.ui.fragment.FragmentCompany;
import com.hoangphuong2.salemanager.ui.fragment.FragmentPrivate;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentPrivate fragmentPrivate = new FragmentPrivate();
                return fragmentPrivate;
            case 1:
                FragmentCompany fragmentCompany = new FragmentCompany();
                return fragmentCompany;
            case 2:
                FragmentBill fragmentBill = new FragmentBill();
                return fragmentBill;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}