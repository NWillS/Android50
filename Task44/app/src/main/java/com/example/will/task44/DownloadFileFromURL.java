package com.example.will.task44;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "NestedTryStatement", "DuplicateStringLiteralInspection", "OverlyLongMethod", "ImplicitCallToSuper", "OverlyBroadCatchBlock"})
class DownloadFileFromURL extends AsyncTask<String, Integer, Bitmap> {

    @SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
    interface DownloadFileListener{
        void succesfully(Bitmap bitmap);
    }

    private final DownloadFileListener listener;


    DownloadFileFromURL(DownloadFileListener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bmp = null;


        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL( strings[0]);

            // HttpURLConnection インスタンス生成
            urlConnection = (HttpURLConnection) url.openConnection();

            // タイムアウト設定
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);

            // リクエストメソッド
            urlConnection.setRequestMethod("GET");

            // リダイレクトを自動で許可しない設定
            urlConnection.setInstanceFollowRedirects(false);

            // ヘッダーの設定(複数設定可能)
            urlConnection.setRequestProperty("Accept-Language", "jp");

            // 接続
            urlConnection.connect();

            int resp = urlConnection.getResponseCode();

            switch (resp){
                case HttpURLConnection.HTTP_OK:
                    InputStream is = null;
                    try{
                        is = urlConnection.getInputStream();
                        bmp = BitmapFactory.decodeStream(is);
                        is.close();
                    } catch(IOException e){
                        Log.d("debug",e.getMessage());
                    } finally{
                        if(is != null){
                            is.close();
                        }
                    }
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.d("debug", "downloadImage error");
            Log.d("debug",e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return bmp;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i("System.out", String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        listener.succesfully(bitmap);
    }
}
