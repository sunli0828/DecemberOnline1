package com.sunli.sunli1228.mvp.callback;

public interface ICallBack<T> {
    void setData(T data);
    void setDataFail(Exception e);
}
