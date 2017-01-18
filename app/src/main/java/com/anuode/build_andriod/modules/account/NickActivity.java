package com.anuode.build_andriod.modules.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.R;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;

import org.apache.thrift.TException;

public class NickActivity extends ActivityBase {
    private RelativeLayout Mcommon_back;//返回
    private TextView mTitle;//标题
    private TextView mUsername;//姓名
    private EditText mUsernameED;//修改名字输入框
    private ImageView mReset;//清空按鈕
    private TextView mConfirm;//提交按钮
    private TextView mTextView;
    private TextView mAgentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_nickname_activity);
        setViews();
    }

    private void setViews() {

        Mcommon_back = (RelativeLayout) findViewById(R.id.common__back );
        Mcommon_back.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.login);
        mUsername = (TextView) findViewById(R.id.username);
        mUsernameED = (EditText) findViewById(R.id.ed_user_name);
        mTextView = (TextView) findViewById(R.id.account__agent_apply_s);
        mAgentContact = (TextView) findViewById(R.id.account__contact);
            mTitle.setText("我的昵称");
            mConfirm = (TextView) findViewById(R.id.submit);
            mConfirm.setText("提交");
            mConfirm.setVisibility(View.VISIBLE);


            mReset = (ImageView) findViewById(R.id.reset);
            mConfirm.setOnClickListener(this);
            if(getIntent().getStringExtra("agentNickName")!=null){
                mUsernameED.setText(getIntent().getStringExtra("agentNickName"));
                mReset.setVisibility(View.VISIBLE);
            }else{
                mUsernameED.setHint("请输入代理商昵称");
            }
            mUsernameED.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && !s.equals("")) {
                        mReset.setVisibility(View.VISIBLE);
                    } else {
                        mReset.setVisibility(View.GONE);
                    }
                }
            });
            mReset.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common__back :
                finish();
                break;
            case R.id.reset:
                mUsernameED.setText("");
                mReset.setVisibility(View.GONE);
                break;
            case R.id.submit:
                if(mUsernameED.length()==0){
                    Toast.makeText(getApplicationContext(), "请输入昵称", Toast.LENGTH_LONG).show();
                    return;
                }
                updateAgentBasicInfo();
                break;
        }
    }

    private void updateAgentBasicInfo() {
//        BasicInfo basicInfo = new BasicInfo();
//        basicInfo.setId(((Agent) Txjc.cache.get("agentMessage")).getSeq_no());
//        basicInfo.setAgent_name(mUsernameED.getText().toString());
//        try {
//            Txjc.getClient().updateAgentBasicInfo(basicInfo, new TAsyncMethodResult<Back>() {
//                @Override
//                public void onResult(Exception e, Back back) {
//                    if(back == null){
//                        return;
//                    }
//                    if (back.getCode() == 1) {
//                        ((Agent) Txjc.cache.get("agentMessage")).setAgent_name(mUsernameED.getText().toString());
//                        finish();
//                        Toast.makeText(getApplicationContext(), "修改昵称成功！", Toast.LENGTH_LONG).show();
//                    }
//                    if (back.getCode() == -1) {
////                        ((Agent) Txjc.cache.get("agentMessage")).setAgent_name(mUsernameED.getText().toString());
//                        Toast.makeText(getApplicationContext(), "昵称不可重复！", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        } catch (TException e) {
//            e.printStackTrace();
//        }
    }
}
