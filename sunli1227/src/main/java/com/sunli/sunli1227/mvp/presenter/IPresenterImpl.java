package com.sunli.sunli1227.mvp.presenter;

import com.sunli.sunli1227.mvp.callback.ICallBack;
import com.sunli.sunli1227.mvp.model.IModelImpl;
import com.sunli.sunli1227.mvp.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void stratRequest(String urlStr, Map<String, String> params, Class clazz) {
        iModel.getResponseData(urlStr, params, clazz, new ICallBack() {
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
