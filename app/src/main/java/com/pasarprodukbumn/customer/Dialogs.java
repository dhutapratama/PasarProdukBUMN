package com.pasarprodukbumn.customer;

import android.app.Dialog;
import android.content.Context;

public class Dialogs {
    public static Dialog loading(Context context) {
        final Dialog dialog = new Dialog(context, R.style.Loading);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        return dialog;
    }
}
