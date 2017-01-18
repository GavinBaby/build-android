package com.anuode.build_andriod.modules.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.R;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.anuode.common.util.ActivityManager;

import org.apache.thrift.TException;

public class ResetPasswordActivity extends ActivityBase {
    private RelativeLayout Mcommon_back;//返回
    private TextView mTitle;//标题
    private EditText mExistingPassword;//现有密码
    private EditText mNewPassword;//新密码
    private EditText mAgainNewPassword;//再次输入新密码
    private ImageView mReset, mReset1, mReset2;//重置按钮
    private Button Msubmit__change_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account__reset_password_activity);
        setViews();
    }

    private void setViews() {
        Mcommon_back = (RelativeLayout) findViewById(R.id.common__back);
        Mcommon_back.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.login);
        mTitle.setText(getString(R.string.amend_password));

        mExistingPassword = (EditText) findViewById(R.id.existing_password);
        mNewPassword = (EditText) findViewById(R.id.new_password);
        mAgainNewPassword = (EditText) findViewById(R.id.again_new_password);

        mReset = (ImageView) findViewById(R.id.reset);
        mReset1 = (ImageView) findViewById(R.id.reset1);
        mReset2 = (ImageView) findViewById(R.id.reset2);

        Msubmit__change_pwd = (Button) findViewById(R.id.submit__change_pwd);
        Msubmit__change_pwd.setOnClickListener(this);
        mReset.setOnClickListener(this);
        mReset1.setOnClickListener(this);
        mReset2.setOnClickListener(this);


        mExistingPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String existing = mExistingPassword.getText().toString();
                if (!existing.equals("")) {
                    mReset.setVisibility(View.VISIBLE);
                } else {
                    mReset.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String existing1 = mNewPassword.getText().toString();
                if (!existing1.equals("")) {
                    mReset1.setVisibility(View.VISIBLE);
                } else {
                    mReset1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAgainNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String existing2 = mAgainNewPassword.getText().toString();
                if (!existing2.equals("")) {
                    mReset2.setVisibility(View.VISIBLE);
                } else {
                    mReset2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.common__back ) {
            finish();
        } else if (i == R.id.reset) {
            mExistingPassword.setText("");
            mReset.setVisibility(View.GONE);
        } else if (i == R.id.reset1) {
            mNewPassword.setText("");
            mReset1.setVisibility(View.GONE);
        } else if (i == R.id.reset2) {
            mAgainNewPassword.setText("");
            mReset2.setVisibility(View.GONE);
        } else if (i == R.id.submit__change_pwd) {
            if(mExistingPassword.getText().length()<6&&mNewPassword.getText().length()<6&&mAgainNewPassword.getText().length()<6){
                Toast.makeText(this, R.string.register__pwd_succeed, Toast.LENGTH_SHORT).show();
                return;
            }
            String text = mNewPassword.getText().toString();
            String texts = mAgainNewPassword.getText().toString();
            if (!text.equals(texts)) {
                Toast.makeText(this, R.string.register__disagree_psw, Toast.LENGTH_SHORT).show();
                return;
            }
            updateAccountInfo();
        }
    }

    private void updateAccountInfo() {
//        Account account = new Account();
//        account.set_id(((Agent) Txjc.cache.get("agentMessage")).getSeq_no());
//        account.setMobile(((Agent) Txjc.cache.get("agentMessage")).getMobile());
//        account.setPassword(mNewPassword.getText().toString());
//        try {
//            Txjc.getClient().updateAccountInfo(account, mExistingPassword.getText().toString(),new TAsyncMethodResult<Back>() {
//                @Override
//                public void onResult(Exception e, Back back) {
//                    if(back == null){
//                        return;
//                    }
//                    if(back.getCode() == -1){
//                        Toast.makeText(getApplicationContext(), back.getText(), Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    if (back.getCode() == 1) {
//                        Txjc.cache.clear();
//                        Intent intent = new Intent(ResetPasswordActivity.this.getApplicationContext(), LoginActivity.class);
//                        startActivity(intent);
//                        ActivityManager.getInstance().setLogoutFlag(true);
//                        ActivityManager.getInstance().logout();
//                        finish();
//                        Toast.makeText(getApplicationContext(), "修改密码成功,请重新登陆！", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        } catch (TException e) {
//            e.printStackTrace();
//        }
    }
}
