package com.bawei;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:51
 *@Description:${DESCRIPTION}
 **/
public interface Contract {
    interface MyCallBack{
        void onRuccess(String url);
        void onError(Throwable throwable);
    }
    interface View{
        void onRuccess(String url);
        void onError(Throwable throwable);
    }
    interface Model{
        void getInfo(String url,MyCallBack myCallBack);
    }
    interface Presenter{
        void getInfo(String url);
    }
}
