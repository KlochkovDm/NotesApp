package com.example.notesapp.ui.alerts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.notesapp.R;

public class AlertDialogFragment extends DialogFragment {

    public static final String TAG = "AlertDialogFragment";
    public static final String RESULT = "AlertDialogResult";

    public static final String RESULT_ALL_CLEAR = "RESULT_ALL_CLEAR";
    public static final String RESULT_SINGLE_DELETE = "RESULT_SINGLE_DELETE";
    public static final String ALL_CLEAR_TYPE = "ALL_CLEAR_TYPE";
    public static final String SINGLE_DELETE_TYPE = "SINGLE_DELETE_TYPE";

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_TYPE = "ARG_TYPE";


    public static AlertDialogFragment newInstance(@StringRes int title, int message, String typeKey) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TITLE, title);
        bundle.putInt(ARG_MESSAGE, message);
        bundle.putString(ARG_TYPE, typeKey);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        if (getArguments() != null && getArguments().get(ARG_TITLE) !=null && getArguments().get(ARG_MESSAGE) != null && getArguments().getString(ARG_TYPE) != null) {
            builder.setTitle(getArguments().getInt(ARG_TITLE))
                    .setMessage(getArguments().getInt(ARG_MESSAGE))
                    .setNegativeButton(R.string.btn_negative, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .setPositiveButton(R.string.btn_positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle result = new Bundle();
                            if(getArguments().getString(ARG_TYPE).equals(AlertDialogFragment.ALL_CLEAR_TYPE)){
                                result.putBoolean(RESULT_ALL_CLEAR, true);
                            }
                            if(getArguments().getString(ARG_TYPE).equals(AlertDialogFragment.SINGLE_DELETE_TYPE )){
                                result.putBoolean(RESULT_SINGLE_DELETE, true);
                            }
                            getParentFragmentManager().setFragmentResult(RESULT, result);
                        }
                    });

        }
        return builder.create();
    }
}
