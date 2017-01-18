package com.anuode.build_andriod.modules.account;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.Build;
import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.common.MyHandler;
import com.anuode.build_andriod.modules.login.LoginActivity;
import com.anuode.build_andriod.modules.util.DialogUtil;
import com.anuode.build_andriod.modules.util.FileUtil;
import com.anuode.build_andriod.modules.util.IntentCenter;
import com.anuode.common.Preferences;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.anuode.common.upload.UploadManager;
import com.anuode.common.util.ActivityManager;
import com.facebook.drawee.view.SimpleDraweeView;

import org.apache.thrift.TException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AccountActivity extends ActivityBase {
    private RelativeLayout mCommon_back;//返回
    private View mIconLayout;//头像布局
    private SimpleDraweeView mAgentIcon;
    private View mNameLayout;//姓名条目
    private TextView mUsername;//姓名
    private TextView mMobile;
    private View mResetPswLayout;//修改密码
    private TextView mPassword;

    private View mPhotograph;//拍照
    private View mPhoto_album;//从相册中选择照片
    private View mCancel;//取消
    private View mLogOut;//退出登录

    private TextView mTitle;//标题
    private Dialog dialog;
    private View v1;

    private Handler mHandler;
    private Dialog mDialgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account__account_activity);
        setViews();
    }

    private void setViews() {
        mIconLayout = findViewById(R.id.account__icon_layout);
        mAgentIcon = ((SimpleDraweeView) findViewById(R.id.account__agent_icon));
        mNameLayout = findViewById(R.id.account__name_layout);
        mUsername = (TextView) findViewById(R.id.account__user__name);
        mResetPswLayout = findViewById(R.id.account__change_password_layout);
        mPassword = (TextView) findViewById(R.id.account__change_password);
        mLogOut = findViewById(R.id.log_out);

        mCommon_back = ((RelativeLayout) findViewById(R.id.common__back));
        mCommon_back.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.login);
        mTitle.setText(getString(R.string.about_me));
        //设置监听
        mIconLayout.setOnClickListener(this);
        mNameLayout.setOnClickListener(this);
        mResetPswLayout.setOnClickListener(this);
        mLogOut.setOnClickListener(this);

        LayoutInflater inflater = LayoutInflater.from(AccountActivity.this);
        v1 = inflater.inflate(R.layout.common_alert_window_pop, null);
        TextView title  = (TextView) v1.findViewById(R.id.title);
        title.setText("修改头像");
        mPhotograph = v1.findViewById(R.id.photograph);//拍照
        mPhotograph.setOnClickListener(this);
        mPhoto_album = v1.findViewById(R.id.photo_album);
        mCancel = v1.findViewById(R.id.cancel);
        dialog = new Dialog(AccountActivity.this, R.style.showCityDialog);
        mDialgo = DialogUtil.createLoadingDialog(this,"上传中......");
        mDialgo.setCanceledOnTouchOutside(false);
        mHandler = new MyHandler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null){
//                    mAgent.setImage_url(msg.obj.toString());
//                    ((Agent) Txjc.cache.get("agentMessage")).setImage_url(msg.obj.toString());
                    updateAgentBasicInfo();
                }
            }
        };
        initAgentInfo();
    }

    private void updateAgentBasicInfo() {
//        BasicInfo basicInfo = new BasicInfo();
//        basicInfo.setId(((Agent) Txjc.cache.get("agentMessage")).getSeq_no());
//        if (mAgent.getImage_url().equals("")) {
//            AccountActivity.this.mDialgo.dismiss();
//            Toast.makeText(getApplicationContext(), "上传失败，请重新上传！", Toast.LENGTH_LONG).show();
//            return;
//        }
//        basicInfo.setImage_url(mAgent.getImage_url());
//        try {
//            Txjc.getClient().updateAgentBasicInfo(basicInfo, new TAsyncMethodResult<Back>() {
//                @Override
//                public void onResult(Exception e, Back back) {
//                    if(back == null){
//                        Toast.makeText(getApplicationContext(), "上传失败，请重新上传！", Toast.LENGTH_LONG).show();
//                        AccountActivity.this.mDialgo.dismiss();
//                        return;
//                    }
//                    if (back.getCode() == 1) {
//                        AccountActivity.this.mDialgo.dismiss();
//                    }else if(back.getCode()==500){
//                        Toast.makeText(getApplicationContext(), back.getText(), Toast.LENGTH_LONG).show();
//                        AccountActivity.this.mDialgo.dismiss();
//                        return;
//                    }
//                }
//            });
//        } catch (TException e) {
//            e.printStackTrace();
//        }
    }

    private void initAgentInfo() {
//        mAgent = ((Agent) Txjc.cache.get("agentMessage"));
//        if (mAgent.getImage_url() != null) {
//            mAgentIcon.setImageURI(Uri.parse(Txjc.PICHOST+"upload/"+mAgent.getImage_url()));
//        }
//        if (mAgent.getMobile() != null) {
//            mMobile.setText(mAgent.getMobile().substring(0, 3) + "****" + mAgent.getMobile().substring(7, mAgent.getMobile().length()));
//        }
        initAgentStatus();
    }

    private void initAgentStatus() {
        try {
//            Txjc.getClient().getStatus("907", new TAsyncMethodResult<GetDictionaryResult>() {
//                @Override
//                public void onResult(Exception e, GetDictionaryResult result) {
//                    if(result==null){
//                        return;
//                    }
//                    mAgentStatusList = new ArrayList();
//                    mAgentStatusList.addAll(result.getGbl());
//                }
//            });
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common__back:
                finish();
                break;
            case R.id.account__icon_layout:
                dialog.setContentView(v1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                dialog.show();
                break;
            case R.id.account__name_layout:
                Intent intent = new Intent(AccountActivity.this.getApplicationContext(),  NickActivity.class);
//                intent.putExtra("nickName",mAgent.getAgent_name());
                startActivity(intent);
                break;
            case R.id.account__change_password_layout:
                startActivity(new Intent(AccountActivity.this.getApplicationContext(), ResetPasswordActivity.class));
                break;

            case R.id.photograph:
                dialog.dismiss();
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent2, 100);
                } else {
                    Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.photo_album:
                dialog.dismiss();
                Intent intent2 = IntentCenter.getAlbumIntent();
                startActivityForResult(intent2, 101);
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
            case R.id.log_out:
                showTips();
                break;
        }
    }
    private void showTips() {
        dialog.setContentView(R.layout.address_poppu_window);
        ((TextView) dialog.findViewById(R.id.common__warn_text)).setText("您确定退出此次登录？");
        ((TextView) dialog.findViewById(R.id.btn_cancel)).setText("点错了");
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Preferences.setLoginUsername(null);
                Preferences.setLoginPassword(null);
                Build.cache.clear();
                Intent intent = new Intent(AccountActivity.this.getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                ActivityManager.getInstance().setLogoutFlag(true);
                ActivityManager.getInstance().logout();
                finish();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 100: // 调用系统相机返回图片信息并对图片进行裁剪
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    //save to file
                    File p = new File(FileUtil.DIR_IMAGES);
                    if (!p.exists()) {
                        p.mkdirs();
                    }
                    String tempPath = FileUtil.DIR_IMAGES + "/" + System.currentTimeMillis() + ".jpg";
                    File file = new File(tempPath);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
                        mAgentIcon.setImageURI(Uri.parse("file://" + file.getAbsolutePath()));
                        UploadManager uploadManager = new UploadManager(file,Build.FILEHOST+"upload",mHandler);
                        new Thread(uploadManager).start();
                        bitmap.recycle();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 101:  // 调用系统相册返回图片信息
                if (data != null) {
                    Uri uri = data.getData();
                    String path = FileUtil.getPath(uri, AccountActivity.this.getApplicationContext());
                    mAgentIcon.setImageURI(Uri.parse("file://" + path));
                    mDialgo.show();
                    UploadManager uploadManager = new UploadManager(new File(path),Build.FILEHOST+"upload",mHandler);
                    new Thread(uploadManager).start();
                }
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        if (mAgent.getAgent_name() != null) {
//            mUsername.setText(mAgent.getAgent_name());
//        }
    }
}
