package com.presentedbykaran.bookshelf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.presentedbykaran.bookshelf.R;

/**
 * Created by karan on 05/08/18.
 */
public class AlertDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.alert_title)
        .setMessage(R.string.error_message)
        .setPositiveButton(R.string.error_button_text, null);

        return builder.create();
    }
}
