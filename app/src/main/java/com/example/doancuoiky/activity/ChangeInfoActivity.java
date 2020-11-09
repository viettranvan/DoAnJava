package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.doancuoiky.R;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;

public class ChangeInfoActivity extends AppCompatActivity {

    private ImageView goBackChangeInfo;
    private RadioButton male,female;
    private Button update;
    private TextView birthday;
    private EditText edtFullName,edtIdentify, edtPhoneNumber, edtAddress;
    private ImageView avatar, openFile;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        anhXa();

        goBackChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeInfoActivity.this, "open file", Toast.LENGTH_SHORT).show();
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeInfoActivity.this,male.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeInfoActivity.this,female.getText(),Toast.LENGTH_SHORT).show();
            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToMainActivity();

                finish();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(ChangeInfoActivity.this,MainActivity.class);
                intent.putExtra("gotoProfile","profile");
        startActivity(intent);
    }

    private void showDateDialog() {

        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH); // tháng render từ 0-11
        int DATE = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
                String strDate = dateOfMonth + "/" + (month+1) + "/" + year;
                birthday.setText(strDate);
            }
        }, YEAR,MONTH , DATE);

        datePickerDialog.show();
    }

    private void anhXa() {
        goBackChangeInfo = findViewById(R.id.iv_back_change_info);
        male = findViewById(R.id.rd_gender_male_change_info);
        female = findViewById(R.id.rd_gender_female_change_info);
        update = findViewById(R.id.btn_update_change_info);
        birthday = findViewById(R.id.tv_birthday_change_info);
        edtFullName = findViewById(R.id.edt_username_change_info);
        edtIdentify = findViewById(R.id.edt_identification_card_change_info);
        edtPhoneNumber = findViewById(R.id.edt_phone_number_change_info);
        edtAddress = findViewById(R.id.edt_address_change_info);
        avatar = findViewById(R.id.img_avatar_change_info);
        openFile = findViewById(R.id.img_open_folder);


    }

}