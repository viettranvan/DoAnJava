package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doancuoiky.R;

public class LoginActivity extends AppCompatActivity {


    private Button forgotPassword,signIn, signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("yourBoolName", true);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"quen mat khau",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void anhXa() {

        signIn = findViewById(R.id.btn_sign_in);

        signUp = findViewById(R.id.btn_sign_up);
        forgotPassword = findViewById(R.id.btn_forgot_password_in_login);
    }
}