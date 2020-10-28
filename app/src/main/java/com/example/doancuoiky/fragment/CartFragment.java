package com.example.doancuoiky.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.adapter.CartAdapter;
import com.example.doancuoiky.adapter.PhotoAdapter;
import com.example.doancuoiky.adapter.ProductAdapter;
import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Photo;
import com.example.doancuoiky.modal.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import me.relex.circleindicator.CircleIndicator;

public class CartFragment extends Fragment  {

    private LinearLayout oderContainer;
    private Button btnOder;
    private TextView tvNotice, tvTotal;
    private MainActivity mainActivity;
    private Product product;


    ListView lvCart;
    CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        anhXa(view);

        // kiểm tra arrayCart có rỗng không
        checkData();

        // sự kiện khi nhấn vào button đặt hàng
        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.arrarCart.clear();
                checkData();
                Toast.makeText(getActivity(),"tiến hành đặt hàng " + MainActivity.arrarCart.size(),Toast.LENGTH_SHORT).show();
                mainActivity.setCountProductInCart(0);

                // trả lại trạng thái enebal cho tất cả button thêm trong Product Fragment
                for(int i = 0;i < MainActivity.arrarProduct.size();i++){
                    product = MainActivity.arrarProduct.get(i);
                    product.setAddToCart(false);
                }
            }
        });

        cartAdapter.onDelete(new CartAdapter.IClickOnDeleteProductInCart() {
            @Override
            public void onClickDeleteProductInCart(final int index) {

                AlertDialog.Builder alertDelete;
                alertDelete = new AlertDialog.Builder(getContext());
                alertDelete.setTitle("Thông báo");
                alertDelete.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?");

                alertDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // trả lại trạng thái enebal cho button thêm giỏ hàng -> Fragment Product
                        // (đang duyệt dựa trên tên sản phẩm) -> đổi lại id sau này
                        String name = MainActivity.arrarCart.get(index).getName();
                        for(int k = 0 ;k < MainActivity.arrarProduct.size();k ++){
                            if(MainActivity.arrarProduct.get(k).getName().equals(name)){

                                product = MainActivity.arrarProduct.get(k);
                                product.setAddToCart(false);
                            }
                        }

                        MainActivity.arrarCart.remove(index);
                        cartAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                        mainActivity.setCountProductInCart(MainActivity.arrarCart.size());
                        checkData();

                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        tvTotal.setText(decimalFormat.format(totalPrice()) + "đ");
                    }
                });

                alertDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDelete.show();
            }

        });

        return  view;
    }

    // kiem tra neu gio hang rong thi hien thong bao gio hang rong
    private void checkData() {
        if(MainActivity.arrarCart.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            tvNotice.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
            oderContainer.setVisibility(LinearLayout.INVISIBLE);
        }
        else{
            cartAdapter.notifyDataSetChanged();
            oderContainer.setVisibility(LinearLayout.VISIBLE);
            tvNotice.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }
    }

    private void anhXa(View view) {
        oderContainer = view.findViewById(R.id.order_container);
        btnOder = view.findViewById(R.id.btn_order);
        tvNotice = view.findViewById(R.id.tv_notice);
        tvTotal = view.findViewById(R.id.tv_total);
        lvCart = view.findViewById(R.id.lv_cart);

        mainActivity = (MainActivity) getActivity();

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTotal.setText(decimalFormat.format(totalPrice()) + "đ");
        cartAdapter = new CartAdapter(getContext(),R.layout.item_cart,MainActivity.arrarCart);
        lvCart.setAdapter(cartAdapter);
    }

    public int totalPrice(){
        int total = 0;
        for(int i = 0;i < MainActivity.arrarCart.size();i++){
            total += MainActivity.arrarCart.get(i).getPrice();
        }
        return total;
    }

}
