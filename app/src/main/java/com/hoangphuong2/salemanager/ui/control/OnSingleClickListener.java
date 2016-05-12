package com.hoangphuong2.salemanager.ui.control;

import android.os.SystemClock;
import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {
    private long minClickInterval;
    private long mLastClickTime;
    public abstract void onSingleClick(View v);

    public OnSingleClickListener(long minClickInterval){
        this.minClickInterval = minClickInterval;
    }

    @Override
    public final void onClick(View v) {
        long currentClickTime=SystemClock.uptimeMillis();
        long elapsedTime=currentClickTime-mLastClickTime;
        mLastClickTime=currentClickTime;
        if(elapsedTime<=minClickInterval)
            return;
        onSingleClick(v);
    }

}