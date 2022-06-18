package com.vc.technologies.sam.appointments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vc.technologies.sam.R;


public class Appointment extends Fragment {


    public Appointment() {
        // Required empty public constructor
    }

    public static Appointment newInstance() {

        return new Appointment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
  View view= inflater.inflate(R.layout.fragment_appointment, container, false);


    return view;
    }
}