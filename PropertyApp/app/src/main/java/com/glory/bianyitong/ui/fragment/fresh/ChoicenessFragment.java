package com.glory.bianyitong.ui.fragment.fresh;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.view.MyGridView;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.adapter.EveryDayFirstGoodsAdapter;
import com.glory.bianyitong.ui.adapter.FruitListAdapter;
import com.glory.bianyitong.ui.adapter.MyViewPagerAdapter;
import com.glory.bianyitong.ui.adapter.QualityFirstAdapter;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 精选
 */
public class ChoicenessFragment extends BaseFragment {
    private static final float APP_PAGE_SIZE = 3.0f;
    //(大家都在卖)
    @BindView(R.id.base_listView)
    ListView base_listView;
    @BindView(R.id.base_pullToRefreshView)
    NewPullToRefreshView base_pullToRefreshView;
    @BindView(R.id.lay_no_network)
    LinearLayout lay_no_network;
    private Context context;
    //    ArrayList<LinkedTreeMap<String, Object>> taglist; //标签数据
    private View everyday_first; //每日抢鲜
    private View quality_first; //品质首选
    private TextView tv_module_name1;
    private TextView tv_module_name2;
    private TextView tv_module_name3;
    //每日抢鲜
    private LinearLayout lay_everyday_first;
    private MyViewPagerAdapter type_adapter;
    private ViewPager type_viewPager;
    private LayoutInflater inflater;
    private PageControl pageControl;
    private Map<Integer, GridView> map;
    private List<LinkedTreeMap<String, Object>> lvdatalist; // 每日抢鲜数据
    //品质首选
    private ImageView iv_quality_first_pic;
    private MyGridView gridView;
    private QualityFirstAdapter qualityFirstAdapter;
    private ArrayList<LinkedTreeMap<String, Object>> gvdatalist; //品质首选数据
    private FruitListAdapter fruitListAdapter;
//    private ArrayList<LinkedTreeMap<String, Object>> goodslist;//商品数据
    private List<FreashInfo.ListFreshBean> goodslist;//商品数据
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private int index_page = 0;
    private ProgressDialog progressDialog = null;

    private String str_tag;
    private int flag;//当前fragment在fragment数组中的位置

    public static ChoicenessFragment newInstance(String content, int flag) {
        Bundle bundle = new Bundle();

        bundle.putString("content", content);
        bundle.putInt("flag", flag);
        ChoicenessFragment testFm = new ChoicenessFragment();
        testFm.setArguments(bundle);
        return testFm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            str_tag = bundle.getString("content");
            flag = bundle.getInt("flag");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.lay_listview_no_title, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflater = getActivity().getLayoutInflater();
        everyday_first = inflater.inflate(R.layout.lay_everyday_first, null); //每日抢鲜
        quality_first = inflater.inflate(R.layout.lay_quality_first, null);//品质首选
        lay_everyday_first = (LinearLayout) everyday_first.findViewById(R.id.lay_everyday_first);
        tv_module_name1 = (TextView) everyday_first.findViewById(R.id.tv_module_name1); //每日抢鲜
        tv_module_name2 = (TextView) quality_first.findViewById(R.id.tv_module_name2);//品质首选
        tv_module_name3 = (TextView) quality_first.findViewById(R.id.tv_module_name3);//大家都在买
        gridView = (MyGridView) quality_first.findViewById(R.id.gv_quality_first);
        iv_quality_first_pic = (ImageView) quality_first.findViewById(R.id.iv_quality_first_pic);

        view_loading = getActivity().getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        base_listView.addHeaderView(everyday_first);
        base_listView.addHeaderView(quality_first);
        base_listView.addFooterView(view_loading);
        base_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (goodslist != null && have_GoodsList && !getGoodsListStart) {
                            getGoodsListStart = true;
                            loading_lay.setVisibility(View.VISIBLE);
                            index_page++;
                            request2(index_page, false);
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
        });

