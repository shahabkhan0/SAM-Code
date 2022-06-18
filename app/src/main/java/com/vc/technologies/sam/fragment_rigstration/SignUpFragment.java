package com.vc.technologies.sam.fragment_rigstration;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.vc.technologies.sam.activities.MainActivity;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.common.Common;
import com.vc.technologies.sam.models.UserModel;
import com.vc.technologies.sam.helper.Helper;
import com.vc.technologies.sam.utils.Utils;


public class SignUpFragment extends Fragment {
    EditText full_name, new_email,mobileNumber, new_password;
    MaterialButton signUp;
    TextView login;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FrameLayout frameLayout;
    TextView updateText;
    CardView dropmenu;
    int accountType = -1;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {

        return new SignUpFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initView(view);


        return view;
    }

    private void initView(View view) {
        full_name = view.findViewById(R.id.full_name);
        new_email = view.findViewById(R.id.new_email_address);
        mobileNumber = view.findViewById(R.id.mobile_Number);
        new_password = view.findViewById(R.id.new_password);
        signUp = view.findViewById(R.id.sign_up_new);
        login = view.findViewById(R.id.back_login);
        frameLayout = getActivity().findViewById(R.id.frame_layout);
        dropmenu = view.findViewById(R.id.account_type_menu);
        updateText=view.findViewById(R.id.account_type);

        dropmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDropDown(view);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.load_fragment(getActivity(), frameLayout, LoginFragment.newInstance());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheackInput();
            }
        });
    }

    private void showDropDown(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Customer:
                        accountType = 0;
                        updateText.setText("Customor");
                        return true;
                    case R.id.Doctor:
                        accountType = 2;
                        updateText.setText("Doctor");
                        return true;
                    case R.id.Butcher:
                        accountType = 1;
                        updateText.setText("Bucher");
                        return true;
                    default:
                        return false;

                }
            }
        });
        popup.inflate(R.menu.signup_drop_menu);
        popup.show();
    }

    private void cheackInput() {


        if (TextUtils.isEmpty(full_name.getText().toString().trim())) {
            full_name.setError("Fill Full Name");
            full_name.requestFocus();


        }
        if (TextUtils.isEmpty(mobileNumber.getText().toString().trim())) {
            full_name.setError("Fill Mobile Number");
            full_name.requestFocus();


        }else if (!Helper.isEmailvalid(new_email.getText().toString().trim())) {
            new_email.setError("incorrect");
            new_email.requestFocus();

        } else if (TextUtils.isEmpty(new_password.getText().toString().trim())) {
            new_password.setError("incorrect");
            new_password.requestFocus();

        } else if (accountType == -1) {
            Toast.makeText(getActivity(), "No account type selected yet", Toast.LENGTH_SHORT).show();
        } else {
            ContinueRegistration();
        }

    }

    private void ContinueRegistration() {
        mAuth.createUserWithEmailAndPassword(new_email.getText().toString().trim(),
                new_password.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = task.getResult().getUser();
                            uploadtofirebase(user);
                        } else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void uploadtofirebase(FirebaseUser user) {
        UserModel userModel = new UserModel();
        userModel.setUid(user.getUid());
        userModel.setName(full_name.getText().toString().trim());
        userModel.setEmail(new_email.getText().toString().trim());
        userModel.setPassword(new_password.getText().toString().trim());
        userModel.setMobileNo(mobileNumber.getText().toString().trim());
        userModel.setAccountType(accountType);


        FirebaseDatabase.getInstance().getReference("Users")
                .child(user.getUid())
                .setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Common.CurrentUser = userModel;
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

}