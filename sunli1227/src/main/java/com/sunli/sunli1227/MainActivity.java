package com.sunli.sunli1227;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sunli.sunli1227.bean.LoginBean;
import com.sunli.sunli1227.bean.RegisterBean;
import com.sunli.sunli1227.mvp.presenter.IPresenterImpl;
import com.sunli.sunli1227.mvp.view.IView;
import com.sunli.sunli1227.network.ApiUtils;
import com.sunli.sunli1227.network.Constants;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private IPresenterImpl iPresenter;
    private EditText edit_phone;
    private EditText edit_pwd;

    private final int TYPE_LOGIN = 0;
    private final int TYPE_REGISTER = TYPE_LOGIN + 1;
    private final int TYPE_GET_USER_INFO = TYPE_REGISTER + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresenter = new IPresenterImpl(this);

        initView();
    }

    private void initView() {
        edit_phone = findViewById(R.id.main_edit_phone);
        edit_phone.setText("13800138000");

        edit_pwd = findViewById(R.id.main_edit_pwd);
        edit_pwd.setText("123456");

        findViewById(R.id.main_btn_login).setOnClickListener(this);
        findViewById(R.id.main_btn_enroll).setOnClickListener(this);
        findViewById(R.id.main_btn_getpersonalinfo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_login:
                checkPermission(TYPE_LOGIN);
                break;
            case R.id.main_btn_enroll:
                checkPermission(TYPE_REGISTER);
                break;
            case R.id.main_btn_getpersonalinfo:
                checkPermission(TYPE_GET_USER_INFO);
                break;
            default:
                break;
        }
    }

    private void checkPermission(int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, type);
            } else {
                startRequest(type);
            }
        } else {
            startRequest(type);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRequest(requestCode);
        }
    }

    private void startRequest(int type) {
        switch (type) {
            case TYPE_LOGIN:
                Map<String, String> params = new HashMap<>();
                params.put(Constants.POST_BODY_KEY_LOGIN_PHONE, edit_phone.getText().toString());
                params.put(Constants.POST_BODY_KEY_LOGIN_PASSWORD, edit_pwd.getText().toString());
                iPresenter.stratRequest(ApiUtils.TYPE_LOGIN_URL, params, LoginBean.class);
                break;
            case TYPE_REGISTER:
                Map<String , String > map = new HashMap<>();
                map.put(Constants.POST_BODY_KEY_REGISTER_PHONE, edit_phone.getText().toString());
                map.put(Constants.POST_BODY_KEY_REGISTER_PASSWORD, edit_pwd.getText().toString());
                iPresenter.stratRequest(ApiUtils.TYPE_REGISTER_URL, map, RegisterBean.class);
                break;
            case TYPE_GET_USER_INFO:
                break;
            default:
                break;
        }
    }


    @Override
    public void showResponseData(Object data) {
        if(data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
            Toast.makeText(this, loginBean.getCode(), Toast.LENGTH_SHORT).show();
        }else if(data instanceof RegisterBean){
            RegisterBean registerBean = (RegisterBean) data;
            Toast.makeText(this, registerBean.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showResponseFail(String e) {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}