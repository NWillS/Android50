package com.example.will.task47;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMMessageReceiverService extends FirebaseMessagingService {
    private NotificationCompat.Builder notificationCompatBuilder;
    private NotificationManager localNotificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.d("remoteMessage", "Message data payload: " + remoteMessage.getData());
        }

        localNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannel channel = new NotificationChannel("TEST", "Test", NotificationManager.IMPORTANCE_DEFAULT);
            localNotificationManager.createNotificationChannel(channel);
            notificationCompatBuilder = new NotificationCompat.Builder(this, channel.getId());
        }
        else
        {
            notificationCompatBuilder = new NotificationCompat.Builder(this, "");
        }
        Log.d("fcm", "received notification");
        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


    private void sendNotification(String messageTitle, String messageContent) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP  // 起動中のアプリがあってもこちらを優先する
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED  // 起動中のアプリがあってもこちらを優先する
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS  // 「最近利用したアプリ」に表示させない
                     );
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                    PendingIntent.FLAG_UPDATE_CURRENT |PendingIntent.FLAG_ONE_SHOT);
            notificationCompatBuilder.setContentIntent(pendingIntent);
            notificationCompatBuilder.setSmallIcon(R.mipmap.ic_launcher);
            notificationCompatBuilder.setTicker("ticker");
            notificationCompatBuilder.setContentTitle(messageTitle);
            notificationCompatBuilder.setContentText(messageContent);
            notificationCompatBuilder.setAutoCancel(true);

            localNotificationManager.notify(1, notificationCompatBuilder.build());

    }
}