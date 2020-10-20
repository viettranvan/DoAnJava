package com.example.doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.doancuoiky.R;
import com.example.doancuoiky.fragment.CartFragment;
import com.example.doancuoiky.fragment.HomeFragment;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.modal.Product;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends BaseAdapter{

    Context myContext;
    int myLayout;
    List<Cart> arrayCart;
    TextView cartProductName,cartProductDescription,cartProductPrice,cartProductCount;
    Button btnMinus,btnPlus,deleteProduct;
    ImageView imgCartProduct;

    // constructor
    public CartAdapter(Context context,int layout, List<Cart> cartList){
        myContext = context;
        myLayout = layout;
        arrayCart = cartList;
    }

    @Override
    public int getCount() {
        return arrayCart.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(myLayout,null);
        // Ánh xạ và gán giá trị
        cartProductName = view.findViewById(R.id.tv_cart_product_name);
        cartProductDescription = view.findViewById(R.id.tv_cart_product_description);
        cartProductPrice = view.findViewById(R.id.tv_cart_product_price);
        cartProductCount = view.findViewById(R.id.tv_cart_product_count);
        btnPlus = view.findViewById(R.id.btn_cart_plus);
        btnMinus = view.findViewById(R.id.btn_cart_minus);
        deleteProduct = view.findViewById(R.id.btn_delete_product);
        imgCartProduct = view.findViewById(R.id.img_cart_product);

        // gán giá trị
        cartProductName.setText(arrayCart.get(i).getName());
        cartProductDescription.setText(arrayCart.get(i).getDescription());
        cartProductPrice.setText(arrayCart.get(i).getPrice());
        cartProductCount.setText(arrayCart.get(i).getCount());
        imgCartProduct.setImageResource(arrayCart.get(i).getCartProductImg());
        return view;
    }
}