        base_pullToRefreshView.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(NewPullToRefreshView view) {
                if (goodslist != null && !getGoodsListStart) {
                    getGoodsListStart = true;
                    index_page = 0;//重置index_page
                    index_page++;
                    request2(index_page, true);//刷新
                }
            }
        });
        Log.i("resultString", "onViewCreated------");
        if(ActivityUtils.isNetworkAvailable()){
            base_pullToRefreshView.setVisibility(View.VISIBLE);
            lay_no_network.setVisibility(View.GONE);

            request();
            index_page = 0;
            index_page++;
            request2(index_page, true);
        }else {
            base_pullToRefreshView.setVisibility(View.GONE);
            lay_no_network.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("resultString", "onStart------");
    }

    private void request() { //精选数据
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
        String json = "{\"freshFeatured\":{\"communityID\":" + communityID + "},\"controllerName\":\"News\",\"actionName\":\"StructureQuery\"," +
                "\"nowpagenum\":\"1\",\"pagerownum\":\"10\",\"userid\":\"" + userID + "\"}";

//        String json = "{\"freshFeatured\":{},\"controllerName\":\"News\",\"actionName\":\"StructureQuery\"," +
//                "\"nowpagenum\":\"1\",\"pagerownum\":\"10\",\"userid\":\"" + userID + "\"}";

        Log.i("resultString", "精选json------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/QueryFreshFeatured")
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        base_pullToRefreshView.onHeaderRefreshComplete();
                        getGoodsListStart = false;
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "------------");
                        Log.i("resultString", "精选===" + s);
                        Log.i("resultString", "------------");
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                        });
                        if (hashMap2 != null && hashMap2.get("listFeatured") != null) {
                            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listFeatured");
                            if (list != null && list.size() > 0) {
                                showlist(list);
                            }
                        }
                        if (hashMap2 != null && hashMap2.get("advertising") != null) { //就一张广告图
                            ArrayList<LinkedTreeMap<String, Object>> list2 = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("advertising");
//                            Log.i("resultString", "list2===" + hashMap2.get("advertising"));
                            if (list2 != null && list2.size() > 0 && list2.get(0) != null && list2.get(0).get("advertisingPicture") != null) {
//                                ServiceDialog.setPicture(list2.get(0).get("advertisingPicture").toString(), iv_quality_first_pic, null);
                                String data = list2.get(0).get("advertisingPicture").toString();
                                Glide.with(context).load(data).error(R.drawable.wait).placeholder(R.drawable.wait).into(iv_quality_first_pic);
                            }
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        base_pullToRefreshView.onHeaderRefreshComplete();
                        getGoodsListStart = false;
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(context, getResources().getString(R.string.failed_to_connect_to_server));
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        progressDialog = ProgressDialog.show(context, "", getResources().getString(R.string.load), true);
                        progressDialog.setCanceledOnTouchOutside(true);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                    }

                });
    }

    private void request2(int page, final boolean isrefresh) { //大家都在买
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
        String json = "{\"fresh\":{\"communityID\":" + communityID + "},\"controllerName\":\"Fresh\"," +
                "\"actionName\":\"StructureQuery\",\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\",\"userid\":\"" + userID + "\"}";

//        String json = "{\"fresh\":{},\"controllerName\":\"Fresh\"," +
//                "\"actionName\":\"StructureQuery\",\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\",\"userid\":\"" + userID + "\"}";
        Log.i("resultString", "大家都在买json------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/StructureQuery")
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        base_pullToRefreshView.onHeaderRefreshComplete();
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "------------");
                        Log.i("resultString", "大家买===" + s);
                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("list_Fresh") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> list_Fresh = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("list_Fresh");
