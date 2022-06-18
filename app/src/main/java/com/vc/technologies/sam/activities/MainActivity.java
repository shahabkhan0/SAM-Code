package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vc.technologies.sam.R;

public class MainActivity extends AppCompatActivity {
    ImageView animal, product, butcher, veterinary, tracking;
    ImageView profile;


    public static void go(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


    }

    private void initViews() {

        animal = findViewById(R.id.animal_card);
        product = findViewById(R.id.prodoct_card);
        butcher = findViewById(R.id.butcher_card);
        veterinary = findViewById(R.id.doctor_card);
        tracking = findViewById(R.id.tracking_card);
        profile=findViewById(R.id.sign_out);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimalActivity.class);
                startActivity(intent);

            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);

            }
        });

        butcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAppointment.class);
                startActivity(intent);

            }
        });
        veterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAppointment.class);
                startActivity(intent);

            }
        });

        tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrackingActivity.class);
                startActivity(intent);

            }
        });


    }
}