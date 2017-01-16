package com.anuode.build_andriod.modules.index;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.login.LoginActivity;
import com.anuode.common.Preferences;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncHttpClientManager;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends ActivityBase {

    private SimpleDraweeView mSimpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        mSimpleDraweeView = ((SimpleDraweeView) findViewById(R.id.app__boot_welcome));
        initView();
    }

    public void initView() {
        TimerTask task = new TimerTask(){
            public void run(){
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }
//    private void initViews() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Page page = new Page();
//                    page.setSortName("");
//                    txjcSvc.AsyncClient client = TAsyncHttpClientManager.getClient(txjcSvc.AsyncClient.class);
//                    assert client != null;
//                    client.getContentList("3", page, new TAsyncMethodResult<ContentList>() {
//                        @Override
//                        public void onResult(Exception e, ContentList result) {
//                            if (result == null) {
//                                bootNext();
//                                return;
//                            }
//                            Message message = Message.obtain();
//                            message.obj = result.getData().get(0).getImage_url();
//                            mMyHnadler.sendMessage(message);
//                        }
//                    });
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

//    private Handler mMyHnadler = new MyHandler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if(msg!=null&&msg.obj!=null){
//                mSimpleDraweeView.setImageURI(Uri.parse(FileUtil.enCodeUrl(msg.obj.toString())));
//            }
//            bootNext();
//        }
//    };


//    private void bootNext() {
//        new Thread() {
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    doFinish();
//                } catch (InterruptedException e) {
//                    finish();
//                }
//            }
//        }.start();
//    }
//
//    private void doFinish() {
//        if (Preferences.isFirstBoot()) {
//            startActivity(new Intent(WelcomeActivity.this.getApplicationContext(), GuideActivity.class));
//        } else {
//            startActivity(new Intent(WelcomeActivity.this.getApplicationContext(), MainActivity.class));
//        }
//        finishWithoutAnimation();
//    }

    @Override
    public void onClick(View v) {

    }
}
