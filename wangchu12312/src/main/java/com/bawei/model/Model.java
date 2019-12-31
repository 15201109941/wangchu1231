package com.bawei.model;

import com.bawei.Contract;
import com.bawei.util.HttpUtil;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:58
 *@Description:${DESCRIPTION}
 **/
public class Model implements Contract.Model {
    @Override
    public void getInfo(String url, final Contract.MyCallBack myCallBack) {
        HttpUtil.getInstance().getInfo(url, new HttpUtil.MyCallBack() {
            @Override
            public void onRuccess(String url) {
                myCallBack.onRuccess(url);
            }

            @Override
            public void onError(Throwable throwable) {
                myCallBack.onError(throwable);
            }
        });
    }
}
