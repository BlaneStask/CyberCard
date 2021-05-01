package com.example.cybercardv02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class QrCode extends Fragment {
    /*
    public fragment3() {
        super(R.layout.fragment3);
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment3, viewGroup, false);
    }
}
