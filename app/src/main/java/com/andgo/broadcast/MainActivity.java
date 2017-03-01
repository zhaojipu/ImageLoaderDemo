package com.andgo.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andgo.broadcast.activity.BroadcastActicity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="*****";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"内存大小为："+Runtime.getRuntime().maxMemory()/1024+"\n"
                +"可用内存为："+Runtime.getRuntime().freeMemory()/1024+"\n"
                +"总共内存为："+Runtime.getRuntime().totalMemory()/1024);
    }

    public void onClick(View view){
        startActivity(new Intent(this, BroadcastActicity.class));
    }
}
