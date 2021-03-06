package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.ContainsEmojiEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 我来(添加)评论
 */
public class AddCommentActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.iv_title_text_left)
    TextView iv_title_text_left;

    @BindView(R.id.et_comment_txt)
    ContainsEmojiEditText et_comment_txt;


    private ProgressDialog progressDialog = null;
    private int neighborhoodid; //近邻 id
    private int CommentToID; //近邻 评论 id
    private int commentToUserID; //评论 评论的用户id
    private String commentToUserName = ""; //评论 评论的用户名

    @Override
    protected int getContentId() {
        return R.layout.ac_addcomment;
    }

    @Override
    protected void init() {
        super.init();
        neighborhoodid = getIntent().getIntExtra("neighborhoodID", 0); //近邻 id
        CommentToID = getIntent().getIntExtra("CommentToID", 0); //评论 id
        commentToUserID = getIntent().getIntExtra("commentToUserID", 0);
        commentToUserName = getIntent().getStringExtra("commentToUserName");
        if (commentToUserName == null) {
            commentToUserName = "";
        }
        //初始化标题栏
        inintTitle(getResources().getString(R.string.neighborhood_comments), false, getResources().getString(R.string.release)); //近邻评论  发布
        left_return_btn.setVisibility(View.GONE);
        iv_title_text_left.setVisibility(View.VISIBLE);
        iv_title_text_left.setText(getResources().getString(R.string.cancel));//取消
        iv_title_text_left.setOnClickListener(new View.OnClickListener() { //取消
            @Override
            public void onClick(View view) {
                AddCommentActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//发布
            @Override
            public void onClick(View v) {
                String desc = et_comment_txt.getText().toString();
                if (desc.equals("")) {
                    ToastUtils.showToast(AddCommentActivity.this, getResources().getString(R.string.comments_can_not_be_empty));//评论不能为空
                } else {
                    request(desc);
                }
            }
        });
    }

    private void request(String txt) { //评论
        String customerPhoto = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
            customerPhoto = Database.USER_MAP.getCustomerPhoto();
        }
        String userID = RequestUtil.getuserid();
        String loginName = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
            loginName = Database.USER_MAP.getLoginName();
        }
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间
        // CommentToID 评论近邻 0  评论评论 评论id
//        String json = "{\"neighborhoodComment\": {\"neighboCommentID\": 1,\"neighborhoodID\": " + neighborhoodid + ",\"userID\": 1,\"userName\": \"admin\"," +
//                "\"userPhoto\": \"" + customerPhoto + "\",\"commentDateTime\": \"" + nowdate + "\",\"commentContent\": \"" + txt + "\",\"CommentToID\": " + CommentToID + "" +
//                "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\"," +
//                "\"DeviceType\": \"3\",\"nowpagenum\": \"\",\"pagerownum\": \"\",\"controllerName\": \"NeighborhoodComment\",\"actionName\": \"ADD\"}";

        String json = "{\"neighborhoodComment\": {\"neighboCommentID\": 1,\"neighborhoodID\": \"" + neighborhoodid + "\",\"userID\": 1,\"userName\": \"" + loginName + "\"," +
                "\"userPhoto\": \"" + customerPhoto + "\",\"commentDateTime\": \"" + nowdate + "\",\"commentContent\": \"" + txt + "\",\"commentToID\": " + CommentToID + "," +
                "\"commentToUserID\":" + commentToUserID + ",\"commentToUserName\":\"" + commentToUserName + "\"},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"NeighborhoodComment\",\"actionName\": \"ADD\"}";
        String url = HttpURL.HTTP_LOGIN_AREA + "/WebApi/Post";
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {

                    ToastUtils.showToast(AddCommentActivity.this, getResources().getString(R.string.comments_are_successful));//评论成功
                    Database.isAddComment = true;
                    AddCommentActivity.this.finish();
                    EventBus.getDefault().post("addComment");
                } else {
                    ToastUtils.showToast(AddCommentActivity.this, getResources().getString(R.string.comment_failed));//评论失败
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AddCommentActivity.this, "", getResources().getString(R.string.release), true);
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url, json);
    }

}
