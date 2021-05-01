package com.example.cybercardv02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SaveDialog extends DialogFragment {
    public interface ButtonClickListener {
        void onButtonClick(boolean val);
    }
    private ButtonClickListener mListener;
    private String mTitle;
    public void setTitle(String title){
        mTitle = title;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        if (savedInstanceState != null) mTitle = savedInstanceState.getString("title");
        builder.setTitle(mTitle).setPositiveButton("Allow",
                (dialog, which) -> mListener.onButtonClick(true)).setNegativeButton("Cancel",
                (dialog, which) -> mListener.onButtonClick(false));
        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (ButtonClickListener) context;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("title", mTitle);
    }
}
