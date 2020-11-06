package com.example.doancuoiky.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView goBackToLogin;
    private Button btnSendMail;
    private EditText edtEmail;
    private boolean isValidEmail = false;

//    private Button btnConfirmOTP,btnConfirmNewPassword;
//    private LinearLayout lSendMail,lConfirmOTP,lNewPassword;

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
                validateEmail();
                if(isValidEmail){
                    Toast.makeText(ForgotPasswordActivity.this, edtEmail.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void anhXa() {
        goBackToLogin = findViewById(R.id.icon_back_toolbar_forgot_password);
        btnSendMail = findViewById(R.id.btn_send_mail_forgot_password);
        edtEmail = findViewById(R.id.edt_email_forgot_password);

//        lSendMail = findViewById(R.id.layout_send_mail);
//        lConfirmOTP = findViewById(R.id.layout_OTP_code);
//        lNewPassword = findViewById(R.id.layout_new_password);
//        btnConfirmOTP = findViewById(R.id.btn_confirm_OTP);
//        btnConfirmNewPassword = findViewById(R.id.btn_confirm_new_password);
//
//        lConfirmOTP.setVisibility(View.GONE);
//        lNewPassword.setVisibility(View.GONE);
    }

    private void validateEmail(){
        String email = edtEmail.getText().toString().trim();

        if (email.length() <= 0) {
            edtEmail.setError("Vui lòng nhập email");
            isValidEmail = false;
        } else if (!email.matches(GlobalVariable.EMAIL_PATTERN)) {
            edtEmail.setError("Định dạng email không hợp lệ");
            isValidEmail = false;

        } else {
            edtEmail.setError(null);
            isValidEmail = true;
        }
    }
}

//    private boolean validatePassword() {
//        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
//        if (passwordInput.isEmpty()) {
//            textInputPassword.setError("Field can't be empty");
//            return false;
//        } else if (!GlobalVariable.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
//            textInputPassword.setError("Password too weak");
//            return false;
//        } else {
//            textInputPassword.setError(null);
//            return true;
//        }
//    }

//        btnConfirmOTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lConfirmOTP.setVisibility(View.GONE);
//                lNewPassword.setVisibility(View.VISIBLE);
//            }
//        });
//
//        btnConfirmNewPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
//                builder.setTitle("Thông báo");
//                builder.setMessage("Cập nhật mật khẩu thành công!");
//
//                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                builder.show();
//            }
//        });