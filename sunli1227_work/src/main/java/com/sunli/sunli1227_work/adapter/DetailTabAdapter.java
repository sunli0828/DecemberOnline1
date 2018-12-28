package com.sunli.sunli1227_work.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunli.sunli1227_work.fragment.DetailFragment;
import com.sunli.sunli1227_work.fragment.DiscussFragment;
import com.sunli.sunli1227_work.fragment.ThingsFragment;

public class DetailTabAdapter extends FragmentPagerAdapter {

    private String[] pageNum = new String[]{"商品", "详情", "评论"};

    public DetailTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ThingsFragment();
            case 1:
                return new DetailFragment();
            case 2:
                return new DiscussFragment();
            default:
                return new ThingsFragment();
        }
    }

    @Override
    public int getCount() {
        return pageNum.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageNum[position];
    }
}
