package com.glory.bianyitong.ui.activity;


import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;

/**
 * Created by lucy on 2016/11/10.
 * 注册
 */
public class RegisterActivity extends BaseActivity{
    @Override
    protected int getContentId() {
        return R.layout.ac_regiter;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("注册",true,"");
    }
}
