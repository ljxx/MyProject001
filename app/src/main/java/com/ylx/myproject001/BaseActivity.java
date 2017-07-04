package com.ylx.myproject001;


import com.ylx.myproject001.core.BaseRxActivity;

/**
 * Created by Weiss on 2017/1/17.
 */

public abstract class BaseActivity extends BaseRxActivity {

    protected abstract int getLayoutId();

    protected abstract void initView();

    //token失效处理
    public void tokenInvalid( ){

    }
    //是否登录
    protected boolean isLogin(){
        return false;
    }
}
