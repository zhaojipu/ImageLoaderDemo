package com.andgo.broadcast.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/3/1 0001.
 */

public class ImageLoader {

    public static final String TAG="imageLoader";
    private LruCache<String,Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    private ImageLoader(Context context){
        mContext=context.getApplicationContext();
        //缓存的总大小
        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        Log.d(TAG,"内存大小为："+Runtime.getRuntime().maxMemory()/1024+"\n"
        +"可用内存为："+Runtime.getRuntime().freeMemory()/1024+"\n"
        +"总共内存为："+Runtime.getRuntime().totalMemory()/1024);

        //设置该应用缓存为总容量的1/8
        mMemoryCache=new LruCache<String, Bitmap>(maxMemory/8){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                Log.d(TAG,"key为："+key+"的bitmap的大小为："+bitmap.getRowBytes()*bitmap.getHeight()/1024);
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                //lruCache移除旧缓存时回调用次方法，可在此方法中完成资源的回收工作
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };



    }
}
