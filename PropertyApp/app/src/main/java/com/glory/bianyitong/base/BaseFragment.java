package com.glory.bianyitong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.glory.bianyitong.util.ToastUtils;

/**
 * Created by lucy on 2016/11/21.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String tag = getTag();
        if (!tag.isEmpty()) {
            switch (tag) {
                case "IndexFragment":

                    break;
                case "NeighbourFragment":

                    break;
                case "OpenTheDoorFragment":

                    break;
                case "FreshSupermarketFragment":

                    break;
                case "MyFragment":

                    break;
            }
        }
    }
    //页面跳转
//    protected void startActivity(Class<?> cls) {
//        Intent intent = new Intent(getActivity(), cls);
//        startActivity(intent);
//    }

    protected void showShort(String text) {
        ToastUtils.showShort(getActivity(), text);
    }

    protected void showLong(String text) {
        ToastUtils.showLong(getActivity(), text);
    }

    @Override
    public void onClick(View view) {

    }
}