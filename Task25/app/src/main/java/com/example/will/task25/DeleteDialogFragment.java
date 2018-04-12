package com.example.will.task25;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnqualifiedInnerClassAccess"})
public class DeleteDialogFragment extends DialogFragment {
    @SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
    interface DeleteDialogListener{
        void onClickOk();
    }

    private DeleteDialogListener listener;

    void setListener(DeleteDialogListener listener){
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Builder builder = new Builder(getActivity());
        builder.setMessage("削除しますか")
                .setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(listener != null){
                            listener.onClickOk();
                        }
                    }
                })
                .setNegativeButton("Cancel", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
