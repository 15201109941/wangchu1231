package com.bawei.base;

import com.bawei.Contract;

import java.lang.ref.WeakReference;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:53
 *@Description:${DESCRIPTION}
 **/
public abstract class BasePresenter<V extends Contract.View> implements Contract.Presenter {
    private WeakReference<V> mWeak;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
    public void onAttch(V view){
        mWeak=new WeakReference<>(view);
    }
    public void onDeAttch(){
        if (mWeak!=null){
            mWeak.clear();
            mWeak=null;
        }
    }
    public V getView(){
        return mWeak.get();
    }
}
