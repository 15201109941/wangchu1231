package com.bawei.presenter;

import com.bawei.Contract;
import com.bawei.base.BasePresenter;
import com.bawei.model.Model;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:59
 *@Description:${DESCRIPTION}
 **/
public class Presenter extends BasePresenter {
    private Contract.Model model;
    @Override
    protected void initModel() {
        model=new Model();
    }

    @Override
    public void getInfo(String url) {
        model.getInfo(url, new Contract.MyCallBack() {
            @Override
            public void onRuccess(String url) {
                getView().onRuccess(url);
            }

            @Override
            public void onError(Throwable throwable) {
                getView().onError(throwable);
            }
        });
    }
}
