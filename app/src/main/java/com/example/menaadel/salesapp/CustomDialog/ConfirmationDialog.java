package com.example.menaadel.salesapp.CustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.menaadel.salesapp.R;

/**
 * Created by MenaAdel on 6/13/2018.
 */

public class ConfirmationDialog {
    Context context;
    String confirmationMessage;
    DialogInterface.OnClickListener listener;
    AlertDialog dialog = null;

    public ConfirmationDialog(Context context, String confirmationMessage, DialogInterface.OnClickListener listener) {
        this.context = context;
        this.confirmationMessage = confirmationMessage;
        this.listener = listener;
        init();
    }

    private void init() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(confirmationMessage);
        builder.setTitle(context.getString(R.string.txt_confirm));
        String positiveText = context.getString(android.R.string.ok);
        builder.setPositiveButton(positiveText, listener);
        String negativeText = context.getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                (dialog1, which) -> {
                    // negative button logic
                    dialog1.dismiss();
                });

        dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void Dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }
}
