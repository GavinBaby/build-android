package com.anuode.common.widget.slidelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.anuode.common.R;

public class DeleteListview extends ListView {
    //用户滑动的最小距离
    private int touchSlop;
    //是否相应滑动
    private boolean isSliding;
    //手指按下时的x坐标
    private int xDown;
    //手指按下时的y坐标
    private int yDown;
    //手指移动时的x坐标
    private int xMove;
    //手指移动时的y坐标
    private int yMove;

    private LayoutInflater minflater;
    private PopupWindow mPopupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;
    private Button mDelBtn;

    //为删除按钮提供一个回调接口
    private DelButtonClickListener mListener;
    //当前手指触摸的view
    private View mCurrentView;
    //当前手指触摸的位置
    private int mCurrentViewPos;
    public interface DelButtonClickListener {
         void clickHappend(int position);
    }
    /**
     * 必要的一些初始化
     * @param context
     * @param attrs
     */

    public DeleteListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        minflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledEdgeSlop();

        View view = minflater.inflate(R.layout.delete_listview_delete_btn,null);
        mDelBtn = (Button) view.findViewById(R.id.id_item_btn);
        mPopupWindow = new PopupWindow(view,android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);


        /**
         * 先调用晓measure，否则拿不到宽和高
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                /**
                 * 如果当前popwindow显示，则直接隐藏，然后屏蔽listview的touch事件的下传
                 */
                if (mPopupWindow.isShowing()) {
                    dismissPopWindow();
                    return false;
                }
                //获得当前手指按下时的item位置
                mCurrentViewPos = pointToPosition(xDown, yDown);
                //获得当前手指按下时的item
                View view = getChildAt(mCurrentViewPos-getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                /**
                 * 判断是否是从右到左的滑动
                 */
                if (xMove < xDown && Math.abs(dx) > touchSlop && Math.abs(dy) < touchSlop) {
                    isSliding = true;
                }
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        int action = ev.getAction();
        /**
         * 如果是从右到左的滑动才响应
         */
        if (isSliding) {
            switch (action) {
                case MotionEvent.ACTION_MOVE:
                    int []location = new int[2];
                    //获得当前item的位置x与y
                    mCurrentView.getLocationInWindow(location);
                    //设置popupwindow的动画
                    mPopupWindow.setAnimationStyle(R.style.details_dialog);
                    mPopupWindow.update();
                    mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT|Gravity.TOP,location[0]+mCurrentView.getWidth(),location[1]+mCurrentView.getHeight()/2
                            -mPopupWindowHeight/2);
                    //设置删除按钮回调
                    mDelBtn.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            // TODO Auto-generated method stub
                            if (mListener != null) {
                                mListener.clickHappend(mCurrentViewPos);
                                mPopupWindow.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    isSliding = false;
                default:
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 隐藏popupwindow
     */
    private void dismissPopWindow() {
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public DelButtonClickListener getmListener() {
        return mListener;
    }

    public void setmListener(DelButtonClickListener mListener) {
        this.mListener = mListener;
    }
}
