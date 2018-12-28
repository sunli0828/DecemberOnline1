package com.sunli.sunli1227_work;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sunli.sunli1227_work.adapter.SearchShopAdapter;
import com.sunli.sunli1227_work.bean.ShopBean;
import com.sunli.sunli1227_work.mvp.presenter.IPresenterImpl;
import com.sunli.sunli1227_work.mvp.view.IView;
import com.sunli.sunli1227_work.network.ApiUtils;
import com.sunli.sunli1227_work.network.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private IPresenterImpl iPresenter;
    private EditText editText;
    private XRecyclerView xRecyclerView;

    private final int TYPE_SORT_MULTIPLE = 0;
    private final int TYPE_SORT_PRICE = 1;
    private final int TYPE_SORT_SALENUM = 2;
    private final int TYPE_SORT_CHOOSE = 4;

    private boolean isLinear = true;
    private int i = 0;
    private int mPage;
    private final int spanCount = 2;
    private SearchShopAdapter searchShopAdapter;
    private int sort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresenter = new IPresenterImpl(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.main_text_change).setOnClickListener(this);
        findViewById(R.id.main_text_multiple).setOnClickListener(this);
        findViewById(R.id.main_text_price).setOnClickListener(this);
        findViewById(R.id.main_text_salenum).setOnClickListener(this);
        findViewById(R.id.main_text_choose).setOnClickListener(this);

        xRecyclerView = findViewById(R.id.main_xreyclerview);

        editText = findViewById(R.id.main_edit_search);
        editText.setText("  手机");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);

        searchShopAdapter = new SearchShopAdapter(this, isLinear);

        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                i = 0;
                mPage = 1;
                /*Map<String, Object> params = new HashMap<>();
                params.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
                params.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");
                iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params, ShopBean.class);*/

                iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGE_URL, mPage), null, ShopBean.class);
            }

            @Override
            public void onLoadMore() {
                i = 1;
                /*Map<String, Object> params = new HashMap<>();
                params.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
                params.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");
                iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params, ShopBean.class);*/

                iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGE_URL, mPage), null, ShopBean.class);
            }
        });
        xRecyclerView.setAdapter(searchShopAdapter);
        
        /*Map<String, Object> params = new HashMap<>();
        params.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
        params.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");*/
        
      //  iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params, ShopBean.class);
        iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGE_URL, mPage), null, ShopBean.class);
        
        changeRecclerView();
    }

    /*private void itemClick() {
        searchShopAdapter.setClickListener(new SearchShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<ShopBean.DataBean> list = searchShopAdapter.getList();
                Intent item_itent = new Intent(MainActivity.this, DetailActivity.class);
                item_itent.putExtra("id", list.get(position).getPid());
                item_itent.putExtra("title", list.get(position).getTitle());
                item_itent.putExtra("price", list.get(position).getPrice());
                item_itent.putExtra("image", list.get(position).getImages());
                startActivity(item_itent);
            }
        });
    }*/

    private void changeRecclerView() {
        if (isLinear) {
            LinearLayoutManager linearlayoutManager = new LinearLayoutManager(this);
            linearlayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(linearlayoutManager);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(gridLayoutManager);
        }

        searchShopAdapter = new SearchShopAdapter(this, isLinear);
        xRecyclerView.setAdapter(searchShopAdapter);

        isLinear = !isLinear;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_text_change:
                List<ShopBean.DataBean> dataList = searchShopAdapter.getList();
                changeRecclerView();
                searchShopAdapter.setData(dataList);
                break;
            case R.id.main_text_multiple:
                checkPermission(TYPE_SORT_MULTIPLE);
                break;
            case R.id.main_text_price:
                checkPermission(TYPE_SORT_PRICE);
                break;
            case R.id.main_text_salenum:
                checkPermission(TYPE_SORT_SALENUM);
                break;
            case R.id.main_text_choose:
                checkPermission(TYPE_SORT_CHOOSE);
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
            case TYPE_SORT_MULTIPLE:
                /*Map<String, Object> params1 = new HashMap<>();
                params1.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
                params1.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");
                params1.put(Constants.POST_BODY_SORT_TYPE, "0");
                iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params1, ShopBean.class);*/

                int sort = 0;
                iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGESORT_URL, mPage, sort), null, ShopBean.class);
                break;
            case TYPE_SORT_PRICE:
                /*Map<String , Object > params2 = new HashMap<>();
                params2.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
                params2.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");
                params2.put(Constants.POST_BODY_SORT_TYPE, "1");
                iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params2, ShopBean.class);*/
                int sort1 = 1;
                iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGESORT_URL, mPage, sort1), null, ShopBean.class);
                break;
            case TYPE_SORT_SALENUM:
                /*Map<String, Object> params3 = new HashMap<>();
                params3.put(Constants.POST_BODY_SEARCH_TYPE, editText.getText().toString());
                params3.put(Constants.POST_BODY_SHOPPAGE_TYPE, "0");
                params3.put(Constants.POST_BODY_SORT_TYPE, "2");
                iPresenter.stratRequest(ApiUtils.TYPE_SHOW_URL, params3, ShopBean.class);*/
                int sort2 = 2;
                iPresenter.startRequestget(String.format(ApiUtils.TYPE_SHOWPAGESORT_URL, mPage, sort2), null, ShopBean.class);
                break;
            case TYPE_SORT_CHOOSE:
                break;
            default:
                break;
        }
    }

    @Override
    public void showResponseData(Object data) {
        if (data instanceof ShopBean) {
            ShopBean shopBean = (ShopBean) data;
            List<ShopBean.DataBean> list = shopBean.getData();
            if (i == 0) {
                searchShopAdapter.setData(list);
                xRecyclerView.refreshComplete();
            } else {
                searchShopAdapter.addData(list);
                mPage ++;
                xRecyclerView.loadMoreComplete();
            }
        }
    }

    @Override
    public void showResponseFail (String e){
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}
