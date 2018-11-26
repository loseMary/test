package com.example.huzhikuan.list.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Myokhttp {
    MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
    String request = "I am Jdqm.";
    public  void postasync(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType,request);
        final Request request = new Request.Builder().url("").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
