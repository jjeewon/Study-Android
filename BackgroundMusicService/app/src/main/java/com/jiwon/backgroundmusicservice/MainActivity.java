package com.jiwon.backgroundmusicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Boolean mIsPlaying;
    private View mBtnPlay;
    private View mBtnStop;
    private BackgroundMusicService mServiceBinder;
    //서비스와의 콜백
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServiceBinder = ((BackgroundMusicService.MyBinder) binder).getService();
            Log.d("ServiceConnection","connected");
            updateButtonEnabled();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("ServiceConnection","disconnected");
            mServiceBinder = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnPlay = findViewById(R.id.btn_play);
        mBtnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mServiceBinder != null){
                    mServiceBinder.play();
                }
                updateButtonEnabled();
            }
        });
        mBtnStop = findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mServiceBinder != null){
                    mServiceBinder.stop();
                }
                updateButtonEnabled();
            }
        });
    }

    private void updateButtonEnabled(){
        if(mServiceBinder != null){
            if(mServiceBinder.isPlaying()){
                mBtnPlay.setEnabled(false);
                mBtnStop.setEnabled(true);
                Log.d("log","재생 멈춰라 들어왓다");
            }else{
                Log.d("log","재생시작해라 들어왓다");
                mBtnPlay.setEnabled(true);
                mBtnStop.setEnabled(false);
            }
        }else{
            mBtnPlay.setEnabled(false);
            mBtnStop.setEnabled(false);
        }
    }

    public void doBindService(){
        Intent intent = null;
        intent = new Intent(this,BackgroundMusicService.class);
        bindService(intent,myConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume(){
        Log.d("activity","onResume");
        super.onResume();
        if(mServiceBinder == null){
            doBindService();
        }
        startService(new Intent(getApplicationContext(),BackgroundMusicService.class));
    }

    @Override
    protected void onPause(){
        Log.d("activity","onPause");
        super.onPause();
        if(mServiceBinder!= null){
            mIsPlaying = mServiceBinder.isPlaying();
            if(!mIsPlaying){
                mServiceBinder.stopSelf();
            }
            unbindService(myConnection);
            mServiceBinder = null;
        }
    }
}
