package com.sunli.sunli1227_work.mvp.model;

import com.google.gson.Gson;
import com.sunli.sunli1227_work.mvp.callback.ICallBack;
import com.sunli.sunli1227_work.network.RetrofitManager;

import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;

public class IModelImpl<T> implements IModel {
    @Override
    public void getResponseData(String urlStr, Map<String, Object> params, final Class clazz, final ICallBack iCallBack) {
        /*Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().postFormBody(urlStr, map).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallBack != null) {
                        iCallBack.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallBack != null) {
                        iCallBack.setDataFail(e.getMessage());
                    }
                }

            }

            @Override
            public void onFail(String error) {
                if (iCallBack != null) {
                    iCallBack.setDataFail(error);
                }
            }
        });*/
    }

    @Override
    public void getResponseDataGet(String urlStr, String params, final Class clazz, final ICallBack iCallBack) {
        RetrofitManager.getInstance().get(urlStr).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallBack != null) {
                        iCallBack.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallBack != null) {
                        iCallBack.setDataFail(e.getMessage());
                    }
                }

            }

            @Override
            public void onFail(String error) {
                if (iCallBack != null) {
                    iCallBack.setDataFail(error);
                }
            }
        });
    }
}
