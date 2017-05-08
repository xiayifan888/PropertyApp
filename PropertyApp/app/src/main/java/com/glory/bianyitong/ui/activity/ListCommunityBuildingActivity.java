package com.glory.bianyitong.ui.activity;


import android.content.Context;
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
 * 楼栋列表页
 */
public class ListCommunityBuildingActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.province_list)
    LinearLayout communityBuilding_list;

    @Override
    protected int getContentId() {
        return R.layout.activity_province;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.choose_floor), true, "");//选择楼栋
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListCommunityBuildingActivity.this.finish();
            }
        });

        ScrollViewLayout(ListCommunityBuildingActivity.this, Database.list_CommunityBuilding, communityBuilding_list);

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
                final TextView tv_community_name = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_community_line = (TextView) view.findViewById(R.id.view_province_line);

                if (list != null && list.get(i) != null && list.get(i).get("buildingName") != null) {
                    tv_community_name.setText(list.get(i).get("buildingName").toString()); //小区名称
                }

                if (i == list.size() - 1) { //最后一根线
                    view_community_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_community_name.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).get("buildingID") != null) {
                            Database.buildingName = list.get(j).get("buildingName").toString();
                            Database.buildingID = Double.valueOf(list.get(j).get("buildingID").toString()).intValue();

                            Database.unitName = "";
                            Database.unitID = 0;
                            Database.roomName = "";
                            Database.roomID = 0;
                            ListCommunityBuildingActivity.this.finish();
                        }

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }


}
