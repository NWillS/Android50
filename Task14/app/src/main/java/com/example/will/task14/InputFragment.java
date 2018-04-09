package com.example.will.task14;

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
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        findViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText());
            }
        });
    }

    protected void findViews(){
        editText = (EditText)getActivity().findViewById(R.id.editText);
        button = (Button)getActivity().findViewById(R.id.button);
        textView = (TextView)getActivity().findViewById(R.id.textView);
    }
}
