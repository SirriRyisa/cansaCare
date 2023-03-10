package com.eagle.cansacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class CreateAccount extends AppCompatActivity {

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        View newAccountBackArrowBtn = findViewById(R.id.back_arrow);
        newAccountBackArrowBtn.setOnClickListener(view -> {
            Intent previousScreen = new Intent(CreateAccount.this, GetStarted.class);
            startActivity(previousScreen);
        });

        MaterialButton createAccountBtn = findViewById(R.id.createAccountBtn);
        TextInputEditText firstname = findViewById(R.id.firstnameEditText);
        TextInputEditText lastname = findViewById(R.id.lastnameEditText);
        TextInputEditText emailTextView = findViewById(R.id.emailEditText);
        TextInputEditText passwordTextView = findViewById(R.id.passwordEditText);
        MaterialButton goToLogin = findViewById(R.id.loginLink);

        progressDialog = new ProgressDialog(this);
        createAccountBtn.setOnClickListener(v -> {
            String firstName= Objects.requireNonNull(firstname.getText()).toString().trim();
            String lastName = Objects.requireNonNull(lastname.getText()).toString().trim();
            String email = Objects.requireNonNull(emailTextView.getText()).toString().trim();
            String password = Objects.requireNonNull(passwordTextView.getText()).toString();


            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener(authResult -> {
//                                Todo replace MainActivity with actual activity
                        startActivity(new Intent(CreateAccount.this, ProfileSetup.class));
                        progressDialog.cancel();


                        firebaseFirestore.collection("User")
                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                .set(new UserModel(firstName,lastName,email,password));

                    })


                    .addOnFailureListener(e -> {
                        Toast.makeText(CreateAccount.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    });




        });

        goToLogin.setOnClickListener(v -> startActivity(new Intent(CreateAccount.this,Login.class)));


    }




}
