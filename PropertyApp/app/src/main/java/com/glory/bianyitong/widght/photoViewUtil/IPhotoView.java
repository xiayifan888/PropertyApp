/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.glory.bianyitong.widght.photoViewUtil;

import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

public interface IPhotoView {
	/**
	 * Returns true if the PhotoView is set to allow zooming of Photos.
	 * 
	 * @return true if the PhotoView allows zooming.
	 */
	boolean canZoom();

	/**
	 * Gets the Display Rectangle of the currently displayed Drawable. The
	 * Rectangle is relative to this View and includes all scaling and
	 * translations. 获取当前显示的可绘制对象的显示矩形。矩形是相对于这个视图，包括所有的缩放和 翻译。
	 * 
	 * @return - RectF of Displayed Drawable
	 */
	RectF getDisplayRect();

	/**
	 * @return The current minimum scale level. What this value represents
	 *         depends on the current {@link ImageView.ScaleType}
	 *         .目前的最低规模水平。这是什么值表示取决于当前
	 */
	float getMinScale();

	/**
	 * Sets the minimum scale level. What this value represents depends on the
	 * current {@link ImageView.ScaleType}.
	 */
	void setMinScale(float minScale);

	/**
	 * @return The current middle scale level. What this value represents
	 *         depends on the current {@link ImageView.ScaleType}
	 *         .
	 */
	float getMidScale();

	/**
	 * Sets the middle scale level. What this value represents depends on the
	 * current {@link ImageView.ScaleType}.
	 */
	void setMidScale(float midScale);

	/**
	 * @return The current maximum scale level. What this value represents
	 *         depends on the current {@link ImageView.ScaleType}
	 *         .
	 */
	float getMaxScale();

	/**
	 * Sets the maximum scale level. What this value represents depends on the
	 * current {@link ImageView.ScaleType}.
	 */
	void setMaxScale(float maxScale);

	/**
	 * Returns the current scale value
	 *
	 * @return float - current scale value
	 */
	float getScale();

	/**
	 * Return the current scale type in use by the ImageView.
	 */
	ImageView.ScaleType getScaleType();

	/**
	 * Controls how the image should be resized or moved to match the size of
	 * the ImageView. Any scaling or panning will happen within the confines of
	 * this {@link ImageView.ScaleType}.
	 *
	 * @param scaleType
	 *            - The desired scaling mode.
	 */
	void setScaleType(ImageView.ScaleType scaleType);

	/**
	 * Whether to allow the ImageView's parent to intercept the touch event when
	 * the photo is scroll to it's horizontal edge.是否允许的ImageView的父母时，拦截触摸事件
	 * 照片是滚动到它的水平边缘。
	 */
	void setAllowParentInterceptOnEdge(boolean allow);

	/**
	 * Register a callback to be invoked when the Photo displayed by this view
	 * is long-pressed.
	 *
	 * @param listener
	 *            - Listener to be registered.
	 */
	void setOnLongClickListener(View.OnLongClickListener listener);

	/**
	 * Register a callback to be invoked when the Matrix has changed for this
	 * View. An example would be the user panning or scaling the Photo.
	 * 注册时，矩阵已经改变了这个被调用的回调 视图。一个例子是平移或缩放的图像的用户。
	 *
	 * @param listener
	 *            - Listener to be registered.
	 */
	void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener);

	/**
	 * Register a callback to be invoked when the Photo displayed by this View
	 * is tapped with a single tap. 注册时所显示的图片由这个视图被调用的回调 被窃听用一个水龙头。
	 *
	 * @param listener
	 *            - Listener to be registered.
	 */
	void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener);

	/**
	 * Register a callback to be invoked when the View is tapped with a single
	 * tap.
	 *
	 * @param listener
	 *            - Listener to be registered.
	 */
	void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener);

	/**
	 * Allows you to enable/disable the zoom functionality on the ImageView.
	 * When disable the ImageView reverts to using the FIT_CENTER matrix.
	 * *允许您启用/禁用对ImageView的缩放功能。 当禁用了ImageView的恢复使用FIT_CENTER矩阵。
	 * 
	 * @param zoomable
	 *            - Whether the zoom functionality is enabled.
	 */
	void setZoomable(boolean zoomable);

	/**
	 * Zooms to the specified scale, around the focal point given.
	 * 缩放到指定的规模，围绕给定的焦点。
	 * @param scale
	 *            - Scale to zoom to规模放大到
	 * @param focalX
	 *            - X Focus PointX对焦点
	 * @param focalY
	 *            - Y Focus Point
	 */
	void zoomTo(float scale, float focalX, float focalY);
}
