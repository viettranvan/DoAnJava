package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.OrderAdapter;
import com.example.doancuoiky.modal.Order;

import java.util.ArrayList;

public class PendingOrderFragment extends Fragment {

    ListView lvPendingOrder;
    ArrayList<Order> arrayPendingOrder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_oder, container, false);

        anhXa(view);

        return view;
    }

    private void anhXa(View view) {
        lvPendingOrder = view.findViewById(R.id.lv_pending_order_fragment_pending_order);

        if(arrayPendingOrder == null ){
            arrayPendingOrder = new ArrayList<>();
            for(int i = 0;i < GlobalVariable.arrayOrder.size();i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 0){
                    arrayPendingOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }
        else{
            arrayPendingOrder.clear();
            for(int i = 0;i < GlobalVariable.arrayOrder.size();i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 0){
                    arrayPendingOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }
        OrderAdapter adapter = new OrderAdapter(getContext(),R.layout.item_order,arrayPendingOrder);
        lvPendingOrder.setAdapter(adapter);
    }
}
