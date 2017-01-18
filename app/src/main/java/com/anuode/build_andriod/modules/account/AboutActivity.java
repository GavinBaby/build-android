package com.anuode.build_andriod.modules.account;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.util.DialogUtil;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.app.update.VersionManager;
import com.anuode.common.util.PackageUtil;

public class AboutActivity extends ActivityBase implements View.OnClickListener{
    private RelativeLayout Mcommon_back;//返回按钮
    private TextView Mcommon_top_title;// 头部标题
    private ImageView Mcommon_about_img;
    private RelativeLayout Mversion_updates_layout;//版本更新条目
    private TextView mVersion;//版本更新
    private TextView mVersion_;//版本更新
    private RelativeLayout Mservice_tel_layout;//客服电话条目
    private TextView Mservice_tel;//客服电话
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my__setting_about);
        initViews();
    }


    private void initViews() {
        Mcommon_back=(RelativeLayout)findViewById(R.id.common__back);
        Mcommon_back.setOnClickListener(this);
        Mcommon_top_title=(TextView)findViewById(R.id.login);
        Mcommon_top_title.setText("关于");
        Mcommon_about_img=(ImageView)findViewById(R.id.common_about_img);
        Mversion_updates_layout=(RelativeLayout)findViewById(R.id.version_updates_layout);
        Mversion_updates_layout.setOnClickListener(this);
        mVersion=(TextView)findViewById(R.id.common__version);
        mVersion.setText("版本号："+ PackageUtil.getAppVersionName(getApplicationContext()));
        mVersion_ = (TextView)findViewById(R.id.common__version_tv);
        mVersion_.setText("v."+ PackageUtil.getAppVersionName(getApplicationContext()));
        Mservice_tel_layout=(RelativeLayout)findViewById(R.id.service_tel_layout);
        Mservice_tel_layout.setOnClickListener(this);
        Mservice_tel=(TextView)findViewById(R.id.service_tel);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.common__back) {
            finish();
        }
        if (i == R.id.version_updates_layout) {
            VersionManager.checkVersionManager(AboutActivity.this,1);
        }
        if (i == R.id.service_tel_layout) {
            Dialog mDialgo = DialogUtil.createLoadingDialog(this,
                    "           客服热线\n\n" +
                    "李春梅    13691288595\n" +
                    "王俊秋    18810336007\n" +
                    "马明亮    18810298976\n" +
                    "吴志远    13521776733\n" +
                    "买晓琪    18810283809\n" +
                    "于小龙    15011519962\n\n\n");
            mDialgo.setCanceledOnTouchOutside(true);
            ProgressBar bar = (ProgressBar) mDialgo.findViewById(R.id.home__pager_progressbar);
            bar.setVisibility(View.INVISIBLE);
            mDialgo.show();
        }
    }
}
