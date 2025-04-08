package com.thapamusic.deezspot;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;
import java.io.IOException;

public class MusicPlayerService extends Service {
    private static final String TAG = "MusicPlayerService";

    public static final String ACTION_PLAY = "com.example.deezspot.ACTION_PLAY";
    public static final String ACTION_PAUSE = "com.example.deezspot.ACTION_PAUSE";
    public static final String ACTION_STOP = "com.example.deezspot.ACTION_STOP";

    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            Music music = (Music) intent.getSerializableExtra("music");

            if (action != null && music != null) {
                switch (action) {
                    case ACTION_PLAY:
                        playMusic(music);
                        break;
                    case ACTION_PAUSE:
                        pauseMusic();
                        break;
                    case ACTION_STOP:
                        stopMusic();
                        break;
                }
            }
        }
        return START_NOT_STICKY;
    }

    private void playMusic(Music music) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(music.getMusicUrl());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start();
                Log.d(TAG, "Playing: " + music.getTitle());
            });
            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                Log.e(TAG, "MediaPlayer error: " + what + ", " + extra);
                return false;
            });
        } catch (IOException e) {
            Log.e(TAG, "Error setting data source", e);
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.d(TAG, "Music paused");
        }
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Log.d(TAG, "Music stopped");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
}