package com.sunli.sunli1227.mvp.model;

import com.sunli.sunli1227.mvp.callback.ICallBack;

import java.util.Map;

public interface IModel {
    void getResponseData(String urlStr, Map<String, String> params, Class clazz, ICallBack iCallBack);
}
