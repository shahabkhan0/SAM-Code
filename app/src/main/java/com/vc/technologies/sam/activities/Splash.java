package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.common.Common;
import com.vc.technologies.sam.models.UserModel;

public class Splash extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {
            fetchUserInfo(mAuth.getCurrentUser().getUid());
        } else {
            navigateTo(-1);  //-1 means navigating to registration screen
        }

    }

    private void fetchUserInfo(String uid) {
        FirebaseDatabase.getInstance().getReference("Users")
                .child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel currentUser = snapshot.getValue(UserModel.class);
                        if (currentUser != null) {
                            Common.CurrentUser = currentUser;
                            navigateTo(currentUser.getAccountType());
                        } else
                            navigateTo(-1); //-1 means navigating to registration screen

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        navigateTo(-1);  //-1 means navigating to registration screen
                    }
                });
    }

    private void navigateTo(int currentUser) {
        if (currentUser == 0) {
            MainActivity.go(Splash.this);
        } else if (currentUser == 1) {
            ButcherAppointment.go(Splash.this);
        } else if (currentUser == 2) {
            VeterinaryAppointment.go(Splash.this);
        } else {
            Registration.go(Splash.this);
        }
    }
}