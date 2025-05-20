package com.example.dalnoboy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private static final String TEST_CLIENT_LOGIN = "client@example.com";
    private static final String TEST_CLIENT_PASSWORD = "client123";
    private static final String TEST_DRIVER_LOGIN = "driver@example.com";
    private static final String TEST_DRIVER_PASSWORD = "driver123";

    private EditText editTextLogin, editTextPassword;
    private TextView loginErrorText, passwordErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in); //

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginErrorText = findViewById(R.id.loginErrorText);
        passwordErrorText = findViewById(R.id.passwordErrorText);

        Button signInButton = findViewById(R.id.buttonSignIn);
        Button registerButton = findViewById(R.id.buttonRegister);

        signInButton.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignInActivity.this, "Будь ласка, заповніть усі поля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TEST_CLIENT_LOGIN.equals(login)) {
                if (TEST_CLIENT_PASSWORD.equals(password)) {
                    Intent intent = new Intent(SignInActivity.this, com.example.dalnoboy.client.MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    passwordErrorText.setVisibility(View.VISIBLE);
                    loginErrorText.setVisibility(View.GONE);
                }
            } else if (TEST_DRIVER_LOGIN.equals(login)) {
                if (TEST_DRIVER_PASSWORD.equals(password)) {
                    Intent intent = new Intent(SignInActivity.this, com.example.dalnoboy.driver.MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    passwordErrorText.setVisibility(View.VISIBLE);
                    loginErrorText.setVisibility(View.GONE);
                }
            } else {
                loginErrorText.setVisibility(View.VISIBLE);
                passwordErrorText.setVisibility(View.GONE);
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        editTextLogin.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginErrorText.setVisibility(View.GONE);
            }
        });

        editTextPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordErrorText.setVisibility(View.GONE);
            }
        });
    }

    abstract class SimpleTextWatcher implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void afterTextChanged(Editable s) {}
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }
}
