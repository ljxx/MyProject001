package com.ylx.myproject001.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ylx.myproject001.R;
import com.ylx.myproject001.adapter.GankViewProvider;
import com.ylx.myproject001.core.BaseRxFragment;
import com.ylx.myproject001.core.view.PtrRecyclerView;
import com.ylx.myproject001.entity.Gank;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseRxFragment {

    private static String TYPE = "type";

    @BindView(R.id.baseRecyclerView)
    PtrRecyclerView ptrRecyclerView;

    private MultiTypeAdapter adapter;
    private String type;


    public static MainActivityFragment newInstance(String type) {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    protected void parseArguments(Bundle bundle) {
        type = bundle.getString(TYPE);
    }

    @Override
    protected void initView() {
        parseArguments(getArguments());
        ptrRecyclerView.setParam("gank", type);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //RecyclerView滑动过程中不断请求layout的Request，不断调整item见的间隙，并且是在item尺寸显示前预处理，因此解决RecyclerView滑动到顶部时仍会出现移动问题
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        ptrRecyclerView.setLayoutManager(layoutManager);
        ptrRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        adapter = new MultiTypeAdapter();
        adapter.register(Gank.class, new GankViewProvider(type));
        ptrRecyclerView.setAdapter(adapter, new Gank());
    }
}
