package cn.leiax00.app.szuni.module;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;

import org.apache.commons.lang3.StringUtils;

import cn.leiax00.app.common.constant.System;
import cn.leiax00.app.common.domain.dto.Result;
import cn.leiax00.app.common.domain.dto.Rst;
import cn.leiax00.app.szuni.activity.SzActivity;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class Main extends UniModule {
    @JSMethod(uiThread = false)
    public void init(JSONObject opts) {
        this.register();
    }


    @JSMethod
    public void showActivity(JSONObject opts, UniJSCallback cb) {
        try {
            Context context = this.mUniSDKInstance.getContext();
            Intent intent = new Intent(context, SzActivity.class);
            intent.putExtra("params", opts.toJSONString());
            context.startActivity(intent);
            cb.invoke(Rst.newBuilder().code(200).build());
        } catch (Exception e) {
            cb.invoke(Rst.newBuilder().code(400).msg(e.getMessage()).build());
        }


    }

    @SuppressLint({"NewApi", "WrongConstant"})
    private void register() {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("SZ-UNI", "Receive action: " + intent.getAction());
                String dataStr = intent.getStringExtra("data");
                JSONObject data = null;
                if (StringUtils.isNotBlank(dataStr)) {
                    data = JSON.parseObject(dataStr);
                }
                fireEvent(intent.getAction(), data);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(System.ACTION_ACCEPT);
        filter.addAction(System.ACTION_REJECT);
        this.mUniSDKInstance.getContext().registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
    }

    private <T> void fireEvent(String action, T data) {
        this.mUniSDKInstance.fireGlobalEventCallback(
                "sz-uni-event",
                new Result() {{
                    put("action", action);
                    if (data != null) {
                        put("data", data);
                    }
                }}
        );
    }

}
