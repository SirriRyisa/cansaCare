package com.eagle.cansacare.getStartedAccountsCreation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.eagle.cansacare.R;
import com.eagle.cansacare.profileSetUp.ProfileSetup;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Login extends AppCompatActivity {


    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        View loginBackArrowBtn = findViewById(R.id.back_arrow);
        loginBackArrowBtn.setOnClickListener(view -> {
            Intent previousScreen = new Intent(Login.this, GetStarted.class);
            startActivity(previousScreen);
        });


        MaterialButton loginBtn = findViewById(R.id.loginBtn);
        MaterialButton forgotPassword = findViewById(R.id.forgotPasswordButton);
        MaterialButton goToSignup = findViewById(R.id.createUserLink);
        TextInputEditText emailTextView = findViewById(R.id.emailEditText);
        TextInputEditText passwordTextView = findViewById(R.id.passwordEditText);


        progressDialog = new ProgressDialog(this);
        loginBtn.setOnClickListener(v -> {
            String email = Objects.requireNonNull(emailTextView.getText()).toString().trim();
            String password = passwordTextView.getText().toString();

            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        startActivity(new Intent(Login.this, ProfileSetup.class));
                        progressDialog.cancel();


                    })

                    .addOnFailureListener(e -> {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    });
        });

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {

            String Email = account.getEmail();

            emailTextView.setText(Email);

        }


        forgotPassword.setOnClickListener(v -> {
            String email = Objects.requireNonNull(emailTextView.getText()).toString().trim();
            progressDialog.setTitle("Sending Mail");
            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(unused -> {
                        progressDialog.cancel();
                        Toast.makeText(Login.this, "Email Sent", Toast.LENGTH_SHORT).show();

                    })
                    .addOnFailureListener(e -> {
                        progressDialog.cancel();
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });


        });

        goToSignup.setOnClickListener(v -> startActivity(new Intent(Login.this, CreateAccount.class)));


    }


}