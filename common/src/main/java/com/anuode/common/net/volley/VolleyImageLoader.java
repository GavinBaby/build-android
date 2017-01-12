package com.anuode.common.net.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class VolleyImageLoader {
	private static ImageLoader mImageLoader = null;
	private static int mLoadingImageResId = 0;
	private static int mErrorImageResId = 0;

	public static void init(Context context, int cacheSize) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(requestQueue, new BitmapMemoryLruCache(cacheSize));
        setLoadingImage(0);
        setErrorImage(0);
	}

    public static void setLoadingImage(int resId) {
        mLoadingImageResId = resId;
    }

    public static void setErrorImage(int resId) {
        mErrorImageResId = resId;
    }
	
	public static void displayImage(ImageView childAt, String url, final ImageView imageView, final String uri, ImageListener outListener) {
		displayImage(imageView, uri, mLoadingImageResId, mErrorImageResId, null);
	}

    public static void displayImage(final ImageView imageView, final String uri, final int loadingImageResId, final int errorImageResId) {
        displayImage(imageView, uri, loadingImageResId, errorImageResId, null);
    }
	
	/**
	 * 加载图片
	 * @param imageView  显示图片的View
	 * @param uri   图片的url
	 */
	public static void displayImage(final ImageView imageView, final String uri, final int loadingImageResId, final int errorImageResId, final ImageListener outListener) {
		imageView.setTag(uri);
		if (!TextUtils.isEmpty(uri)) {
            ImageListener listener = new ImageListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (errorImageResId != 0) {
                        imageView.setImageResource(errorImageResId);
                    }
                }

                @Override
                public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                	if (imageView.getTag() != null && !response.getRequestUrl().equals(imageView.getTag().toString())) {
                	    return;
                    }
                	
                	//如果图片不为空，则设置图片
                    if (response.getBitmap() != null) {
                        Drawable oldDrawable = imageView.getDrawable();
                        if (oldDrawable != null) {
                            Bitmap oldBitmap = ((BitmapDrawable)oldDrawable).getBitmap();
                            if (oldBitmap != null && oldBitmap.equals(response.getBitmap())) {
                                return;
                            }                            
                        }

                        //如果图片数据已经被保存则不需要执行动画
                        if (mImageLoader.isCached(response.getRequestUrl(), 0, 0)){
                           imageView.setImageBitmap(response.getBitmap());
                            if (outListener != null) {
                                outListener.onResponse(response, isImmediate);
                            }
                            return;
                        }

                        imageView.setImageBitmap(response.getBitmap());
                        if (outListener != null) {
                            outListener.onResponse(response, isImmediate);
                        }
                    } else if (errorImageResId != 0) {
                        imageView.setImageResource(errorImageResId);
                    }
                }
            };
            
            // 加载图片，设置图片监听器
            imageView.setImageResource(loadingImageResId);
			mImageLoader.get(uri, listener);
		}
	}

    public static class BitmapMemoryLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        private final BitmapSoftRefCache softRefCache;

        public BitmapMemoryLruCache(int maxSize) {
            super(maxSize);
            softRefCache = new BitmapSoftRefCache();
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }

        @Override
        protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
            if (evicted) {
                softRefCache.putBitmap(key, oldValue);
            }
        }

        @Override
        public Bitmap getBitmap(String url) {
            url = String.valueOf(url.hashCode());
            Bitmap bitmap = get(url);
            if (bitmap == null) {
                bitmap = softRefCache.getBitmap(url);
            }
            return bitmap;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            url = String.valueOf(url.hashCode());
            put(url, bitmap);
        }
    }

    public static class BitmapSoftRefCache implements ImageLoader.ImageCache {

        private LinkedHashMap<String, SoftReference<Bitmap>> map;
        public BitmapSoftRefCache() {
            map = new LinkedHashMap<>();
        }

        @Override
        public Bitmap getBitmap(String url) {
            Bitmap bitmap = null;
            SoftReference<Bitmap> softRef = map.get(url);
            if(softRef != null){
                bitmap = softRef.get();
                if(bitmap == null){
                    map.remove(url);
                }
            }
            return bitmap;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            SoftReference<Bitmap> softRef = new SoftReference<>(bitmap);
            map.put(url, softRef);
        }
    }
}
