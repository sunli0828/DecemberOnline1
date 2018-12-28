package com.sunli.decemberonline1.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sunli.decemberonline1.R;
import com.sunli.decemberonline1.adapter.MainTabAdapter;
import com.sunli.decemberonline1.bean.PhoneBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.main_tab)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.main_tab);
        viewPager = findViewById(R.id.main_viewpager);

        viewPager.setAdapter(new MainTabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onChangeItem() {viewPager.setCurrentItem(1);}

}
