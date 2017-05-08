package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
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
import com.glory.bianyitong.util.DateUtil;
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
 * Created by lucy on 2016/11/14.
 * 添加小区
 */
public class AddRoomActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_building_no) //楼栋
            TextView tv_building_no;
    @BindView(R.id.tv_cell_no) //单元
            TextView tv_cell_no;
    @BindView(R.id.tv_room_no) //房号
            TextView tv_room_no;
    @BindView(R.id.tv_submit_addarea) //提交
            TextView tv_submit_addarea;
    private String userID = "";
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_add_room;
    }

    @Override
    protected void init() {
        super.init();
        //初始化标题栏
        inintTitle(getString(R.string.add_community), true, "");//添加小区
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                AddRoomActivity.this.finish();
            }
        });

        userID = RequestUtil.getuserid();
        tv_building_no.setOnClickListener(this);
        tv_cell_no.setOnClickListener(this);
        tv_room_no.setOnClickListener(this);
        tv_submit_addarea.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_building_no: //楼栋
                if (Database.communityID != 0) {
                    request_building(Database.communityID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_cell_first));//请先选择小区
                }
                break;
            case R.id.tv_cell_no: //单元
                if (Database.buildingID != 0) {
                    request_cell(Database.buildingID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_choose_floor_first));//请先选择楼栋
                }
                break;
            case R.id.tv_room_no: //房号
                if (Database.unitID != 0) {
                    request_room(Database.unitID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_unit_first));//请先选择单元
                }

                break;
            case R.id.tv_submit_addarea: //提交
                if (Database.roomID != 0) {
                    request_submit(Database.communityID, Database.communityName, Database.id_province, Database.str_province, Database.id_city, Database.str_city
                            , Database.buildingID, Database.buildingName, Database.roomID, Database.roomName, Database.unitID, Database.unitName);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_room_first));//请先选择房间
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //楼栋名称
        if (Database.buildingName != null) {
            tv_building_no.setText(Database.buildingName);
        }
        //单元名称
        if (Database.unitName != null) {
            tv_cell_no.setText(Database.unitName);
        }
        //房号名称
        if (Database.roomName != null) {
            tv_room_no.setText(Database.roomName);
        }

    }
