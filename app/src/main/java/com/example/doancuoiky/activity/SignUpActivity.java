package com.example.doancuoiky.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.doancuoiky.R;

public class SignUpActivity extends AppCompatActivity {

    private ImageView goBack;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhXa();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

                builder.setTitle("Thông báo");
                builder.setMessage("Đăng ký thành công, đăng nhập ngay");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void anhXa() {
        goBack = findViewById(R.id.icon_back_toolbar_sign_up);
        signUp = findViewById(R.id.btn_sign_up2);
    }
}


//    private boolean validateUsername(){
//        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
//
//        if (usernameInput.isEmpty()) {
//            textInputUsername.setError("Field can't be empty");
//
//            return false;
//        } else if (usernameInput.length() > 15) {
//            textInputUsername.setError("Username too long");
//            return false;
//        } else {
//            textInputUsername.setError(null);
//            return true;
//        }
//
//    }
//
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
//
//    public void confirmInput(View v) {
//        if ( !validateUsername() | !validatePassword()) {
//            return;
//        }
//
//        String input = "Username: " + textInputUsername.getEditText().getText().toString();
//        input += "\n";
//        input += "Password: " + textInputPassword.getEditText().getText().toString();
//        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
//    }