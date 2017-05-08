package com.glory.bianyitong.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.ui.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;


public abstract class ViewPagerFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    protected ArrayList<BaseFragment> fragments = new ArrayList<>();
    protected FragmentActivity activity;
    protected View view;
    protected Resources resources;
    protected int currIndex = 0;// 当前选择的pager
    protected ViewPager viewPager;
    protected int pagerNum = 1;// pager个数
    protected ArrayList<Integer> positions;// 导航TextView底部滑动的位置
    protected LinearLayout ivBottomLine;// 导航TextView底部滑动图标
    protected ArrayList<TextView> tvList = new ArrayList<>();// 导航TextView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        resources = getResources();
    }

    // 所有初始化工作，在onCreateView中调用
    protected void init(int pagerNum, int currIndex) {
        initPagerNum(pagerNum);
        initView();
        initFragments();
        initTvList();
        initNavigation(currIndex);
    }

    // 设置pager个数
    private void initPagerNum(int pagerNum) {
        this.pagerNum = pagerNum;
        positions = new ArrayList<>();
    }

    // 初始化滑动导航栏
    private void initNavigation(int currIndex) {
        ViewGroup.LayoutParams params;
        params = ivBottomLine.getLayoutParams();
        params.width = getLineWidth();
        params.height = 50;
        ivBottomLine.setLayoutParams(params);

        for (int i = 0; i < pagerNum; i++) {
            positions.add(i * params.width);
        }

        for (int i = 0; i < tvList.size(); i++) {
            tvList.get(i).setOnClickListener(new MyOnClickListener(i));
        }

        tvList.get(currIndex).setTextColor(resources.getColor(R.color.light_blue));


        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(pagerNum);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragments));
        viewPager.setCurrentItem(currIndex);
    }

    // 初始化控件
    protected abstract void initView();

    protected abstract void initTvList();

    protected abstract void initFragments();

    protected int getLineWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / pagerNum;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int which) {
        Animation animation;
        animation = new TranslateAnimation(positions.get(currIndex), positions.get(which), 0, 0);
        tvList.get(currIndex).setTextColor(resources.getColor(R.color.text_color));
        tvList.get(which).setTextColor(resources.getColor(R.color.light_blue));
        currIndex = which;
        animation.setFillAfter(true);
        animation.setDuration(300);
        ivBottomLine.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    private class MyOnClickListener implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListener(int i) {
            this.index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }
}
