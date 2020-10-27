package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancuoiky.AnimationUtil;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Product;
import com.example.doancuoiky.adapter.ProductAdapter;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.MainActivity;

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
        productAdapter.setData(MainActivity.arrarProduct, new ProductAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(final ImageView imgAddToCart, final Product product) {
                AnimationUtil.translateAnimation(mainActivity.getViewAnimation(), imgAddToCart, mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        product.setAddToCart(true);
                        imgAddToCart.setBackgroundResource(R.drawable.bg_gray_corner_6);
                        productAdapter.notifyDataSetChanged();

                        int id = product.getResourceId();
                        String name = product.getName();
                        String description = product.getDescription();
                        String price = product.getPrice();

                        Cart cart = new Cart(id, name, description, price, "1");
                        MainActivity.arrarCart.add(cart);

                        // tăng số lượng sản phẩm giỏ hàng lên 1
                        mainActivity.setCountProductInCart(MainActivity.arrarCart.size());
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        rcvProduct.setAdapter(productAdapter);
//        mainActivity.setCountProductInCart(MainActivity.arrarCart.size());

        return  mView;
    }

}
