package com.vc.technologies.sam.appointments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vc.technologies.sam.R;

public class Pinding extends Fragment {

    public Pinding() {
        // Required empty public constructor
    }

    public static Pinding newInstance() {

        return new Pinding();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pinding, container, false);



        return view;
    }
}