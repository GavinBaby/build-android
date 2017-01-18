package com.anuode.build_andriod.modules.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.Build;
import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.index.MainActivity;
import com.anuode.build_andriod.modules.util.RegexUtil;
import com.anuode.build_andriod.thrift.Account;
import com.anuode.common.Preferences;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.anuode.common.util.ActivityManager;

/**
 * 登录界面
 */
public class LoginActivity extends ActivityBase {
    private TextView mHeadline;//头标题
    private TextView mLoginForgetPWD;//忘记密码
    private TextView mError;//错误提示信息

    private EditText mLoginUserName;//手机号码
    private EditText mLoginPWD;//密码

    private Button mLoginBTN;//登录
    private Button mLoginRegister;//注册
    private RelativeLayout mLoginBack; //返回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__login_activity);
        initViews();
    }

    private void initViews() {
        mHeadline = (TextView) findViewById(R.id.login);
        mHeadline.setText(getResources().getString(R.string.login__login));
        mLoginForgetPWD = (TextView) findViewById(R.id.login__forget_pwd);
        mError = (TextView) findViewById(R.id.login__pwd_error);
        mLoginUserName = (EditText) findViewById(R.id.login__user_name);
        mLoginPWD = (EditText) findViewById(R.id.login__pwd);

        mLoginBTN = (Button) findViewById(R.id.login__login_btn);
        mLoginRegister = (Button) findViewById(R.id.login__register);
        mLoginBack = (RelativeLayout) findViewById(R.id.common__back);

           /* 设置监听*/
        mLoginForgetPWD.setOnClickListener(this);
        mLoginBTN.setOnClickListener(this);
        mLoginRegister.setOnClickListener(this);
        mLoginBack.setOnClickListener(this);
    }

    public void authSvc() {
//        if (!(checkName() && checkpsw())) {
//            return;
//        }
        Account account = new Account();
//        account.setMobile(mLoginUserName.getText().toString());
//        account.setPassword(mLoginPWD.getText().toString());
        account.setMobile("15657820119");
        account.setPassword("123123");
        account.setType("2");
        try {
            Build.getClient().login(account, new TAsyncMethodResult<Account>() {
                @Override
                public void onResult(Exception e, Account result) {
                    if (result == null) {
                        Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (result.getBack().getCode() ==  1){
                        Preferences.setLoginUsername(mLoginUserName.getText().toString());
                        Preferences.setLoginPassword(mLoginPWD.getText().toString());
                        ActivityManager.getInstance().setLogoutFlag(false);
                        Intent intent = new Intent(LoginActivity.this.getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
                        Build.cache.put("userinfo", result);
                        Toast.makeText(LoginActivity.this, result.getBack().getText(), Toast.LENGTH_SHORT).show();
//                        finish();
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }else{
                        Toast.makeText(LoginActivity.this, result.getBack().getText(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            Log.w("login", ">>>>>>>>>>>>>>>>>>登陆异常！");
        }
    }

    //判断手机
    private boolean checkName() {
        String text = mLoginUserName.getText().toString();
        if (mLoginUserName.length() == 0) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.regist__not_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mError.setVisibility(View.GONE);
        }
        if (!RegexUtil.isMobileNO(text)) {
            Toast.makeText(LoginActivity.this,"请输入正确的手机号！", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mError.setVisibility(View.GONE);
        }
        return true;
    }

    //判断密码
    private boolean checkpsw() {
        String text = mLoginPWD.getText().toString();
        if (text.length() == 0) {
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.regist__not_psw), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mError.setVisibility(View.GONE);
        }
        if (text.length() < 6) {
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.register__pwd_succeed), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mError.setVisibility(View.GONE);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common__back:
                finish();
                break;
            case R.id.login__login_btn:
               authSvc();
                break;
            case R.id.login__register:
                Intent intent2 = new Intent(LoginActivity.this.getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.login__forget_pwd:
                Intent intent3 = new Intent(LoginActivity.this.getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(ActivityManager.getInstance().getmActivityContainer().size()==0){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
