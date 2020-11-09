package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView goBackChangPassword;
    private EditText edtCurrentPassword, edtNewPassword, edtConfirmPassword;
    private Button btnConfirmChangePassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        anhXa();

        validateCurrentPassword();
        validateNewPassword();
        validateConfirmPassword();

        goBackChangPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnConfirmChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String confirm = edtConfirmPassword.getText().toString().trim();
                if (checkData()) {
                    if (edtNewPassword.getText().toString().trim().equals(confirm)) {
                        Toast.makeText(ChangePasswordActivity.this, "success", Toast.LENGTH_SHORT).show();
                    } else {
                        edtConfirmPassword.setError("Mật khẩu không trùng khớp");
                    }
                } else {
                    setError();
                }
            }
        });

    }

    private boolean checkData() {

        int edtCurrentPasswordLength = edtCurrentPassword.getText().toString().trim().length();
        int edtNewPasswordLength = edtNewPassword.getText().toString().trim().length();
        int edtConfirmPasswordLength = edtConfirmPassword.getText().toString().trim().length();

        if (edtCurrentPasswordLength >= 8 && edtNewPasswordLength >= 8 && edtConfirmPasswordLength >= 8) {
            return true;
        } else {
            return false;
        }
    }

    private void anhXa() {
        goBackChangPassword = findViewById(R.id.iv_back_change_password);
        edtCurrentPassword = findViewById(R.id.edt_current_password_change_password);
        edtNewPassword = findViewById(R.id.edt_new_password_change_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password_change_password);
        btnConfirmChangePassword = findViewById(R.id.btn_sign_up_change_password);

        progressBar = findViewById(R.id.progressBar_check_password_change_password);

        edtNewPassword.addTextChangedListener(new TextWatcher() {

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
                if (edtNewPassword.getText().toString().trim().matches(".*[a-z].*")){
                    strength+=10;
                }
                if (edtNewPassword.getText().toString().trim().matches(".*[A-Z].*")){
                    strength+=10;
                }
                if (edtNewPassword.getText().toString().trim().matches(".*[0-9].*")){
                    strength+=10;
                }
                if (edtNewPassword.getText().toString().trim().matches(".*[@#$%&+=^].*")){
                    strength+=10;
                }


                if(edtNewPassword.getText().length() > 0 && edtNewPassword.getText().length() < 8){
                    strength+=10;
                }
                else if(edtNewPassword.getText().length() >= 8 && edtNewPassword.getText().length() <= 12){
                    strength+=30;
                }
                else if(edtNewPassword.getText().length() > 12){
                    strength+=40;
                }

                progressBar.setProgress(strength);
            }
        });
    }

    private void validateNewPassword() {
        edtNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    setErrorNewPassword();
                }
            }
        });
    }

    private void validateCurrentPassword() {
        edtNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    setErrorCurrentPassword();
                }
            }
        });
    }

    private void validateConfirmPassword() {
        edtNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    setErrorConfirmPassword();
                }
            }
        });
    }

    private void setErrorNewPassword() {

        if (edtNewPassword.getText().toString().trim().length() <= 0) {
            edtNewPassword.setError("Vui lòng nhập mật khẩu");
        } else if (!edtNewPassword.getText().toString().trim().matches(GlobalVariable.PASSWORD_PATTERN)) {
            edtNewPassword.setError("Định dạng mật khẩu không hợp lệ, mật khẩu phải gồm 8 ký tự bao gồm ký tự thường," +
                    " in hoa, chữ số và ký tự đặc biệt");
        } else {
            edtNewPassword.setError(null);
        }
    }

    private void setErrorCurrentPassword() {
        if (edtCurrentPassword.getText().toString().length() <= 0) {
            edtCurrentPassword.setError("Vui lòng nhập mật khẩu");
        } else if (edtCurrentPassword.getText().toString().length() > 0 &&
                edtCurrentPassword.getText().toString().length() < 8) {
            edtCurrentPassword.setError("Mật khẩu phải chứa ít nhất 8 ký tự bao gồm chữ thường, " +
                    "chữ hoa và ký tự đặc biệt");
        } else {
            edtCurrentPassword.setError(null);
        }
    }

    private void setErrorConfirmPassword() {
        if (edtConfirmPassword.getText().toString().length() <= 0) {
            edtConfirmPassword.setError("Vui lòng nhập mật khẩu");
        } else if (edtConfirmPassword.getText().toString().length() > 0 &&
                edtConfirmPassword.getText().toString().length() < 8) {
            edtConfirmPassword.setError("Mật khẩu phải chứa ít nhất 8 ký tự bao gồm chữ thường, " +
                    "chữ hoa và ký tự đặc biệt");
        } else {
            edtConfirmPassword.setError(null);
        }
    }

    private void setError() {
        if (edtCurrentPassword.getText().length() < 8) {
            setErrorCurrentPassword();
        }
        if (edtNewPassword.getText().length() < 8) {
            setErrorNewPassword();
        }
        if (edtConfirmPassword.getText().length() < 8) {
            setErrorConfirmPassword();
        }
    }

}