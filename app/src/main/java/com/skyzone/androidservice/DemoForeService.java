package com.skyzone.androidservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Skyzone on 2/20/2017.
 */

public class DemoForeService extends Service {

    public static final String TAG = DemoForeService.class.getSimpleName();

    private DemoForeService.MyBinder mBinder = new DemoForeService.MyBinder();

    @Override
    public void onCreate() {
        System.out.println("onCreate:" + TAG);
        super.onCreate();

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, MainActivity.class), 0);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("demo service")
                .setContentText("hahhahhaha")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand:" + TAG);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind:" + TAG);
        return mBinder;
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy:" + TAG);
        super.onDestroy();
    }

    class MyBinder extends Binder {

        public void startDownload() {
            System.out.println("service start download in Thread:" + Thread
                    .currentThread());
        }
    }
}
