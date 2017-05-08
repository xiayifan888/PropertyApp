package com.glory.bianyitong.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;

import java.util.List;


public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {

    private List<BaseFragment> fragments; // 一个tab页面对应一个Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentActivity fragmentActivity; // Fragment所属的Activity
    private Fragment fragment; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引
    private CheckedChangedListener changedListener; // 用于让调用者在切换tab时候增加新的功能

    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<BaseFragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        init();
    }

    public FragmentTabAdapter(Fragment fragment, List<BaseFragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragment = fragment;
        this.fragmentContentId = fragmentContentId;
        init();
    }

    private void init() {
        FragmentTransaction ft;
        String tag = "";
        if (fragment != null) {
            ft = fragment.getChildFragmentManager().beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {// 全部加载
                switch (i) {
                    case 0:
                        tag = "IndexFragment";
                        break;
                    case 1:
                        tag = "NeighbourFragment";
                        break;
                    case 2:
                        tag = "OpenTheDoorFragment";
                        break;
                    case 3:
                        tag = "FreshSupermarketFragment";
                        break;
                    case 4:
                        tag = "MyFragment";
                        break;
                }
                ft.add(fragmentContentId, fragments.get(i), tag);
            }
        } else {
            ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
            ft.add(fragmentContentId, fragments.get(0), "MainFragment");        // 默认显示第一页
        }

        ft.commitAllowingStateLoss();

        rgs.setOnCheckedChangeListener(this);

        if (fragmentActivity == null) return;
        int dp20 = (int) fragmentActivity.getResources().getDimension(R.dimen.tab);
        for (int i = 0; i < rgs.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rgs.getChildAt(i);
            Drawable drawable = rb.getCompoundDrawables()[1];
            if (drawable != null) {
                drawable.setBounds(0, 0, dp20, dp20);//第一是距左边距离，第二是距上边距离，第三第四分别是长宽
            }
            rb.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        String tag = "";
        for (int i = 0; i < rgs.getChildCount(); i++) {
            switch (i) {
                case 0:
                    tag = "IndexFragment";
                    break;
                case 1:
                    tag = "NeighbourFragment";
                    break;
                case 2:
                    tag = "OpenTheDoorFragment";
                    break;
                case 3:
                    tag = "FreshSupermarketFragment";
                    break;
                case 4:
                    tag = "MyFragment";
                    break;
            }

            if (rgs.getChildAt(i).getId() == checkedId) {
                // 如果设置了切换tab额外功能功能接口
                if (null != changedListener) {
                    changedListener.onCheckChange(radioGroup, checkedId, i);
                }
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                getCurrentFragment().onPause(); // 暂停当前tab

                if (fragment.isAdded()) {
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.add(fragmentContentId, fragment, tag);
                }
                showTab(i); // 显示目标tab
                ft.commitAllowingStateLoss();

            }
        }
    }

    /**
     * 切换Fragment
     *
     * @param tabId
     */
    public void showFragment(int tabId){
        String tag = "";
        switch (tabId) {
            case 0:
                tag = "IndexFragment";
                break;
            case 1:
                tag = "NeighbourFragment";
                break;
            case 2:
                tag = "OpenTheDoorFragment";
                break;
            case 3:
                tag = "FreshSupermarketFragment";
                break;
            case 4:
                tag = "MyFragment";
                break;
        }

            Fragment fragment = fragments.get(tabId);
            FragmentTransaction ft = obtainFragmentTransaction(tabId);

            getCurrentFragment().onPause(); // 暂停当前tab

            if (fragment.isAdded()) {
                fragment.onResume(); // 启动目标tab的onResume()
            } else {
                ft.add(fragmentContentId, fragment, tag);
            }
            showTab(tabId); // 显示目标tab
            ft.commitAllowingStateLoss();
    }

    /**
     * 切换tab
     *
     * @param index
     */
    private void showTab(int index) {

        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(index);
            if (index == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        currentTab = index; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft;
        if (fragment != null) {
            ft = fragment.getChildFragmentManager().beginTransaction();
        } else {
            ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        }
        // 设置切换动画
        if(index == 2){
            ft.setCustomAnimations(R.anim.push_bottom_in,R.anim.push_bottom_out);
        }else if (index > currentTab) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public void setCheckedChangedListener(CheckedChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static class CheckedChangedListener {

        public void onCheckChange(RadioGroup radioGroup, int checkedId, int index) {

        }
    }
}
