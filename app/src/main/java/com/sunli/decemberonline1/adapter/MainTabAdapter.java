package com.sunli.decemberonline1.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunli.decemberonline1.ui.fragment.DrinkFragment;
import com.sunli.decemberonline1.ui.fragment.HotFragment;
import com.sunli.decemberonline1.ui.fragment.SignFragment;
import com.sunli.decemberonline1.ui.fragment.SnackFragment;
import com.sunli.decemberonline1.ui.fragment.StapleFoodFragment;

public class MainTabAdapter extends FragmentPagerAdapter {

    private String[] pageNum = new String[]{"热销", "招牌", "主食", "小吃", "饮品"};

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HotFragment();
            case 1:
                return new SignFragment();
            case 2:
                return new StapleFoodFragment();
            case 3:
                return new SnackFragment();
            case 4:
                return new DrinkFragment();
            default:
                return new HotFragment();
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
