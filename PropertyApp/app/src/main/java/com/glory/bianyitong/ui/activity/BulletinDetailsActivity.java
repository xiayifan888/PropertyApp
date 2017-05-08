package com.glory.bianyitong.ui.activity;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/28.
 * 公告详情
 */
public class BulletinDetailsActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_ans_title)
    TextView tv_ans_title;

    @BindView(R.id.tv_ans_content)
    TextView tv_ans_content;

    @BindView(R.id.tv_ans_auth)
    TextView tv_ans_auth;

    @BindView(R.id.tv_ans_time)
    TextView tv_ans_time;
    private int PushID;
    private String bulletinTittle;
    private String bulletinContent;
    private String communityName;
    private String bulletinDatetime;

    @Override
    protected int getContentId() {
        return R.layout.ac_bulletin_details;
    }

    @Override
    protected void init() {
        super.init();
        initview();
        PushID = getIntent().getIntExtra("PushID", 0);
        bulletinTittle = getIntent().getStringExtra("bulletinTittle");
        bulletinContent = getIntent().getStringExtra("bulletinContent");
        communityName = getIntent().getStringExtra("communityName");
        bulletinDatetime = getIntent().getStringExtra("bulletinDatetime");
        if (bulletinTittle == null) {
            bulletinTittle = "";
        }
        if (bulletinContent == null) {
            bulletinContent = "";
        }
        if (communityName == null) {
            communityName = "";
        }
        if (bulletinDatetime == null) {
            bulletinDatetime = "";
        }
        tv_ans_title.setText(bulletinTittle);
        tv_ans_content.setText(bulletinContent);
        tv_ans_auth.setText(communityName);
        tv_ans_time.setText(bulletinDatetime);

        if (PushID != 0) {
            request(PushID);
        }
    }

    private void request(int bulletinID) {
        String userID = RequestUtil.getuserid();

        String json = "{\"communityBulletin\": {\"bulletinID\":" + bulletinID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"CommunityBulletin\",\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json----------" + json);
        String url = HttpURL.HTTP_LOGIN;
//        OkGo.post(HttpURL.HTTP_LOGIN)
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listCommunityBulletin") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBulletin");
//                            if (list != null && list.size() != 0) {
//                                if (list.get(0) != null && list.get(0).get("bulletinTittle") != null) {
//                                    tv_ans_title.setText(list.get(0).get("bulletinTittle").toString()); //管家姓名
//                                }
//
//                                if (list.get(0) != null && list.get(0).get("bulletinContent") != null) {
//                                    tv_ans_content.setText(list.get(0).get("bulletinContent").toString());
//                                }
//
//                                if (list.get(0) != null && list.get(0).get("communityName") != null) {
//                                    tv_ans_auth.setText(list.get(0).get("communityName").toString());
//                                }
//
//                                if (list.get(0) != null && list.get(0).get("bulletinDatetime") != null) {
//                                    tv_ans_time.setText(list.get(0).get("bulletinDatetime").toString());
//                                }
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ServiceDialog.showRequestFailed();
//                    }
//
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("listCommunityBulletin") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBulletin");
                    if (list != null && list.size() != 0) {
                        if (list.get(0) != null && list.get(0).get("bulletinTittle") != null) {
                            tv_ans_title.setText(list.get(0).get("bulletinTittle").toString()); //管家姓名
                        }
                        if (list.get(0) != null && list.get(0).get("bulletinContent") != null) {
                            tv_ans_content.setText(list.get(0).get("bulletinContent").toString());
                        }
                        if (list.get(0) != null && list.get(0).get("communityName") != null) {
                            tv_ans_auth.setText(list.get(0).get("communityName").toString());
                        }
                        if (list.get(0) != null && list.get(0).get("bulletinDatetime") != null) {
                            tv_ans_time.setText(list.get(0).get("bulletinDatetime").toString());
                        }
                    }
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(url,json);
    }

    private void initview() {
        inintTitle(getString(R.string.notice_details), true, "");//公告详情
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                BulletinDetailsActivity.this.finish();
            }
        });
    }

}
