package com.example.dalnoboy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dalnoboy.R;
import com.example.dalnoboy.client.MainActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);

        Button signInButton = findViewById(R.id.buttonSignIn);
        Button registerButton = findViewById(R.id.buttonRegister);

        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // закриває SignInActivity
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, com.example.dalnoboy.RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
