package com.bawei.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.Contract;

import butterknife.ButterKnife;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:53
 *@Description:${DESCRIPTION}
 **/
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements Contract.View {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId()!=0){
            setContentView(layoutId());
            mPresenter=initmPresenter();
            if (mPresenter!=null){
                mPresenter.onAttch(this);
            }
            ButterKnife.bind(this);
            initView();
            initData();
        }
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initmPresenter();

    protected abstract int layoutId();
    //N内存溢出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDeAttch();
            mPresenter=null;
        }
    }
}
