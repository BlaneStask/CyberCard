package com.example.cybercardv02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * <h1>Home</h1>
 *
 * @author  Blane Staskiewicz
 * @version 2.0
 * @since   2021-07-28
 */
public class Home extends Fragment {
    /*
    public fragment1() {
        super(R.layout.fragment1);
    }
    */
    
    /**
     * This method creates Home View
     *
     * @param  inflater             used to inflate layout
     * @param  viewGroup            view of Home tab
     * @param  savedInstanceState   state when oriented
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment1, viewGroup, false);
    }
}
