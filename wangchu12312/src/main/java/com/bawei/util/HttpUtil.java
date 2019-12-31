package com.bawei.util;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:45
 *@Description:${DESCRIPTION}
 **/
public class HttpUtil {

    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private HttpUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
    private static class NetHolder{
        private static final HttpUtil mNet = new HttpUtil();
    }
    public static HttpUtil getInstance(){
        return NetHolder.mNet;
    }
    public interface MyCallBack{
        void onRuccess(String url);
        void onError(Throwable throwable);
    }
    public void getInfo(String url, final MyCallBack myCallBack){
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                if (response!=null&&response.isSuccessful()){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onRuccess(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("请求失败"));
                        }
                    });
                }
            }
        });
    }
}
