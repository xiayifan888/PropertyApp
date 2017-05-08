package com.glory.bianyitong.ui.fragment.fresh;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.bean.HousekeeperInfo;
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
import com.glory.bianyitong.ui.adapter.FruitListAdapter;
import com.glory.bianyitong.ui.adapter.GridTypeAdapter;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 水果
 */
public class FruitFragment extends BaseFragment {
    Context context;
    @BindView(R.id.base_listView)
    ListView base_listView;
    @BindView(R.id.base_pullToRefreshView)
    NewPullToRefreshView base_pullToRefreshView;
    @BindView(R.id.lay_no_network)
    LinearLayout lay_no_network;
    FruitListAdapter fruitListAdapter;
    private View view_fruit;
    private LinearLayout lay_tag_gv;
    private GridView gvType; //标签GridView
    private GridTypeAdapter gridTypeAdapter; //标签适配器
    private ArrayList<LinkedTreeMap<String, Object>> taglist; //标签数据
//    private ArrayList<LinkedTreeMap<String, Object>> goodslist;//商品数据
    private List<FreashInfo.ListFreshBean> goodslist;//商品数据
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;
    private String freshTypeLeaf; //刷新用的 生鲜类别id
    private Handler cc_handler;
    private int index_page = 0;
    private int f_freshTypeLeaf = 0; //记录选择的类型
    private int f_freshTypeID = 0;
    private int gv_position = 0; //用于判断上一次传的值

    private String str_tag;
    private int flag;//当前fragment在fragment数组中的位置

