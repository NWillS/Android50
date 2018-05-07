package com.example.will.task47;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMMessageReceiverService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

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

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Notification notificationBuilder = new NotificationCompat.Builder(this,"TEST")
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(messageTitle)
                    .setSubText(messageContent)
                    .setAutoCancel(false)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder);

    }
}