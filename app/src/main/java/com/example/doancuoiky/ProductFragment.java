package com.example.doancuoiky;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private RecyclerView rcvProduct;
    private View mView;
    private MainActivity mainActivity;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product, container, false);
        mainActivity = (MainActivity) getActivity();

        rcvProduct = mView.findViewById(R.id.rcv_product);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvProduct.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter();
        productAdapter.setData(getListProduct(), new ProductAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Product product) {

            }
        });
        rcvProduct.setAdapter(productAdapter);

        return  mView;
    }

    private List<Product> getListProduct(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));
        list.add(new Product(R.drawable.iphone1,"Iphone XR","Mau do","10000000"));
        list.add(new Product(R.drawable.iphone,"Iphone XR","Mau den","10000001"));

        return list;
    }
}
