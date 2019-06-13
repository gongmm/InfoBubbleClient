package com.example.gnaiz.infobubble.discfragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.adapter.NewsAdapter;
import com.example.gnaiz.infobubble.view.EmptyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplyDemandFragment extends Fragment {

    private EmptyRecyclerView sdRecyclerView;
    private View mEmptyView;
    ProgressDialog dialog;

    public SupplyDemandFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new SupplyDemandFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supply_demand, container, false);

        sdRecyclerView = view.findViewById(R.id.recyclerView_sd_info);
        mEmptyView = view.findViewById(R.id.empty_iv);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }

    protected void initData()
    {

        //dialog = ProgressDialog.show(getContext(), "", "正在加载...");
        //getHistoryOrderByCoupon();
    }

    protected void setRecyclerView(){

        NewsAdapter newsAdapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sdRecyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        sdRecyclerView.setAdapter(newsAdapter);
        sdRecyclerView.setEmptyView(mEmptyView); //设置空布局
    }

}
