package com.hoangphuong2.salemanager.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.ui.activity.MainActivity;
import com.hoangphuong2.salemanager.ui.adapter.list.DataAdapter;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;
import com.hoangphuong2.salemanager.util.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MrAn on 12-May-16.
 */
public class FragmentPrivate extends Fragment {
    private RecyclerView listData;
    private LinearLayout lnOption;
    private float screenWidth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        listData = (RecyclerView) view.findViewById(R.id.listData);
        lnOption = (LinearLayout) view.findViewById(R.id.lnOption);
        lnOption.setVisibility(View.GONE);
        List<Private> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            Private pri = new Private(0,(char)(r.nextInt(26) + 'a')+" Sample : " + i,null,null,null,0,0);
            list.add(pri);
        }
        screenWidth = ScreenUtil.getScreenWidth(getActivity().getWindowManager());
        Bitmap img = ResizeBitmap.resize(BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.red_circle), screenWidth / 8);
        DataAdapter dataAdapter = new DataAdapter(list, img);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listData.setLayoutManager(layoutManager);
        listData.setAdapter(dataAdapter);
        setOnSrcollListener();
    }

    private void setOnSrcollListener() {
        listData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if(dy>10){
                        // Scrolling up
                        if (lnOption.getVisibility()==View.GONE){
                            lnOption.startAnimation(AnimationUtil.slideInTop(getActivity()));
                            lnOption.setVisibility(View.VISIBLE);
                            ((MainActivity)getActivity()).setVisibleAddButton(false);
                        }
                    }
                } else {
                    if(dy<-10){
                        // Scrolling down
                        if (lnOption.getVisibility()==View.VISIBLE){
                            lnOption.setVisibility(View.GONE);
                            ((MainActivity)getActivity()).setVisibleAddButton(true);
                        }
                    }
                }
            }
        });
    }
}
