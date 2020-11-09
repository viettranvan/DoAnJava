package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
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

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;

public class ChangeInfoActivity extends AppCompatActivity {

    private ImageView goBackChangeInfo;
    private RadioButton male,female;
    private Button update;
    private TextView birthday;
    private EditText edtFullName, edtEmail, edtIdentify, edtPhoneNumber, edtAddress;
    private ImageView avatar, openFile;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        anhXa();
        setOnClick();

    }

    private void setOnClick() {
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
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        // permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show pop up
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else{
                        // permission already granted
                        pickImageFromGallery();
                    }
                }else{
                    // system os is less then marshmallow
                    pickImageFromGallery();
                }
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

    private void pickImageFromGallery() {
        // intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    // handle result for runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permission was granted
                    pickImageFromGallery();
                }
                else{
                    // permission was denied
                    Toast.makeText(this, "Vui lòng cấp quyền...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // handle result for pick image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            // set image to image view
            avatar.setImageURI(data.getData());
        }
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
        edtEmail = findViewById(R.id.edt_email_change_info);
        edtIdentify = findViewById(R.id.edt_identification_card_change_info);
        edtPhoneNumber = findViewById(R.id.edt_phone_number_change_info);
        edtAddress = findViewById(R.id.edt_address_change_info);
        avatar = findViewById(R.id.img_avatar_change_info);
        openFile = findViewById(R.id.img_open_folder);

        edtFullName.setText(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_USER_NAME));
        edtEmail.setText(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_EMAIL));
        edtIdentify.setText(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_CITIZEN_IDENTIFICATION));
        edtPhoneNumber.setText(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_PHONE_NUMBER));
        edtAddress.setText(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_ADDRESS));

        if(GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_ADDRESS).length() == 0 ||
                GlobalVariable.arrayProfile.get(GlobalVariable.INDEX_ADDRESS).equals("0")){
            male.setChecked(true);
        }
        else{
            female.setChecked(true);
        }
    }

}