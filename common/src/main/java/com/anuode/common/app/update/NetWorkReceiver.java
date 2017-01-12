package com.anuode.common.app.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkReceiver extends BroadcastReceiver {
    public static boolean isNetWorkOk = true;
    public static int mNetWorkType = 0;

    /**
     *  wifi网络
     */
    public static final int NETWORKTYPE_WIFI = 5;
    /**
     *  没有网络
     */
    public static final int NETWORKTYPE_INVALID = 0;
    /**
     *  wap网络
     */
    public static final int NETWORKTYPE_WAP = 1;

    /**
     *  网络变更
     *
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        mNetWorkType = getNetWorkType(context);
      /*  Intent mIntent = new Intent("com.changtu.wetrip.download");
        mIntent.putExtra("isNetWorkOk",isFastMobileNetwork(context));
        context.sendBroadcast(mIntent);*/
    }

    /**
     *  获取当前网络类型
     *
     */
    public static int getNetWorkType(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

       // boolean isNetWorkOk = true;
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();

            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                mNetWorkType = NETWORKTYPE_WAP;
            }
           // isNetWorkOk = true;
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
           // isNetWorkOk = false;
        }
        return mNetWorkType;
    }
}