    public static FruitFragment newInstance(String content, int flag) {
        Bundle bundle = new Bundle();

        bundle.putString("content", content);
        bundle.putInt("flag", flag);
        FruitFragment testFm = new FruitFragment();
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
        view_fruit = getActivity().getLayoutInflater().inflate(R.layout.fg_fruit, null);
        initview();
        if (taglist != null && taglist.size() != 0) {
            if (taglist.size() > 8) {
                gethight(8); //换算GridView高度
            } else {
                gethight(taglist.size());
            }
        } else {
            taglist = Database.alltaglist.get(flag);
            LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
            map.put("freshTypeID", 0);
            map.put("freshTypeName", getResources().getString(R.string.all));
            taglist.add(0, map);
            if (taglist.size() > 8) {
                LinkedTreeMap<String, Object> map2 = new LinkedTreeMap<>();
                map2.put("freshTypeID", -1);
                map2.put("freshTypeName", getResources().getString(R.string.more));
                taglist.add(map2);
                Collections.swap(taglist, taglist.size() - 1, 7); //默认收起
                gethight(8); //换算GridView高度
            } else {
                gethight(taglist.size());
            }
        }

//        Log.i("resultString", "taglist------" + taglist.size());
        cc_handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: //第一次
                        if (taglist != null && taglist.get(0) != null) {
                            gridTypeAdapter = null;
                            gridTypeAdapter = new GridTypeAdapter(context, taglist, cc_handler, 0); //做刷新标签背景颜色
                            gvType.setAdapter(gridTypeAdapter);
                            gv_position = 0;
                            if (taglist.get(1) != null && taglist.get(1).get("freshTypeLeaf") != null) {
                                freshTypeLeaf = taglist.get(1).get("freshTypeLeaf").toString();
                                f_freshTypeLeaf = Double.valueOf(freshTypeLeaf).intValue();
                            } else {
                                f_freshTypeLeaf = 0;
                            }
//                            progressDialog = ProgressDialog.show(context, "", "加载..", true);
//                            progressDialog.setCanceledOnTouchOutside(true);
                            index_page = 0;
                            index_page++;
                            f_freshTypeID = 0;
                            getcommodityLsist(index_page, f_freshTypeLeaf, true, 0); //大类别
                        }
                        break;
                    case 1: //选择类型
                        if (taglist != null && taglist.get(msg.arg1) != null) {
                            if (gv_position != msg.arg1) {
                                gridTypeAdapter = null;
                                gridTypeAdapter = new GridTypeAdapter(context, taglist, cc_handler, msg.arg1);
                                gvType.setAdapter(gridTypeAdapter);
                                gv_position = msg.arg1;
                                if (taglist.get(msg.arg1) != null && taglist.get(msg.arg1).get("freshTypeID") != null) {
                                    String freshTypeID = taglist.get(msg.arg1).get("freshTypeID").toString();
                                    f_freshTypeID = Double.valueOf(freshTypeID).intValue();
                                } else {
                                    f_freshTypeID = 0;
                                }
                                index_page = 0;
                                index_page++;
                                f_freshTypeLeaf = 0;
                                progressDialog = ProgressDialog.show(context, "", getResources().getString(R.string.load), true);//加载
                                progressDialog.setCanceledOnTouchOutside(true);
                                getcommodityLsist(index_page, 0, true, f_freshTypeID); //小类别
                            }
                        }
                        break;
                    case 2: //更多 收起
                        if (taglist != null && taglist.get(msg.arg1) != null && taglist.get(msg.arg1).get("freshTypeName") != null) {
                            if (taglist.get(msg.arg1).get("freshTypeName").equals(getResources().getString(R.string.more))) {
                                taglist.get(msg.arg1).put("freshTypeName", getResources().getString(R.string.put_away));//收起
                                Collections.swap(taglist, msg.arg1, taglist.size() - 1);
                                gethight(taglist.size());
                                gridTypeAdapter = null;
                                gridTypeAdapter = new GridTypeAdapter(context, taglist, cc_handler, taglist.size() - 1);
                                gvType.setAdapter(gridTypeAdapter);
                                gethight(taglist.size());
                            } else if (taglist.get(msg.arg1).get("freshTypeName").equals(getResources().getString(R.string.put_away))) {
                                taglist.get(msg.arg1).put("freshTypeName", getResources().getString(R.string.more));//更多
                                Collections.swap(taglist, taglist.size() - 1, 7);
                                gethight(8);
                                gridTypeAdapter = null;
                                gridTypeAdapter = new GridTypeAdapter(context, taglist, cc_handler, 7);
                                gvType.setAdapter(gridTypeAdapter);
                            }
                        }
                        break;
                }
            }
        };
        Log.i("resultString", "onViewCreated------");
        if(ActivityUtils.isNetworkAvailable()){
            base_pullToRefreshView.setVisibility(View.VISIBLE);
            lay_no_network.setVisibility(View.GONE);
            cc_handler.sendEmptyMessage(0); //默认 大分类
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

    private void initview() {
        lay_tag_gv = (LinearLayout) view_fruit.findViewById(R.id.lay_tag_gv);
        gvType = (GridView) view_fruit.findViewById(R.id.gv_type);

        view_loading = getActivity().getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        base_listView.addHeaderView(view_fruit);
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
                            noGoods.setVisibility(View.GONE);
                            index_page++;
                            getcommodityLsist(index_page, f_freshTypeLeaf, false, f_freshTypeID);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        base_pullToRefreshView.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(NewPullToRefreshView view) {
                if (goodslist != null && !getGoodsListStart) {
                    getGoodsListStart = true;
                    index_page = 0;//重置index_page
                    index_page++;
                    getcommodityLsist(index_page, f_freshTypeLeaf, true, f_freshTypeID);
                }
            }
        });

    }

    private void gethight(int size) {
        //计算 gv高度
        int height = 0;
        if (size <= 4) {//小于等于4
            height = 36;  //控价高36 上下边距 10
        } else {
            if (size % 4 == 0) { //整除 8 12
                height = ((size / 4) * 36) + ((size / 4 - 1) * 10);
            } else { //5 ,6 ,7 ,9
                height = ((size / 4) + 1) * 36 + (size / 4 * 10);
            }
        }
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) gvType.getLayoutParams(); // 取控件mGrid当前的布局参数
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int h_screen = dm.widthPixels;
        if (h_screen == 1080) {   // 当控件的高强制设成 象素
            linearParams.height = height * 3;
        } else if (h_screen == 720) {
            linearParams.height = height * 2;
        } else if (h_screen == 1440) {
            linearParams.height = height * 4;
        }
        gvType.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件myGrid
