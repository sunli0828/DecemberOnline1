package com.sunli.sunli1227.mvp.presenter;

import java.util.Map;

public interface IPresenter {
    void stratRequest(String urlStr, Map<String, String> params, Class clazz);
}
