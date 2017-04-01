package com.anuode.common.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.anuode.common.app.update.VersionManager;
//import com.anuode.common.net.http.JsonHttpClient;
import com.anuode.common.net.thrift.TAsyncHttpClientManager;
import com.anuode.common.net.volley.SelfSignSslOkHttpStack;
//import com.anuode.common.net.volley.VolleyImageLoader;
import com.anuode.common.util.PreferenceUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public abstract class ApplicationBase extends MultiDexApplication {

    public static class AppParams {
        private int mVolleyImageCacheSize;
        private String mVersionUpdateCheckUrl;

        public AppParams() {
            mVolleyImageCacheSize = (int)(Runtime.getRuntime().maxMemory()) / 12;
        }

        public int getVolleyImageCacheSize() {
            return mVolleyImageCacheSize;
        }

        public AppParams setVolleyImageCacheSize(int volleyImageCacheSize) {
            this.mVolleyImageCacheSize = volleyImageCacheSize;
            return this;
        }

        public String getVersionUpdateCheckUrl() {
            return mVersionUpdateCheckUrl;
        }

        public AppParams setVersionUpdateCheckUrl(String versionUpdateCheckUrl) {
            this.mVersionUpdateCheckUrl = versionUpdateCheckUrl;
            return this;
        }
    }

    private final AppParams mParams = new AppParams();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        onInitAppParams(mParams);
        onInitComponents();
        onInitUIComponents();
        super.onCreate();
    }

    protected void onInitAppParams(AppParams params) {
    }

    protected void onInitComponents() {
        TAsyncHttpClientManager.init(this);
        SelfSignSslOkHttpStack.init(this);
//        JsonHttpClient.init(this);
        PreferenceUtil.init(this);
        Fresco.initialize(this);
//        VolleyImageLoader.init(this, mParams.getVolleyImageCacheSize());
        VersionManager.init(mParams.getVersionUpdateCheckUrl());
    }

    protected void onInitUIComponents() {
    }
}
