package com.example.dalnoboy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dalnoboy.client.BaseActivity;
import com.example.dalnoboy.driver.MainActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText lastName, firstName, phone, email, password;
    RadioGroup roleGroup;
    Button registerButton;
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        lastName = findViewById(R.id.surname);
        firstName = findViewById(R.id.name);
        phone = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        roleGroup = findViewById(R.id.roleGroup);
        registerButton = findViewById(R.id.buttonRegister);
        errorText = findViewById(R.id.errorText);

        // Initially disable register button
        registerButton.setEnabled(false);

        TextWatcher watcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                checkFields();
            }
        };

        lastName.addTextChangedListener(watcher);
        firstName.addTextChangedListener(watcher);
        phone.addTextChangedListener(watcher);
        email.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);

        roleGroup.setOnCheckedChangeListener((group, checkedId) -> checkFields());

        registerButton.setOnClickListener(v -> {
            if (!isAllValid()) {
                errorText.setText("Будь ласка, заповніть усі поля.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);

                int selectedRole = roleGroup.getCheckedRadioButtonId();
                Intent intent;

                if (selectedRole == R.id.role_customer) {
                    intent = new Intent(RegistrationActivity.this, com.example.dalnoboy.client.MainActivity.class);
                } else if (selectedRole == R.id.role_driver) {
                    intent = new Intent(RegistrationActivity.this, com.example.dalnoboy.driver.MainActivity.class);
                } else {
                    // Default to client if no role selected (shouldn't happen due to validation)
                    intent = new Intent(RegistrationActivity.this, com.example.dalnoboy.client.MainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        });
    }

    private void checkFields() {
        registerButton.setEnabled(isAllValid());
    }

    private boolean isAllValid() {
        return !lastName.getText().toString().isEmpty() &&
                !firstName.getText().toString().isEmpty() &&
                !phone.getText().toString().isEmpty() &&
                !email.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty() &&
                roleGroup.getCheckedRadioButtonId() != -1;
    }

    abstract class TextWatcherAdapter implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }
}