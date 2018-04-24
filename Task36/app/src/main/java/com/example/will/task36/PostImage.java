package com.example.will.task36;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

public class PostImage extends AppCompatActivity {
    private Uri myImageUri;
    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;
    private ProgressBar progressBar;

    private BroadcastReceiver myResultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case TweetUploadService.UPLOAD_SUCCESS:
                    // success
                    Log.i("share", "share success" + context);
                    Toast.makeText(context, "投稿完了", Toast.LENGTH_SHORT).show();
                    stopProgressBar();
                    break;
                case TweetUploadService.UPLOAD_FAILURE:
                    // failure
                    Log.i("share", "share fail");
                    Toast.makeText(context, "投稿失敗", Toast.LENGTH_SHORT).show();
                    stopProgressBar();
                    break;
                case TweetUploadService.TWEET_COMPOSE_CANCEL:
                    // cancel
                    stopProgressBar();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);
        permission();

        registerReceiver(myResultReceiver, new IntentFilter("com.twitter.sdk.android.tweetcomposer.UPLOAD_SUCCESS"));
        registerReceiver(myResultReceiver, new IntentFilter("com.twitter.sdk.android.tweetcomposer.UPLOAD_FAILURE"));
        registerReceiver(myResultReceiver, new IntentFilter("com.twitter.sdk.android.tweetcomposer.TWEET_COMPOSE_CANCEL"));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission();
                showGallery();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwitterCore.getInstance().getSessionManager().clearActiveSession();
                finish();
            }
        });
    }

    private void showGallery() {

        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.TITLE, photoName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー用のIntent作成
        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1111);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHOOSER) {

            if (resultCode != RESULT_OK) {
                // キャンセル時
                return;
            }

            Uri resultUri = (data != null ? data.getData() : m_uri);

            if (resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );
            TwitterSession session = TwitterCore.getInstance().getSessionManager()
                    .getActiveSession();
            Log.i("userID", String.valueOf(session.getUserId()));
            Intent intent = new ComposerActivity.Builder(getApplication())
                    .session(session)
                    .image(resultUri)
                    .text("test")
                    .hashtags("#Android")
                    .createIntent();
            startActivity(intent);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
