package com.anuode.common.widget.aircalender;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 日历控件 功能：获得点选的日期区间
 */
public class CalendarView extends View implements View.OnTouchListener {
    private final static String TAG = "anCalendar";
    private Date mSelectedStartDate;
    private Date mSelectedEndDate;
    private Date mCurDate; // 当前日历显示的月
    private Date mToday; // 今天的日期文字显示红色
    private Date mDownDate; // 手指按下状态时临时日期
    private Date mShowFirstDate, mShowLastDate; // 日历显示的第一个日期和最后一个日期
    private int mDownIndex; // 按下的格子索引
    private Calendar mCalendar;
    private Surface mSurface;
    private final int[] date = new int[42]; // 日历显示数字
    private int mCurStartIndex, mCurEndIndex; // 当前显示的日历起始的索引
    private boolean mCompleted = false; // 为false表示只选择了开始日期，true表示结束日期也选择了
    private boolean mIsSelectMore = false;
    //给控件设置监听事件
    private OnItemClickListener mOnItemClickListener;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCurDate = mSelectedStartDate = mSelectedEndDate = mToday = new Date();
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mCurDate);
        mSurface = new Surface();
        mSurface.mDensity = getResources().getDisplayMetrics().density;
        setBackgroundColor(mSurface.mBgColor);
        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mSurface.mWidth = getResources().getDisplayMetrics().widthPixels;
        mSurface.mHeight = getResources().getDisplayMetrics().heightPixels * 2 / 5;

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mSurface.mWidth,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mSurface.mHeight,
                MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        Log.d(TAG, "[onLayout] changed:"
                + (changed ? "new size" : "not change") + " left:" + left
                + " top:" + top + " right:" + right + " bottom:" + bottom);
        if (changed) {
            mSurface.init();
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        // 画框
        canvas.drawPath(mSurface.mBoxPath, mSurface.mBorderPaint);
        float weekTextY = mSurface.monthHeight + mSurface.mWeekHeight * 3 / 4f;
        for (int i = 0; i < mSurface.weekText.length; i++) {
            float weekTextX = i
                    * mSurface.mCellWidth
                    + (mSurface.mCellWidth - mSurface.mWeekPaint
                    .measureText(mSurface.weekText[i])) / 2f;
            canvas.drawText(mSurface.weekText[i], weekTextX, weekTextY,
                    mSurface.mWeekPaint);
        }

        // 计算日期
        calculateDate();
        // 按下状态，选择状态背景色
        drawDownOrSelectedBg(canvas);
        int todayIndex = -1;
        mCalendar.setTime(mCurDate);
        String curYearAndMonth = mCalendar.get(Calendar.YEAR) + ""
                + mCalendar.get(Calendar.MONTH);
        mCalendar.setTime(mToday);
        String todayYearAndMonth = mCalendar.get(Calendar.YEAR) + ""
                + mCalendar.get(Calendar.MONTH);
        if (curYearAndMonth.equals(todayYearAndMonth)) {
            int todayNumber = mCalendar.get(Calendar.DAY_OF_MONTH);
            todayIndex = mCurStartIndex + todayNumber - 1;
        }
        for (int i = 0; i < 42; i++) {
            int color = mSurface.mTextColor;
            if (isLastMonth(i)) {
                color = mSurface.mBorderColor;
            } else if (isNextMonth(i)) {
                color = mSurface.mBorderColor;
            }
            if (todayIndex != -1 && i == todayIndex) {
                color = mSurface.mTodayNumberColor;
            }
            drawCellText(canvas, i, date[i] + "", color);
        }
        super.onDraw(canvas);
    }

    private void calculateDate() {
        mCalendar.setTime(mCurDate);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayInWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        Log.d(TAG, "day in week:" + dayInWeek);
        int monthStart = dayInWeek;
        if (monthStart == 1) {
            monthStart = 8;
        }
        monthStart -= 1;  //以日为开头-1，以星期一为开头-2
        mCurStartIndex = monthStart;
        date[monthStart] = 1;
        // last month
        if (monthStart > 0) {
            mCalendar.set(Calendar.DAY_OF_MONTH, 0);
            int dayInmonth = mCalendar.get(Calendar.DAY_OF_MONTH);
            for (int i = monthStart - 1; i >= 0; i--) {
                date[i] = dayInmonth;
                dayInmonth--;
            }
            mCalendar.set(Calendar.DAY_OF_MONTH, date[0]);
        }
        mShowFirstDate = mCalendar.getTime();
        // this month
        mCalendar.setTime(mCurDate);
        mCalendar.add(Calendar.MONTH, 1);
        mCalendar.set(Calendar.DAY_OF_MONTH, 0);
        int monthDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        for (int i = 1; i < monthDay; i++) {
            date[monthStart + i] = i + 1;
        }
        mCurEndIndex = monthStart + monthDay;
        // next month
        for (int i = monthStart + monthDay; i < 42; i++) {
            date[i] = i - (monthStart + monthDay) + 1;
        }
        if (mCurEndIndex < 42) {
            // 显示了下一月的
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        mCalendar.set(Calendar.DAY_OF_MONTH, date[41]);
        mShowLastDate = mCalendar.getTime();
    }

    private void drawCellText(Canvas canvas, int index, String text, int color) {
        int x = getXByIndex(index);
        int y = getYByIndex(index);
        mSurface.mDatePaint.setColor(color);
        float cellY = mSurface.monthHeight + mSurface.mWeekHeight + (y - 1)
                * mSurface.mCellHeight + mSurface.mCellHeight * 3 / 4f;
        float cellX = (mSurface.mCellWidth * (x - 1))
                + (mSurface.mCellWidth - mSurface.mDatePaint.measureText(text))
                / 2f;
        canvas.drawText(text, cellX, cellY, mSurface.mDatePaint);
    }

    private void drawCellBg(Canvas canvas, int index, int color) {
        int x = getXByIndex(index);
        int y = getYByIndex(index);
        mSurface.mCellBgPaint.setColor(color);
        float left = mSurface.mCellWidth * (x - 1) + mSurface.mBorderWidth;
        float top = mSurface.monthHeight + mSurface.mWeekHeight + (y - 1)
                * mSurface.mCellHeight + mSurface.mBorderWidth;
        canvas.drawRect(left, top, left + mSurface.mCellWidth
                - mSurface.mBorderWidth, top + mSurface.mCellHeight
                - mSurface.mBorderWidth, mSurface.mCellBgPaint);
    }

    private void drawDownOrSelectedBg(Canvas canvas) {
        // down and not up
        if (mDownDate != null) {
            drawCellBg(canvas, mDownIndex, mSurface.mCellDownColor);
        }
        // selected bg color
        if (!mSelectedEndDate.before(mShowFirstDate)
                && !mSelectedStartDate.after(mShowLastDate)) {
            int[] section = new int[]{-1, -1};
            mCalendar.setTime(mCurDate);
            mCalendar.add(Calendar.MONTH, -1);
            findSelectedIndex(0, mCurStartIndex, mCalendar, section);
            if (section[1] == -1) {
                mCalendar.setTime(mCurDate);
                findSelectedIndex(mCurStartIndex, mCurEndIndex, mCalendar, section);
            }
            if (section[1] == -1) {
                mCalendar.setTime(mCurDate);
                mCalendar.add(Calendar.MONTH, 1);
                findSelectedIndex(mCurEndIndex, 42, mCalendar, section);
            }
            if (section[0] == -1) {
                section[0] = 0;
            }
            if (section[1] == -1) {
                section[1] = 41;
            }
            for (int i = section[0]; i <= section[1]; i++) {
                drawCellBg(canvas, i, mSurface.mCellSelectedColor);
            }
        }
    }

    private void findSelectedIndex(int startIndex, int endIndex,
                                   Calendar mCalendar, int[] section) {
        for (int i = startIndex; i < endIndex; i++) {
            mCalendar.set(Calendar.DAY_OF_MONTH, date[i]);
            Date temp = mCalendar.getTime();
            // Log.d(TAG, "temp:" + temp.toLocaleString());
            if (temp.compareTo(mSelectedStartDate) == 0) {
                section[0] = i;
            }
            if (temp.compareTo(mSelectedEndDate) == 0) {
                section[1] = i;
                return;
            }
        }
    }

  /*  public Date getmSelectedStartDate() {
        return mSelectedStartDate;
    }*/
/*
    public Date getmSelectedEndDate() {
        return mSelectedEndDate;
    }*/

    private boolean isLastMonth(int i) {
        return i < mCurStartIndex;
    }

    private boolean isNextMonth(int i) {
        return i >= mCurEndIndex;
    }

    private int getXByIndex(int i) {
        return i % 7 + 1; // 1 2 3 4 5 6 7
    }

    private int getYByIndex(int i) {
        return i / 7 + 1; // 1 2 3 4 5 6
    }

    // 获得当前应该显示的年月
    public String getYearAndMonth() {
        mCalendar.setTime(mCurDate);
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH) + 1;
        return year + "-" + month;
    }

    // 上一月
    public String clickLeftMonth() {
        mCalendar.setTime(mCurDate);
        mCalendar.add(Calendar.MONTH, -1);
        mCurDate = mCalendar.getTime();
        invalidate();
        return getYearAndMonth();
    }

    // 下一月
    public String clickRightMonth() {
        mCalendar.setTime(mCurDate);
        mCalendar.add(Calendar.MONTH, 1);
        mCurDate = mCalendar.getTime();
        invalidate();
        return getYearAndMonth();
    }

    // 设置日历时间
    public void setCalendarData(Date date) {
        mCalendar.setTime(date);
        invalidate();
    }

    // 获取日历时间
   /* public void getCalendatData() {
        mCalendar.getTime();
    }*/

    // 设置是否多选
    public boolean mIsSelectMore() {
        return mIsSelectMore;
    }

    public void setSelectMore() {
        this.mIsSelectMore = false;
    }

    private void setSelectedDateByColor(float x, float y) {
        if (y > mSurface.monthHeight + mSurface.mWeekHeight) {
            int m = (int) (Math.floor(x / mSurface.mCellWidth) + 1);
            int n = (int) (Math.floor((y - (mSurface.monthHeight + mSurface.mWeekHeight)) / mSurface.mCellHeight) + 1);
            mDownIndex = (n - 1) * 7 + m - 1;
            Log.d(TAG, "downIndex:" + mDownIndex);
            mCalendar.setTime(mCurDate);
            if (isLastMonth(mDownIndex)) {
                mCalendar.add(Calendar.MONTH, -1);
            } else if (isNextMonth(mDownIndex)) {
                mCalendar.add(Calendar.MONTH, 1);
            }
            mCalendar.set(Calendar.DAY_OF_MONTH, date[mDownIndex]);
            mDownDate = mCalendar.getTime();
        }
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setSelectedDateByColor(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                if (mDownDate != null) {
                    if (mIsSelectMore) {
                        if (!mCompleted) {
                            if (mDownDate.before(mSelectedStartDate)) {
                                mSelectedEndDate = mSelectedStartDate;
                                mSelectedStartDate = mDownDate;
                            } else {
                                mSelectedEndDate = mDownDate;
                            }
                            mCompleted = true;
                            // 响应监听事件
                            mOnItemClickListener.OnItemClick(mSelectedStartDate, mSelectedEndDate, mDownDate);
                        } else {
                            mSelectedStartDate = mSelectedEndDate = mDownDate;
                            mCompleted = false;
                        }
                    } else {
                        mSelectedStartDate = mSelectedEndDate = mDownDate;
                        // 响应监听事件
                        mOnItemClickListener.OnItemClick(mSelectedStartDate, mSelectedEndDate, mDownDate);
                    }
                    invalidate();
                }
                break;
        }
        return true;
    }

    // 给控件设置监听事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    // 监听接口
    public interface OnItemClickListener {
        void OnItemClick(Date mSelectedStartDate, Date mSelectedEndDate, Date mDownDate);
    }

    /**
     * 1. 布局尺寸 2. 文字颜色，大小 3. 当前日期的颜色，选择的日期颜色
     */
    private class Surface {
        public float mDensity;
        public int mWidth; // 整个控件的宽度
        public int mHeight; // 整个控件的高度
        public float monthHeight; // 显示月的高度
        // public float monthChangeWidth; // 上一月、下一月按钮宽度
        public float mWeekHeight; // 显示星期的高度
        public float mCellWidth; // 日期方框宽度
        public float mCellHeight; // 日期方框高度
        public float mBorderWidth;
        public final int mBgColor = Color.parseColor("#FFFFFF");
        private final int mTextColor = Color.BLACK;
        // private int textColorUnimportant = Color.parseColor("#666666");
        private final int mBtnColor = Color.parseColor("#666666");
        private final int mBorderColor = Color.parseColor("#CCCCCC");
        public final int mTodayNumberColor = Color.RED;
        public final int mCellDownColor = Color.parseColor("#CCFFFF");
        public final int mCellSelectedColor = Color.parseColor("#99ccff");
        public Paint mBorderPaint;
        public Paint monthPaint;
        public Paint mWeekPaint;
        public Paint mDatePaint;
        public Paint monthChangeBtnPaint;
        public Paint mCellBgPaint;
        public Path mBoxPath; // 边框路径
        // public Path preMonthBtnPath; // 上一月按钮三角形
        // public Path nextMonthBtnPath; // 下一月按钮三角形
        public final String[] weekText = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        // public String[] monthText = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        public void init() {
            float temp = mHeight / 7f;
            monthHeight = 0;// (float) ((temp + temp * 0.3f) * 0.6);
            // monthChangeWidth = monthHeight * 1.5f;
            mWeekHeight = (float) ((temp + temp * 0.3f) * 0.7);
            mCellHeight = (mHeight - monthHeight - mWeekHeight) / 6f;
            mCellWidth = mWidth / 7f;
            mBorderPaint = new Paint();
            mBorderPaint.setColor(mBorderColor);
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderWidth = (float) (0.5 * mDensity);
            mBorderWidth = mBorderWidth < 1 ? 1 : mBorderWidth;
            mBorderPaint.setStrokeWidth(mBorderWidth);
            monthPaint = new Paint();
            monthPaint.setColor(mTextColor);
            monthPaint.setAntiAlias(true);
            float textSize = mCellHeight * 0.4f;
            Log.d(TAG, "text size:" + textSize);
            monthPaint.setTextSize(textSize);
            monthPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mWeekPaint = new Paint();
            mWeekPaint.setColor(mTextColor);
            mWeekPaint.setAntiAlias(true);
            float weekTextSize = mWeekHeight * 0.3f;
            mWeekPaint.setTextSize(weekTextSize);
            mWeekPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mDatePaint = new Paint();
            mDatePaint.setColor(mTextColor);
            mDatePaint.setAntiAlias(true);
            float cellTextSize = mCellHeight * 0.5f;
            mDatePaint.setTextSize(cellTextSize);
            mDatePaint.setTypeface(Typeface.DEFAULT_BOLD);
            mBoxPath = new Path();
            // boxPath.addRect(0, 0, width, height, Direction.CW);
            // boxPath.moveTo(0, monthHeight);
            mBoxPath.rLineTo(mWidth, 0);
            mBoxPath.moveTo(0, monthHeight + mWeekHeight);
            mBoxPath.rLineTo(mWidth, 0);
            for (int i = 1; i < 6; i++) {
                mBoxPath.moveTo(0, monthHeight + mWeekHeight + i * mCellHeight);
                mBoxPath.rLineTo(mWidth, 0);
                mBoxPath.moveTo(i * mCellWidth, monthHeight);
                mBoxPath.rLineTo(0, mHeight - monthHeight);
            }
            mBoxPath.moveTo(6 * mCellWidth, monthHeight);
            mBoxPath.rLineTo(0, mHeight - monthHeight);

            monthChangeBtnPaint = new Paint();
            monthChangeBtnPaint.setAntiAlias(true);
            monthChangeBtnPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            monthChangeBtnPaint.setColor(mBtnColor);
            mCellBgPaint = new Paint();
            mCellBgPaint.setAntiAlias(true);
            mCellBgPaint.setStyle(Paint.Style.FILL);
            mCellBgPaint.setColor(mCellSelectedColor);
        }
    }
}
