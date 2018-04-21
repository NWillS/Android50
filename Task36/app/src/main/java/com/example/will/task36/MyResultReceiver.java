package com.example.will.task36;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

public class MyResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case TweetUploadService.UPLOAD_SUCCESS:
                // success
                Log.i("share", "share success");
                Toast.makeText(context, "投稿完了", Toast.LENGTH_SHORT).show();
                break;
            case TweetUploadService.UPLOAD_FAILURE:
                // failure
                Log.i("share", "share fail");
                Toast.makeText(context, "投稿失敗", Toast.LENGTH_SHORT).show();
                break;
            case TweetUploadService.TWEET_COMPOSE_CANCEL:
                // cancel
                break;

            default:
                break;
        }
    }
}