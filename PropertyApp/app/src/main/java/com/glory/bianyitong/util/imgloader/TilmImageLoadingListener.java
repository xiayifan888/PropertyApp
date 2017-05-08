package com.glory.bianyitong.util.imgloader;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class TilmImageLoadingListener extends SimpleImageLoadingListener {

	@Override
	public void onLoadingStarted(String imageUri, View view) {
		super.onLoadingStarted(imageUri, view);
		this.onLoadingStarted();
	}

	@Override
	public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
		super.onLoadingFailed(imageUri, view, failReason);
		this.onLoadingFailed();
	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		super.onLoadingComplete(imageUri, view, loadedImage);
		this.onLoadingComplete();
	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {
		super.onLoadingCancelled(imageUri, view);
	}

	public void onLoadingFailed() {
	}

	public void onLoadingComplete() {
	}

	public void onLoadingStarted() {
	}
}
