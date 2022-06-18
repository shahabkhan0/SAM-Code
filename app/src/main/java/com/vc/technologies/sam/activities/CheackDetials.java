package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.common.Common;

public class CheackDetials extends AppCompatActivity {
    ImageView showItem, profile;
    TextView profileName, productName, categrory, detial, price;
    MaterialButton submitOder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheack_detials);
        initView();

    }

    private void initView() {
        showItem = findViewById(R.id.get_image);
        profile = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_Name);
        productName = findViewById(R.id.name_of_product);
        categrory = findViewById(R.id.category);
        detial = findViewById(R.id.detials_of_product);
        price = findViewById(R.id.price_of_product);
        submitOder = findViewById(R.id.submit_Oder);

        submitOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheackDetials.this, getOder.class));
            }
        });
        pitchCommonData();
    }

    private void pitchCommonData() {
        Glide.with(this).load(Common.SelectedItem.getImage()).into(showItem);
        productName.setText(Common.SelectedItem.getName());
        price.setText(new StringBuilder("Rs  ").append(Common.SelectedItem.getPrice()));

    }

}