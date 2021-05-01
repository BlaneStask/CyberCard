package com.example.cybercardv02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Contact extends Fragment {
    /*
    public fragment2() {
        super(R.layout.fragment2);
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment2, viewGroup, false);
    }
}
