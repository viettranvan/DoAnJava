package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.doancuoiky.R;

public class ChangeInfoActivity extends AppCompatActivity {

    private ImageView iconBack;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        anhXa();


        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void anhXa() {
        iconBack = findViewById(R.id.iv_back);
    }

    private void changeInfoToolbar(){
//        getSupportActionBar(toolbar1);
    }

}