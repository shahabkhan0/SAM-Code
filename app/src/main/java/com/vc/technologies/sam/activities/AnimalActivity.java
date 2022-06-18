package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.adapter.PostsAdapter;
import com.vc.technologies.sam.interfaces.IDataLoadlistner;
import com.vc.technologies.sam.models.PostModel;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity implements IDataLoadlistner {
    TextView photo;
    ImageView back;
    ProgressBar progressBar;

    RecyclerView recyclerView;
    IDataLoadlistner loadlistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        initViews();

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnimalActivity.this, UploadAnimalPhoto.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        loadlistner = this;
        photo = findViewById(R.id.upload_animal);
        recyclerView = findViewById(R.id.recycler_posts);
        progressBar=findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        back=findViewById(R.id.back_activity_animal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnimalActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAnimals();

    }

    private void fetchAnimals() {
        final List<PostModel> tempList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Animal")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot itemSnap : snapshot.getChildren()) {
                            PostModel postModel = itemSnap.getValue(PostModel.class);
                            tempList.add(postModel);
                        }
                        loadlistner.onDataLoaded(tempList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loadlistner.onDataLoadedFailed(error.getMessage());
                    }
                });
    }


    @Override
    public void onDataLoaded(List<PostModel> dataModelList) {
        progressBar.setVisibility(View.GONE);
        PostsAdapter postsAdapter = new PostsAdapter(AnimalActivity.this, dataModelList);
        recyclerView.setAdapter(postsAdapter);
    }

    @Override
    public void onDataLoadedFailed(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(AnimalActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}