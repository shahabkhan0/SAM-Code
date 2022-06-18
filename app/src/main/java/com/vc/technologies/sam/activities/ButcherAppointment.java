package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vc.technologies.sam.R;
import com.vc.technologies.sam.appointments.Appointment;
import com.vc.technologies.sam.common.Common;
import com.vc.technologies.sam.fragment_rigstration.LoginFragment;
import com.vc.technologies.sam.utils.Utils;

public class ButcherAppointment extends AppCompatActivity {


    public static void go(Activity activity) {
        activity.startActivity(new Intent(activity, ButcherAppointment.class));
        activity.finish();
    }

    FrameLayout frameLayout;
    ImageView butcherProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butcher_appointment);


        frameLayout = findViewById(R.id.frame_layout_appointment);
        butcherProfile=findViewById(R.id.sign_out_butcher);

        butcherProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ButcherAppointment.this,Profile.class));
            }
        });
  

    }
}