//        Log.i("resultString", "size------" + size);
//        Log.i("resultString", "height------" + height);
        lay_tag_gv.setMinimumHeight(height);
    }

    private void getcommodityLsist(int page, int freshTypeLeaf, final boolean isrefresh, int freshTypeID) {
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
        String json = "";
        if (freshTypeID == 0) {
            json = "{\"fresh\":{\"communityID\":" + communityID + ",\"freshTypeLeaf\":\"" + freshTypeLeaf + "\"" +
                    "},\"controllerName\":\"Fresh\",\"userid\":\"" + userID + "\"," +
                    "\"actionName\":\"StructureQuery\",\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\"}";

//            json = "{\"fresh\":{\"freshTypeLeaf\":\"" + freshTypeLeaf + "\"" +
//                    "},\"controllerName\":\"Fresh\",\"userid\":\"" + userID + "\"," +
//                    "\"actionName\":\"StructureQuery\",\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\"}";
        } else {
            json = "{\"fresh\":{\"communityID\":" + communityID + ",\"freshTypeID\":\"" + freshTypeID + "\"}," +
                    "\"controllerName\":\"Fresh\",\"userid\":\"" + userID + "\",\"actionName\":\"StructureQuery\"," +
                    "\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\"}";

//            json = "{\"fresh\":{\"freshTypeID\":\"" + freshTypeID + "\"}," +
//                    "\"controllerName\":\"Fresh\",\"userid\":\"" + userID + "\",\"actionName\":\"StructureQuery\"," +
//                    "\"nowpagenum\":\"" + page + "\",\"pagerownum\":\"10\"}";
        }
        Log.i("resultString", "水果json------" + json);

        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/StructureQuery")
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
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
//                                    have_GoodsList = false;
//                                } else {
//                                    for (int i = 0; i < list_Fresh.size(); i++) {
//                                        goodslist.add(list_Fresh.get(i));
//                                    }
//                                    have_GoodsList = true;
//                                }
//                            }
//                            //---------------------------------------------------------------
//                            if (goodslist != null && goodslist.size() != 0) {
//                                if (fruitListAdapter == null || isrefresh) {
//                                    have_GoodsList = true;
//                                    fruitListAdapter = new FruitListAdapter(context, goodslist);
//                                    base_listView.setAdapter(fruitListAdapter);
//                                    noGoods.setVisibility(View.GONE);
//                                } else if (have_GoodsList) {
//                                    base_listView.requestLayout();
//                                    fruitListAdapter.notifyDataSetChanged();
//                                    noGoods.setVisibility(View.GONE);
//                                } else {
//                                    noGoods.setVisibility(View.VISIBLE);
//                                }
//                            } else {//没有数据
//                                noGoods.setVisibility(View.VISIBLE);
//                                base_listView.setAdapter(null);
//                                have_GoodsList = false;
//                            }
//                        } else {
//                            if (goodslist != null && goodslist.size() > 0 && !isrefresh) { //分页加载无数据
//
//                            } else { //加载无数据
//                                base_listView.setAdapter(null);
//                            }
//                            have_GoodsList = false;
//                            noGoods.setVisibility(View.VISIBLE);
//                            loading_lay.setVisibility(View.GONE);
//                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        getGoodsListStart = false;
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(context, getResources().getString(R.string.failed_to_connect_to_server)); //未能连接到服务器
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
                        base_pullToRefreshView.onHeaderRefreshComplete();
                        getGoodsListStart = false;
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
