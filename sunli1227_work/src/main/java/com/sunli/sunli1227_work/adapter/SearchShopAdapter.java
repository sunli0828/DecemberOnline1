package com.sunli.sunli1227_work.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunli.sunli1227_work.DetailActivity;
import com.sunli.sunli1227_work.R;
import com.sunli.sunli1227_work.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean> list1 = new ArrayList<>();

    private boolean isLinear;

    public SearchShopAdapter(Context context, boolean isLinear) {
        this.context = context;
        this.isLinear = isLinear;
    }

    public List<ShopBean.DataBean> getList() {
        return list1;
    }

    public void setData(List<ShopBean.DataBean> list) {
        if (list != null) {
            list1 = list;
        }
        notifyDataSetChanged();
    }

    public void addData(List<ShopBean.DataBean> list) {
        if (list != null) {
            list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (isLinear) {
            View view = View.inflate(context, R.layout.item_linear_xrecycler, null);
            return new ViewHolderLinear(view);
        } else {
            View view1 = View.inflate(context, R.layout.item_grid_xrecycler, null);
            return new ViewHolderLinear(view1);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (isLinear) {
            ViewHolderLinear holderLinear = (ViewHolderLinear) holder;

            holderLinear.textView.setText(list1.get(position).getTitle());
            Pattern compile = Pattern.compile("\\|");
            String[] split = compile.split(list1.get(position).getImages().replace("https", "http"));
            Uri uri = Uri.parse(split[0]);
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holderLinear.img.setController(controller);
            holderLinear.ll_item_recycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (clickListener != null) {
                        clickListener.onItemClick(position);
                    }*/
                    context.startActivity(new Intent(context, DetailActivity.class));
                }
            });
        } else {
            ViewHolderGrid holderGrid = (ViewHolderGrid) holder;
            holderGrid.textView.setText(list1.get(position).getTitle());
            Pattern compile = Pattern.compile("\\|");
            String[] split = compile.split(list1.get(position).getImages().replace("https", "http"));
            Uri uri = Uri.parse(split[0]);
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holderGrid.img.setController(controller);
        }

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class ViewHolderLinear extends RecyclerView.ViewHolder {
        public SimpleDraweeView img;
        public TextView textView;
        public LinearLayout ll_item_recycle;

        public ViewHolderLinear(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_linear_icon);
            textView = itemView.findViewById(R.id.item_linear_text);
            ll_item_recycle = itemView.findViewById(R.id.ll_item_recycler);
        }
    }

    class ViewHolderGrid extends RecyclerView.ViewHolder {
        public SimpleDraweeView img;
        public TextView textView;

        public ViewHolderGrid(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_item_grid);
            textView = itemView.findViewById(R.id.tv_item_grid);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnLongItemClickListener {
        void onItemLongClick(int position);
    }

    private OnItemClickListener clickListener;
    private OnLongItemClickListener longClickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(OnLongItemClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

}