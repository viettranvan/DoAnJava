package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancuoiky.R;

public class CartFragment extends Fragment {


    private LinearLayout oderContainer;
    private Button btnOder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        oderContainer = view.findViewById(R.id.order_container);
        btnOder = view.findViewById(R.id.btn_order);



        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oderContainer.setVisibility(LinearLayout.GONE);
                Toast.makeText(getActivity(),"tiến hành đặt hàng",Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }
}
