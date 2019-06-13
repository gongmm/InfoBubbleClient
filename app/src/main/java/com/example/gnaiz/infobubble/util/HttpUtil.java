package com.example.gnaiz.infobubble.util;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static final String address="http://192.168.137.1:8000";
    private static OkHttpClient okHttpClient;
    static {
        okHttpClient=new OkHttpClient();
    }
    public static void sendGet(String location, Callback callback){
        Request request=new Request.Builder()
                    .url(address+location)
                    .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void sendPost(String location, RequestBody requestBody, Callback callback){
        Request request=new Request.Builder()
                .url(address+location)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
