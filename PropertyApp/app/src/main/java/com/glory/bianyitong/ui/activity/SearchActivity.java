package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.adapter.FruitListAdapter;
import com.glory.bianyitong.ui.adapter.GridSearchTagAdapter;
import com.glory.bianyitong.ui.adapter.GridTypeAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.SharePreToolsKits;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
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
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 搜索页
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.tv_search_cancel)
    TextView tv_search_cancel;
    @BindView(R.id.clean_word)
    RelativeLayout clean_word;
    @BindView(R.id.tv_search_txt)
    EditText tv_search_txt;

    @BindView(R.id.lay_search_last) //最近搜索
            LinearLayout lay_search_last;
    @BindView(R.id.lay_search_last_delete)
    RelativeLayout lay_search_last_delete;
    @BindView(R.id.gv_search_last)
    GridView gv_search_last;
    @BindView(R.id.lay_search_hot)//热门搜索
            LinearLayout lay_search_hot;
    @BindView(R.id.gv_search_hot)
    GridView gv_search_hot;
    @BindView(R.id.lay_search_nothing) //无结果
            LinearLayout lay_search_nothing;
    @BindView(R.id.search_listView)
    ListView search_listView;
    private GridSearchTagAdapter searchlistAdapter;
    private ArrayList<LinkedTreeMap<String, Object>> search_tag_list;//最近搜索 数据
    private GridSearchTagAdapter gridSearchTagAdapter; //标签适配器
    private ArrayList<LinkedTreeMap<String, Object>> taglist_hot;
    private FruitListAdapter fruitListAdapter;
//    private ArrayList<LinkedTreeMap<String, Object>> goodslist;//商品数据
    private List<FreashInfo.ListFreshBean> goodslist;//商品数据
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;

    private int index_page = 0;
    //    private String tag_str = "";
//    private int type_search = 0;
    private String freshName = "";

    private Handler cc_handler;
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                //这里写发送信息的方法
                String tag_name = tv_search_txt.getText().toString().trim();
                if (tag_name.equals("")) {
                    ToastUtils.showToast(SearchActivity.this, getString(R.string.search_can_not_be_empty));//搜索不能为空
                } else {
                    Message msg = new Message();
                    msg.obj = tag_name;
                    msg.what = 0;
                    cc_handler.sendMessage(msg);
                }
                return true;
            }
            return false;
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        super.init();
        initview();
        search_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                            request2(index_page, false, freshName); //, tag_str, type_search
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) { }
        });
        cc_handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: // 输入框
                        index_page = 0;
                        index_page++;
                        freshName = msg.obj.toString();
                        addSearch(freshName, "input");
                        if (!getGoodsListStart) {
//                            progressDialog = ProgressDialog.show(SearchActivity.this, "", "", true);
//                            progressDialog.setCanceledOnTouchOutside(true);
                            lay_search_last.setVisibility(View.GONE);
                            lay_search_hot.setVisibility(View.GONE);
                            tv_search_txt.setText(freshName);
                            tv_search_txt.setSelection(freshName.length());
                            request2(index_page, true, freshName);//, tag_str, 0
                        }
                        break;
                    case 1: //热门搜索标签
                        index_page = 0;
                        index_page++;
