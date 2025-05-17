package com.example.dalnoboy.client;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;

import com.example.dalnoboy.R;

public class CreateActivity extends BaseActivity {

    private EditText phoneInput, lastNameInput, firstNameInput;
    private EditText deliveryNameInput, weightInput;
    private EditText pickUpStreetInput, deliveryStreetInput;
    private Spinner citySpinner1, citySpinner2;
    private Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_create_order);
        setupBottomNav();

        // 1. Пошук полів
        phoneInput = findViewById(R.id.phone_input);
        lastNameInput = findViewById(R.id.last_name_input);
        firstNameInput = findViewById(R.id.first_name_input);
        deliveryNameInput = findViewById(R.id.devivery_name);
        weightInput = findViewById(R.id.weight);
        pickUpStreetInput = findViewById(R.id.pick_up_street);
        deliveryStreetInput = findViewById(R.id.delivery_street);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // 2. Спінери (обидва однакові)
        citySpinner1 = findViewById(R.id.city_spinner1);
        citySpinner2 = findViewById(R.id.city_spinner2);

        String[] cities = {"Оберіть місто", "Київ", "Львів", "Харків", "Одеса", "Дніпро"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, R.id.spinner_text, cities
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.spinner_text);
                textView.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.spinner_text);
                textView.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };

        citySpinner1.setAdapter(adapter);
        citySpinner2.setAdapter(adapter);

        // 3. Додавання TextWatcher до всіх полів
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                checkFields();
            }
        };

        phoneInput.addTextChangedListener(watcher);
        lastNameInput.addTextChangedListener(watcher);
        firstNameInput.addTextChangedListener(watcher);
        deliveryNameInput.addTextChangedListener(watcher);
        weightInput.addTextChangedListener(watcher);
        pickUpStreetInput.addTextChangedListener(watcher);
        deliveryStreetInput.addTextChangedListener(watcher);

        // 4. Обробка вибору міст
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkFields();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        };

        citySpinner1.setOnItemSelectedListener(spinnerListener);
        citySpinner2.setOnItemSelectedListener(spinnerListener);

        // 5. Кнопка "Скасувати"
        btnCancel.setOnClickListener(v -> {
            phoneInput.setText("");
            lastNameInput.setText("");
            firstNameInput.setText("");
            deliveryNameInput.setText("");
            weightInput.setText("");
            pickUpStreetInput.setText("");
            deliveryStreetInput.setText("");
            citySpinner1.setSelection(0);
            citySpinner2.setSelection(0);
            finish(); // або startActivity(new Intent(this, MainActivity.class));
        });

        // 6. Кнопка "Оформити замовлення"
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(this, "Відправлення зареєстроване", Toast.LENGTH_SHORT).show();
            // Тут можна зберігати в БД, або переходити на інший екран
        });

        // Початково кнопка вимкнена
        checkFields();
    }

    // 7. Перевірка всіх полів
    private void checkFields() {
        boolean allFilled =
                !phoneInput.getText().toString().trim().isEmpty() &&
                        !lastNameInput.getText().toString().trim().isEmpty() &&
                        !firstNameInput.getText().toString().trim().isEmpty() &&
                        !deliveryNameInput.getText().toString().trim().isEmpty() &&
                        !weightInput.getText().toString().trim().isEmpty() &&
                        !pickUpStreetInput.getText().toString().trim().isEmpty() &&
                        !deliveryStreetInput.getText().toString().trim().isEmpty() &&
                        citySpinner1.getSelectedItemPosition() != 0 &&
                        citySpinner2.getSelectedItemPosition() != 0;

        btnSubmit.setEnabled(allFilled);
    }
}
