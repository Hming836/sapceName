package com.bwei.demo_hming20190618;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bwei.demo_hming20190618.api.ApiService;
import com.bwei.demo_hming20190618.entity.UpImgEntity;
import com.bwei.demo_hming20190618.net.HttpUtils;

import java.io.File;
import java.util.HashMap;

import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private HttpUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = HttpUtils.getInstance();


        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "6444");
        params.put("sessionId", "15608474768466444");


    }

    @OnClick(R.id.tv_main)
    public void upData(View view) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建文件
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello.jpg");

            //创建 文件请求体对象
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            //多表单上传的工具类
            MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("userId", "6444");
            headers.put("sessionId", "15608474768466444");
            utils.createService(ApiService.class).upImg(headers, imgPart)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<UpImgEntity>() {
                        @Override
                        public void accept(UpImgEntity upImgEntity) throws Exception {
                            Toast.makeText(MainActivity.this, "" + upImgEntity.message, Toast.LENGTH_SHORT).show();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}
