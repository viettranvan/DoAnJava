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
import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.ViewPagerAdapter;
import com.example.doancuoiky.fragment.HomeFragment;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Product;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        setUpViewPager(); /*chuyển trang bằng cách click vào icon hoặc vuốt*/

        actionToolBar(); // toolbar, mở drawer menu

        checkLogin();


        menuNavigationView.setNavigationItemSelectedListener(this);

        if(GlobalVariable.arrayCart.size() > 0){
            setCountProductInCart(GlobalVariable.arrayCart.size());
        }

        if(getIntent().getExtras() != null){

            Intent intent = getIntent();

            // chuyen den man hinh profile
            String toProfile = intent.getStringExtra("gotoProfile");
            if (toProfile != null && toProfile.contentEquals("profile")) {
                GlobalVariable.isLogin=true;
                checkLogin();
                ahBottomNavigation.setCurrentItem(4);
            }

            // chuyen den man hinh gio hang
            String toCart = intent.getStringExtra("gotoCart");
            if (toCart != null && toCart.contentEquals("cart")) {
                ahBottomNavigation.setCurrentItem(3);
            }

            if(getIntent().getExtras().getBoolean("loginTrue") == true){
                GlobalVariable.isLogin = true;
                checkLogin();
            }
        }
    }

    private void checkLogin() {
        Menu menu = menuNavigationView.getMenu();

        if(GlobalVariable.isLogin){
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

        if(GlobalVariable.arrayCart != null){

        }else{
            GlobalVariable.arrayCart = new ArrayList<>();

//            GlobalVariable.arrayCart.add(new Cart("00a","001","test","4gb","small",100000,R.drawable.meow,1));
        }

        if(GlobalVariable.arrarProduct != null){

        }else{
            GlobalVariable.arrarProduct = new ArrayList<>();
            GlobalVariable.arrarProduct.add(new Product("001","001","Điện thoại realme",
                    getString(R.string.mota),"4gb-64gb",4000000,R.drawable.realme_banner_resize));
            GlobalVariable.arrarProduct.add(new Product("002","001","Điện thoại iphone XR",
                    getString(R.string.mota),"4gb-64gb",10000000,R.drawable.iphone));
            GlobalVariable.arrarProduct.add(new Product("003","001","Điện thoại samsung",
                    getString(R.string.mota),"4gb-64gb",8000000,R.drawable.samsum_banner_resize));
            GlobalVariable.arrarProduct.add(new Product("004","002","Laptop Asus",
                    getString(R.string.mota),"4gb-64gb",10500500,R.drawable.laptop_asus));
            GlobalVariable.arrarProduct.add(new Product("005","002","Laptop dell",
                    getString(R.string.mota),"4gb-64gb",9000000,R.drawable.laptop_dell));
            GlobalVariable.arrarProduct.add(new Product("006","001","Điện thoại samsung",
                    getString(R.string.mota),"4gb-64gb",8000000,R.drawable.meow));
            GlobalVariable.arrarProduct.add(new Product("007","002","Laptop Asus",
                    getString(R.string.mota),"4gb-64gb",10500500,R.mipmap.ic_launcher));
            GlobalVariable.arrarProduct.add(new Product("008","002","Laptop dell",
                    getString(R.string.mota),"4gb-64gb",9000000,R.drawable.iphone1));
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
                Toast.makeText(this,"share: " + GlobalVariable.isLogin ,Toast.LENGTH_SHORT).show();
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
                GlobalVariable.isLogin = false;
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
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.bg_red))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 3);
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