//                        tag_str = msg.obj.toString();
//                        addSearch(tag_str, "hot");
                        freshName = msg.obj.toString();
                        addSearch(freshName, "input");
                        if (!getGoodsListStart) {
                            progressDialog = ProgressDialog.show(SearchActivity.this, "", "", true);
                            progressDialog.setCanceledOnTouchOutside(true);

                            lay_search_last.setVisibility(View.GONE);
                            lay_search_hot.setVisibility(View.GONE);
                            tv_search_txt.setText(freshName);
                            tv_search_txt.setSelection(freshName.length());
//                            request2(index_page, true, tag_str, 1, freshName);, tag_str, 1
                            request2(index_page, true, freshName);
                        }
                        break;
                    case 2: // 最近搜索
                        index_page = 0;
                        index_page++;
                        String type = search_tag_list.get(msg.arg1).get("type").toString();
                        if (type.equals("input")) { //搜索
                            freshName = msg.obj.toString();
                            if (!getGoodsListStart) {
                                progressDialog = ProgressDialog.show(SearchActivity.this, "", "", true);
                                progressDialog.setCanceledOnTouchOutside(true);

                                lay_search_last.setVisibility(View.GONE);
                                lay_search_hot.setVisibility(View.GONE);
                                tv_search_txt.setText(freshName);
                                tv_search_txt.setSelection(freshName.length());
//                                request2(index_page, true, tag_str, 0, freshName); , tag_str, 0
                                request2(index_page, true, freshName);
                            }
                        } else if (type.equals("hot")) {//热门
//                            tag_str = msg.obj.toString();
                            freshName = msg.obj.toString();
                            if (!getGoodsListStart) {
                                lay_search_last.setVisibility(View.GONE);
                                lay_search_hot.setVisibility(View.GONE);
//                                tv_search_txt.setText(tag_str);
//                                request2(index_page, true, tag_str, 1, freshName);
                                tv_search_txt.setText(freshName);
                                tv_search_txt.setSelection(freshName.length());
                                request2(index_page, true, freshName);
                            }
                        }

                        break;
                }
            }
        };
        initSearch();
        request_hottag();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initSearch() {
        // 搜索记录更新
        String json = SharePreToolsKits.fetchJsonDataString(SearchActivity.this, Constant.SEARCH);

        if (json != null && !json.isEmpty() && json.length() != 0) {
            search_tag_list = JsonHelper.fromJson(json, new TypeToken<ArrayList<LinkedTreeMap<String, Object>>>() {});

            if (search_tag_list != null && search_tag_list.size() != 0) {
                searchlistAdapter = new GridSearchTagAdapter(SearchActivity.this, search_tag_list, cc_handler, 2);
                gv_search_last.setAdapter(searchlistAdapter);
                lay_search_last.setVisibility(View.VISIBLE);
            } else {
                lay_search_last.setVisibility(View.GONE);
            }
        } else {
            lay_search_last.setVisibility(View.GONE);
        }
    }

    private void request2(int page, final boolean isrefresh, String name) { //1 热门标签搜索  0 输入框搜索 , String tag, int type
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
        String url = "";
        String json = "";
//        if (type == 0) {
//            json = "{\"fresh\": {\"freshName\":\"" + name + "\",\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\"," +
//                    "\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": " + page + "," +
//                    "\"pagerownum\": \"10\",\"controllerName\": \"FreshQuery\",\"actionName\": \"FuzzyQuery\"}";
//            url = HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/FuzzyQuery";
//        } else if (type == 1) {
//            json = "{\"fresh\": {\"Tag\":\"" + tag + "\"},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\"," +
//                    "\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\":" + page + ",\"pagerownum\": \"10\"," +
//                    "\"controllerName\": \"FreshQuery\",\"actionName\": \"FreshTagQuery\"}";
//            url = HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/FreshTagQuery";
//        }
        json = "{\"fresh\": {\"freshName\":\"" + name + "\",\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\"," +
                "\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": " + page + "," +
                "\"pagerownum\": \"10\",\"controllerName\": \"FreshQuery\",\"actionName\": \"FuzzyQuery\"}";
        url = HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/FuzzyQuery";
        Log.i("resultString", "热门标签搜索json------" + json);
        OkGo.post(url)
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
//                                    fruitListAdapter = new FruitListAdapter(SearchActivity.this, goodslist);
//                                    search_listView.setAdapter(fruitListAdapter);
//                                } else {
//                                    have_GoodsList = true;
//                                    search_listView.requestLayout();
//                                    fruitListAdapter.notifyDataSetChanged();
//                                }
//                            } else {//没有数据
//                                noGoods.setVisibility(View.GONE);
//                                search_listView.setAdapter(null);
//                                have_GoodsList = false;
//                                lay_search_nothing.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            have_GoodsList = false;
//                            loading_lay.setVisibility(View.GONE);
//                            if (goodslist != null && goodslist.size() > 0) { //分页加载无数据
//                                noGoods.setVisibility(View.VISIBLE);
//                            } else { //加载无数据
//                                search_listView.setVisibility(View.GONE);
//                                lay_search_nothing.setVisibility(View.VISIBLE);
//                            }
//
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

                                    } else {
                                        for (int i = 0; i < list_Fresh.size(); i++) {
                                            goodslist.add(list_Fresh.get(i));
                                        }
                                    }
                                }
                                //---------------------------------------------------------------
                                if (goodslist != null && goodslist.size() != 0) {
                                    if (fruitListAdapter == null || isrefresh) {
                                        have_GoodsList = true;
                                        fruitListAdapter = new FruitListAdapter(SearchActivity.this, goodslist);
                                        search_listView.setAdapter(fruitListAdapter);
                                    } else {
                                        have_GoodsList = true;
                                        search_listView.requestLayout();
                                        fruitListAdapter.notifyDataSetChanged();
                                    }
                                } else {//没有数据
                                    noGoods.setVisibility(View.GONE);
                                    search_listView.setAdapter(null);
                                    have_GoodsList = false;
                                    lay_search_nothing.setVisibility(View.VISIBLE);
                                }
                            } else {
                                have_GoodsList = false;
                                loading_lay.setVisibility(View.GONE);
                                if (goodslist != null && goodslist.size() > 0) { //分页加载无数据
                                    noGoods.setVisibility(View.VISIBLE);
                                } else { //加载无数据
                                    search_listView.setVisibility(View.GONE);
                                    lay_search_nothing.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        loading_lay.setVisibility(View.GONE);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(SearchActivity.this, "请求失败...");
                        ServiceDialog.showRequestFailed();
                    }
                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }
                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        getGoodsListStart = false;
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                    }
                });
    }

    private void request_hottag() { //获取热门搜索
        String userID = RequestUtil.getuserid();
        int communityID = RequestUtil.getcommunityid();
        String json = "{\"fresh\": {\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"1\"," +
                "\"pagerownum\": \"10\",\"controllerName\": \"FreshQuery\",\"actionName\": \"QueryTag\"}";

        Log.i("resultString", "json------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/FreshQuery/QueryTag")
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                        });
                        if (hashMap2 != null && hashMap2.get("tags") != null) {
                            ArrayList<String> list = (ArrayList<String>) hashMap2.get("tags"); //"list_Tag":["海鲜","精选","蔬菜","水果","肉类","水产"]
                            if (list != null && list.size() != 0) {
                                taglist_hot = new ArrayList<>();
                                for (int i = 0; i < list.size(); i++) {
                                    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                                    map.put("freshTypeName", list.get(i));
                                    map.put("type", "hot");
                                    taglist_hot.add(map);
                                }
                                if (taglist_hot != null && taglist_hot.size() > 0) {
                                    gridSearchTagAdapter = new GridSearchTagAdapter(SearchActivity.this, taglist_hot, cc_handler, 1);
                                    gv_search_hot.setAdapter(gridSearchTagAdapter);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(SearchActivity.this, "请求失败...");
                        ServiceDialog.showRequestFailed();
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }
                });
    }

    private void addSearch(String search_wrod, String type) { //搜索关键字 类型  输入  热门
        if (search_tag_list != null) {
            int size = search_tag_list.size();
            ArrayList<LinkedTreeMap<String, Object>> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {  //添加  然后 排序
                if (search_tag_list.get(i).get("freshTypeName").equals(search_wrod) &&
                        search_tag_list.get(i).get("type").equals(type)) {
                    break;
                }
                if (i == size - 1) {
                    for (int j = 0; j < size + 1; j++) {
                        if (j == 0) {
                            LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                            map.put("freshTypeName", search_wrod);
                            map.put("type", type);
                            list.add(map);
                        } else {
                            list.add(search_tag_list.get(j - 1));
                        }
                    }
                    search_tag_list = list;
                    if (searchlistAdapter == null) {
                        searchlistAdapter = new GridSearchTagAdapter(SearchActivity.this, search_tag_list, cc_handler, 2);
                        gv_search_last.setAdapter(searchlistAdapter);
                    } else {
//                        searchlistAdapter.notifyDataSetChanged(); //刷新最近搜索
//                        gv_search_last.requestLayout();
                        searchlistAdapter = null;
                        searchlistAdapter = new GridSearchTagAdapter(SearchActivity.this, search_tag_list, cc_handler, 2);
                        gv_search_last.setAdapter(searchlistAdapter);
                    }
                    String json = JsonHelper.toJson(search_tag_list);
                    SharePreToolsKits.putJsonDataString(SearchActivity.this, Constant.SEARCH, json);//缓存最近搜索数据
                }
            }
        } else {
            search_tag_list = new ArrayList<>();
            LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
            map.put("freshTypeName", search_wrod);
            map.put("type", type);
            search_tag_list.add(map);
            if (searchlistAdapter == null) {
                searchlistAdapter = new GridSearchTagAdapter(SearchActivity.this, search_tag_list, cc_handler, 2);
                gv_search_last.setAdapter(searchlistAdapter);
            } else {
//                searchlistAdapter.notifyDataSetChanged(); //刷新最近搜索
//                gv_search_last.requestLayout();
                searchlistAdapter = null;
                searchlistAdapter = new GridSearchTagAdapter(SearchActivity.this, search_tag_list, cc_handler, 2);
                gv_search_last.setAdapter(searchlistAdapter);
            }
            String json = JsonHelper.toJson(search_tag_list);
            SharePreToolsKits.putJsonDataString(SearchActivity.this, Constant.SEARCH, json);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                SearchActivity.this.finish();
                break;
            case R.id.clean_word:
                tv_search_txt.setText("");
                if (search_tag_list != null && search_tag_list.size() != 0) {
                    lay_search_last.setVisibility(View.VISIBLE);
                } else {
                    lay_search_last.setVisibility(View.GONE);
                }
                lay_search_hot.setVisibility(View.VISIBLE);
                search_listView.setAdapter(null);
                break;
            case R.id.lay_search_last_delete:
                lay_search_last.setVisibility(View.GONE);
                SharePreToolsKits.putJsonDataString(SearchActivity.this, Constant.SEARCH, "");//缓存最近搜索数据
                break;
        }
    }

    private void initview() {
        tv_search_txt.setOnKeyListener(onKeyListener);
        tv_search_cancel.setOnClickListener(this);
        clean_word.setOnClickListener(this);
        lay_search_last_delete.setOnClickListener(this);
        tv_search_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                if (arg0.length() > 0) {
                    clean_word.setVisibility(View.VISIBLE);
                } else {
                    clean_word.setVisibility(View.GONE);
                }
            }
        });
        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);
        search_listView.addFooterView(view_loading);

    }
}
