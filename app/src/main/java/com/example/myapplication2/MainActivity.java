package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            setupNavigation();
        } catch (Exception e) {
            Log.e("MainActivity", "Initialization failed", e);
            Toast.makeText(this, "Ошибка инициализации приложения", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupNavigation() {
        // Кнопка уведомлений
        findViewById(R.id.btn_notifications).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } catch (Exception e) {
                Log.e("Navigation", "Failed to open notifications", e);
                Toast.makeText(MainActivity.this,
                        "Не удалось открыть уведомления", Toast.LENGTH_SHORT).show();
            }
        });

        // Кнопка профиля
        findViewById(R.id.btn_profile).setOnClickListener(v -> {
            Toast.makeText(this, "Раздел профиля в разработке", Toast.LENGTH_SHORT).show();
        });

        // Кнопка главной (уже на главной)
        findViewById(R.id.btn_home).setOnClickListener(v -> {
            Toast.makeText(this, "Вы уже на главной странице", Toast.LENGTH_SHORT).show();
        });

        // Кнопка заказов
        findViewById(R.id.btn_orders).setOnClickListener(v -> {
            Toast.makeText(this, "Раздел заказов в разработке", Toast.LENGTH_SHORT).show();
        });

        // Кнопка истории
        findViewById(R.id.btn_history).setOnClickListener(v -> {
            Toast.makeText(this, "Раздел истории в разработке", Toast.LENGTH_SHORT).show();
        });

        // Кнопка настроек
        findViewById(R.id.btn_settings).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } catch (Exception e) {
                Log.e("Navigation", "Failed to open settings", e);
                Toast.makeText(MainActivity.this,
                        "Не удалось открыть настройки", Toast.LENGTH_SHORT).show();
            }
        });
        // Кнопка заказов (машинка)
        findViewById(R.id.btn_orders).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } catch (Exception e) {
                Log.e("Navigation", "Failed to open orders", e);
                Toast.makeText(MainActivity.this,
                        "Не удалось открыть заказы", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Сброс выделения кнопок при возврате на главную
        resetButtonSelections();
    }

    private void resetButtonSelections() {
        findViewById(R.id.btn_home).setSelected(true);
        findViewById(R.id.btn_orders).setSelected(false);
        findViewById(R.id.btn_history).setSelected(false);
        findViewById(R.id.btn_settings).setSelected(false);
    }

    @Override
    public void onBackPressed() {
        // Стандартное поведение - выход из приложения
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}