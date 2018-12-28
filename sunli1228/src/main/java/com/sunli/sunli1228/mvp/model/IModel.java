package com.sunli.sunli1228.mvp.model;

import com.sunli.sunli1228.mvp.callback.ICallBack;

public interface IModel {
    void getResponseData(String urlStr, String params, Class clazz, ICallBack iCallBack);
}
