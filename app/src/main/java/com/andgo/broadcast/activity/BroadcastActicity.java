package com.andgo.broadcast.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.andgo.broadcast.MainActivity;
import com.andgo.broadcast.R;
import com.andgo.broadcast.casts.MyBroadcastReceiver;

public class BroadcastActicity extends AppCompatActivity {

    MyBroadcastReceiver mBroadcastReceiver;//广播的注册分为两种

    NotificationManager manager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        //1、在AndroidMainfest.xml中静态注册（代码去注册文件中查看）

        /*<receiver android:name=".casts.MyBroadcastReceiver">
        <intent-filter>
        <action android:name="com.handsome.first"/>
        </intent-filter>
        </receiver>*/

        //2、动态注册
        mBroadcastReceiver=new MyBroadcastReceiver();
        this.registerReceiver(mBroadcastReceiver,new IntentFilter("com.handsome.first"));


        manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //3、反注册广播，取消广播
        this.unregisterReceiver(mBroadcastReceiver);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button2:
                //4、发送广播
                Intent intent=new Intent();
                intent.setAction("com.handsome.first");
                sendBroadcast(intent);
                break;
            case R.id.button3:
                //发送有序广播
                Intent intent1=new Intent();
                intent1.setAction("com.handsome.second");
                sendOrderedBroadcast(intent1,null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    setNotification();
                }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setNotification(){
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0);
//        Bitmap bm=new Bitmap();
        Notification builder=new Notification.Builder(this)
                .setContentTitle("测试demo")
                .setContentText("点击可以打开")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setNumber(1)
                .build();

        manager.notify(1,builder);
    }
}
