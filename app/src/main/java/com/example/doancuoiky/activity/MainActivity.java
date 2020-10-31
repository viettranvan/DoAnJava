package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.doancuoiky.fragment.CartFragment;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.ViewPagerAdapter;
import com.example.doancuoiky.fragment.HomeFragment;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Product;
import com.example.doancuoiky.modal.ProductNew;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.goToCartOnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView menuNavigationView;
    private long backPressedTime;
    private ListView listView;
    private Toast mToast;
    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;
    private TextView toolBarTitle;

    private View viewEndAnimation;
    private ImageView viewAnimation;

    private int mCountProduct;

    public static ArrayList<Cart> arrarCart;
    public static ArrayList<Product> arrarProduct;
    public static ArrayList<ProductNew> arrayProductNew;
    public static boolean isLogin = false;
    Boolean yourBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        /*chuyển trang bằng cách click vào icon hoặc vuốt*/
        setUpViewPager();

        // toolbar, mở drawer menu
        actionToolBar();


        /*======================= Navigation Drawer Menu===========================*/
        // ẩn hoắc hiện login, profile
        checkLogin();


//        menuNavigationView.bringToFront();
        menuNavigationView.setNavigationItemSelectedListener(this);

        if(arrarCart.size() > 0){
            setCountProductInCart(arrarCart.size());
        }

        if(getIntent().getExtras() != null){

            Intent intent = getIntent();

            // chuyen den man hinh profile
            String toProfile = intent.getStringExtra("gotoProfile");
            if (toProfile != null && toProfile.contentEquals("profile")) {
                ahBottomNavigation.setCurrentItem(4);
            }

            // chuyen den man hinh gio hang
            String toCart = intent.getStringExtra("gotoCart");
            if (toCart != null && toCart.contentEquals("cart")) {
                ahBottomNavigation.setCurrentItem(3);
            }

            yourBool = getIntent().getExtras().getBoolean("yourBoolName");
            MainActivity.isLogin = yourBool;
            checkLogin();
        }
    }

    private void checkLogin() {
        Menu menu = menuNavigationView.getMenu();

        if(isLogin){
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(true); // hiện logout
            menu.findItem(R.id.nav_profile).setVisible(true); // hiện profile
        }
        else{
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false); // ẩn logout
            menu.findItem(R.id.nav_profile).setVisible(false); // ẩn profile
        }

    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarTitle.setText("Trang chủ");
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuNavigationView = findViewById(R.id.navigation_view);
        listView = findViewById(R.id.listview);
        ahBottomNavigation  = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);
        toolBarTitle = findViewById(R.id.tv_toolbar_title);

        if(arrarCart != null){

        }else{
            arrarCart = new ArrayList<>();
//            arrarCart.add(new Cart(R.drawable.realme_banner_resize,"realme","Điện thoại realme","4.000.000","1"));
//            arrarCart.add(new Cart(R.drawable.iphone,"ihone","Điện thoại iphone XR","10.000.000","2"));
//            arrarCart.add(new Cart(R.drawable.samsum_banner_resize,"Samsung","Điện thoại samsung","8.000.000","3"));
//            arrarCart.add(new Cart(R.drawable.oppo_banner_resize," Oppo","Điện thoại oppo","10.234.432","4"));
//            arrarCart.add(new Cart(R.drawable.laptop_asus,"Laptop asus","Laptop Asus","10.500.500","5"));
//            arrarCart.add(new Cart(R.drawable.laptop_dell,"Laptop dell","Laptop dell","9.000.000","6"));
        }

        if(arrarProduct != null){

        }else{
            arrarProduct = new ArrayList<>();
            arrarProduct.add(new Product(R.drawable.realme_banner_resize,"realme","Điện thoại realme",4000000));
            arrarProduct.add(new Product(R.drawable.iphone,"Iphone XR","Điện thoại iphone XR",10000000));
            arrarProduct.add(new Product(R.drawable.samsum_banner_resize,"Samsung","Điện thoại samsung",8000000));
            arrarProduct.add(new Product(R.drawable.laptop_asus,"Laptop asus","Laptop Asus",10500500));
            arrarProduct.add(new Product(R.drawable.laptop_dell,"Laptop dell","Laptop dell",9000000));
        }

        if(arrayProductNew != null){

        }else{
            arrayProductNew = new ArrayList<>();
            arrayProductNew.add(new ProductNew(1,1,"iphone XR","Điện thoại iphone XR",10000000,R.drawable.iphone));
            arrayProductNew.add(new ProductNew(2,1,"Samsung","Điện thoại samsung ",8000000,R.drawable.samsum_banner_resize));
            arrayProductNew.add(new ProductNew(3,1,"Oppo","Điện thoại oppo",5000000,R.drawable.oppo_banner_resize));
            arrayProductNew.add(new ProductNew(4,2,"Asus","Laptop  Asus",10500500,R.drawable.laptop_asus));
            arrayProductNew.add(new ProductNew(5,2,"Dell","Laprop Dell",9000000,R.drawable.laptop_dell));
        }
    }

    // bottom tab
    private void setUpViewPager(){
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        // Create items
        AHBottomNavigationItem tab_home = new AHBottomNavigationItem(R.string.tab_home, R.mipmap.baseline_home_black_24, R.color.tab_home);
        AHBottomNavigationItem tab_product = new AHBottomNavigationItem(R.string.tab_product, R.mipmap.icon_product, R.color.tab_product);
        AHBottomNavigationItem tab_search = new AHBottomNavigationItem(R.string.tab_search, R.mipmap.baseline_search_black_24, R.color.tab_product);
        AHBottomNavigationItem tab_cart = new AHBottomNavigationItem(R.string.tab_cart, R.mipmap.icon_cart, R.color.tab_cart);
        AHBottomNavigationItem tab_profile = new AHBottomNavigationItem(R.string.tab_profile, R.mipmap.baseline_person_black_24, R.color.tab_profile);

        // Add items
        ahBottomNavigation.addItem(tab_home);
        ahBottomNavigation.addItem(tab_product);
        ahBottomNavigation.addItem(tab_search);
        ahBottomNavigation.addItem(tab_cart);
        ahBottomNavigation.addItem(tab_profile);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                // đặt lại toolbar title tương ứng với tab
                setToolbarTitle(position);

                return true;
            }
        });

        // chuyen fragment bang cach vuot
        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            if(backPressedTime + 2000 > System.currentTimeMillis()){
                mToast.cancel();
                super.onBackPressed();
                return;
            }
            else{
                mToast = Toast.makeText(MainActivity.this,"Nhấn back thêm 1 lần nữa để thoát",Toast.LENGTH_SHORT);
                mToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }

    // su kien khi item trong drawer menu dc click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_mobile:
