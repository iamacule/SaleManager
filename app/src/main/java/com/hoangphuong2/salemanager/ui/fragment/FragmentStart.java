package com.hoangphuong2.salemanager.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.SplashScreen;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;

/**
 * Created by MrAn on 12-May-16.
 */
public class FragmentStart extends Fragment {
    private ImageView imgLogo;
    private EditText etName;
    private Button btnConfirm;
    private float screenWidth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        initLayout(view);
        setOnClick();
        return view;
    }

    private void setOnClick() {
        OnSingleClickListener click = new OnSingleClickListener(200) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()){
                    case R.id.btnComfirm:
                        save();
                        break;
                }
            }
        };
        btnConfirm.setOnClickListener(click);
    }

    private void save() {
        ((SplashScreen)getActivity()).getPreferences().storeValue(
                ((SplashScreen)getActivity()).getPreferences().SALE_MANAGER_OWNER_NAME,etName.getText().toString());
        Boast.makeText(getActivity(),getActivity().getString(R.string.toast_save_success)).show();
        ((SplashScreen)getActivity()).getFragmentNavigator().goTo(new FragmentMain());
    }

    private void initLayout(View v) {
        imgLogo = (ImageView)v.findViewById(R.id.imgLogo);
        etName = (EditText) v.findViewById(R.id.etName);
        btnConfirm = (Button) v.findViewById(R.id.btnComfirm);
        screenWidth = ScreenUtil.getScreenWidth(getActivity().getWindowManager());
        Bitmap logo = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(),
                R.drawable.sale_manager_icon),screenWidth/4);
        imgLogo.setImageBitmap(logo);
    }
}
