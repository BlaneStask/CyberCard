package com.example.cybercardv02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * <h1>QR Code</h1>
 *
 * @author  Blane Staskiewicz
 * @version 2.0
 * @since   2021-07-28
 */
public class SaveDialog extends DialogFragment {
    /**
     * ButtonClickListener interface for callback method
     */
    public interface ButtonClickListener {
        void onButtonClick(boolean val);
    }
    
    private ButtonClickListener mListener;
    private String mTitle;
    
    /**
     * This method sets Title
     *
     * @param title  Title to be set
     */
    public void setTitle(String title){
        mTitle = title;
    }
    
    /**
     * This Dialog method creates the Dialog
     *
     * @param  savedInstanceState  Reinitalizes title when interrupted
     */
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
    
    /**
     * This method attaches listener to context
     *
     * @param  context  Passed context
     */
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (ButtonClickListener) context;
    }
    
    /**
     * This method saves instance state when interrupted
     *
     * @param  outState  Bundle of saved instance state for title
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("title", mTitle);
    }
}
