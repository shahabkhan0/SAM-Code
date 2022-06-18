package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.models.PostModel;

import java.io.IOException;
import java.util.UUID;

public class UploadAnimalPhoto extends AppCompatActivity {
    ImageView photo_upload;
    EditText name_upload, price_upload,detial;
    MaterialButton data_upload;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_animal_photo);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);


        initview();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                photo_upload.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }


    }

    private void initview() {

        photo_upload = findViewById(R.id.animal_photo);
        name_upload = findViewById(R.id.animal_name);
        price_upload = findViewById(R.id.animal_price);
        data_upload = findViewById(R.id.data_upload);
        detial=findViewById(R.id.animal_Detial);

        photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."),
                        PICK_IMAGE_REQUEST);

            }
        });


        data_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {

                    // Code for showing progressDialog while uploading

                    // Defining the child of storageReference
                    StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                    // adding listeners on upload
                    // or failure of image

                    ref.putFile(filePath)
                            .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if(task.isSuccessful()){
                                        ref.getDownloadUrl().addOnSuccessListener(
                                                new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(@NonNull Uri uri) {
                                                        PostModel postModel=new PostModel();
                                                        postModel.setImage(uri.toString());
                                                        postModel.setName(name_upload.getText().toString().trim());
                                                        postModel.setPrice(price_upload.getText().toString().trim());
                                                        postModel.setName(detial.getText().toString().trim());
                                                        postModel.setUserUid("0");

                                                        uploadDataToFirebase(postModel);
                                                    }
                                                });

                                    }
                                    Toast.makeText(UploadAnimalPhoto.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnSuccessListener(
                                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot snapshot) {


                                        }
                                    })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {


                                    Toast.makeText(UploadAnimalPhoto.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });

    }

    private void uploadDataToFirebase(PostModel postModel) {

        String key = FirebaseDatabase.getInstance().getReference("Animal").push().getKey();

        FirebaseDatabase.getInstance().getReference("Animal")
                .child(key)
                .setValue(postModel)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadAnimalPhoto.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UploadAnimalPhoto.this,"uploaded",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}