//                CartFragment cartFragment = new CartFragment();
//                loadFragment(cartFragment);
//                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this,"mobile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_laptop:
                drawerLayout.closeDrawer(GravityCompat.START);
                ahBottomNavigation.setCurrentItem(1);
                break;

            case R.id.nav_login:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"login",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                ahBottomNavigation.setCurrentItem(4);
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawer(GravityCompat.START);
                MainActivity.isLogin = false;
                checkLogin();

                finish();
                startActivity(new Intent(this,MainActivity.class));

                break;
        }
        return true;
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_activity,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getViewAnimation() {
        return viewAnimation;
    }

    public void setCountProductInCart(int count){
        mCountProduct = count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.bg_red))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 3);
    }

    public int getCountProduct() {
        return mCountProduct;
    }

    private void setToolbarTitle(int position){
        switch (position){
            case 0:
                setSupportActionBar(toolbar);
                toolBarTitle.setText("Trang chủ");
                break;
            case 1:
                setSupportActionBar(toolbar);
                toolBarTitle.setText("Danh mục sản phẩm");
                break;
            case 2:
                setSupportActionBar(toolbar);
                toolBarTitle.setText("Tìm kiếm");

                break;
            case 3:
                setSupportActionBar(toolbar);
                toolBarTitle.setText("Giỏ hàng");
                break;
            case 4:
                setSupportActionBar(toolbar);
                toolBarTitle.setText("Thông tin cá nhân");
                break;
        }
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    // chuyen den man hinh gio hang
    @Override
    public void onCartIconClickListener() {
        ahBottomNavigation.setCurrentItem(3);
    }


}
