package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.OrderAdapter;
import com.example.doancuoiky.modal.Order;

import java.util.ArrayList;

public class ShippingOderFragment extends Fragment {

    ListView lvShippingOrder;
    ArrayList<Order> arrayShippingOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping_oder,container,false);

        anhXa(view);

        return view;
    }

    private void anhXa(View view) {
        lvShippingOrder = view.findViewById(R.id.lv_shipping_order_fragment_pending_order);

        if(arrayShippingOrder == null){
            arrayShippingOrder = new ArrayList<>();
            for(int i = 0; i < GlobalVariable.arrayOrder.size(); i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 1){
                    arrayShippingOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }
        else{
            arrayShippingOrder.clear();
            for(int i = 0;i < GlobalVariable.arrayOrder.size();i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 1){
                    arrayShippingOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }

        OrderAdapter adapter = new OrderAdapter(getContext(),R.layout.item_order,arrayShippingOrder);
        lvShippingOrder.setAdapter(adapter);
    }
}