//                            //分页加载数据----------------------------------------------------
//                            if (goodslist == null) {
//                                goodslist = list_Fresh;
//                            } else {
//                                if (isrefresh) {
//                                    if (goodslist != null) {
//                                        goodslist = null;
//                                        goodslist = list_Fresh;
//                                    }
//                                }
//                                if (goodslist.size() != 0
//                                        && list_Fresh.get(list_Fresh.size() - 1).get("freshID")
//                                        .equals(goodslist.get(goodslist.size() - 1).get("freshID"))) {
//
//                                } else {
//                                    for (int i = 0; i < list_Fresh.size(); i++) {
//                                        goodslist.add(list_Fresh.get(i));
//                                    }
//                                }
//                            }
//                            //---------------------------------------------------------------
//                            if (goodslist != null && goodslist.size() != 0) {
//                                if (fruitListAdapter == null || isrefresh) {
//                                    have_GoodsList = true;
//                                    fruitListAdapter = new FruitListAdapter(context, goodslist);
//                                    base_listView.setAdapter(fruitListAdapter);
//                                } else {
//                                    have_GoodsList = true;
//                                    base_listView.requestLayout();
//                                    fruitListAdapter.notifyDataSetChanged();
//                                }
//                            } else {//没有数据
//                                noGoods.setVisibility(View.VISIBLE);
//                                base_listView.setAdapter(null);
//                                have_GoodsList = false;
//                            }
//                        } else {
//                            if (goodslist != null && goodslist.size() > 0) { //分页加载无数据
//
//                            } else { //加载无数据
//                                base_listView.setAdapter(null);
//                            }
//                            have_GoodsList = false;
//                            noGoods.setVisibility(View.VISIBLE);
//                            loading_lay.setVisibility(View.GONE);
//                        }
                        try {
                            JSONObject jo = new JSONObject(s);
//                            String statuscode = jo.getString("statuscode");
//                            String statusmessage = jo.getString("statusmessage");
                            FreashInfo finfo = new Gson().fromJson(jo.toString(), FreashInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                            if (finfo != null && finfo.getList_Fresh() != null) {
                                List<FreashInfo.ListFreshBean> list_Fresh = finfo.getList_Fresh();
                                //分页加载数据----------------------------------------------------
                                if (goodslist == null) {
                                    goodslist = list_Fresh;
                                } else {
                                    if (isrefresh) {
                                        if (goodslist != null) {
                                            goodslist = null;
                                            goodslist = list_Fresh;
                                        }
                                    }
                                    if (goodslist.size() != 0
                                            && list_Fresh.get(list_Fresh.size() - 1).getFreshID() ==
                                            goodslist.get(goodslist.size() - 1).getFreshID()) {
                                        have_GoodsList = false;
                                    } else {
                                        for (int i = 0; i < list_Fresh.size(); i++) {
                                            goodslist.add(list_Fresh.get(i));
                                        }
                                        have_GoodsList = true;
                                    }
                                }
                                //---------------------------------------------------------------
                                if (goodslist != null && goodslist.size() != 0) {
                                    if (fruitListAdapter == null || isrefresh) {
                                        have_GoodsList = true;
                                        fruitListAdapter = new FruitListAdapter(context, goodslist);
                                        base_listView.setAdapter(fruitListAdapter);
                                        noGoods.setVisibility(View.GONE);
                                    } else if (have_GoodsList) {
                                        base_listView.requestLayout();
                                        fruitListAdapter.notifyDataSetChanged();
                                        noGoods.setVisibility(View.GONE);
                                    } else {
                                        noGoods.setVisibility(View.VISIBLE);
                                    }
                                } else {//没有数据
                                    noGoods.setVisibility(View.VISIBLE);
                                    base_listView.setAdapter(null);
                                    have_GoodsList = false;
                                }
                            } else {
                                if (goodslist != null && goodslist.size() > 0 && !isrefresh) { //分页加载无数据

                                } else { //加载无数据
                                    base_listView.setAdapter(null);
                                }
                                have_GoodsList = false;
                                noGoods.setVisibility(View.VISIBLE);
                                loading_lay.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        base_pullToRefreshView.onHeaderRefreshComplete();

                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(context, getResources().getString(R.string.failed_to_connect_to_server));
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
//                        progressDialog = ProgressDialog.show(context, "", "加载..", true);
//                        progressDialog.setCanceledOnTouchOutside(true);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        getGoodsListStart = false;
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
                    }

                });
    }

    private void showlist(ArrayList<LinkedTreeMap<String, Object>> list) {
        lvdatalist = new ArrayList<>();
        gvdatalist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                LinkedTreeMap<String, Object> map = list.get(i);
                if (map != null && map.get("featuredTypeID") != null
                        && Double.valueOf(map.get("featuredTypeID").toString()).intValue() == 1) {
                    //每日抢鲜
                    lvdatalist.add(map);
                }
                if (map != null && map.get("featuredTypeID") != null
                        && Double.valueOf(map.get("featuredTypeID").toString()).intValue() == 2) {
                    //品质首选
                    gvdatalist.add(map);
                }
            }
        }
        Log.i("resultString", "品质首选----" + gvdatalist);
        initViewType();
        initViewType2();
    }

    private void initViewType() { //每日抢鲜
        if (lvdatalist != null && lvdatalist.size() != 0) {
            final int PageCount = (int) Math.ceil(lvdatalist.size() / APP_PAGE_SIZE);
            map = new HashMap<>();
            for (int i = 0; i < PageCount; i++) {
                GridView appPage = new GridView(context);
                final EveryDayFirstGoodsAdapter adapter = new EveryDayFirstGoodsAdapter(context, lvdatalist, i);
                appPage.setAdapter(adapter);
                appPage.setNumColumns(3);
                appPage.setOnItemClickListener(adapter);
                map.put(i, appPage);
            }

            type_viewPager = (ViewPager) everyday_first.findViewById(R.id.adViewPager2);
            type_adapter = new MyViewPagerAdapter(context, map);
            type_viewPager.setAdapter(type_adapter);
            type_viewPager.setOnPageChangeListener(new MyListener());
            ViewGroup group = (ViewGroup) everyday_first.findViewById(R.id.viewGroup2);
            pageControl = new PageControl(context, (LinearLayout) group, PageCount);
        } else {//没有数据

        }
    }

    private void initViewType2() {//品质首选  4个商品
        if (gvdatalist != null && gvdatalist.size() != 0) {
            if (qualityFirstAdapter == null) {
                qualityFirstAdapter = new QualityFirstAdapter(context, gvdatalist);
                gridView.setAdapter(qualityFirstAdapter);
            } else {
                gridView.requestLayout();
                qualityFirstAdapter.notifyDataSetChanged();
            }
        } else {//没有数据
            gridView.setAdapter(null);
        }

    }

    /**
     * 商品类型模块
     */
    class MyListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            pageControl.selectPage(arg0);
        }
    }

    class PageControl {
        private LinearLayout layout2;
        private ImageView[] imageViews2;
        private ImageView imageView2;
        private int pageSize2;
        private int selectedImage = R.drawable.point_focused;
        //        private int unselectedImage = R.drawable.home_point_unfocused;
        private int unselectedImage = R.drawable.point_unfocused;
        private int currentPage = 0;
        private Context mContext;

        public PageControl(Context context, LinearLayout layout, int pageSize) {
            this.mContext = context;
            this.pageSize2 = pageSize;
            this.layout2 = layout;

            initDots();
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        void initDots() {
            imageViews2 = new ImageView[pageSize2];
            for (int i = 0; i < pageSize2; i++) {
                imageView2 = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
                layoutParams.setMargins(10, 0, 10, 0);
                imageView2.setLayoutParams(layoutParams);
                imageViews2[i] = imageView2;
                if (i == 0) {
                    // 默认进入程序后第一张图片被选中;
                    imageViews2[i].setBackgroundResource(R.drawable.point_focused);
                } else {
                    imageViews2[i].setBackgroundResource(R.drawable.point_unfocused);
//                    imageViews2[i].setBackgroundResource(R.drawable.home_point_unfocused);
                }
                layout2.addView(imageViews2[i]);
            }
        }

        boolean isFirst() {
            return this.currentPage == 0;
        }

        boolean isLast() {
            return this.currentPage == pageSize2;
        }

        public void selectPage(int current) {
            for (int i = 0; i < imageViews2.length; i++) {
                imageViews2[current].setBackgroundResource(R.drawable.point_focused);
                if (current != i) {
                    imageViews2[i].setBackgroundResource(R.drawable.point_unfocused);
//                    imageViews2[i].setBackgroundResource(R.drawable.home_point_unfocused);
                }
            }
        }

        void turnToNextPage() {
            if (!isLast()) {
                currentPage++;
                selectPage(currentPage);
            }
        }

        void turnToPrePage() {
            if (!isFirst()) {
                currentPage--;
                selectPage(currentPage);
            }
        }
    }

}
