package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.vc.technologies.sam.R;

public class SubmitOder extends AppCompatActivity {

    MaterialButton continueOrdoring,goToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_oder);


        initView();
    }

    private void initView() {
        continueOrdoring=findViewById(R.id.continue_Ordoring);
        goToHome=findViewById(R.id.go_To_Home);

        continueOrdoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueOrdoring();
            }
        });

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });

    }

    private void goToHome() {
    }

    private void continueOrdoring() {
        startActivity(new Intent(SubmitOder.this,MainActivity.class));
    }
}