package com.anuode.build_andriod.modules.index;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.anuode.build_andriod.R;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.util.ActivityManager;

import java.util.ArrayList;

public class MainActivity extends ActivityBase {

    public static class TabPageInfo {
        private String title;
        private Class<?> pageClass;
        private String tag;
        private int icon;

        public TabPageInfo(String title, Class<?> pageClass, String tag, int icon) {
            this.title = title;
            this.pageClass = pageClass;
            this.tag = tag;
            this.icon = icon;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class<?> getPageClass() {
            return pageClass;
        }

        public void setPageClass(Class<?> pageClass) {
            this.pageClass = pageClass;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    private static final ArrayList<TabPageInfo> mTabPageInfo = new ArrayList<>();

    public static void addTabPage(TabPageInfo pageInfo) {
        mTabPageInfo.add(pageInfo);
    }

    public static FragmentTabHost mainTabHost;

    public static FragmentTabHost getMainTabHost() {
        return mainTabHost;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app__boot__main_activity);
//        mainTabHost = (FragmentTabHost) findViewById(R.id.main_tab_host);
//        mainTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);
//        for (TabPageInfo page : mTabPageInfo) {
//            View view = getLayoutInflater().inflate(R.layout.app__boot__main_activity_item, null);
//            ImageView imageView = (ImageView) view.findViewById(R.id.icon_index_image_view);
//            imageView.setImageResource(page.getIcon());
//            TextView textView = (TextView) view.findViewById(R.id.icon_index_txt);
//            if (page.getTag().equals("Index")) {
//                textView.setTextColor(Color.parseColor("#ca0915"));
//            }
//            textView.setText(page.getTitle());
//            mainTabHost.addTab(mainTabHost.newTabSpec(page.getTag()).setIndicator(view), page.getPageClass(), null);
//        }

//        mainTabHost.setOnTabChangedListener(tabId -> {
//            mainTabHost.setCurrentTabByTag(tabId);
//            updateTab(mainTabHost);
//        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                // Permission Granted
//                Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_LONG).show();
            } else {
                // Permission Denied
                Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_LONG).show();
            }
        }
    }


//    private void updateTab(FragmentTabHost mainTabHost) {
//        TabWidget tabWidget = mainTabHost.getTabWidget();
//        for (int i = 0; i < tabWidget.getChildCount(); i++) {
//            View view = tabWidget.getChildAt(i);
//            TextView textView = ((TextView) view.findViewById(R.id.icon_index_txt));
//            if (mainTabHost.getCurrentTab() == i) {   //选中
//                textView.setTextColor(Color.parseColor("#ca0915"));
//            } else {
//                textView.setTextColor(Color.parseColor("#696463"));
//            }
//        }
//    }

    private long exitTime = 0;
    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.getInstance().exit();
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getStringExtra("My")!=null && getIntent().getStringExtra("My").equals("My")) {
            getIntent().putExtra("My","");
            mainTabHost.setCurrentTabByTag("My");
        }
    }
}
