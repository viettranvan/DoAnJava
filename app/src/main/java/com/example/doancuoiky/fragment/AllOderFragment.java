package com.example.doancuoiky.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.OrderAdapter;
import com.example.doancuoiky.modal.Order;

import java.util.ArrayList;

public class AllOderFragment extends Fragment {

    public AllOderFragment() {
        // Required empty public constructor
    }

    ListView lvOrder;
    RelativeLayout noticeOrderIsEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_oder, container, false);

        anhXa(view);
        if(GlobalVariable.arrayOrder.size() == 0){
            lvOrder.setVisibility(View.GONE);
            noticeOrderIsEmpty.setVisibility(View.VISIBLE);
        }else{
            lvOrder.setVisibility(View.VISIBLE);
            noticeOrderIsEmpty.setVisibility(View.GONE);
        }

        return view;

    }

    private void anhXa(View view) {
        lvOrder = view.findViewById(R.id.lv_all_order_fragment_all_order);
        noticeOrderIsEmpty = view.findViewById(R.id.layout_notice_order_empty_all_order);

        OrderAdapter adapter = new OrderAdapter(getContext(),R.layout.item_order,GlobalVariable.arrayOrder);
        lvOrder.setAdapter(adapter);

    }
}
