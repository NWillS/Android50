package com.example.will.task12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Observer;

public class ConnectionReceiver extends BroadcastReceiver {
    private Observer mObserver;

    public ConnectionReceiver(Observer observer) {
        super();
        mObserver = observer;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            mObserver.onDisconnect();
        }else {
            mObserver.onConnect();
        }
    }

    //----- コールバックを定義 -----
    interface Observer {
        void onConnect();
        void onDisconnect();
    }
}