//夏影 等风 潮鸣
    private void request_building(int communityID) { //获取楼栋
        String json = "{\"communityBuilding\": {\"communityID\": " + communityID + ",\"buildingStatus\":\"E\"},\"userid\": \"" + userID + "\",\"groupid\": \"\"," +
                "\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\"," +
                "\"nowpagenum\": \"\",\"pagerownum\": \"\",\"controllerName\": \"CommunityBuilding\",\"actionName\": \"StructureQuery\"}";
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
//                        if (hashMap2 != null && hashMap2.get("listCommunityBuilding") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBuilding");
//                            if (ad_list != null && ad_list.size() != 0) {
//                                Database.list_CommunityBuilding = ad_list;
//                            }
//                            if (Database.list_CommunityBuilding != null && Database.list_CommunityBuilding.size() != 0) {
//                                Intent intent = new Intent(AddRoomActivity.this, ListCommunityBuildingActivity.class);
//                                startActivity(intent);
//                            } else {
//                                ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_district_can_not_find_floor));//该小区找不到楼栋
//                            }
//                        } else {
//                            ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_district_can_not_find_floor));//该小区找不到楼栋
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(AddRoomActivity.this, getString(R.string.failed_to_connect_to_server));//未能连接到服务器
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
//
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
                if (hashMap2 != null && hashMap2.get("listCommunityBuilding") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBuilding");
                    if (ad_list != null && ad_list.size() != 0) {
                        Database.list_CommunityBuilding = ad_list;
                    }
                    if (Database.list_CommunityBuilding != null && Database.list_CommunityBuilding.size() != 0) {
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityBuildingActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_district_can_not_find_floor));//该小区找不到楼栋
                    }
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_district_can_not_find_floor));//该小区找不到楼栋
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

    private void request_cell(int buildingID) { //获取单元
        String json = "{\"communityUnit\": {\"buildingID\":" + buildingID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\"," +
                "\"pagerownum\": \"\",\"controllerName\": \"CommunityUnit\",\"actionName\": \"StructureQuery\"}";
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
//                        if (hashMap2 != null && hashMap2.get("listCommunityUnit") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityUnit");
//
//                            if (ad_list != null && ad_list.size() != 0) {
//                                Database.listCommunityUnit = ad_list;
//                            }
//                            if (Database.listCommunityUnit != null && Database.listCommunityUnit.size() != 0) {
//                                Intent intent = new Intent(AddRoomActivity.this, ListCommunityUnitActivity.class);
//                                startActivity(intent);
//                            } else {
//                                ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_floor_can_not_find_the_unit));//该楼栋找不到单元
//                            }
//                        } else {
//                            ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_floor_can_not_find_the_unit));//该楼栋找不到单元
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(AddRoomActivity.this, getString(R.string.failed_to_connect_to_server));
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
                if (hashMap2 != null && hashMap2.get("listCommunityUnit") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityUnit");

                    if (ad_list != null && ad_list.size() != 0) {
                        Database.listCommunityUnit = ad_list;
                    }
                    if (Database.listCommunityUnit != null && Database.listCommunityUnit.size() != 0) {
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityUnitActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_floor_can_not_find_the_unit));//该楼栋找不到单元
                    }
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_floor_can_not_find_the_unit));//该楼栋找不到单元
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() { }
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(url,json);
    }

    private void request_room(int unitID) { //获取房号
        String json = "{\"communityRoom\": {\"unitID\":" + unitID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\"," +
                "\"pagerownum\": \"\",\"controllerName\": \"CommunityRoom\",\"actionName\": \"StructureQuery\"}";
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
//                        if (hashMap2 != null && hashMap2.get("listCommunityRoom") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityRoom");
//                            if (ad_list != null && ad_list.size() != 0) {
//                                Database.listCommunityRoom = ad_list;
//                            }
//                            if (Database.listCommunityRoom != null && Database.listCommunityRoom.size() != 0) {
//                                Intent intent = new Intent(AddRoomActivity.this, ListCommunityRoomActivity.class);
//                                startActivity(intent);
//                            } else {
//                                ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_unit_can_not_find_the_room_number));//该单元找不到房号
//                            }
//                        } else {
//                            ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_unit_can_not_find_the_room_number));//该单元找不到房号
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
                if (hashMap2 != null && hashMap2.get("listCommunityRoom") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> ad_list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityRoom");
                    if (ad_list != null && ad_list.size() != 0) {
                        Database.listCommunityRoom = ad_list;
                    }
                    if (Database.listCommunityRoom != null && Database.listCommunityRoom.size() != 0) {
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityRoomActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_unit_can_not_find_the_room_number));//该单元找不到房号
                    }
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.the_unit_can_not_find_the_room_number));//该单元找不到房号
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


    private void request_submit(int communityID, String communityName, int provinceID, String provinceName, int cityID, String cItyName
            , int buildingID, String buildingName, int roomID, String roomName, int unitID, String unitName) { //提交
        String userName = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getUserName() != null) {
            userName = Database.USER_MAP.getUserName();
        }
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间
        String json = "{\"userCommnunityMapping\": {\"userCommunityID\": 1,\"userID\": 1,\"userName\": \"" + userName + "\",\"communityID\": " + communityID + "," +
                "\"communityName\": \"" + communityName + "\",\"userIDentityID\": 1,\"userIDentityName\": \"业主\",\"provinceID\":" + provinceID + "," +
                "\"provinceName\": \"" + provinceName + "\",\"cityID\":" + cityID + ",\"cItyName\": \"" + cItyName + "\",\"buildingID\":" + buildingID + ",\"buildingName\": \"" + buildingName + "\"," +
                "\"roomID\":" + roomID + ",\"roomName\": \"" + roomName + "\", \"approvalStatus\": \"2\",\"unitID\":" + unitID + ",\"unitName\": \"" + unitName + "\"," +
                "\"approvalDate\": \"" + nowdate + "\"},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"10\"," +
                "\"pagerownum\": \"20\",\"controllerName\": \"UserCommnunityMapping\",\"actionName\": \"ADD\"}";

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
//                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
//                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
//                            ToastUtils.showToast(AddRoomActivity.this, getString(R.string.submitted_successfully));//提交成功
//                            Database.isAddarea = true;
//                            Intent intent = new Intent(AddRoomActivity.this, AuthAreaActivity.class);
//                            startActivity(intent);
//                            AddRoomActivity.this.finish();
//                        } else if (hashMap2 != null && hashMap2.get("statuscode") != null &&
//                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == -120) {
//                            //该小区房号的认证申请已经提交过了
//                            ToastUtils.showLong(AddRoomActivity.this, getString(R.string.The_district_room_number_of_the_certification_application_has_been_submitted));
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
//                        progressDialog = ProgressDialog.show(AddRoomActivity.this, "", getString(R.string.commit), true);//提交
//                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
//                    }
//                });
        String url = HttpURL.HTTP_LOGIN;
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.submitted_successfully));//提交成功
                    Database.isAddarea = true;
                    Intent intent = new Intent(AddRoomActivity.this, AuthAreaActivity.class);
                    startActivity(intent);
                    AddRoomActivity.this.finish();
                } else if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == -120) {
                    //该小区房号的认证申请已经提交过了
                    ToastUtils.showLong(AddRoomActivity.this, getString(R.string.The_district_room_number_of_the_certification_application_has_been_submitted));
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AddRoomActivity.this, "", getString(R.string.commit), true);//提交
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url,json);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.str_province = "";
        Database.str_city = "";
        Database.str_district = "";
        Database.id_province = 0;
        Database.id_city = 0;
        Database.id_district = 0;
        Database.district_id = 0;

        Database.communityName = "";
        Database.communityID = 0;

        Database.buildingName = "";
        Database.buildingID = 0;

        Database.unitName = "";
        Database.unitID = 0;

        Database.roomName = "";
        Database.roomID = 0;

    }
}
