package com.andgo.broadcast.imageLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * 用于图片压缩
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/3/1 0001.
 */

public class ImageResizer {
    public static final String TAG = "ImageResizer";

    public Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置为true只加载图片的宽高等基本信息
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;


        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeBitmapFromFileDescriptor(FileDescriptor fd,int reqWidth,int reqHeight){
        BitmapFactory.Options options=new BitmapFactory.Options();

        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }


    /**
     * 根据需要展示的宽高，计算采样率
     * @param options--带有原图宽高的option
     * @param reqWidth--设计的宽
     * @param reqHeight--设计的高
     * @return--采样率
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }
        //获取图片的原始大小
        int width = options.outWidth;
        int height = options.outHeight;
        Log.d(TAG, "width=" + width + "height=" + height);
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while ((halfHeight / inSampleSize >= reqHeight) && (halfWidth / inSampleSize >= reqHeight)) {
                inSampleSize *= 2;
            }
        }
        Log.d(TAG,"inSampleSize="+inSampleSize);

        return inSampleSize;
    }
}
