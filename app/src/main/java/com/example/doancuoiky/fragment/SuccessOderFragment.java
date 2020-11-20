package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.GlobalVariable;
import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.OrderAdapter;
import com.example.doancuoiky.modal.Order;

import java.util.ArrayList;

public class SuccessOderFragment extends Fragment {

    ListView lvSuccessOrder;
    ArrayList<Order> arraySuccessOrder;
    RelativeLayout noticeOrderIsEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success_oder,container,false);

        anhXa(view);
        if(arraySuccessOrder.size() == 0){
            lvSuccessOrder.setVisibility(View.GONE);
            noticeOrderIsEmpty.setVisibility(View.VISIBLE);
        }else{
            lvSuccessOrder.setVisibility(View.VISIBLE);
            noticeOrderIsEmpty.setVisibility(View.GONE);
        }

        return view;
    }

    private void anhXa(View view) {
        lvSuccessOrder = view.findViewById(R.id.lv_success_order_fragment_pending_order);
        noticeOrderIsEmpty = view.findViewById(R.id.layout_notice_order_empty_success_order);

        if(arraySuccessOrder == null){
            arraySuccessOrder = new ArrayList<>();
            for(int i = 0; i < GlobalVariable.arrayOrder.size(); i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 2){
                    arraySuccessOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }
        else{
            arraySuccessOrder.clear();
            for(int i = 0;i < GlobalVariable.arrayOrder.size();i++){
                if(GlobalVariable.arrayOrder.get(i).getOrder_status() == 2){
                    arraySuccessOrder.add(GlobalVariable.arrayOrder.get(i));
                }
            }
        }

        OrderAdapter adapter = new OrderAdapter(getContext(),R.layout.item_order,arraySuccessOrder);
        lvSuccessOrder.setAdapter(adapter);
    }
}