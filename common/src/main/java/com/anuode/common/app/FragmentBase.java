package com.anuode.common.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.anuode.common.R;

public abstract class FragmentBase extends Fragment implements View.OnClickListener {
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.app__base_activity_next_zoom_in, R.anim.app__base_activity_next_zoom_out);
    }
}
