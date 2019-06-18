package com.bwei.demo_hming20190618.net;

import com.bwei.demo_hming20190618.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther :Hming
 * @Date : 2019/6/18  21:19
 * @Description: ${DESCRIPTION}
 */
public class HttpUtils {

    private final Retrofit retrofit;

    private HttpUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                // 添加拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static HttpUtils utils;

    public static HttpUtils getInstance() {
        if (utils == null) {
            synchronized (HttpUtils.class) {
                if (utils == null) {
                    utils = new HttpUtils();
                }
            }
        }
        return utils;


    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }

}
