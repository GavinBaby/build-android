package com.anuode.build_andriod.modules.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.anuode.common.net.thrift.TAsyncMethodResult;

public class TimeCountUtil extends CountDownTimer {
    private Activity mActivity;
    private TextView btn;//按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtil(Activity mActivity, long millisInFuture, long countDownInterval, TextView btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn = btn;
    }

    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + "秒后重新获取");//设置倒计时时间
        //设置按钮为灰色，这时是不能点击的
        btn.setTextColor(Color.parseColor("#28AF63"));
        //btn.setBackground(null);
        Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
        btn.setText(span);
    }

    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        btn.setText("获取验证码");
        btn.setTextColor(Color.parseColor("#c9a063"));
        btn.setClickable(true);//重新获得点击
        //btn.setBackground(null);//还原背景色
    }

    public static void SendVerifyCode(String mobile) throws Exception {
//        Txjc.getClient().getMobilesSendVerifyCode(mobile, new TAsyncMethodResult<Back>() {
//            @Override
//            public void onResult(Exception e, Back back) {
//                super.onResult(e, back);
//            }
//        });
    }
}
