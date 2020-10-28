package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

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
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.modal.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvProductName,tvProductPrice;
    private ImageView goBack;
    Toolbar toolbar;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;

    private List<Photo> mListPhoto;


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

        tvProductName.setText(MainActivity.arrayProductNew.get(pos).getProductName());
        tvProductPrice.setText(MainActivity.arrayProductNew.get(pos).getProductPrice() + "");

    }

    private void anhXa() {
        tvProductName = findViewById(R.id.product_detail_name);
        tvProductPrice = findViewById(R.id.product_detail_price);
        goBack = findViewById(R.id.iv_back_product_detail);
        toolbar = findViewById(R.id.toolbar_product_detail);
        viewPager = findViewById(R.id.view_pager_photo_detail_photo);
        circleIndicator = findViewById(R.id.circle_indicator_detail);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(this,mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

    }

    private List<Photo> getListPhoto(){
        Intent i = getIntent();
        int pos = i.getIntExtra("productDetail",0);

        List<Photo> list = new ArrayList<>();
        list.add(new Photo(MainActivity.arrayProductNew.get(pos).getProductImage()));
        list.add(new Photo(R.drawable.vivo_banner_resize));
        list.add(new Photo(MainActivity.arrayProductNew.get(pos).getProductImage()));
        list.add(new Photo(R.drawable.samsum_banner_resize));
        list.add(new Photo(MainActivity.arrayProductNew.get(pos).getProductImage()));
        list.add(new Photo(R.drawable.xiaomi_banner_resize));

        return list;

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