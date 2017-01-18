package com.anuode.build_andriod.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.util.RegexUtil;
import com.anuode.build_andriod.modules.util.TimeCountUtil;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;


/**
 * 忘记密码界面
 */
public class ForgetPasswordActivity extends ActivityBase implements View.OnClickListener {
    private TextView mHeadline;//头标题
    private Button mForgetPWD;//重设密码按钮
    private EditText mNumber;//手机号码
    private EditText mAuthCode;//验证码

    private RelativeLayout mBack;//返回按钮
    private TextView mAuthCodeBUT;//验证码按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__forget_pwd_activity_first);
        setViews();

    }

    private void setViews() {
        mHeadline = (TextView) findViewById(R.id.login);
        mHeadline.setText(getResources().getString(R.string.login__forget));
        mForgetPWD = (Button) findViewById(R.id.forget_pwd_btn_one);
        mNumber = (EditText) findViewById(R.id.register__num_edit);
        mNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});//限制显示的数量
        DigitsKeyListener numericOnlyListener = new DigitsKeyListener(false, true);
        mNumber.setKeyListener(numericOnlyListener);

        mAuthCode = (EditText) findViewById(R.id.register__Verify_edit);
        mAuthCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        mAuthCode.setKeyListener(numericOnlyListener);

        mBack = (RelativeLayout) findViewById(R.id.common__back);
        mAuthCodeBUT = (TextView) findViewById(R.id.register__get_verify);

       /* 设置监听*/
        mForgetPWD.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mAuthCodeBUT.setOnClickListener(this);
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

    //判断验证码
    protected boolean checkCode() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common__back:
                Intent intent1 = new Intent(ForgetPasswordActivity.this.getApplicationContext(), LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.forget_pwd_btn_one:  //下一步
                if (!checkName()) {
                    return;
                }
                if (!checkCode()) {
                    return;
                }
                try {
                    checkVerificationCode();
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
        }
    }

    private void isRegistered() throws Exception {
//        Txjc.getClient().isRegistered(mNumber.getText().toString(), new TAsyncMethodResult<Back>() {
//            @Override
//            public void onResult(Exception e, Back back) {
//                if (back == null) {
//                    return;
//                }
//                if (back.getCode() == -1) {   //手机号已注获得验证按ma
//                    try {
//                        TimeCountUtil.SendVerifyCode(mNumber.getText().toString());
//                    } catch (Exception e1) {
//                        e.printStackTrace();
//                    }
//                    TimeCountUtil time = new TimeCountUtil(ForgetPasswordActivity.this, 60000, 1000, mAuthCodeBUT);
//                    time.start();
//                }else{
//                    Toast.makeText(ForgetPasswordActivity.this, "手机号未注册，请先注册！", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(ForgetPasswordActivity.this.getApplicationContext(), RegisterActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
    }

    private void checkVerificationCode() throws Exception {
//        Txjc.getClient().checkVerificationCode(mNumber.getText().toString(), mAuthCode.getText().toString(), new TAsyncMethodResult<Back>() {
//            @Override
//            public void onResult(Exception e, Back back) {
//                if(back == null){
//                    Toast.makeText(ForgetPasswordActivity.this, "网络不通畅！", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (back.getCode() == -1) {
//                    Toast.makeText(ForgetPasswordActivity.this, back.getText(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Intent intent = new Intent(ForgetPasswordActivity.this.getApplicationContext(), PasswordResetActivity.class);
//                intent.putExtra("mobile",mNumber.getText().toString());
//                startActivity(intent);
//            }
//        });
    }

}
