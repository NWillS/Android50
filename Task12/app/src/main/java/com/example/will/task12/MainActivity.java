package com.example.will.task12;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ConnectionReceiver.Observer {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ConnectionReceiver receiver = new ConnectionReceiver(this);
        registerReceiver(receiver, filter);

        this.webView = (WebView) findViewById(R.id.webView);
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl("https://www.google.co.jp/");


        Button reloadButton = (Button) findViewById(R.id.reload);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        Button forwardButton = (Button) findViewById(R.id.forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
    }

    @Override
    public void onConnect() {
        //ネットワークに接続した時の処理
        webView.reload();
    }

    @Override
    public void onDisconnect() {
        //ネットワークが切断された時の処理
        Log.i("System.out/Offline","オフラインです。");
    }
}

