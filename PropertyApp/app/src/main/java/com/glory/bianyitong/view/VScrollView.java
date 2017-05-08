package com.glory.bianyitong.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.glory.bianyitong.interfaces.VScrollViewListener;

public class VScrollView extends ScrollView {
	private VScrollViewListener scrollViewListener = null;

	public VScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public VScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public VScrollView(Context context, ViewPager pager) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		// TODO Auto-generated method stub
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}

	public void setScrollViewListener(VScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

}
