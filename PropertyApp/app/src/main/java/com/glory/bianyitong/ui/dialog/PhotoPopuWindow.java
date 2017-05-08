package com.glory.bianyitong.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.activity.ShearPicturesActivity;

/**
 * Created by Administrator on 2016/7/2.
 * 选择相册
 */
public class PhotoPopuWindow extends PopupWindow implements View.OnClickListener {
    TextView b1, b2, b3;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private int type = 0; //有权限 1

    public PhotoPopuWindow(final Context context, int type) {
        super(context);
        this.context = context;
        this.type = type;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_photo, null);
        b1 = (TextView) mMenuView.findViewById(R.id.b1);
        b2 = (TextView) mMenuView.findViewById(R.id.b2);
        b3 = (TextView) mMenuView.findViewById(R.id.b3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.b1: //拍照
                PhotoPopuWindow.this.dismiss();
                Intent intent4 = new Intent(context, ShearPicturesActivity.class);
                intent4.putExtra("type", "Camera");
                context.startActivity(intent4);
                break;
            case R.id.b2: //手机相册选择
                PhotoPopuWindow.this.dismiss();
                Intent intent = new Intent(context, ShearPicturesActivity.class);
                intent.putExtra("type", "Photo");
                context.startActivity(intent);
                break;
            case R.id.b3: //取消
                PhotoPopuWindow.this.dismiss();
                break;
            default:
                break;
        }
    }

    private boolean checkPermission() {
        String permission = "android.permission.CAMERA"; //你要判断的权限名字
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
