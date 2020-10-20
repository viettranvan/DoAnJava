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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.doancuoiky.fragment.CartFragment;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView menuNavigationView;
    private long backPressedTime;
    private ListView listView;
    private Toast mToast;
    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;

    private View viewEndAnimation;
    private ImageView viewAnimation;

    private int mCountProduct;

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
        Menu menu = menuNavigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false); // ẩn logout
        menu.findItem(R.id.nav_profile).setVisible(false); // ẩn profile

//        menuNavigationView.bringToFront();
        menuNavigationView.setNavigationItemSelectedListener(this);
    }

    private void actionToolBar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trang chủ");
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
                CartFragment cartFragment = new CartFragment();
                loadFragment(cartFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_laptop:
                drawerLayout.closeDrawer(GravityCompat.START);
                ahBottomNavigation.setCurrentItem(1);
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
                getSupportActionBar().setTitle("Trang chủ");

                break;
            case 1:
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Danh mục sản phẩm");
                break;
            case 2:
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Tìm kiếm");
                break;
            case 3:
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Giỏ hàng");
                break;
            case 4:
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Thông tin cá nhân");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_cart_toolbar:
                Toast.makeText(this,"Giỏ hàng",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
