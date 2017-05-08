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

import java.util.List;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 区列表页
 */
public class ListDistrictActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.province_list)
    LinearLayout district_list;

    @Override
    protected int getContentId() {
        return R.layout.activity_province;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.choose_district_and_county), true, "");//选择区县
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListDistrictActivity.this.finish();
            }
        });

        ScrollViewLayout(ListDistrictActivity.this, Database.district_list, district_list);

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
                final TextView tv_district_name = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_district_line = (TextView) view.findViewById(R.id.view_province_line);

                if (list != null && list.get(i).get("name") != null && list.get(i).get("name").toString().length() != 0 && !list.get(i).get("name").toString().equals("")) {
                    tv_district_name.setText(list.get(i).get("name").toString()); //省份名称
                }

                if (i == list.size() - 1) { //最后一根线
                    view_district_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_district_name.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).get("iD") != null) {
                            Database.str_district = list.get(j).get("name").toString();
                            Database.district_id = Double.valueOf(list.get(j).get("iD").toString()).intValue();
                            Database.id_district = Double.valueOf(list.get(j).get("iD").toString()).intValue();

                            Database.communityName = "";
                            Database.communityID = 0;
                            Database.buildingName = "";
                            Database.buildingID = 0;
                            Database.unitName = "";
                            Database.unitID = 0;
                            Database.roomName = "";
                            Database.roomID = 0;
                            Intent intent = new Intent(ListDistrictActivity.this, AddAreaCityActivity.class);
                            startActivity(intent);
                        }

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }


}
