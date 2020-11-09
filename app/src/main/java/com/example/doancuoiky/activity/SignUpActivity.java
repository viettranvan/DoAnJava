package com.example.doancuoiky.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ImageView goBack;
    private Button signUp;
    private EditText edtEmail,edtUsername,edtFullName,edtPassword,edtPhoneNumber,edtConfirmPassword;
    private ProgressBar progressBar;
    private boolean isValidEmail = false;
    private boolean isValidPhone = false;

    String _email, _username, _name, _phone, _password, _confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhXa();

        validation();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkData()){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
//
//                    builder.setTitle("Thông báo");
//                    builder.setMessage("Đăng ký thành công, đăng nhập ngay");
//
//                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//
//                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    builder.show();
                    _email = edtEmail.getText().toString().trim();
                    _username = edtUsername.getText().toString().trim();
                    _name = validateNameFirstUpperCase(edtFullName.getText().toString().trim()) ;
                    _phone = edtPhoneNumber.getText().toString().trim();
                    _password = edtPassword.getText().toString().trim();
                    _confirmPassword = edtConfirmPassword.getText().toString().trim();

                    if(!_password.equals(_confirmPassword)){
                        edtConfirmPassword.setError("Mật khẩu không trùng khớp");
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();

                        Log.d("DATA1",
                                "abc:" + _email + "\n" +
                                        _username + "\n" +
                                        _name + "\n" +
                                        _phone + "\n" +
                                        _password + "\n" +
                                        _confirmPassword + "\n"
                        );
                    }
                }
                else {
                    checkError();
                }
            }
        });
    }



    private void validation() {
        validateEmail();
        validateUsername();
        validateFullName();
        validatePhoneNumber();
        validatePassword();
    }

    private void anhXa() {
        goBack = findViewById(R.id.icon_back_toolbar_sign_up);
        signUp = findViewById(R.id.btn_sign_up_sign_up);
        edtEmail = findViewById(R.id.edt_email_sign_up);
        edtUsername = findViewById(R.id.edt_username_sign_up);
        edtFullName = findViewById(R.id.edt_full_name_sign_up);
        edtPhoneNumber = findViewById(R.id.edt_phone_sign_up);
        edtPassword= findViewById(R.id.edt_password_sign_up);
        edtConfirmPassword= findViewById(R.id.edt_confirm_password_sign_up);
        progressBar = findViewById(R.id.progressBar_check_password);

        edtPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length() > 0){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int strength = 0;
                if (edtPassword.getText().toString().trim().matches(".*[a-z].*")){
                        strength+=10;
                }
                if (edtPassword.getText().toString().trim().matches(".*[A-Z].*")){
                    strength+=10;
                }
                if (edtPassword.getText().toString().trim().matches(".*[0-9].*")){
                    strength+=10;
                }
                if (edtPassword.getText().toString().trim().matches(".*[@#$%&+=^].*")){
                    strength+=10;
                }


                if(edtPassword.getText().length() > 0 && edtPassword.getText().length() < 8){
                    strength+=10;
                }
                else if(edtPassword.getText().length() >= 8 && edtPassword.getText().length() <= 12){
                    strength+=30;
                }
                else if(edtPassword.getText().length() > 12){
                    strength+=40;
                }

                progressBar.setProgress(strength);
            }
        });
    }

    private void validateEmail(){
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorEmail();
                }
            }
        });
    }

    private void validateUsername() {
        edtUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorUsername();
                }
            }
        });
    }

    private  void validateFullName(){
        edtFullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorFullName();
                }
            }
        });
    }

    private void validatePhoneNumber(){
        edtPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorPhoneNumber();
                }
            }
        });
    }

    private void validatePassword(){
        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    setErrorPassword();
                }
            }
        });
    }

    private String validateNameFirstUpperCase(String string){
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    // Kiểm tra độ dài nhập ở edittext có hợp lệ
    private boolean checkData(){

        int edtUsernameLength = edtUsername.getText().toString().trim().length();
        int edtPasswordLength = edtPassword.getText().toString().trim().length();


        if(edtUsernameLength >= 8 && edtPasswordLength >= 8 && isValidEmail && isValidPhone){
            return true;
        }
        else{
            return false;
        }
    }

    private void setErrorEmail(){
        if (edtEmail.getText().toString().trim().length() <= 0) {
            edtEmail.setError("Vui lòng nhập email");
            isValidEmail = false;
        } else if (!edtEmail.getText().toString().trim().matches(GlobalVariable.EMAIL_PATTERN)) {
            edtEmail.setError("Định dạng email không hợp lệ");
            isValidEmail = false;
        } else {
            edtEmail.setError(null);
            isValidEmail = true;
        }
    }

    private void setErrorUsername(){
        if (edtUsername.getText().toString().trim().length() <= 0) {
            edtUsername.setError("Vui lòng nhập tên tài khoản");
        } else if (edtUsername.getText().toString().trim().length() > 0
                && edtUsername.getText().toString().trim().length() < 8) {
            edtUsername.setError("Tên đăng nhập phải dài ít nhất 8 ký tự");
        } else {
            edtUsername.setError(null);
        }
    }

    private void setErrorFullName(){
        if (edtFullName.getText().toString().trim().length() <= 0) {
            edtFullName.setError("Vui lòng nhập họ tên của bạn");
        } else {
            edtFullName.setError(null);
        }
    }

    private void setErrorPhoneNumber(){
        if (edtPhoneNumber.getText().toString().trim().length() <= 0) {
            edtPhoneNumber.setError("Vui lòng nhập số điện thoại");
            isValidPhone = false;
        } else if (!edtPhoneNumber.getText().toString().trim().matches(GlobalVariable.PHONE_PATTERN)) {
            edtPhoneNumber.setError("Định dạng số điện thoại không hợp lệ");
            isValidPhone = false;
        } else {
            edtPhoneNumber.setError(null);
            isValidPhone = true;
        }
    }

    private void setErrorPassword(){
        if (edtPassword.getText().toString().trim().length() <= 0) {
            edtPassword.setError("Vui lòng nhập mật khẩu");
        } else if (!edtPassword.getText().toString().trim().matches(GlobalVariable.PASSWORD_PATTERN)) {
            edtPassword.setError("Định dạng mật khẩu không hợp lệ, mật khẩu phải gồm 8 ký tự bao gồm ký tự thường," +
                    " in hoa, chữ số và ký tự đặc biệt");
        } else {
            edtPassword.setError(null);
        }
    }


    private void checkError() {
        if(!isValidEmail){
            setErrorEmail();
        }
        if(!isValidPhone){
            setErrorPhoneNumber();
        }
        if(edtUsername.getText().toString().trim().length() < 8){
            setErrorUsername();
        }
        if(edtPassword.getText().toString().trim().length() < 8){
            setErrorPassword();
        }
        if(edtFullName.getText().toString().trim().length() == 0){
            setErrorFullName();
        }
    }
}

