package com.glory.bianyitong.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.AdvertisingInfo;
import com.glory.bianyitong.bean.HousekeeperInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.dialog.CallPhoneDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/11.
 * 物业管家
 */
public class StewardActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.steward_name) //管家名称
            TextView steward_name;
    @BindView(R.id.steward_phone) //电话
            TextView steward_phone;
    @BindView(R.id.steward_cert) //认证
            TextView steward_cert;
    @BindView(R.id.steward_call) //一键呼叫
            TextView steward_call;
    @BindView(R.id.steward_pic) //管家头像
            ImageView steward_pic;
    ArrayList<LinkedTreeMap<String, Object>> housekeeper_list;//物业管家
    HousekeeperInfo.ListHousekeeperBean housekeeper;
    private String phone_str = "";//要拨打的电话

    @Override
    protected int getContentId() {
        return R.layout.ac_steward;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getResources().getString(R.string.property_steward), true, "");//物业管家
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                StewardActivity.this.finish();
            }
        });
        steward_call.setOnClickListener(new View.OnClickListener() { //电话图标
            @Override
            public void onClick(View view) {
                if (phone_str != null && !phone_str.equals("") && phone_str.length() > 0) {
                    startLocation();
                }
            }
        });
        steward_phone.setOnClickListener(new View.OnClickListener() { //电话
            @Override
            public void onClick(View view) {
                if (phone_str != null && !phone_str.equals("") && phone_str.length() > 0) {
                    startLocation();
                }
            }
        });
        if (Database.my_community != null && Database.my_community_List != null) {
        } else {
            Intent intent = new Intent(StewardActivity.this, AddAreaCityActivity.class); //
            intent.putExtra("from", "index");
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        request();
    }

    private void request() {
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
//        int unitID = 0;
//        if (Database.my_community != null && Database.my_community.get("unitID") != null) {
//            unitID = Double.valueOf(Database.my_community.get("unitID").toString()).intValue();
//        }
        int unitID = 0;
        if (Database.my_community != null && Database.my_community.getUnitID() != 0) {
            unitID = Database.my_community.getUnitID();
        }
//        int buildingID = 0;
//        if (Database.my_community != null && Database.my_community.get("buildingID") != null) {
//            buildingID = Double.valueOf(Database.my_community.get("buildingID").toString()).intValue();
//        }
        int buildingID = 0;
        if (Database.my_community != null && Database.my_community.getBuildingID() != 0) {
            buildingID = Database.my_community.getBuildingID();
        }
//        String json = "{\"housekeeper\": {\"houseKepperID\":1,\"communityID\":" + communityID + ",\"unitID\":" + unitID + "," +
//                "\"buildingID\":" + buildingID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
//                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\"," +
//                "\"pagerownum\": \"\",\"controllerName\": \"Housekeeper\",\"actionName\": \"StructureQuery\"}";
        String json = "{\"housekeeper\": {\"communityID\":" + communityID + ",\"unitID\":" + unitID + "," +
                "\"buildingID\":" + buildingID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\"," +
                "\"pagerownum\": \"\",\"controllerName\": \"Housekeeper\",\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json----------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN)
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
                        try {
                            JSONObject jo = new JSONObject(s);
//                            String statuscode = jo.getString("statuscode");
//                            String statusmessage = jo.getString("statusmessage");
                            HousekeeperInfo hinfo = new Gson().fromJson(jo.toString(), HousekeeperInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                            if (hinfo != null && hinfo.getListHousekeeper() != null) {
                                List<HousekeeperInfo.ListHousekeeperBean> hlist = hinfo.getListHousekeeper();
                                if (hlist.get(0) != null) {
                                    housekeeper = hlist.get(0);
                                    if (housekeeper != null && housekeeper.getHouseKeeperName() != null) {
                                        steward_name.setText(housekeeper.getHouseKeeperName());
                                    }
                                    if (housekeeper != null && housekeeper.getWorkPhoneNum() != null) {
                                        steward_phone.setText(housekeeper.getWorkPhoneNum());
                                        phone_str = housekeeper.getWorkPhoneNum();
                                    }
                                    String communityName = "";
                                    String unitName = "";
                                    String buildingName = "";
                                    if (housekeeper != null && housekeeper.getCommunityName() != null) {
                                        communityName = housekeeper.getCommunityName(); //小区名称
                                    }
                                    if (housekeeper != null && housekeeper.getUnitName() != null) {
                                        unitName = housekeeper.getUnitName(); //单元名称
                                    }
                                    if (housekeeper != null && housekeeper.getBuildingName() != null) {
                                        buildingName = housekeeper.getBuildingName(); //楼栋名称
                                    }
                                    steward_cert.setText(getResources().getString(R.string.certification)+":" + communityName + unitName + buildingName);
                                    if (housekeeper != null && housekeeper.getHouseKepperPhoto() != null) {
                                        ServiceDialog.setPicture(housekeeper.getHouseKepperPhoto(), steward_pic, null);//管家 头像
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(StewardActivity.this, getResources().getString(R.string.failed_to_connect_to_server));//未能连接到服务器
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
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }


    // android 6.0d 权限管理变了，需要在运行时手动申请，参考如下代码
    private void startLocation() {
        Log.i("resultString", "----------msg");
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(StewardActivity.this, Manifest.permission.CALL_PHONE);//拨打电话权限
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            } else {
                Log.i("resultString", "----------msg");
                CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                calldialog.show();
            }
        } else {
            Log.i("resultString", "----------msg");
            CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
            calldialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                    calldialog.show();
                } else {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                    calldialog.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
