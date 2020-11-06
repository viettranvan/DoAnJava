package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button forgotPassword, signIn, signUp;
    private EditText textInputUsername, textInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        validateUsername();
        validatePassword();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("loginTrue", true);
//                startActivity(intent);

                if(checkData()){
                    Toast.makeText(LoginActivity.this, textInputUsername.getText().toString() + "\n" +
                            textInputPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    setError();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "quen mat khau", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void anhXa() {

        signIn = findViewById(R.id.btn_sign_in);

        signUp = findViewById(R.id.btn_sign_up);
        forgotPassword = findViewById(R.id.btn_forgot_password_in_login);

        textInputUsername = findViewById(R.id.edt_username_login);
        textInputPassword = findViewById(R.id.edt_password_login);
    }

    private void validateUsername() {
        textInputUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorUsername();
                }
            }
        });
    }

    private void validatePassword() {
        textInputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    setErrorPassword();
                }
            }
        });
    }

    private void setError(){
        if(textInputUsername.getText().length() < 8){
            setErrorUsername();
        }
        if(textInputPassword.getText().length() < 8){
            setErrorPassword();
        }

    }

    // Kiểm tra độ dài nhập ở edittext có hợp lệ
    private boolean checkData(){
        int edtUsernameLength = textInputUsername.getText().toString().trim().length();
        int edtPasswordLength = textInputPassword.getText().toString().trim().length();

        if(edtUsernameLength < 8 || edtPasswordLength < 8){
            return false;
        }
        else{
            return true;
        }
    }

    private void setErrorUsername(){
        if (textInputUsername.getText().toString().length() <= 0) {
            textInputUsername.setError("Vui lòng nhập email hoặc tên tài khoản");
        } else if (textInputUsername.getText().toString().length() > 0 &&
                textInputUsername.getText().toString().length() < 8) {
            textInputUsername.setError("Tên đăng nhập phải dài ít nhất 8 ký tự");
        } else {
            textInputUsername.setError(null);
        }
    }

    private void setErrorPassword(){
        if (textInputPassword.getText().toString().length() <= 0) {
            textInputPassword.setError("Vui lòng nhập mật khẩu");
        } else if (textInputPassword.getText().toString().length() > 0 &&
                textInputPassword.getText().toString().length() < 6) {
            textInputPassword.setError("Mật khẩu phải chứa ít nhất 8 ký tự bao gồm chữ thường, " +
                    "chữ hoa và ký tự đặc biệt");
        } else {
            textInputPassword.setError(null);
        }
    }

}