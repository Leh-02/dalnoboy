package com.example.dalnoboy.client;

import android.content.Intent;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dalnoboy.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupBottomNav() {
        ImageButton btnHome = findViewById(R.id.free_icon_h);
        ImageButton btnCreate = findViewById(R.id.free_icon_c);
        ImageButton btnHistory = findViewById(R.id.free_icon_his);
        ImageButton btnSettings = findViewById(R.id.free_icon_s);

        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                if (!getClass().equals(MainActivity.class)) {
                    startActivity(new Intent(this, MainActivity.class));
                }
            });
        }

        if (btnCreate != null) {
            btnCreate.setOnClickListener(v -> {
                if (!getClass().equals(CreateActivity.class)) {
                    startActivity(new Intent(this, CreateActivity.class));
                }
            });
        }

        if (btnHistory != null) {
            btnHistory.setOnClickListener(v -> {
                if (!getClass().equals(HistoryActivity.class)) {
                    startActivity(new Intent(this, HistoryActivity.class));
                }
            });
        }

        if (btnSettings != null) {
            btnSettings.setOnClickListener(v -> {
                if (!getClass().equals(SettingsActivity.class)) {
                    startActivity(new Intent(this, SettingsActivity.class));
                }
            });
        }
    }
}
