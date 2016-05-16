package com.hoangphuong2.salemanager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.ui.activity.MainActivity;
import com.hoangphuong2.salemanager.ui.adapter.list.DataAdapter;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.DataUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by MrAn on 12-May-16.
 */
public class FragmentPrivate extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayout lnOption;
    private List<Private> listData;
    private DataAdapter dataAdapter;
    private TextView txtNoData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.listData);
        lnOption = (LinearLayout) view.findViewById(R.id.lnOption);
        txtNoData = (TextView) view.findViewById(R.id.txtNodata);
        lnOption.setVisibility(View.GONE);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Database.getInstance().open();
        listData = Database.getInstance().getAllPrivate();
        Collections.sort(listData,Private.COMPARE_BY_NAME);
        Database.getInstance().close();
        showHideNodata(listData.size());
        dataAdapter = new DataAdapter(getActivity(),listData);
        recyclerView.setAdapter(dataAdapter);
        setOnSrcollListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(DataUtil.needAddNew){
            DataUtil.needAddNew = false;
            listData.add(DataUtil.privateData);
            Collections.sort(listData,Private.COMPARE_BY_NAME);
            dataAdapter.notifyDataSetChanged();
        }
    }

    private void showHideNodata(int size) {
        if (size > 0)
            txtNoData.setVisibility(View.GONE);
    }

    private void setOnSrcollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (dy > 10) {
                        // Scrolling up
                        if (lnOption.getVisibility() == View.GONE) {
                            lnOption.startAnimation(AnimationUtil.slideInTop(getActivity()));
                            lnOption.setVisibility(View.VISIBLE);
                            ((MainActivity) getActivity()).setVisibleAddButton(false);
                        }
                    }
                } else {
                    if (dy < -10) {
                        // Scrolling down
                        if (lnOption.getVisibility() == View.VISIBLE) {
                            lnOption.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).setVisibleAddButton(true);
                        }
                    }
                }
            }
        });
    }
}
