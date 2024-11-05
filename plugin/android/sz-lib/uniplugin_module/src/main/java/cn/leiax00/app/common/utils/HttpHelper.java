package cn.leiax00.app.common.utils;


import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import cn.leiax00.app.common.constant.HttpStatus;
import cn.leiax00.app.common.domain.dto.Rst;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpHelper {
    private static OkHttpClient client;

    public synchronized static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder().build();
        }
        return client;
    }

    public static CompletableFuture<Rst<Object>> get(JSONObject reqOpts) {
        OkHttpClient client = getClient();
        String url = reqOpts.getString("url");
        JSONObject params = reqOpts.getJSONObject("params");
        if (params != null) {
            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?");
            for (String key : params.keySet()) {
                urlBuilder.append(key).append("=").append(params.getString(key)).append("&");
            }
            url = urlBuilder.toString();
            url = url.substring(0, url.length() - 1);
        }
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return request(client, request);
    }

    public static CompletableFuture<Rst<Object>> post(JSONObject reqOpts) {
        OkHttpClient client = getClient();
        MediaType media = MediaType.parse("application/json; charset=utf-8");
        String url = reqOpts.getString("url");
        JSONObject body = reqOpts.getJSONObject("body");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(media, body.toString()))
                .build();
        return request(client, request);
    }

    private static CompletableFuture<Rst<Object>> request(OkHttpClient client, Request request) {
        CompletableFuture<Rst<Object>> future = new CompletableFuture<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Rst<Object> rst;
                try {
                    ResponseBody resp = response.body();
                    int code = response.code();
                    String contentType = response.header("Content-Type");
                    if (resp != null) {
                        String respStr = resp.string();
                        if (StringUtils.isNotBlank(contentType) && contentType.contains("application/json")) {
                            rst = Rst.newBuilder().code(code).data(JSON.parseObject(respStr)).build();
                        } else {
                            rst = Rst.newBuilder().code(code).data(respStr).build();
                        }
                    } else {
                        rst = Rst.newBuilder().code(code).build();
                    }
                } catch (Exception e) {
                    Logger.e("Failed to Parse response: " + CommonUtils.getStackTrace(e));
                    rst = Rst.newBuilder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .msg(e.getMessage())
                            .build();
                }

                future.complete(rst);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.e("Failed to request: " + CommonUtils.getStackTrace(e));
                future.complete(
                        Rst.newBuilder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .msg(e.getMessage())
                                .build()
                );
            }
        });
        return future;
    }
}
