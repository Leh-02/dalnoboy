package com.example.dalnoboy.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notifications);

            setupNavigation();
            setupNotificationsList();

        } catch (Exception e) {
            Log.e("NOTIFICATIONS", "Activity creation failed", e);
            Toast.makeText(this, "Ошибка при загрузке уведомлений", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupNavigation() {
        try {
            // Кнопка уведомлений
            findViewById(R.id.btn_notifications).setOnClickListener(v ->
                    Toast.makeText(this, "Вы уже в уведомлениях", Toast.LENGTH_SHORT).show());

            // Кнопка профиля
            findViewById(R.id.btn_profile).setOnClickListener(v ->
                    Toast.makeText(this, "Профиль пользователя", Toast.LENGTH_SHORT).show());

            // Нижнее меню
            findViewById(R.id.btn_home).setOnClickListener(v -> navigateToMain());
            findViewById(R.id.btn_orders).setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(NotificationsActivity.this, OrdersActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                } catch (Exception e) {
                    Log.e("Navigation", "Failed to open orders", e);
                    Toast.makeText(NotificationsActivity.this,
                            "Не удалось открыть заказы", Toast.LENGTH_SHORT).show();
                }
            });
            findViewById(R.id.btn_history).setOnClickListener(v -> showComingSoon("История"));
            findViewById(R.id.btn_settings).setOnClickListener(v -> navigateToSettings());

        } catch (Exception e) {
            Log.e("NAVIGATION", "Setup failed", e);
            Toast.makeText(this, "Ошибка настройки навигации", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupNotificationsList() {
        try {
            RecyclerView recyclerView = findViewById(R.id.notifications_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<NotificationItem> notifications = new ArrayList<>();
            notifications.add(new NotificationItem(
                    "Нет новых уведомлений",
                    "Здесь будут появляться ваши уведомления о новых заказах и сообщениях",
                    ""));

            NotificationAdapter adapter = new NotificationAdapter(notifications);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            Log.e("NOTIFICATIONS", "RecyclerView setup failed", e);
            Toast.makeText(this, "Ошибка загрузки списка", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void navigateToSettings() {
        try {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception e) {
            Log.e("NAVIGATION", "Settings open failed", e);
            Toast.makeText(this, "Не удалось открыть настройки", Toast.LENGTH_SHORT).show();
        }
    }

    private void showComingSoon(String feature) {
        Toast.makeText(this, feature + " - скоро будет доступно", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        navigateToMain();
    }
}