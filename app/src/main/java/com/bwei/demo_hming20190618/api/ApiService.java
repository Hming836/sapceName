package com.bwei.demo_hming20190618.api;

import com.bwei.demo_hming20190618.entity.UpImgEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * @Auther :Hming
 * @Date : 2019/6/18  20:30
 * @Description: ${DESCRIPTION}
 */
public interface ApiService {

    /*
     * 多表单上传
     *  单张
     * */
    @POST(Api.UPIMG_URL)
    @Multipart
    Observable<UpImgEntity> upImg(@HeaderMap HashMap<String, String> headers, MultipartBody.Part file);


}
