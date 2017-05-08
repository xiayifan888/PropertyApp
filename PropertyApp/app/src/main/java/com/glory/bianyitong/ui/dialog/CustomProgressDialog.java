package com.glory.bianyitong.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;

public class CustomProgressDialog extends Dialog {
	private static CustomProgressDialog customProgressDialog = null;
	private Context context = null;

	public CustomProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CustomProgressDialog createDialog(Context context) {
		customProgressDialog = new CustomProgressDialog(context, R.style.dialog);
		customProgressDialog.setContentView(R.layout.customprogressdialog);
		//                customProgressDialog.setCancelable(false);
		customProgressDialog.setCanceledOnTouchOutside(false);
		customProgressDialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}

		//        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		//        animationDrawable.start();
	}

	/**
	 * 
	 * [Summary]
	 *       setTitile 标题
	 * @param strTitle
	 * @return
	 *
	 */
	public CustomProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	/**
	 * 
	 * [Summary]
	 *       setMessage 提示内容
	 * @param strMessage
	 * @return
	 *
	 */
	public CustomProgressDialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);

		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}

		return customProgressDialog;
	}
}