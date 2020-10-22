package com.example.doancuoiky.fragment;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import me.relex.circleindicator.CircleIndicator;

public class CartFragment extends Fragment {

    private LinearLayout oderContainer;
    private Button btnOder;
    private TextView tvNotice;


    ListView lvCart;
    ArrayList<Cart> arrayCart;
    CartAdapter cartAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        anhXa(view);
        mSetAdapter(view);

        // kiểm tra arrayCart có rỗng không
        checkData();


        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCart.clear();
                checkData();
                Toast.makeText(getActivity(),"tiến hành đặt hàng " + arrayCart.size(),Toast.LENGTH_SHORT).show();
            }
        });



        return  view;
    }

    // kiem tra neu gio hang rong thi hien thong bao gio hang rong
    private void checkData() {
        if(arrayCart.size() <= 0){
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

    private void mSetAdapter(View view) {

        lvCart = view.findViewById(R.id.lv_cart);
        arrayCart = new ArrayList<Cart>();

        arrayCart = (ArrayList<Cart>) getListProductInCart();

        CartAdapter adapter = new CartAdapter(getContext(),R.layout.item_cart,arrayCart);

        lvCart.setAdapter(adapter);
    }

    private void anhXa(View view) {
        oderContainer = view.findViewById(R.id.order_container);
        btnOder = view.findViewById(R.id.btn_order);
        tvNotice = view.findViewById(R.id.tv_notice);

        cartAdapter = new CartAdapter(getContext(),R.layout.item_cart,arrayCart);
    }


    private List<Cart> getListProductInCart(){
        List<Cart> list = new ArrayList<>();
//
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone1,"iphondadade1 ne","01","10.234.432","1"));
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone,"iphone ne","02","10.234.432","2"));
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone1,"iphone1 ne","03","10.234.432","3"));
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone," ne","04","10.234.432","4"));
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone1,"ada ne","05","10.234.432","5"));
//        MainActivity.arrarCart.add(new Cart(R.drawable.iphone,"26 ne","06","10.234.432","6"));


        list.add(new Cart(R.drawable.iphone1,"iphondadade1 ne","01","10.234.432","1"));
        list.add(new Cart(R.drawable.iphone,"iphone","02","10.234.432","2"));
        list.add(new Cart(R.drawable.iphone1,"iphone1","03","10.234.432","3"));
        list.add(new Cart(R.drawable.iphone,"iphone","041","10.234.432","4"));
        list.add(new Cart(R.drawable.iphone1,"iphone1","05","10.234.432","5"));
        list.add(new Cart(R.drawable.iphone,"iphone1","06","10.234.432","6"));

        return list;
//        return MainActivity.arrarCart;
    }

    private List<Cart> setListProductInCart(int i,String s){
        List<Cart> list = getListProductInCart();

        Cart a = new Cart(arrayCart.get(i).getCartProductImg(),arrayCart.get(i).getName(),arrayCart.get(i).getDescription(),
                arrayCart.get(i).getPrice(),s);
        arrayCart.add(a);

        return list;
    }
}
