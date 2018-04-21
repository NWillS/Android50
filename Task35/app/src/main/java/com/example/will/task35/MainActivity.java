package com.example.will.task35;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        Button postButton = (Button) findViewById(R.id.button);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginButton.setPublishPermissions(Collections.singletonList("publish_actions"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplication(),"ログイン成功", LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void share() {
        if (AccessToken.getCurrentAccessToken() != null && AccessToken.getCurrentAccessToken().getPermissions().contains("publish_actions")) {
            Bitmap bi = BitmapFactory.decodeResource(getResources(),
                    R.drawable.icon);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bi.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();
            GraphRequest graphRequest = GraphRequest.newPostRequest(AccessToken.getCurrentAccessToken(), "me/photos", null, new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse graphResponse) {
                    Toast.makeText(getApplication(),"投稿完了",LENGTH_SHORT).show();
                }
            });
            Bundle postParams = graphRequest.getParameters();
            postParams.putByteArray("picture", data);
            postParams.putString("caption", "test");
            graphRequest.setParameters(postParams);
            graphRequest.executeAsync();
        } else {
            Toast.makeText(getApplication(),"ログインしてください", LENGTH_SHORT).show();
        }
    }
}
