package com.anuode.common.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.ByteArrayOutputStream;

public class ShareManager{
    private static Context mContext;
    private static Tencent mTencent;
    private static IWeiboShareAPI iWeiboShareAPI;
    private static IWXAPI mIWXApi;

    public static void initShareManager(Context mcontext, String qqAppid, String WeiBoAppid, String WeiXinAppid) {
        mContext = mcontext;

        if (mTencent == null) {
            mTencent = Tencent.createInstance(qqAppid, mcontext);
        }

        if (iWeiboShareAPI == null) {
            iWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, WeiBoAppid);
            iWeiboShareAPI.registerApp();
        }

        mIWXApi = WXAPIFactory.createWXAPI(mContext, WeiXinAppid);
        mIWXApi.registerApp(WeiXinAppid);
    }
    public static IWeiboShareAPI getiWeiboShareAPI() {
        return iWeiboShareAPI;
    }

    public static void startQQShare(Activity context, String title, String desc, String url, String imgurl) {
        final Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);//标题
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);//网址
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);//描述
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgurl);//图片地址
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, title);//项目名称
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 0x00);
        mTencent.shareToQQ(context, params, qqShareListener);
    }

    private static final IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            Toast.makeText(mContext, "分享取消", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            Toast.makeText(mContext, "分享成功", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            Toast.makeText(mContext, "分享失败", Toast.LENGTH_LONG).show();
        }
    };

    public static void startweiboShare(Context mContext, String title, String url, Bitmap bitmap, String desc) {
        TextObject textObject = new TextObject();
        textObject.text = title;//项目名
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;//标题
        mediaObject.description = desc;// 描述

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream);
        mediaObject.setThumbImage(bitmap);
        bitmap.recycle();

        mediaObject.actionUrl = url;//网址
        mediaObject.defaultText = "Webpage 默认文案";

        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObject;
        weiboMessage.mediaObject = mediaObject;
        weiboMessage.imageObject = imageObject;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        iWeiboShareAPI.sendRequest((Activity) mContext, request);
    }

    public static void startWeixinShare(Context mContext, String title, String url, Bitmap bitmap, String desc, int shareType) {
        WXWebpageObject webPage = new WXWebpageObject();
        WXMediaMessage wxMediaMessage = new WXMediaMessage(webPage);
        WXImageObject wxImageObject = new WXImageObject(bitmap);
        wxMediaMessage.title = title;// 标题
        webPage.webpageUrl = url;//网址
        wxMediaMessage.description = desc;//描述
        wxMediaMessage.mediaObject = wxImageObject;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        wxMediaMessage.setThumbImage(bitmap);
        bitmap.recycle();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        if (shareType == 0) {
            req.scene = SendMessageToWX.Req.WXSceneSession;  //微信好友
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;  //朋友圈
        }
        mIWXApi.sendReq(req);
    }
}
