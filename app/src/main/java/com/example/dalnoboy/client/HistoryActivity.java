package com.example.dalnoboy.client;

import android.os.Bundle;

import com.example.dalnoboy.R;

public class HistoryActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_history);

        setupBottomNav();
    }
}
