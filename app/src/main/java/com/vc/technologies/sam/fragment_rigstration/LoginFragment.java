package com.vc.technologies.sam.fragment_rigstration;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vc.technologies.sam.activities.MainActivity;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.helper.Helper;
import com.vc.technologies.sam.utils.Utils;


public class LoginFragment extends Fragment {
    MaterialButton signup, login;
    FrameLayout frameLayout;
    EditText email, password;

    FirebaseAuth mAuth;


    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        initView(view);


        return view;
    }

    private void initView(View view) {
        signup = view.findViewById(R.id.sign_up);
        login = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email_address);
        password = view.findViewById(R.id.password);

        frameLayout = getActivity().findViewById(R.id.frame_layout);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.load_fragment(getActivity(), frameLayout, SignUpFragment.newInstance());

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheakInput();
            }
        });
    }

    private void CheakInput() {
        if (!Helper.isEmailvalid(email.getText().toString().trim())) {
            email.setError("incorrect");
            email.requestFocus();

        } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.setError("incorrect");
            password.requestFocus();

        } else {
            ContinueLogin(email.getText().toString().trim(), password.getText().toString().trim());
        }
    }

    private void ContinueLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                          task.getResult().getUser().getUid()
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