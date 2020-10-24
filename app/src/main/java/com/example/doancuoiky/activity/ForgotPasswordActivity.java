package com.example.doancuoiky.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doancuoiky.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView goBackToLogin;
    private Button btnSendMail,btnConfirmOTP,btnConfirmNewPassword;
    private LinearLayout lSendMail,lConfirmOTP,lNewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        anhXa();

        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lSendMail.setVisibility(View.GONE);
                lConfirmOTP.setVisibility(View.VISIBLE);
            }
        });

        btnConfirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lConfirmOTP.setVisibility(View.GONE);
                lNewPassword.setVisibility(View.VISIBLE);
            }
        });

        btnConfirmNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Cập nhật mật khẩu thành công!");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }

    private void anhXa() {
        goBackToLogin = findViewById(R.id.icon_back_toolbar_forgot_password);
        lSendMail = findViewById(R.id.layout_send_mail);
        lConfirmOTP = findViewById(R.id.layout_OTP_code);
        lNewPassword = findViewById(R.id.layout_new_password);
        btnSendMail = findViewById(R.id.btn_send_mail);
        btnConfirmOTP = findViewById(R.id.btn_confirm_OTP);
        btnConfirmNewPassword = findViewById(R.id.btn_confirm_new_password);

        lConfirmOTP.setVisibility(View.GONE);
        lNewPassword.setVisibility(View.GONE);
    }
}