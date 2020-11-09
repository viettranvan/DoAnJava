package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancuoiky.AnimationUtil;
import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Product;
import com.example.doancuoiky.adapter.ProductAdapter;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ProductFragment extends Fragment  {

    private RecyclerView rcvProduct;
    private View mView;
    private MainActivity mainActivity;
    private ProductAdapter productAdapter;
    private Spinner sortSpinner;
    ArrayList<Product> mArrayProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product, container, false);
        mainActivity = (MainActivity) getActivity();

        anhXa(mView);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        noSort();
                        break;
                    case 1:
                        ascendingSortPrice();
                        break;
                    case 2:
                        descendingSortPrice();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rcvProduct = mView.findViewById(R.id.rcv_product);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvProduct.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter();
        productAdapter.setData(mArrayProduct, new ProductAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(final ImageView imgAddToCart, final Product product) {
                AnimationUtil.translateAnimation(mainActivity.getViewAnimation(), imgAddToCart, mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        product.setAddToCart(true);
                        imgAddToCart.setBackgroundResource(R.drawable.bg_gray_corner_6);
                        productAdapter.notifyDataSetChanged();

                        String id = product.getProductID();
                        String typeId = product.getProductTypeID();
                        String name = product.getProductName();
                        String description = product.getProductDescription();
                        String specifications = product.getSpecifications();
                        int price = product.getProductPrice();
                        int image = product.getProductImage();

                        Cart cart = new Cart(id,typeId,name,description,specifications,price,image,1);
                        GlobalVariable.arrayCart.add(cart);

                        // tăng số lượng sản phẩm giỏ hàng lên 1
                        mainActivity.setCountProductInCart(GlobalVariable.arrayCart.size());
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

        return  mView;
    }

    private void anhXa(View mView) {
        sortSpinner = mView.findViewById(R.id.spinner_sort_product_fragment);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.sort));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        if(mArrayProduct == null){
            mArrayProduct = new ArrayList<>();
            mArrayProduct.addAll(GlobalVariable.arrayProduct);
        }

        Collections.sort(mArrayProduct, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getProductName().compareTo(product2.getProductName());
            }
        });

    }

    // khong sap xep
    private void noSort(){
        mArrayProduct.clear();
        mArrayProduct.addAll(GlobalVariable.arrayProduct);
        productAdapter.notifyDataSetChanged();
    }

    // sap xep giam dan theo gia
    private void descendingSortPrice(){
        Collections.sort(mArrayProduct, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return Integer.compare(product2.getProductPrice(), product1.getProductPrice());
            }
        });
        productAdapter.notifyDataSetChanged();
    }

    // sap xep tang dan theo gia
    private void ascendingSortPrice(){
        Collections.sort(mArrayProduct, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return Integer.compare(product1.getProductPrice(), product2.getProductPrice());
            }
        });
        productAdapter.notifyDataSetChanged();
    }
}
