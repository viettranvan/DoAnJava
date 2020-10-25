package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doancuoiky.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView goBackChangPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        anhXa();

        goBackChangPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void anhXa() {
        goBackChangPassword = findViewById(R.id.iv_back_change_password);
    }
}