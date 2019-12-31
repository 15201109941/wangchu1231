package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.bean.News;
import com.bawei.util.GlideUtil;
import com.bawei.wangchu12312.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:9:06
 *@Description:${DESCRIPTION}
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    private List<News.RankingBean> list;
    private Context context;

    public MyAdapter(List<News.RankingBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //绑定
        View view = View.inflate(context, R.layout.a, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        GlideUtil.loadImage(list.get(i).getAvatar(),holder.imgA);
        holder.textA.setText(list.get(i).getName());
        holder.textB.setText(list.get(i).getRank()+"");
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_a)
        ImageView imgA;
        @BindView(R.id.text_a)
        TextView textA;
        @BindView(R.id.text_b)
        TextView textB;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClick{
        void onClick(int i);
    }
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
