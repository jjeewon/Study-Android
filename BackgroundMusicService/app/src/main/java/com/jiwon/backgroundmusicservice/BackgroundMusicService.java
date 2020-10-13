package com.jiwon.backgroundmusicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BackgroundMusicService extends Service {

    private static final String TAG = BackgroundMusicService.class.getSimpleName();

    public class MyBinder extends Binder{
        BackgroundMusicService getService(){
            return BackgroundMusicService.this;
        }
    }
    private final IBinder mBinder = new MyBinder();
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent arg0){
        Log.d("service","onBind");
        return mBinder;
    }

    public BackgroundMusicService(){ }

    /**
     * 음악 재생 중인지 아닌지 반환한다.
     *
     * @return 재생 중인 경우는 true, 나머지는 false.
     */
    public boolean isPlaying() {
        boolean isPlaying = false;
        if (mediaPlayer != null) {
            isPlaying = mediaPlayer.isPlaying();
        }

        return isPlaying;
    }

    /**
     * 음악을 재생한다.
     */
    public void play(){
        Log.d(TAG,"play");
        mediaPlayer = MediaPlayer.create(this,R.raw.freemusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
    }

    /**
     * 음악을 정지한다.
     */
    public void stop(){
        Log.d(TAG,"stop");
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("TAG","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return START_NOT_STICKY;
    }
}
