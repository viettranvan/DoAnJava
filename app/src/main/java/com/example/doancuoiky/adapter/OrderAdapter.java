package com.example.doancuoiky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.OrderDetailActivity;
import com.example.doancuoiky.modal.Order;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends BaseAdapter {

    Context mContex;
    int mLayout;
    List<Order> mArrayOrder;



    public OrderAdapter(Context context, int layout, List<Order> order){
        mContex = context;
        mLayout = layout;
        mArrayOrder = order;
    }

    @Override
    public int getCount() {
        return mArrayOrder.size();
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

        LayoutInflater inflater = (LayoutInflater) mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(mLayout,null);

        anhXa(view,i);

        return view;
    }

    private void anhXa(View view, final int i) {
        TextView tvIdOrder = view.findViewById(R.id.tv_order_id);
        TextView tvDateOrder = view.findViewById(R.id.tv_order_date);
        TextView tvStatusOrder = view.findViewById(R.id.tv_order_status);
        TextView tvTotalOrder = view.findViewById(R.id.tv_order_total);
        Button btnGoToDetail = view.findViewById(R.id.btn_detail_in_order);

        String _idOrder = "ORDER" +  mArrayOrder.get(i).getId_bill_order();
        // gám giá trị
        tvIdOrder.setText(_idOrder);
        tvDateOrder.setText(mArrayOrder.get(i).getDate_order());

        switch (mArrayOrder.get(i).getOrder_status()){
            case 0:
                tvStatusOrder.setText(R.string.text_status_0);
                break;
            case 1:
                tvStatusOrder.setText(R.string.text_status_1);
                break;
            case 2:
                tvStatusOrder.setText(R.string.text_status_2);
                break;
        }
        
        btnGoToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtra("gotoOrderDetail",i);
                intent.putExtra("orderID",mArrayOrder.get(i).getId_bill_order());
                intent.putExtra("orderStatus",mArrayOrder.get(i).getOrder_status());
                intent.putExtra("orderTotal",mArrayOrder.get(i).getTotal());
                view.getContext().startActivity(intent);
            }
        });

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String _total = decimalFormat.format(mArrayOrder.get(i).getTotal()) + " đ";
        tvTotalOrder.setText(_total);

    }
}
