package com.anuode.common.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anuode.common.R;
import com.anuode.common.util.ActivityManager;

public abstract class ActivityBase extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        this.overridePendingTransition(R.anim.app__base_activity_next_zoom_in, R.anim.app__base_activity_next_zoom_out);
    }

    @Override
    public void finish() {
        super.finish();
//        this.overridePendingTransition(R.anim.app__base_activity_back_zoom_in, R.anim.app__base_activity_back_zoom_out);
    }

    public void finishWithoutAnimation() {
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
