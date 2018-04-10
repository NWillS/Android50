package com.example.will.task14;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class InputFragment extends Fragment {
    private EditText editText;
    private Button button;

    public onClickListener listener;

    public interface onClickListener {
        void onClick(String text);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(editText.getText().toString());
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onClickListener) {
            listener = (onClickListener) context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        // 画面からFragmentが離れたあとに処理が呼ばれることを避けるためにNullで初期化しておく
        listener = null;
    }
}
