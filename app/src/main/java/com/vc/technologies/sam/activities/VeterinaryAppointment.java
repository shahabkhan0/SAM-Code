package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vc.technologies.sam.R;

public class VeterinaryAppointment extends AppCompatActivity {

    public static void go(Activity activity) {
        activity.startActivity(new Intent(activity, VeterinaryAppointment.class));
        activity.finish();
    }

   ImageView verterinaryProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_appointment);



         verterinaryProfile=findViewById(R.id.sign_out_doctor);

         verterinaryProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(VeterinaryAppointment.this,Profile.class);
                 startActivity(intent);
             }
         });
    }
}