package cn.leiax00.app.szuni.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

import cn.leiax00.app.R;
import cn.leiax00.app.common.constant.System;
import cn.leiax00.app.szuni.activity.bo.ParamObj;

public class SzActivity extends Activity {
    private final BroadcastReceiver closeActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();  // 结束活动
        }
    };

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("Start to init call activity!");
        Window window = getWindow();
        window.addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        this.setContentView(R.layout.activity_sz);

        Intent intent = getIntent();
        String params = intent.getStringExtra(ParamObj.FIELD_KEY);
        this.updateView(JSON.parseObject(params, ParamObj.class));

        IntentFilter filter = new IntentFilter(System.ACTION_REJECT);
        registerReceiver(closeActivityReceiver, filter, Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(closeActivityReceiver);
    }

    private void updateView(ParamObj opts) {
        TextView callStatus = findViewById(R.id.callStatus);
        FrameLayout answerBtn = findViewById(R.id.answerButton);
        FrameLayout declineBtn = findViewById(R.id.declineButton);
        callStatus.setText(opts.getPhoneNum());
        answerBtn.setOnClickListener(view -> {
            answerCall();
        });
        declineBtn.setOnClickListener(view -> {
            declineCall();
        });
    }

    private void answerCall() {
        Intent intent = new Intent(System.ACTION_ACCEPT);
        sendBroadcast(intent);
    }

    private void declineCall() {
        Intent intent = new Intent(System.ACTION_REJECT);
        sendBroadcast(intent);
    }
}