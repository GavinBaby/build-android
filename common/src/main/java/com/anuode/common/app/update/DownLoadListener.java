package com.anuode.common.app.update;

public interface DownLoadListener {
    void onStart();

    void onSeekChange(int seek);//进度  百分比

    void onComplete();
}
