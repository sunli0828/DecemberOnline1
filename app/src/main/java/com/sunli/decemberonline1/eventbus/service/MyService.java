package com.sunli.decemberonline1.eventbus.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sunli.decemberonline1.bean.PhoneBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        List<PhoneBean> list = new ArrayList<>();
        PhoneBean bean = new PhoneBean();
        bean.getCode();
        list.add(bean);
        EventBus.getDefault().post(list);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
