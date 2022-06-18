package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class FeedActivity extends AppCompatActivity implements IDataLoadlistner {
    TextView upload_photo;
    RecyclerView recyclerView;
    IDataLoadlistner loadlistner;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        initview();
    }

    private void initview() {
        loadlistner = this;

        upload_photo=findViewById(R.id.upload_Feed);
        recyclerView=findViewById(R.id.recycler_product_posts);
        progressBar=findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        upload_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FeedActivity.this,productUpload.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        fetchDataFromproduct();
    }


    private void fetchDataFromproduct() {
        final List<PostModel> tempList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Product")
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
        PostsAdapter postsAdapter = new PostsAdapter(FeedActivity.this, dataModelList);
        recyclerView.setAdapter(postsAdapter);
    }

    @Override
    public void onDataLoadedFailed(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(FeedActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}