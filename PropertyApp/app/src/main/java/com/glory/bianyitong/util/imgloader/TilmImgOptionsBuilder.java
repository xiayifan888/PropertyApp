package com.glory.bianyitong.util.imgloader;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

public class TilmImgOptionsBuilder extends Builder {

	@Override
	public Builder bitmapConfig(Config bitmapConfig) {

		return super.bitmapConfig(bitmapConfig);
	}

	@Override
	public DisplayImageOptions build() {

		return super.build();
	}

	@Override
	public Builder cacheInMemory(boolean cacheInMemory) {

		return super.cacheInMemory(cacheInMemory);
	}

	@Override
	public Builder cacheOnDisc(boolean cacheOnDisc) {

		return super.cacheOnDisc(cacheOnDisc);
	}

	@Override
	public Builder cloneFrom(DisplayImageOptions options) {

		return super.cloneFrom(options);
	}

	@Override
	public Builder decodingOptions(Options decodingOptions) {

		return super.decodingOptions(decodingOptions);
	}

	@Override
	public Builder delayBeforeLoading(int delayInMillis) {

		return super.delayBeforeLoading(delayInMillis);
	}

	@Override
	public Builder displayer(BitmapDisplayer displayer) {

		return super.displayer(displayer);
	}

	@Override
	public boolean equals(Object o) {

		return super.equals(o);
	}

	@Override
	public Builder extraForDownloader(Object extra) {

		return super.extraForDownloader(extra);
	}

	@Override
	public Builder handler(Handler handler) {

		return super.handler(handler);
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public Builder imageScaleType(ImageScaleType imageScaleType) {

		return super.imageScaleType(imageScaleType);
	}

	@Override
	public Builder postProcessor(BitmapProcessor postProcessor) {

		return super.postProcessor(postProcessor);
	}

	@Override
	public Builder preProcessor(BitmapProcessor preProcessor) {

		return super.preProcessor(preProcessor);
	}

	@Override
	public Builder resetViewBeforeLoading(boolean resetViewBeforeLoading) {

		return super.resetViewBeforeLoading(resetViewBeforeLoading);
	}

	@Override
	public Builder showImageForEmptyUri(int imageRes) {

		return super.showImageForEmptyUri(imageRes);
	}

	@Override
	public Builder showImageOnFail(int imageRes) {

		return super.showImageOnFail(imageRes);
	}

	@Override
	public Builder showStubImage(int stubImageRes) {

		return super.showStubImage(stubImageRes);
	}

	@Override
	public String toString() {

		return super.toString();
	}

}
