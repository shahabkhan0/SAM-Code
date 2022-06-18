package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vc.technologies.sam.R;

public class Appoinment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);
        getSupportActionBar().hide();
    }
}