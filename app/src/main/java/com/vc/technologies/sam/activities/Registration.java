package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.vc.technologies.sam.R;
import com.vc.technologies.sam.fragment_rigstration.LoginFragment;
import com.vc.technologies.sam.utils.Utils;

public class Registration extends AppCompatActivity {


    public static void go(Activity activity) {
        activity.startActivity(new Intent(activity, Registration.class));
        activity.finish();
    }


    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        frameLayout = findViewById(R.id.frame_layout);

        Utils.load_fragment(this, frameLayout, LoginFragment.newInstance());
    }
}