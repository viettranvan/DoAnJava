package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doancuoiky.R;
import com.example.doancuoiky.modal.Product;

import java.util.zip.Inflater;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView goBack;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        anhXa();

        setSupportActionBar(toolbar);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent i = getIntent();
        int pos = i.getIntExtra("productDetail",0);
        Toast.makeText(this, MainActivity.arrayProductNew.get(pos).getProductName() , Toast.LENGTH_SHORT).show();

    }




    private void anhXa() {
        textView = findViewById(R.id.product_detail_title);
        goBack = findViewById(R.id.iv_back_product_detail);
        toolbar = findViewById(R.id.toolbar_product_detail);

    }

    // them icon gio hang vao thanh toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // su kien khi icon gio hang tren thanh toolbar dc click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.ic_cart_toolbar:   //this item has your app icon
                Intent intent = new Intent(ProductDetailActivity.this,MainActivity.class);
                intent.putExtra("gotoCart","cart");

                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

}