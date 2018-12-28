package com.sunli.decemberonline1.ui.fragment;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.sunli.decemberonline1.R;
import com.sunli.decemberonline1.bean.PhoneBean;
import com.sunli.decemberonline1.eventbus.MessageEvent;
import com.sunli.decemberonline1.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SignFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Toast.makeText(getActivity(), "得到数据",   Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_sign;
    }

}
