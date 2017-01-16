package com.anuode.build_andriod.modules.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：异步加载图片资源
 * 作成：OUT SOURCE
 * 时间：2015-12-21
 */
public class AsyncImageLoader{
	// 内存缓存
	private MemoryCache mMemoryCache;
	// 文件缓存
	private FileCache mFileCache;
	// 线程池
	private ExecutorService mExecutorService;
	// 记录已经加载图片的ImageView
	private Map<SimpleDraweeView, String> mImageViews = Collections
			.synchronizedMap(new WeakHashMap<SimpleDraweeView, String>());
	private List<LoadPhotoTask> mTaskQueue = new ArrayList<LoadPhotoTask>();
	/**
	 * 默认采用一个大小为5的线程池
	 * @param context
	 * @param memoryCache 所采用的高速缓存
	 * @param fileCache 所采用的文件缓存
	 */
	public AsyncImageLoader(Context context, MemoryCache memoryCache, FileCache fileCache) {
		mMemoryCache = memoryCache;
		mFileCache = fileCache;
		mExecutorService = Executors.newFixedThreadPool(5);
	}

	/**
	 * 根据url加载相应的图片
	 * @param url
	 * @return 如果缓存里有则直接返回，如果没有则异步从文件或网络端获取
	 */
	public Bitmap loadBitmap(SimpleDraweeView imageView, String url) {
		// 先将ImageView记录到Map中,表示该ui已经执行过图片加载了
		mImageViews.put(imageView, url);
		// 先从一级缓存中获取图片
		Bitmap bitmap = mMemoryCache.get(url);
		if(bitmap == null) {
			enquequeLoadPhoto(url, imageView);
		}
		return bitmap;
	}

	/**
	 * 加入图片下载队列
	 * @param url
	 */
	private void enquequeLoadPhoto(String url, SimpleDraweeView imageView) {
		// 如果任务已经存在，则不重新添加
		if(isTaskExisted(url))
			return;
		LoadPhotoTask task = new LoadPhotoTask(url, imageView);
		synchronized (mTaskQueue) {
			// 将任务添加到队列中
			mTaskQueue.add(task);
		}
		// 向线程池中提交任务，如果没有达到上限(5),则运行否则被阻塞
		mExecutorService.execute(task);
	}

	/**
	 * 判断下载队列中是否已经存在该任务
	 * @param url
	 * @return
	 */
	private boolean isTaskExisted(String url) {
		if(url == null)
			return false;
		synchronized (mTaskQueue) {
			int size = mTaskQueue.size();
			for(int i=0; i<size; i++) {
				LoadPhotoTask task = mTaskQueue.get(i);
				if(task != null && task.getUrl().equals(url))
					return true;
			}
		}
		return false;
	}

	/**
	 * 从缓存文件或者网络端获取图片
	 * @param url
	 */
	private Bitmap getBitmapByUrl(String url) {
		// 获得缓存图片路径
		File f = mFileCache.getFile(url);
		// 获得缓存图片路径
		Bitmap b = ImageUtil.decodeFile(f);
		// 不为空表示获得了缓存的文件
		if (b != null)
			return b;
		// 同网络获得图片
		return ImageUtil.loadBitmapFromWeb(url, f);
	}

	/**
	 * 判断该ImageView是否已经加载过图片了（可用于判断是否需要进行加载图片）
	 * @param imageView
	 * @param url
	 * @return
	 */
	private boolean imageViewReused(SimpleDraweeView imageView, String url) {
		String tag = mImageViews.get(imageView);
		if (tag == null || !tag.equals(url))
			return true;
		return false;
	}
	
	private void removeTask(LoadPhotoTask task) {
		synchronized (mTaskQueue) {
			mTaskQueue.remove(task);
		}
	}
	
	class LoadPhotoTask implements Runnable {
		private String url;
		private SimpleDraweeView imageView;
		LoadPhotoTask(String url, SimpleDraweeView imageView) {
			this.url = url;
			this.imageView = imageView;
		}
		
		@Override
		public void run() {
			// 判断ImageView是否已经被复用
			if (imageViewReused(imageView, url)) {
				// 如果已经被复用则删除任务
				removeTask(this);
				return;
			}
			// 从缓存文件或者网络端获取图片
			Bitmap bmp = getBitmapByUrl(url);
			// 将图片放入到一级缓存中
			mMemoryCache.put(url, bmp);
			// 若ImageView未加图片则在ui线程中显示图片
			if (!imageViewReused(imageView, url)) {
				// 含有run()实现设置ImageView图片的组件
				BitmapDisplayer bd = new BitmapDisplayer(bmp, imageView, url);
				Activity a = (Activity) imageView.getContext();
				// 在UI线程调用bd组件的run方法，实现为ImageView控件加载图片
				a.runOnUiThread(bd);
			}
			// 从队列中移除任务
			removeTask(this);
		}
		public String getUrl() {
			return url;
		}
	}

	/**
	 *由UI线程中执行该组件的run方法
	 */
	class BitmapDisplayer implements Runnable {
		private Bitmap bitmap;
		private SimpleDraweeView imageView;
		private String url;
		public BitmapDisplayer(Bitmap b, SimpleDraweeView imageView, String url) {
			bitmap = b;
			this.imageView = imageView;
			this.url = url;
		}
		public void run() {
			if (imageViewReused(imageView, url))
				return;
			if (bitmap != null)
				imageView.setImageBitmap(bitmap);
		}
	}

	/**
	 * 释放资源
	 */
	public void destroy() {
		mMemoryCache.clear();
		mMemoryCache = null;
		mImageViews.clear();
		mImageViews = null;
		mTaskQueue.clear();
		mTaskQueue = null;
		mExecutorService.shutdown();
		mExecutorService = null;
	}
}