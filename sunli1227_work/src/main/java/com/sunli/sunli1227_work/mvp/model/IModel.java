package com.sunli.sunli1227_work.mvp.model;

import com.sunli.sunli1227_work.mvp.callback.ICallBack;

import java.util.Map;

public interface IModel {
    void getResponseData(String urlStr, Map<String, Object> params, Class clazz, ICallBack iCallBack);

    void getResponseDataGet(String urlStr, String params, Class clazz, ICallBack iCallBack);
}
