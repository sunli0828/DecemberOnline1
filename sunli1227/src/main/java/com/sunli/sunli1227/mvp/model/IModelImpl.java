package com.sunli.sunli1227.mvp.model;

import com.google.gson.Gson;
import com.sunli.sunli1227.mvp.callback.ICallBack;
import com.sunli.sunli1227.network.RetrofitManager;

import java.util.Map;

import okhttp3.RequestBody;

public class IModelImpl<T> implements IModel {
    @Override
    public void getResponseData(String urlStr, Map<String, String> params, final Class clazz, final ICallBack iCallBack) {
        Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
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
        });
    }
}
