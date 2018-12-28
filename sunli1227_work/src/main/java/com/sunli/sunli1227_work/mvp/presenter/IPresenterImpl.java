package com.sunli.sunli1227_work.mvp.presenter;

import com.sunli.sunli1227_work.mvp.callback.ICallBack;
import com.sunli.sunli1227_work.mvp.model.IModelImpl;
import com.sunli.sunli1227_work.mvp.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void stratRequest(String urlStr, Map<String, Object> params, Class clazz) {
        /*iModel.getResponseData(urlStr, params, clazz, new ICallBack() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseData(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseFail(e);
            }
        });*/
    }

    @Override
    public void startRequestget(String urlStr, String params, Class clazz) {
        iModel.getResponseDataGet(urlStr, params, clazz, new ICallBack() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseData(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseFail(e);
            }
        });
    }

    public void onDetach() {
        if (iModel != null) {
            iModel = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
