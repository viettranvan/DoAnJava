package com.example.doancuoiky.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.activity.ChangePasswordActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.activity.ProductDetailActivity;
import com.example.doancuoiky.adapter.ProductNewAdapter;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    // tao interface de callback ra ben ngoai (MainActivity)
    public interface goToCartOnClickListener{
        void onCartIconClickListener();
    }

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    private goToCartOnClickListener mGoToCart;
    private RecyclerView recyclerView;

    ProductNewAdapter productNewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa(view);

        productNewAdapter.onGotoDetail(new ProductNewAdapter.IClickGotoProductDetail() {
            @Override
            public void onClickGotoDetail(int index) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProductDetailActivity.class);
//                intent.putExtra("productDetail",index);
                intent.putExtra("productDetail",index);
                startActivity(intent);
            }
        });
        autoSlideImage();
        setHasOptionsMenu(true);

        return  view;
    }

    private void anhXa(View view) {
        viewPager = view.findViewById(R.id.view_pager_photo);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        recyclerView = view.findViewById(R.id.rcv_product_new_home_fragment);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(getContext(),mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        productNewAdapter = new ProductNewAdapter(getContext(), GlobalVariable.arrayProductNew);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(productNewAdapter);
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.iphone_banner_resize));
        list.add(new Photo(R.drawable.vivo_banner_resize));
        list.add(new Photo(R.drawable.vsmart_banner_resize));
        list.add(new Photo(R.drawable.samsum_banner_resize));
        list.add(new Photo(R.drawable.realme_banner_resize));
        list.add(new Photo(R.drawable.xiaomi_banner_resize));

        return list;
    }

    // them icon gio hang len thanh toolbar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.cart,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // su kien click icon gio hang tren thanh toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_cart_toolbar:
                mGoToCart.onCartIconClickListener();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // sự kiến chuyển đến màn hình giỏ hàng khi click icon trên toolbar
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        mGoToCart = (goToCartOnClickListener) activity;
    }

    // Hàm tự chuyển ảnh trong Image Slider
    private void autoSlideImage(){
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager == null){
            return;
        }

        // khoi tao timer
        if(mTimer == null){
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if(currentItem < totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        }
                        else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000); // sau 3s thi chuyen anh, thoi gian delay 0.5s
    }

    // new activity k con ton tai nua thi huy timer
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

}
