package com.example.dalnoboy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dalnoboy.client.MainActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText lastName, firstName, phone, email, password;
    RadioGroup roleGroup;
    Button registerButton;
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        // Виправлення відповідностей з XML
        lastName = findViewById(R.id.surname);
        firstName = findViewById(R.id.name);
        phone = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        roleGroup = findViewById(R.id.roleGroup);
        registerButton = findViewById(R.id.buttonRegister);
        errorText = findViewById(R.id.errorText);

        // Додавання TextWatcher
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

        // Обробка натискання кнопки
        registerButton.setOnClickListener(v -> {
            if (!isAllValid()) {
                errorText.setText("Будь ласка, заповніть усі поля.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);

                // Поки що завжди переходимо до клієнтського інтерфейсу
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // щоб користувач не повернувся назад на реєстрацію
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

    // Спрощений TextWatcher
    abstract class TextWatcherAdapter implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }
}
