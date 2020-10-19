package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.view_pager_photo);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(this,mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImage();

        return  view;
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

    // new com.example.doancuoiky.activity k con ton tai nua thi huy timer
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}
