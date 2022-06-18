package com.vc.technologies.sam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.common.Common;

public class Profile extends AppCompatActivity {
    MaterialButton logout;
    ImageView back;
    TextView name,email,password,typeAccount,phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initview();
    }

    private void initview() {
        logout = findViewById(R.id.log_out);
        back = findViewById(R.id.back_activity_profile);
        name = findViewById(R.id.profile_Name_show);
        email = findViewById(R.id.profile_Email_show);
        password = findViewById(R.id.profile_Password_show);
        typeAccount = findViewById(R.id.account_type_profile);
        phoneNumber = findViewById(R.id.phone_number_profile);



        name.setText(Common.CurrentUser.getName());
        email.setText(Common.CurrentUser.getEmail());
        password.setText(Common.CurrentUser.getPassword());
        phoneNumber.setText(Common.CurrentUser.getMobileNo());
        if (Common.CurrentUser.getAccountType() == 0){
            typeAccount.setText("Custmor");
        }
        else if (Common.CurrentUser.getAccountType() == 1){
            typeAccount.setText("Butcher");
        }
        else if(Common.CurrentUser.getAccountType() == 2){
            typeAccount.setText("Verternery");
        }else{
            typeAccount.setText("Not select Account Type");
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });


        signout();
    }

    private void signout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                finish();
            }
        });
    }
}