package com.example.dalnoboy.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders);

            setupNavigation();
            setupOrdersList();

        } catch (Exception e) {
            Log.e("OrdersActivity", "Initialization failed", e);
            Toast.makeText(this, "Ошибка при загрузке заказов", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupNavigation() {
        try {
            // Кнопки шапки
            findViewById(R.id.btn_notifications).setOnClickListener(v -> {
                Intent intent = new Intent(this, NotificationsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });

            // Нижнее меню
            findViewById(R.id.btn_home).setOnClickListener(v -> navigateToMain());
            findViewById(R.id.btn_orders).setOnClickListener(v ->
                    Toast.makeText(this, "Вы уже в заказах", Toast.LENGTH_SHORT).show());
            findViewById(R.id.btn_history).setOnClickListener(v ->
                    Toast.makeText(this, "История заказов", Toast.LENGTH_SHORT).show());
            findViewById(R.id.btn_settings).setOnClickListener(v -> navigateToSettings());

        } catch (Exception e) {
            Log.e("OrdersActivity", "Navigation setup failed", e);
            Toast.makeText(this, "Ошибка навигации", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupOrdersList() {
        try {
            RecyclerView recyclerView = findViewById(R.id.orders_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<OrderItem> orders = new ArrayList<>();
            orders.add(new OrderItem(
                    "Замовлення №1234",
                    "123 Tralalelo tralaia",
                    "456 crocodilo",
                    "7 days"
            ));
            orders.add(new OrderItem(
                    "Замовлення №12345",
                    "123 Tralalelo tralaia",
                    "456 crocodilo",
                    "77 days"
            ));

            recyclerView.setAdapter(new OrderAdapter(this, orders));

        } catch (Exception e) {
            Log.e("OrdersActivity", "Failed to load orders", e);
            Toast.makeText(this, "Ошибка загрузки списка заказов", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            Log.e("OrdersActivity", "Failed to open settings", e);
            Toast.makeText(this, "Не удалось открыть настройки", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        navigateToMain();
    }
}