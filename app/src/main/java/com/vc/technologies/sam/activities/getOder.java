package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.vc.technologies.sam.R;

public class getOder extends AppCompatActivity {

  MaterialButton submitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_oder);


        initView();
    }

    private void initView() {
        submitOrder=findViewById(R.id.submit_Oder);

        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();

            }
        });
    }

    private void submitOrder() {
        startActivity(new Intent(getOder.this,SubmitOder.class));
    }
}