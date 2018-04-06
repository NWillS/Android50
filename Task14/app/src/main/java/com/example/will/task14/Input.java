package com.example.will.task14;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class Input extends Fragment {

    private InputFragmentListener listener;

    public interface InputFragmentListener {
        void buttonOnClick(String text);
    }
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.editText = (EditText) view.findViewById(R.id.editText);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    String text = editText.getText().toString();
                    listener.buttonOnClick(text);
                }
            }
        });
    }
    void setListener(InputFragmentListener listener){
        this.listener = listener;
    }
}
