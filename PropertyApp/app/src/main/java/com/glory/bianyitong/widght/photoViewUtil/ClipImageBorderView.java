package com.glory.bianyitong.widght.photoViewUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 裁剪边框
 * 
 * @author zhy http://blog.csdn.net/lmj623565791/article/details/39761281
 */
public class ClipImageBorderView extends View {
	/**
	 * 边框距左右边界距离，用于调整边框长度
	 */
	public static final int BORDERDISTANCE = 50;

	private Paint mPaint;
	private Context mContext;

	public ClipImageBorderView(Context context) {
		this(context, null);
		mContext = context;
	}

	public ClipImageBorderView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		mContext = context;
	}

	public ClipImageBorderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		mPaint = new Paint();
	}

	/* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();

		int innerCircle = dip2px(mContext, 150); // 内圆半径
		int ringWidth = height; // 圆环宽度

		mPaint.setAntiAlias(true);// 设置画笔无锯齿
		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

		// 第一种方法绘制圆环
		// 绘制内圆
		mPaint.setColor(Color.WHITE);
		mPaint.setStrokeWidth(3);
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(width / 2, height / 2, innerCircle, mPaint);

		// 绘制圆环
		mPaint.setColor(0xaa000000);
		mPaint.setStrokeWidth(ringWidth);
		canvas.drawCircle(width / 2, height / 2, innerCircle + 1 + ringWidth
				/ 2, mPaint);

	}

}
