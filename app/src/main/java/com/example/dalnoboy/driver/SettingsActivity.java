package com.example.dalnoboy.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupNavigation();
        initControls();
    }

    private void setupNavigation() {
        // Обработчик кнопки "Домой"
        findViewById(R.id.btn_home).setOnClickListener(v -> navigateToMain());

        // Обработчик кнопки "Уведомления"
        findViewById(R.id.btn_notifications).setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, NotificationsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Не удалось открыть уведомления", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработчик кнопки "Заказы" (машинка)
        findViewById(R.id.btn_orders).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(this, OrdersActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } catch (Exception e) {
                Log.e("Navigation", "Failed to open orders", e);
                Toast.makeText(this, "Не удалось открыть заказы", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработчик кнопки "Настройки"
        findViewById(R.id.btn_settings).setOnClickListener(v ->
                Toast.makeText(this, "Вы уже в настройках", Toast.LENGTH_SHORT).show());
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initControls() {
        initSwitches();
        initLanguageSpinner();
    }

    private void initSwitches() {
        SwitchMaterial soundSwitch = findViewById(R.id.sound_switch);
        SwitchMaterial themeSwitch = findViewById(R.id.theme_switch);

        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                showToast("Звуковые уведомления: " + (isChecked ? "вкл" : "выкл")));

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                showToast("Темная тема: " + (isChecked ? "вкл" : "выкл")));
    }

    private void initLanguageSpinner() {
        Spinner languageSpinner = findViewById(R.id.language_spinner);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast("Додаток буде перекладено на цю мову в майбутньому!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToMain();
    }
}