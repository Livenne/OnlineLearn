package com.livenne.onlinelearn.common.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.livenne.onlinelearn.common.model.dto.ResponseEntity;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkUtils {
    public static String token = "";
    public static final String baseUrl = "http://10.0.2.2:8080/Gradle___com_livenne___system_server_1_0_SNAPSHOT_war";
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static OkHttpClient client = new OkHttpClient();
    public static <T> void loadResource(String pathUrl,TypeReference<T> typeReference,NetworkCallable<T> networkCallable){
        loadResource(pathUrl,null,typeReference,networkCallable);
    }
    public static <T> void loadResource(String pathUrl,String body,TypeReference<T> typeReference,NetworkCallable<T> networkCallable){
        System.out.println(baseUrl + pathUrl);
        Request.Builder builder = new Request.Builder()
                .url(baseUrl + pathUrl)
                .header("Authorization", "Bearer " + token);
        if (body == null) builder.get();
        else builder.post(RequestBody.create(body, MediaType.parse("application/json; charset=utf-8")));
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(() -> networkCallable.onFailure("Request Failure"));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                String body = response.body().string();
                System.out.println("Body: "+body + " End;");
                ResponseEntity<?> res = StringUtils.formJson(body, ResponseEntity.class);
                String data = StringUtils.toJson(res.getData());
                T result = StringUtils.formJson(data, typeReference);
                System.out.println("result: " + result);
                handler.post(() -> networkCallable.onSuccess(result));
            }
        });

    }
}
