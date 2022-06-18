package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vc.technologies.sam.R;

public class showAppoinmentDetial extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appoinment_detial);


     button=findViewById(R.id.book_Appoinment);

     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(showAppoinmentDetial.this,Appoinment.class));
         }
     });
    }
}