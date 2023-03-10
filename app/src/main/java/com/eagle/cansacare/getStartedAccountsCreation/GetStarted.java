package com.eagle.cansacare.getStartedAccountsCreation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.eagle.cansacare.R;

public class GetStarted extends AppCompatActivity {

    private Button btnCreateAccount;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started);


        btnCreateAccount = findViewById(R.id.createAccountBtn);
        btnCreateAccount.setOnClickListener(view -> {
          Intent createAccount= new Intent(GetStarted.this, CreateAccount.class);
          startActivity(createAccount);
        });

        btnLogin = findViewById(R.id.signInBtn);
        btnLogin.setOnClickListener(view -> {
            Intent signIn = new Intent(GetStarted.this, Login.class);
            startActivity(signIn);
        });


    }
}
