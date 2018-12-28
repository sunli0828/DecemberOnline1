package com.sunli.sunli1227_work;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sunli.sunli1227_work.adapter.DetailTabAdapter;
import com.sunli.sunli1227_work.mvp.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_viewpager)
    ViewPager viewPager;
    @BindView(R.id.detail_tab)
    TabLayout tabLayout;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {
        bind = ButterKnife.bind(this);

        viewPager.setAdapter(new DetailTabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
