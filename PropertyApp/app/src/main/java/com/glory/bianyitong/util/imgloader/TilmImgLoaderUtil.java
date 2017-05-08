package com.glory.bianyitong.util.imgloader;

import android.graphics.Bitmap;

import com.glory.bianyitong.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class TilmImgLoaderUtil {

	private static DisplayImageOptions options;
	private static DisplayImageOptions options2;

	static {
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.wait) // 设置图片下载期间显示的图片
				//            .showImageForEmptyUri(R.drawable.wait)   // 设置图片Uri为空或是错误的时候显示的图片 
				//            .showImageOnFail(R.drawable.wait)     // 设置图片加载或解码过程中发生错误显示的图片      
				.resetViewBeforeLoading(true).cacheInMemory(true) // 设置下载的图片是否缓存在内存中  
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中  
				.imageScaleType(ImageScaleType.EXACTLY) // 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型      
				.build(); // 创建配置过得DisplayImageOption对象  

		options2 = new DisplayImageOptions.Builder().showStubImage(R.drawable.wait) // 设置图片下载期间显示的图片
				.resetViewBeforeLoading(true).cacheInMemory(true) // 设置下载的图片是否缓存在内存中  
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中  
				.imageScaleType(ImageScaleType.EXACTLY) // 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型      
				.displayer(new FadeInBitmapDisplayer(600)).build();
		//		.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
	}

	public static DisplayImageOptions getDisplayImageOptions() {
		return options;
	}

	public static DisplayImageOptions getDisplayImageOptions2() {
		return options2;
	}

	public static TilmImgOptionsBuilder builderOptions() {
		return new TilmImgOptionsBuilder();
	}
}
