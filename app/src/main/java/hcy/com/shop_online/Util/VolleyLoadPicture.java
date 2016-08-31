package hcy.com.shop_online.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * 图片加载网络加载
 * Created by Administrator on 2016/8/31.
 */
public class VolleyLoadPicture {
    private ImageLoader mImageLoader = null;
    private BitmapCache mBitmapCache;

    private ImageLoader.ImageListener one_listener;

    public VolleyLoadPicture(Context context, ImageView imageView){
        one_listener = ImageLoader.getImageListener(imageView, 0, 0);

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);//发网络请求
        mBitmapCache = new BitmapCache();//得到网络缓存
        mImageLoader = new ImageLoader(mRequestQueue, mBitmapCache);//加载网络图片
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    public void setmImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    public ImageLoader.ImageListener getOne_listener() {
        return one_listener;
    }

    public void setOne_listener(ImageLoader.ImageListener one_listener) {
        this.one_listener = one_listener;
    }

    /**
     * 缓存类
     */
    class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;
        private int sizeValue;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    sizeValue = value.getRowBytes() * value.getHeight();
                    return sizeValue;
                }

            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }

}
