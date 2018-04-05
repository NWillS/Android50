package com.example.will.task10;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.security.SecureRandom;

@SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        final ImageView imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TypedArray typedArray = getApplicationContext().getResources().obtainTypedArray(R.array.default_img);
                SecureRandom random = new SecureRandom();
                Integer rand = random.nextInt(typedArray.length());
                Drawable drawable = typedArray.getDrawable(rand);
                imageView.setImageDrawable(drawable);
                typedArray.recycle();
            }
        });
    }
}
