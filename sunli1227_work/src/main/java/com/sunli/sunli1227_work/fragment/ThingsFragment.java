package com.sunli.sunli1227_work.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sunli.sunli1227_work.R;
import com.sunli.sunli1227_work.bean.DetailBean;
import com.sunli.sunli1227_work.bean.EventBusBean;
import com.sunli.sunli1227_work.mvp.presenter.IPresenterImpl;
import com.sunli.sunli1227_work.mvp.view.IView;
import com.sunli.sunli1227_work.network.ApiUtils;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class ThingsFragment extends Fragment implements IView, View.OnClickListener {

    private Banner banner;
    private TextView textView;
    private IPresenterImpl iPresenter;
    private List<String> list;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_things, null);

        banner = view.findViewById(R.id.fragment_things_banner);
        textView = view.findViewById(R.id.fragment_things_title);
        button = view.findViewById(R.id.fragment_things_btn_intent);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean("魏无羡", "男"));
                Intent intent = new Intent(getContext(), DetailFragment.class);
                startActivity(intent);
            }
        });

        iPresenter = new IPresenterImpl(this);
        iPresenter.startRequestget(ApiUtils.TYPE_SHOWTHINGS_DETAIL, null, DetailBean.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iPresenter.startRequestget(ApiUtils.TYPE_SHOWTHINGS_DETAIL, null, DetailBean.class);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showResponseData(Object data) {
        if (data instanceof DetailBean) {
            DetailBean bean = (DetailBean) data;
            DetailBean.DataBean databean = bean.getData();
            textView.setText(bean.getData().getTitle());
            String images = bean.getData().getImages();
            String[] split = images.split("\\|");
            list = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();
        }
    }

    @Override
    public void showResponseFail(String e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void toastString() {

    }
}
