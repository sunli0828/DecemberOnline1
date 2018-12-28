package com.sunli.sunli1228;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunli.sunli1228.mvp.presenter.IPresenterImpl;
import com.sunli.sunli1228.mvp.view.IView;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener{

    private RecyclerView recyclerView;
    private IPresenterImpl iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresenter = new IPresenterImpl(this);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.main_reyclerData);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showResponseData(Object data) {

    }

    @Override
    public void shoeResponseDataFail(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}
