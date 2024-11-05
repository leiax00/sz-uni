package cn.leiax00.app.szuni.module;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;

import cn.leiax00.app.common.utils.HttpHelper;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class Http extends UniModule {
    @JSMethod(uiThread = false)
    public void get(JSONObject opts, UniJSCallback cb) {
        HttpHelper.get(opts).thenAccept(cb::invoke);
    }

    @JSMethod(uiThread = false)
    public void post(JSONObject opts, UniJSCallback cb) {
        HttpHelper.post(opts).thenAccept(cb::invoke);
    }
}
