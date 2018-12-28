package com.sunli.sunli1227_work.mvp.presenter;

import java.util.Map;

public interface IPresenter {
    void stratRequest(String urlStr, Map<String, Object> params, Class clazz);

    void startRequestget(String urlStr, String params, Class clazz);
}
