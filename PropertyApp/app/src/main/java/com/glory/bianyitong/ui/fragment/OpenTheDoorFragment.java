package com.glory.bianyitong.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.ui.activity.KeyManagerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lucy on 2016/11/10.
 * 开门
 */
public class OpenTheDoorFragment extends BaseFragment{
    @BindView(R.id.iv_open_ad)
    ImageView iv_open_ad;
    @BindView(R.id.tv_key_manager)
    TextView tv_key_manager;
    @BindView(R.id.ll_open_the_door)
    LinearLayout ll_open_the_door;
    @BindView(R.id.iv_opendoor_close)
    ImageView iv_opendoor_close;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fg_openthedoor, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initview();

        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("text" ,"阳光花园"+i+"号门");
//            list.add(map);
//        }
        horizontalScrollViewLayout(context,list,ll_open_the_door);
    }

    /**
     * 横向滑动布局
     */
    public void horizontalScrollViewLayout(final Context context, final List<Map<String, Object>> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.item_hs_door, lay_gallery, false);
                LinearLayout door_lay = (LinearLayout) view.findViewById(R.id.lay_door);
//            ImageView door_pic = (ImageView) view.findViewById(R.id.iv_door_pic);
                final TextView door_name = (TextView) view.findViewById(R.id.tv_door_name);
//            if (list != null && list.get(i).get("picture") != null && list.get(i).get("picture").toString().length() != 0 && !list.get(i).get("picture").toString().equals("")) {
//                setPicture(list.get(i).get("picture").toString(), type_pic, ImageView.ScaleType.FIT_CENTER);
//            }
                if (list != null && list.get(i).get("text") != null && list.get(i).get("text").toString().length() != 0 && !list.get(i).get("text").toString().equals("")) {
                    door_name.setText(list.get(i).get("text").toString());
                }

                final int j = i;
                door_lay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }

    private void initview(){
        iv_opendoor_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_key_manager.setOnClickListener(new View.OnClickListener() { //钥匙管理
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KeyManagerActivity.class);
                startActivity(intent);
            }
        });
    }

}
