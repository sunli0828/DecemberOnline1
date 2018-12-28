package com.sunli.sunli1227_work.mvp.callback;

public interface ICallBack<T> {
    void setDataSuccess(T data);
    void setDataFail(String e);
}
