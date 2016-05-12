package com.hoangphuong2.salemanager.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;

/**
 * Created by MrAn on 12-May-16.
 */
public class FragmentMain extends Fragment {
    private ImageButton btnPrivate;
    private ImageButton btnCompany;
    private ImageButton btnBill;
    private float screenWidth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        screenWidth = ScreenUtil.getScreenWidth(getActivity().getWindowManager());
        initValue(view);
        return view;
    }

    private void initValue(View v) {
        btnPrivate = (ImageButton)v.findViewById(R.id.imgPrivate);
        btnCompany = (ImageButton)v.findViewById(R.id.imgCompany);
        btnBill = (ImageButton)v.findViewById(R.id.imgBill);

        setOnClick();
    }

    private void setOnClick() {
        OnSingleClickListener click = new OnSingleClickListener(500) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()){
                    case R.id.imgPrivate:
                        break;
                    case R.id.imgCompany:
                        break;
                    case R.id.imgBill:
                        break;
                }
            }
        };
        btnPrivate.setOnClickListener(click);
        btnCompany.setOnClickListener(click);
        btnBill.setOnClickListener(click);
    }
}
