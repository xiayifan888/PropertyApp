package com.glory.bianyitong.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 城市列表页
 */
public class ListCityActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.province_list)
    LinearLayout city_list;

    @Override
    protected int getContentId() {
        return R.layout.activity_province;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.select_city), true, "");//选择城市
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListCityActivity.this.finish();
            }
        });

        ScrollViewLayout(ListCityActivity.this, Database.city_list, city_list);

    }


    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_province, lay_gallery, false);
                final TextView tv_city_name = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_city_line = (TextView) view.findViewById(R.id.view_province_line);

                if (list != null && list.get(i).get("name") != null && list.get(i).get("name").toString().length() != 0 && !list.get(i).get("name").toString().equals("")) {
                    tv_city_name.setText(list.get(i).get("name").toString()); //省份名称
                }

                if (i == list.size() - 1) { //最后一根线
                    view_city_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_city_name.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).get("iD") != null) { //选择城市
                            Database.district_list = null;
                            Database.district_list = new ArrayList<>();
                            Database.str_city = list.get(j).get("name").toString();
                            Database.id_city = Double.valueOf(list.get(j).get("iD").toString()).intValue();

                            Database.str_district = "";
                            Database.district_id = 0;
                            Database.id_district = 0;

                            for (int i = 0; i < Database.list_AllCity.size(); i++) {
                                if (Database.list_AllCity.get(i) != null && Database.list_AllCity.get(i).get("pID") != null &&
                                        Database.list_AllCity.get(i).get("pID").toString().equals(list.get(j).get("iD").toString())) {
                                    Database.district_list.add(Database.list_AllCity.get(i)); //获城市列表
                                }
                            }
                            if (Database.district_list != null && Database.district_list.size() > 0) {
                                Intent intent = new Intent(ListCityActivity.this, ListDistrictActivity.class);
                                startActivity(intent);
                            } else {
//                                ToastUtils.showToast(ListCityActivity.this, "获取区失败");
                                Database.district_id = Double.valueOf(list.get(j).get("iD").toString()).intValue();
                                Intent intent = new Intent(ListCityActivity.this, AddAreaCityActivity.class);
                                startActivity(intent);
                            }

                        }
                    }
                });

                lay_gallery.addView(view);
            }
        }
    }


}
