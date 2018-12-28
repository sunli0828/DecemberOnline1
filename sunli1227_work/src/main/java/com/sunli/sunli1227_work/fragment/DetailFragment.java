package com.sunli.sunli1227_work.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunli.sunli1227_work.R;
import com.sunli.sunli1227_work.bean.EventBusBean;

import org.greenrobot.eventbus.Subscribe;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_detail, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe
    public void getData(EventBusBean bean) {
        Toast.makeText(getContext(), bean.getName(), Toast.LENGTH_SHORT).show();
    }
}
