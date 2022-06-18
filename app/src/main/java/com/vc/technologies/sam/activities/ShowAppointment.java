package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.adapter.PostsAdapter;
import com.vc.technologies.sam.adapter.UserAdapter;
import com.vc.technologies.sam.common.Common;
import com.vc.technologies.sam.interfaces.UserData;
import com.vc.technologies.sam.models.PostModel;
import com.vc.technologies.sam.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAppointment extends AppCompatActivity implements UserData {
    Button button;
    RecyclerView recyclerView;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appoinment);
        userData = this;
        button = findViewById(R.id.nextDetail);

        initView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowAppointment.this, showAppoinmentDetial.class));
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.show_Appoinment);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDataFromproduct();
    }

    private void fetchDataFromproduct() {
        final List<UserModel> tempList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot itemSnap : snapshot.getChildren()) {
                            UserModel userModel = itemSnap.getValue(UserModel.class);
                            tempList.add(userModel);
                        }
                        userData.onUserDataLoaded(tempList);



                        Log.i("aminullah", "onDataChange: user data" + tempList.size());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userData.onUserDataLoadedFailed(error.getMessage());
                    }
                });
    }

    @Override
    public void onUserDataLoaded(List<UserModel> dataModelList) {
        UserAdapter userAdapter = new UserAdapter(ShowAppointment.this, dataModelList);
        recyclerView.setAdapter(userAdapter);

    }

    @Override
    public void onUserDataLoadedFailed(String message) {
        Toast.makeText(ShowAppointment.this, message, Toast.LENGTH_SHORT).show();
    }
}




