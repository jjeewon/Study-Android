package com.jiwon.broadcastreciever_volumechanged;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            intent.getExtras();
            if(TextUtils.equals(action,VOLUME_CHANGED_ACTION)){
                Toast.makeText(MainActivity.this,"음량이 변화했습니다.",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter filterON = new IntentFilter(VOLUME_CHANGED_ACTION);
        registerReceiver(mReceiver,filterON);
    }
    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
