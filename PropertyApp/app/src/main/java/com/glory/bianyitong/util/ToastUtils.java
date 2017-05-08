package com.glory.bianyitong.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	protected static Toast toast   = null;
	private static String oldMsg;
	private static long oneTime = 0;
	private static long twoTime = 0;

//	private static Toast toast;

	/**
	 * 安全的土司,显示1秒,可以在任意线程显示
	 *
	 * @param activity
	 * @param text
	 */
	public static void showShort(Context activity,  CharSequence text) {
		if (null == toast) {
			toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

	/**
	 * 安全的土司,显示两秒,可以在任意线程显示
	 * 
	 * @param activity
	 * @param text
	 * 
	 */
	public static void showLong(Context activity, CharSequence text) {
		if (null == toast) {
			toast = Toast.makeText(activity, text, Toast.LENGTH_LONG);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

	public static void showToast(Context context, String s)
	{
		if (toast == null)
		{
			toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
			toast.show();
			oneTime = System.currentTimeMillis();
		} else
		{
			twoTime = System.currentTimeMillis();
			if (s.equals(oldMsg))
			{
				if (twoTime - oneTime > Toast.LENGTH_SHORT)
				{
					toast.show();
				}
			} else
			{
				oldMsg = s;
				toast.setText(s);
				toast.show();
			}
		}
		oneTime = twoTime;
	}

//	public static void showToast(Context context, int resId){
//		showToast(context, context.getString(resId));
//	}

}
