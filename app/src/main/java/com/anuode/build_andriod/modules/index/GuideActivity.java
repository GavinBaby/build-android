package com.anuode.build_andriod.modules.index;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.anuode.build_andriod.R;
import com.anuode.build_andriod.modules.util.FileUtil;
import com.anuode.common.Preferences;
import com.anuode.common.app.ActivityBase;
import com.anuode.common.net.thrift.TAsyncHttpClientManager;
import com.anuode.common.net.thrift.TAsyncMethodResult;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends ActivityBase {
    private List<View> mList;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private int currentItem = 0; // 当前图片的索引号
    private GestureDetector mGestureDetector; // 用户滑动
    /**
     * 记录当前分页ID
     */
    private int flaggingWidth;// 互动翻页所需滚动的长度是当前屏幕宽度的1/3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app__boot__guide_activity);
        mGestureDetector = new GestureDetector(new GuideViewTouch());

        // 获取分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        flaggingWidth = dm.widthPixels / 3;

        Preferences.setFirstBoot(false);
        initViewsData();
    }

    private void initViewsData() {
//        mViewPager = ((ViewPager) findViewById(R.id.app_boot_viewPager));
//        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_welcome);
        mList = new ArrayList<>();
        try {
//            Page page = new Page();
//            page.setSortName("");
//            txjcSvc.AsyncClient client = TAsyncHttpClientManager.getClient(txjcSvc.AsyncClient.class);
//            assert client != null;
//            client.getContentList("4", page, new TAsyncMethodResult<ContentList>() {
//                @Override
//                public void onResult(Exception e, ContentList result) {
//                    if (result == null) {
//                        return;
//                    }
//                    for (int i = 0; i < result.getData().size(); i++) {
//                        SimpleDraweeView mAdImage = ((SimpleDraweeView) getLayoutInflater().inflate(R.layout.login__guide_agent_item, null, false));
//                        mAdImage.setImageURI(Uri.parse(FileUtil.enCodeUrl(result.getData().get(i).getImage_url())));
//                        mList.add(mAdImage);
//                    }
//                    initViews();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(mList.get(position));
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }
        });

        //viewpager设置改变页面的监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                currentItem = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            event.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(event);
    }

    private class GuideViewTouch extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (currentItem == mList.size()-1) {
                if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())
                        && (e1.getX() - e2.getX() <= (-flaggingWidth) || e1
                        .getX() - e2.getX() >= flaggingWidth)) {
                    if (e1.getX() - e2.getX() >= flaggingWidth) {
                        startActivity(new Intent(GuideActivity.this.getApplicationContext(), MainActivity.class));
                        finishWithoutAnimation();
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /*private void initRadioButton(int position) {
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            if (i == position) {
                ((RadioButton) mRadioGroup.getChildAt(i)).setChecked(true);
            } else {
                ((RadioButton) mRadioGroup.getChildAt(i)).setChecked(false);
            }
        }
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(GuideActivity.this.getApplicationContext(), MainActivity.class));
            finishWithoutAnimation();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
}
