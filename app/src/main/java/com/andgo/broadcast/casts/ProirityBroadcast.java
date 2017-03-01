package com.andgo.broadcast.casts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/3/1 0001.
 */

public class ProirityBroadcast {
    //内部类的Broadcast必须用public static修饰，否则会报错
    public static class HighProirityBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("receive","high");
            //拦截广播
            abortBroadcast();
            int code=0;
            Bundle bundle=null;
            String data="hello";
            setResult(code,data,bundle);
        }
    }

    public static class MidPriority extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("receive","mid");
            int code=getResultCode();
            String data=getResultData();
            Log.e("receive","获取到上一个广播接收器的结果：code="+code+"，data="+data);
        }
    }

    public static class LowPriority extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("receive","low");
        }
    }
}
