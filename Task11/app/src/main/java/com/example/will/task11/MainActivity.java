package com.example.will.task11;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@SuppressWarnings({"UnnecessarilyQualifiedInnerClassAccess", "UnnecessaryThis"})
public class MainActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.mainLayout = (ConstraintLayout) findViewById(R.id.layout);


        final EditText editText = findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if(editText.length() > 0) {
                        hideKeyboard();
                        return true;
                    }
                }
                return false;
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideKeyboard();
        return true;
    }

    private void hideKeyboard(){
        // キーボードを隠す
        this.inputMethodManager.hideSoftInputFromWindow(this.mainLayout.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
// 背景にフォーカスを移す
        this.mainLayout.requestFocus();
    }
}
