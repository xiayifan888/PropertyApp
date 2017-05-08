package com.glory.bianyitong.ui.activity;


import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.view.NewPullToRefreshView;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/10.
 * 收藏商品
 */
public class FavoriteProductActivity extends BaseActivity{
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    //    private ArrayList<LinkedTreeMap<String, Object>> community_List; //社区公告列表
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.fp_list_refresh)
    NewPullToRefreshView fp_list_refresh;
    @BindView(R.id.listView_fp)
    ListView listView_fp;

    @Override
    protected int getContentId() {
        return R.layout.ac_favorite_product;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("收藏商品",true,"");
    }



}
