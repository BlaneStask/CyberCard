package com.example.cybercardv02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * <h1>QR Code</h1>
 *
 * @author  Blane Staskiewicz
 * @version 2.0
 * @since   2021-07-28
 */
public class QrCode extends Fragment {
    /*
    public fragment3() {
        super(R.layout.fragment3);
    }
    */
    
    /**
     * This method creates QR Code View
     *
     * @param  inflater             used to inflate layout
     * @param  viewGroup            view of QR Code tab
     * @param  savedInstanceState   state when oriented
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment3, viewGroup, false);
    }
}
