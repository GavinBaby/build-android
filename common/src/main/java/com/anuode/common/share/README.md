第三方分享
  分享所需的权限

      <uses-permission android:name="android.permission.INTERNET" />
      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
      <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
      <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
      <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
      <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
      <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

1.Facebook分享
  （1）.Facebook的初始化

      public static void initFacebookManager(Context mcontext){
            FacebookSdk.sdkInitialize(mcontext);
        }

   （2）向Facebook传递的数据及响应事件的回调

         public static void startFacebookShare(Activity mContext,String title,String desc,String url){
                CallbackManager callbackManager = CallbackManager.Factory.create();
                ShareDialog shareDialog = new ShareDialog( mContext);
                shareDialog.registerCallback(callbackManager, (FacebookCallback<Sharer.Result>) mContext);
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(title)// 标题
                            .setContentDescription(desc)//网址
                            .setContentUrl(Uri.parse(url))
                            .setImageUrl(Uri.parse(url))
                            .build();
                    try{
                        shareDialog.show(linkContent);
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(mcontext, "分享成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(mcontext, "分享取消", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(mcontext, "分享失败", Toast.LENGTH_LONG).show();
            }
   （3）.在AndroidManifest.xml中需要注册FacebookActivity

            <activity android:name="com.facebook.FacebookActivity"></activity>

 2. QQ分享
    （1）.QQ的初始化

     public static void initQQManager(String mAppId,Context context){
            if(mTencent==null) {
                mTencent = Tencent.createInstance(mAppId, context);
            }
        }

    （2）.向QQ传递的数据及响应事件的回调

     public static void startQQShare(Activity context,String title,String desc,String url,String imgurl){
            final Bundle params = new Bundle();
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title);//标题
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);//网址
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY,desc);//描述
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgurl);//图片地址
            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, title);//项目名称
            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 0x00);
            mTencent.shareToQQ( context, params, qqShareListener);
        }

          @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (null != mTencent && requestCode == 10103 && resultCode == -1) {
                    mTencent.onActivityResultData(requestCode, resultCode, data, qqShareListener);
                }
            }

       static  IUiListener qqShareListener = new IUiListener() {
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

    （3）.在AndroidManifest.xml中需要注册Activity及权限

          <activity
                android:name="com.tencent.tauth.AuthActivity"
                android:launchMode="singleTask"
                android:noHistory="true">
                <intent-filter>
                    <action android:name="android.intent.action.VIEW" />

                    <category android:name="android.intent.category.DEFAULT" />
                    <category android:name="android.intent.category.BROWSABLE" />

                    <data android:scheme="tencent1104923754" />
                </intent-filter>
            </activity>
            <activity
                android:name="com.tencent.connect.common.AssistActivity"
                android:configChanges="orientation|keyboardHidden"
                android:screenOrientation="portrait"
                android:theme="@android:style/Theme.Translucent.NoTitleBar" />
            <activity
                android:name="com.tencent.open.yyb.AppbarActivity"
                android:configChanges="orientation|keyboardHidden"
                android:screenOrientation="portrait"
                android:theme="@android:style/Theme.Translucent.NoTitleBar" />
            <activity android:name="com.tencent.connect.avatar.ImageActivity" />

            <service android:name="com.anuode.common.app.update.DownLoadService" />

 3.微博分享
    （1）.微博的初始化

    public static void initWeiBoManager(Context mContext,String mAppId){
        if(iWeiboShareAPI==null) {
            iWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, mAppId);
            iWeiboShareAPI.registerApp();
        }
    }

    （2）.向微博传递的数据及响应事件的回调

      public static void startweiboShare(Context mContext,String title,String url,int iconRes,String desc) {
            TextObject textObject = new TextObject();
            textObject.text = title;//项目名

            ImageObject imageObject = new ImageObject();
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), iconRes);
            imageObject.setImageObject(bitmap);

            WebpageObject mediaObject = new WebpageObject();
            mediaObject.identify = Utility.generateGUID();
            mediaObject.title =title;//标题
            mediaObject.description = desc;// 描述
            Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(),iconRes);
            // 设置 Bitmap 类型的图片到视频对象里  设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
            mediaObject.setThumbImage(bitmap1);
            mediaObject.actionUrl = url;//网址
            mediaObject.defaultText = "Webpage 默认文案";

            WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
            weiboMessage.textObject = textObject;
            weiboMessage.mediaObject = imageObject;
            weiboMessage.mediaObject = mediaObject;

            // 2. 初始化从第三方到微博的消息请求
            SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
            // 用transaction唯一标识一个请求
            request.transaction = String.valueOf(System.currentTimeMillis());
            request.multiMessage = weiboMessage;
            iWeiboShareAPI.sendRequest((Activity) mContext, request);
        }

          @Override
            protected void onNewIntent(Intent intent) {
                super.onNewIntent(intent);
                // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
                // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
                // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
                WeiboManager.getiWeiboShareAPI().handleWeiboResponse(intent, this);
            }
              WeiboManager.getiWeiboShareAPI().handleWeiboResponse(getIntent(), this);


               @Override
                  public void onResponse(BaseResponse baseResponse) {
                      switch (baseResponse.errCode) {
                          case WBConstants.ErrorCode.ERR_OK:
                              Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
                              break;
                          case WBConstants.ErrorCode.ERR_CANCEL:
                              Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
                              break;
                          case WBConstants.ErrorCode.ERR_FAIL:
                              Toast.makeText(this,
                                      "失敗" + "Error Message: " + baseResponse.errMsg,
                                      Toast.LENGTH_LONG).show();
                              break;
                      }

                  }

    （3）.在AndroidManifest.xml中需要注册Activity及权限

         <activity
            android:name=".samples.share.ShareActivity"
            android:label="@string/title_activity_share"
            android:theme="@style/AppTheme.NoActionBar"
            android:configChanges="keyboardHidden|orientation"
            android:launchMode="singleTask"
            android:screenOrientation="portrait">

            <intent-filter>
                <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

 3.微信分享
    （1）.微信的初始化

      public static void initWeixinManager(Context mContext,String mAppId){
            mIWXApi = WXAPIFactory.createWXAPI(mContext,mAppId);
            mIWXApi.registerApp(mAppId);
        }

    （2）.向微信传递的数据及响应事件的回调

      public static void startWeixinShare(Context mContext,String title,String url,int iconRes,String desc,int shareType){
            WXWebpageObject webPage = new WXWebpageObject();
            WXMediaMessage wxMediaMessage = new WXMediaMessage(webPage);
            wxMediaMessage.title = title;// 标题
            webPage.webpageUrl = url;//网址
            wxMediaMessage.description = desc;//描述
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), iconRes);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            wxMediaMessage.thumbData = byteArray;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wxMediaMessage;
            if (shareType == 0) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            }
            mIWXApi.sendReq(req);
        }

    （3）.在AndroidManifest.xml中需要注册Activity及权限

        <activity
            android:name=".wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />