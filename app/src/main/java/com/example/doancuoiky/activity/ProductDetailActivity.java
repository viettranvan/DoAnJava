package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.adapter.PhotoProductAdapter;
import com.example.doancuoiky.adapter.ProductAdapter;
import com.example.doancuoiky.fragment.CartFragment;
import com.example.doancuoiky.fragment.ProductFragment;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.modal.PhotoProduct;
import com.example.doancuoiky.modal.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvProductName,tvProductPrice, tvDescription;
    private ImageView goBack;
    Toolbar toolbar;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Button addToCart;
    private int pos = 0;
    ArrayAdapter<Product> adapter;
    ListView description;
    ArrayList<String> arraySpecifications;
    int indexProductInCart = -1;
    MainActivity m;

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



        Intent intent = getIntent();
        String idProduct = intent.getStringExtra("productDetail");

        for(int ii = 0;ii < GlobalVariable.arrayProduct.size();ii++){
            if(GlobalVariable.arrayProduct.get(ii).getProductID().equals(idProduct)){
                pos = ii;
                break;
            }
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // them vao gio hang
                GlobalVariable.arrayProduct.get(pos).setAddToCart(true);
                ProductFragment.productAdapter.notifyDataSetChanged();

                String id = GlobalVariable.arrayProduct.get(pos).getProductID();
                String typeId = GlobalVariable.arrayProduct.get(pos).getProductTypeID();
                String name = GlobalVariable.arrayProduct.get(pos).getProductName();
                String description = GlobalVariable.arrayProduct.get(pos).getProductDescription();
                int price = GlobalVariable.arrayProduct.get(pos).getProductPrice();
                String image = GlobalVariable.arrayProduct.get(pos).getProductImage();

                if(GlobalVariable.arrayCart.size() > 0){
                    for(int i = 0;i < GlobalVariable.arrayCart.size();i++){
                        if(GlobalVariable.arrayCart.get(i).getID().equals(id)){
                            indexProductInCart = i;
                            break;
                        }
                    }
                }

                if(indexProductInCart == -1){
                    Cart cart = new Cart(id,typeId,name,description,price,image,1);
                    GlobalVariable.arrayCart.add(cart);
                    MainActivity.setCountProductInCart(GlobalVariable.arrayCart.size());
                }else{
                    Cart cart = GlobalVariable.arrayCart.get(indexProductInCart);

                    int newCount = cart.getCount();
                    if(cart.getCount() < 10){
                        newCount += 1;
                    }
                    GlobalVariable.arrayCart.set(indexProductInCart,new Cart(id,typeId,name,description,price,image,newCount));
                    CartFragment.updateTotalPrice();
                    CartFragment.cartAdapter.notifyDataSetChanged();
                }
                Toast.makeText(ProductDetailActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

            }
        });

        setPhotoAdapter(GlobalVariable.arrayProduct.get(pos).getProductID());
        setDataSpecifications(GlobalVariable.arrayProduct.get(pos).getProductID());

        tvProductName.setText(GlobalVariable.arrayProduct.get(pos).getProductName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String _price = decimalFormat.format(GlobalVariable.arrayProduct.get(pos).getProductPrice())  + " đ";
        tvProductPrice.setText(_price);

        tvDescription.setText(GlobalVariable.arrayProduct.get(pos).getProductDescription());


    }

    @SuppressLint("ClickableViewAccessibility")
    private void anhXa() {

        tvProductName = findViewById(R.id.product_detail_name);
        tvProductPrice = findViewById(R.id.product_detail_price);
        goBack = findViewById(R.id.iv_back_product_detail);
        toolbar = findViewById(R.id.toolbar_product_detail);
        viewPager = findViewById(R.id.view_pager_photo_detail_photo);
        circleIndicator = findViewById(R.id.circle_indicator_detail);
        addToCart = findViewById(R.id.btn_add_product_to_cart);
        tvDescription = findViewById(R.id.tv_description_detail_activity);

        description = findViewById(R.id.lv_description);

        description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    private void setDataSpecifications(final String idProduct) {
        arraySpecifications = new ArrayList<>();

        final StringRequest request = new StringRequest(StringRequest.Method.GET, GlobalVariable.PRODUCT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray data = object.getJSONArray("data");
                    int position = 0;
                    for(int index = 0;index < data.length();index++){
                        JSONObject result = (JSONObject) data.get(index);
                        String id = result.getString("id_product");
                        if(idProduct.equals(id)){
                            position = index;
                        }
                    }
                    JSONObject result = (JSONObject) data.get(position);
                    JSONArray specifications = result.getJSONArray("specifications");
                    for(int i = 0;i < specifications.length();i++){
                        arraySpecifications.add(specifications.get(i).toString());
                    }
                    adapter = new ArrayAdapter(ProductDetailActivity.this,android.R.layout.simple_list_item_1,arraySpecifications);
                    description.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(ProductDetailActivity.this);
        queue.add(request);
    }


    private void setPhotoAdapter(final String idProduct){
        final ArrayList<PhotoProduct> imgss = new ArrayList<>();
        StringRequest request = new StringRequest(StringRequest.Method.POST, GlobalVariable.PRODUCT_IMAGE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray data = object.getJSONArray("data");
                            for(int i = 0;i < data.length();i++){
                                JSONObject img = (JSONObject) data.get(i);
                                String image = img.getString("image");
                                imgss.add(new PhotoProduct(image));
                            }

                            PhotoProductAdapter photoAdapter = new PhotoProductAdapter(ProductDetailActivity.this, imgss);
                            viewPager.setAdapter(photoAdapter);
                            circleIndicator.setViewPager(viewPager);
                            photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("DATA1", "error1: " + e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATA1", "error2: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("productID",idProduct);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(ProductDetailActivity.this);
        queue.add(request);


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

        if (item.getItemId() == R.id.ic_cart_toolbar) {   //this item has your app icon
            Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
            intent.putExtra("gotoCart", "cart");

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}