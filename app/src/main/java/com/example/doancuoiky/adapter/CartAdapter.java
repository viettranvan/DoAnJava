package com.example.doancuoiky.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.activity.ProductDetailActivity;
import com.example.doancuoiky.fragment.CartFragment;
import com.example.doancuoiky.fragment.HomeFragment;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.modal.Product;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends BaseAdapter{

    private IClickOnDeleteProductInCart iClickOnDeleteProductInCart;

    // dung interface de callback su kien ra ben ngoai -> Fragment Cart
    public interface IClickOnDeleteProductInCart{
        // dinh nghia cho method muon xu ly
        void onClickDeleteProductInCart(int index); // truyền vào index -> vị trí cần xóa
    }

    public void onDelete(IClickOnDeleteProductInCart listener){
        this.iClickOnDeleteProductInCart = listener;
    }

    Context myContext;
    int myLayout;
    List<Cart> arrayCart;
    TextView cartProductName,cartProductDescription,cartProductPrice,cartProductCount;
    Button btnMinus,btnPlus,btnDeleteProduct,btnDetail;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(myLayout,null);

        anhXa(view,i);


        // nút giảm số lượng
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(arrayCart.get(i).getCount());

                if(currentCount <= 1){
                }
                else{
                    currentCount -= 1;
                }
                MainActivity.arrarCart.get(i).setCount(currentCount + "");
                cartProductCount.setText(arrayCart.get(i).getCount());
                CartFragment.updateTotalPrice();
                notifyDataSetChanged();

            }

        });

        // nút tăng số lượng
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(arrayCart.get(i).getCount());
                currentCount += 1;
                MainActivity.arrarCart.get(i).setCount(currentCount + "");
                cartProductCount.setText(arrayCart.get(i).getCount());
                CartFragment.updateTotalPrice();
                notifyDataSetChanged();
            }
        });

        // nút xóa sản phẩm khỏi giỏ hàng
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                iClickOnDeleteProductInCart.onClickDeleteProductInCart(i);
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"tới màn hình chi tiết index = " + i,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ProductDetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }



    private void anhXa(View view,int i) {
        // Ánh xạ và gán giá trị
        cartProductName = view.findViewById(R.id.tv_cart_product_name);
        cartProductDescription = view.findViewById(R.id.tv_cart_product_description);
        cartProductPrice = view.findViewById(R.id.tv_cart_product_price);
        cartProductCount = view.findViewById(R.id.tv_cart_product_count);
        btnPlus = view.findViewById(R.id.btn_cart_plus);
        btnMinus = view.findViewById(R.id.btn_cart_minus);
        btnDeleteProduct = view.findViewById(R.id.btn_delete_product);
        imgCartProduct = view.findViewById(R.id.img_cart_product);
        btnDetail = view.findViewById(R.id.btn_cart_detail);

        // gán giá trị
        cartProductName.setText(arrayCart.get(i).getName());
        cartProductDescription.setText(arrayCart.get(i).getDescription());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        cartProductPrice.setText(decimalFormat.format(arrayCart.get(i).getPrice()) + " đ");


        cartProductCount.setText(arrayCart.get(i).getCount());
        imgCartProduct.setImageResource(arrayCart.get(i).getCartProductImg());

        int currentCount = Integer.parseInt(arrayCart.get(i).getCount());
        if(currentCount <= 1){
            btnMinus.setEnabled(false);
            notifyDataSetChanged();
        }
        else if(currentCount >= 10){
            btnPlus.setEnabled(false);
            notifyDataSetChanged();
        }
    }
}
