package com.example.home_pc.testpurchaselistapp.ui.main.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.PurchaseFragment;

import java.util.ArrayList;

public class MainAdapter extends FragmentStatePagerAdapter {

    public static final int MY_PURCHASES = 0;
    public static final int FUTURE_PURCHASES = 1;

    private ArrayList<PurchaseFragment> mFragmentList = new ArrayList<>();
    private ArrayList<String> mFragmentTitleList = new ArrayList<>();

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public String getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(PurchaseFragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

   public void onPageSelected(int position) {
        mFragmentList.get(position).onTabChanged(position);
    }

    public void addPurchasesToMyList(){
        PurchaseFragment fragment = mFragmentList.get(FUTURE_PURCHASES);
        fragment.addSelectedPurchasesToMyList();
    }

}

