package com.sunli.decemberonline1.ui.fragment;

import android.view.View;
import android.widget.Toast;

import com.sunli.decemberonline1.R;
import com.sunli.decemberonline1.bean.PhoneBean;
import com.sunli.decemberonline1.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class HotFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.hot_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
    }
}
