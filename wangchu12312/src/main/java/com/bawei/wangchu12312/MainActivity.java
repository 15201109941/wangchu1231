package com.bawei.wangchu12312;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.adapter.MyAdapter;
import com.bawei.base.BaseActivity;
import com.bawei.base.BasePresenter;
import com.bawei.bean.News;
import com.bawei.presenter.Presenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.recy)
    RecyclerView recy;
    private List<News.RankingBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void initData() {
        mPresenter.getInfo("http://blog.zhaoliang5156.cn/api/news/ranking.json");
    }

    @Override
    protected void initView() {
        //添加适配器
        myAdapter = new MyAdapter(list,this);
        recy.setAdapter(myAdapter);
        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //设置点击
        myAdapter.setOnItemClick(new MyAdapter.OnItemClick() {
            @Override
            public void onClick(int i) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        //跳转到 二维码页面
        textA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected BasePresenter initmPresenter() {
        return new Presenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onRuccess(String url) {
        //gson解析
        Gson gson = new Gson();
        News news = gson.fromJson(url, News.class);
        list.addAll(news.getRanking());
        //刷新适配器
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable throwable) {

    }

}
