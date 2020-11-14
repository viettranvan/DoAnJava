package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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


import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.modal.Photo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvProductName,tvProductPrice, tvDescription;
    private ImageView goBack;
    Toolbar toolbar;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Button addToCart;
    private int pos = 0;
    ArrayAdapter adapter;

    ArrayList<String> arrayDescription;

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

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this, "them vao gio hang" , Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
//        String id = i.getIntExtra("productDetail",0);
        String idProduct = intent.getStringExtra("productDetail");

        for(int ii = 0;ii < GlobalVariable.arrayProduct.size();ii++){
            if(GlobalVariable.arrayProduct.get(ii).getProductID().equals(idProduct)){
                pos = ii;
                break;
            }
        }
        setPhotoAdapter();

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

        ListView description = findViewById(R.id.lv_description);

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

        arrayDescription = new ArrayList<>();
        addTempData();


        adapter = new ArrayAdapter(ProductDetailActivity.this,android.R.layout.simple_list_item_1,arrayDescription);
        description.setAdapter(adapter);

    }

    private void addTempData() {
        if(arrayDescription != null && arrayDescription.size() == 0){
            arrayDescription.add("Hãng sản xuất: Apple");
            arrayDescription.add("Kích thước: 158.2 x 77.9 x 7.3 mm (6.23 x 3.07 x 0.29 in)");
            arrayDescription.add("Trọng lượng: 192 g (6.77 oz)");
            arrayDescription.add("Bộ nhớ đệm / Ram: 32 GB, 2 GB RAM");
            arrayDescription.add("Bộ nhớ trong: 32 GB");
            arrayDescription.add("Loại SIM: Nano-SIM");
            arrayDescription.add("Loại màn hình: Cảm ứng điện dung LED-backlit IPS LCD, 16 triệu màu");
            arrayDescription.add("Kích thước màn hình: 5.5 inches");
            arrayDescription.add("Độ phân giải màn hình: 1080 x 1920 pixels");
            arrayDescription.add("Hệ điều hành: iOS");
            arrayDescription.add("Phiên bản hệ điều hành: 11");
            arrayDescription.add("Chipset: Apple A9 APL1022");
            arrayDescription.add("CPU: 2x 1.84 GHz Twister");
            arrayDescription.add("GPU: PowerVR GT7600 (6 lõi đồ họa)");
            arrayDescription.add("Khe cắm thẻ nhớ: Không");
            arrayDescription.add("Camera sau: 12 MP (f/2.2, 29mm, 1/3\", 1.22 µm), tự động lấy nét nhận diện theo giai đoạn, OIS, LED flash kép (2 tone)");
            arrayDescription.add("Camera trước: 5 MP (f/2.2, 31mm), 1080p@30fps, 720p@240fps, nhận diện khuôn mặt, HDR, panorama");
            arrayDescription.add("Quay video: 2160p@30fps, 1080p@60fps, 1080p@120fps, 720p@240fps");
            arrayDescription.add("3G: HSPA 42.2/5.76 Mbps, EV-DO Rev.A 3.1 Mbps");
            arrayDescription.add("4G: LTE-A (2CA) Cat6 300/50 Mbps");
            arrayDescription.add("WLAN: Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot");
            arrayDescription.add("Bluetooth: 4.2, A2DP, LE");
            arrayDescription.add("GPS: A-GPS, GLONASS, GALILEO, QZSS");
            arrayDescription.add("NFC: Yes");
            arrayDescription.add("USB: 2.0");
            arrayDescription.add("Cảm biến: Vân tay, gia tốc, la bàn, khoảng cách, con quay quy hồi, phong vũ biểu");
            arrayDescription.add("Pin: Li-ion 2750 mA");

        }
    }

    private void setPhotoAdapter(){
        List<Photo> mListPhoto = getListPhoto(pos);
        PhotoAdapter photoAdapter = new PhotoAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto(int index){

        List<Photo> list = new ArrayList<>();
        list.add(new Photo(GlobalVariable.arrayProduct.get(index).getProductImage()));
        list.add(new Photo(R.drawable.vivo_banner_resize));
        list.add(new Photo(GlobalVariable.arrayProduct.get(index).getProductImage()));
        list.add(new Photo(R.drawable.samsum_banner_resize));
        list.add(new Photo(GlobalVariable.arrayProduct.get(index).getProductImage()));
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

        if (item.getItemId() == R.id.ic_cart_toolbar) {   //this item has your app icon
            Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
            intent.putExtra("gotoCart", "cart");

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}