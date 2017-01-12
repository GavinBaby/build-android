package com.anuode.common.app.update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.anuode.common.net.http.JsonHttpClient;
import com.anuode.common.util.PackageUtil;

import java.io.File;

public class VersionManager {

    private ProgressDialog mProgressDialog;
    private static String mCheckVersionUrl;
    private Context mContext;
    private static VersionManager versionManager;

    public static void init(String mCheckUrl) {
        mCheckVersionUrl = mCheckUrl;
    }

    public static void checkVersionManager(Context context, int flag) {

        if (versionManager == null) {
            versionManager = new VersionManager();
        }
        versionManager.doCheckVersion(context, flag);
    }

    private void doCheckVersion(Context context, int flag) {
        mContext = context;
        new JsonHttpClient().get(mCheckVersionUrl, (err, objectData, json) -> {
            if (err == null) {
                final String mDownloadUrl = objectData.get("data").has("url") ? objectData.get("data").findValue("url").asText() : "http://dn-wetrip-public.qbox.me/WeTrip-release-1.0.1.apk";
                String[] version = PackageUtil.getAppVersionName(mContext).split("\\.");
                String[] latestVersion = objectData.get("data").findValue("version").asText().split("\\.");
                if (Integer.parseInt(latestVersion[0]) > Integer.parseInt(version[0]) || Integer.parseInt(latestVersion[1]) > Integer.parseInt(version[1])
                        || Integer.parseInt(latestVersion[2]) > Integer.parseInt(version[2])) {
                    AlertDialog dialog = new AlertDialog.Builder(mContext).setTitle("发现新版本，是否升级？").
                            setNegativeButton("取消", (dialog_, which) -> {
                                dialog_.dismiss();

                            }).setPositiveButton("确定", (dialog_, which) -> {
                        dialog_.dismiss();
                        if (NetWorkReceiver.getNetWorkType(mContext) != NetWorkReceiver.NETWORKTYPE_WIFI) {
                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).setTitle("您现在不是在WIFI环境下，请确认是否升级？")
                                    .setNegativeButton("取消", (dialog__, which__) -> {
                                        dialog__.dismiss();

                                    }).setPositiveButton("确定", (dialog__, which__) -> {
                                        dialog__.dismiss();
                                        download(mDownloadUrl);
                                    }).create();
                            alertDialog.setCancelable(false);
                            alertDialog.show();
                        } else {
                            download(mDownloadUrl);
                        }
                    }).create();
                    dialog.setCancelable(false);
                    dialog.show();
                } else {
                    if (flag == 0) {

                    } else {
                        Toast.makeText(mContext, "已经是最新版本！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    /**
     * 下载最新版本的APP
     */
    private void download(String mDownloadUrl) {
        mProgressDialog = new ProgressDialog(mContext, ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("正在下载最新安装包.....");
        mProgressDialog.setMax(100);
        mProgressDialog.show();

        MyBroadCastReceiver mBroadCastReciver = new MyBroadCastReceiver();
        mContext.registerReceiver(mBroadCastReciver, new IntentFilter("com.txjc.app.download"));
        Intent intent = new Intent(mContext, DownLoadService.class);
        intent.putExtra("path", mDownloadUrl);
        mContext.startService(intent);
    }


    class MyBroadCastReceiver extends BroadcastReceiver {
        public MyBroadCastReceiver() {
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("start")) {
                mProgressDialog.setProgress(0);
            }
            if (intent.hasExtra("process")) {
                int progress = intent.getIntExtra("process", 0);
                Log.e("process----->", progress + "");
                mProgressDialog.setProgress(progress);
            }
            if (intent.hasExtra("complete")) {
                mProgressDialog.dismiss();
                PackageUtil.installApk(mContext, new File(Environment.getExternalStorageDirectory(), "txjc.apk"));
            }
        }
    }
}
