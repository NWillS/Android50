package com.example.will.task16;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class TestDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CharSequence[] items = {"Facebook", "Twitter", "Line"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Activity activity = getActivity();
                switch (which) {
                    case 0:
                        Log.d("System.out/Facebook","Facebookが押されました。");
                        break;
                    case 1:
                        Log.d("System.out/Twitter","Twitterが押されました。");
                        break;
                    case 2:
                        Log.d("System.out/Line","Lineが押されました。");
                        break;
                    default:
                        break;
                }
            }
        });
        return builder.create();
    }
}