package com.example.doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.modal.ProductNew;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductNewAdapter extends RecyclerView.Adapter<ProductNewAdapter.ItemHolder> {

    private IClickGotoProductDetail iClickGotoProductDetail;

    // dung interface de callback su kien ra ben ngoai -> Fragment Home
    public interface IClickGotoProductDetail{
        // dinh nghia cho method muon xu ly
        void onClickGotoDetail(int index); // truyền vào index -> vị trí cần đến
    }

    public void onGotoDetail(IClickGotoProductDetail listener){
        this.iClickGotoProductDetail = listener;
    }

    Context context;
    ArrayList<ProductNew> arrayProductNew;



    public ProductNewAdapter(Context context, ArrayList<ProductNew> arrayProductNew) {
        this.context = context;
        this.arrayProductNew = arrayProductNew;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_new,null);
        ItemHolder itemHolder = new ItemHolder(view);


        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position){
        ProductNew productNew = arrayProductNew.get(position);

        holder.tvProductName.setText(productNew.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvProductPrice.setText("Giá: " + decimalFormat.format(productNew.getProductPrice())+ " đ");

        holder.imgProduct.setImageResource(productNew.getProductImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickGotoProductDetail.onClickGotoDetail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayProductNew.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgProduct;
        public TextView tvProductName, tvProductDescription, tvProductPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product_image);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);

        }
    }
}
