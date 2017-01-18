package com.anuode.build_andriod.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.Build;
import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.util.RegexUtil;
import com.anuode.build_andriod.modules.util.TimeCountUtil;
import com.anuode.build_andriod.thrift.Account;
import com.anuode.build_andriod.thrift.Back;
import com.anuode.common.Preferences;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.anuode.common.util.ActivityManager;

/**
 * 注册界面
 */

public class RegisterActivity extends ActivityBase implements View.OnClickListener {
    private TextView mHeadline;//头标题
    private EditText mNumber;//手机号码
    private EditText mAuthCode;//验证码
    private EditText mPassword;//密码
    private EditText mPassword_;//确认密码
//    private EditText mSpreadCode;//推广码
//    private CheckBox mCheckBox;//同意协议的对勾
//    private View mConsentLayout;//同意协议点击范围
    private Button mRegister_BTN;//注册并登录按钮
    private RelativeLayout mBack;//返回按钮

    private TextView mCodeCountdown;//验证码倒计时

    private TextView mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__regist_activity);
        setViews();

    }

    private void setViews() {
        mHeadline = (TextView) findViewById(R.id.login);
        mHeadline.setText(getResources().getString(R.string.register__registers));

        mNumber = (EditText) findViewById(R.id.register__num_edit);
        mNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        DigitsKeyListener numericOnlyListener = new DigitsKeyListener(false, true);
        mNumber.setKeyListener(numericOnlyListener);

        mAuthCode = (EditText) findViewById(R.id.register__Verify_edit);
        mAuthCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        mAuthCode.setKeyListener(numericOnlyListener);

        mPassword = (EditText) findViewById(R.id.register__psw_one);
        mPassword_ = (EditText) findViewById(R.id.register__psw_two);
//        mSpreadCode = (EditText) findViewById(R.id.company_name_edit);
//        mCheckBox = (CheckBox) findViewById(R.id.regist__unagree_img);
//        mConsentLayout = findViewById(R.id.consent);
        mRegister_BTN = (Button) findViewById(R.id.register_btn);
        mCodeCountdown = (TextView) findViewById(R.id.register__get_verify);
        mBack = ((RelativeLayout) findViewById(R.id.common__back));
//        mLink = (TextView) findViewById(R.id.regist__deal_link);

//        mConsentLayout.setOnClickListener(this);
        mRegister_BTN.setOnClickListener(this);
//        mCheckBox.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mCodeCountdown.setOnClickListener(this);
//        mLink.setOnClickListener(this);
    }

    //判断手机
    private boolean checkName() {
        String text = mNumber.getText().toString();
        if (mNumber.length() == 0) {
            Toast.makeText(this, R.string.regist__not_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!RegexUtil.isMobileNO(text)) {
            Toast.makeText(this, R.string.regist__phone_format_err, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //判断密码
    private boolean checkPwd() {
        if (mPassword.length() == 0) {
            Toast.makeText(this, R.string.regist__not_psw, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mPassword.length() < 6) {
            Toast.makeText(this, R.string.register__pwd_succeed, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mPassword_.length() == 0) {
            Toast.makeText(this, R.string.register__pwd, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mPassword.getText().toString().equals(mPassword_.getText().toString())) {
            Toast.makeText(this, R.string.register__disagree_psw, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //判断验证码
    private boolean checkCode() {
        String code = mAuthCode.getText().toString();
        if (mAuthCode.length() == 0) {
            Toast.makeText(this, R.string.register__Verify_empty, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mAuthCode.length() < 6) {
            Toast.makeText(this, R.string.regist__phone_format_ecc, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void authSvc() {
        if (!(checkName() && checkCode() && checkPwd())) {
            return;
        }
//        if (!mCheckBox.isChecked()) {
//            Toast.makeText(RegisterActivity.this, R.string.register__disagree_deal, Toast.LENGTH_SHORT).show();
//            return;
//        }
        try {
            checkVerificationCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkVerificationCode() throws Exception {
//        Txjc.getClient().checkVerificationCode(mNumber.getText().toString(), mAuthCode.getText().toString(), new TAsyncMethodResult<Back>() {
//            @Override
//            public void onResult(Exception e, Back back) {
//                if(back == null){
//                    return;
//                }
//                if (back.getCode() == -1) {
//                    Toast.makeText(RegisterActivity.this, back.getText(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//                try {
//                    createAgent();
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
    }

    private void createAgent() throws Exception {
        Account account = new Account();
        account.setMobile(mNumber.getText().toString());
        account.setPassword(mPassword.getText().toString());
        Build.getClient().create(account, new TAsyncMethodResult<Back>() {
            @Override
            public void onResult(Exception e, Back result) {
                if (result == null) {
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                if (result.getCode() == 1) {
                    Toast.makeText(RegisterActivity.this, result.getText(), Toast.LENGTH_LONG).show();
                    authSvc(mNumber.getText().toString(), mPassword.getText().toString());
                } else {
                    Toast.makeText(RegisterActivity.this, result.getText(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void authSvc(String mobile,String password) {
        Account account = new Account();
        account.setMobile(mobile);
        account.setPassword(password);
        try {
            Build.getClient().login(account, new TAsyncMethodResult<Account>() {
                @Override
                public void onResult(Exception e, Account result) {
                    if (result == null) {
                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        return;
                    }
//                    Preferences.setLoginUsername(mobile);
//                    Preferences.setLoginPassword(password);
                    ActivityManager.getInstance().setLogoutFlag(false);
//                    Intent intent = new Intent(RegisterActivity.this.getApplicationContext(), GuideAgentActivity.class);
//                    startActivity(intent);
                    Build.cache.put("userinfo", result);
                    finish();
                }
            });
        } catch (Exception e) {
            Log.w("login", ">>>>>>>>>>>>>>>>>>登陆异常！");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common__back:
                Intent intent1 = new Intent(RegisterActivity.this.getApplicationContext(), LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.register_btn:
                try {
                    authSvc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.register__get_verify:
                if (!checkName()) {
                    return;
                }
                try {
                    isRegistered();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
//            case R.id.regist__deal_link:
//                startActivity(new Intent(this.getApplicationContext(),LinkActivity.class));
//                break;
        }
    }

    private void isRegistered() throws Exception {
//        Txjc.getClient().isRegistered(mNumber.getText().toString(), new TAsyncMethodResult<Back>() {
//            @Override
//            public void onResult(Exception e, Back back) {
//                if(back==null){
//                    return;
//                }
//                if (back.getCode() == -1) {
//                    Toast.makeText(RegisterActivity.this, "手机号已注册", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                try {
//                    TimeCountUtil time = new TimeCountUtil(RegisterActivity.this, 60000, 1000, mCodeCountdown);
//                    time.start();
//                    TimeCountUtil.SendVerifyCode(mNumber.getText().toString());
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
    }
}
