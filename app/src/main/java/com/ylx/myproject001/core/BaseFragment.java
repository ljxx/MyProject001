package com.ylx.myproject001.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Logger;

import butterknife.ButterKnife;

/**
 * Created by Weiss on 2017/1/10.
 */

public abstract class BaseFragment extends Fragment{
    public View rootView;
    public LayoutInflater inflater;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater=inflater;
        Logger.getLogger("================oncreateview============");
        if (rootView == null) {
            rootView = inflater.inflate(this.getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            Log.i("=========","=========");
            Logger.getLogger("============================");
            initView();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }


    protected abstract int getLayoutId();

    protected abstract void initView();
}
