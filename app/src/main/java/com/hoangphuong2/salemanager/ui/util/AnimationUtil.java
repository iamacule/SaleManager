package com.hoangphuong2.salemanager.ui.util;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn on 13-May-16.
 */
public class AnimationUtil {
    public static Animation slideInTop(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.slide_in_top);
    }

    public static Animation slideOutTop(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.slide_out_top);
    }

    public static Animation slideInBottom(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.slice_in_bottom);
    }

    public static Animation slideOutBottom(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.slice_out_bottom);
    }
}
