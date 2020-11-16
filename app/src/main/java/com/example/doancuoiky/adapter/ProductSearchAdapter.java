package com.example.doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.modal.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ItemHolder> {

    private IClickGotoProductDetail iClickGotoProductDetail;

    // dung interface de callback su kien ra ben ngoai -> Fragment Home
    public interface IClickGotoProductDetail{
        // dinh nghia cho method muon xu ly
        void onClickGotoDetail(String idProduct); // truyền vào index -> vị trí cần đến
    }

    public void onGotoDetail(IClickGotoProductDetail listener){
        this.iClickGotoProductDetail = listener;
    }

    Context context;
    ArrayList<Product> arrayProductSearch;


    public ProductSearchAdapter(Context context, ArrayList<Product> arrayProductSearch) {
        this.context = context;
        this.arrayProductSearch = arrayProductSearch;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_search,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position){
        Product productNew = arrayProductSearch.get(position);

        holder.tvProductName.setText(productNew.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String _format_price = "Giá: " + decimalFormat.format(productNew.getProductPrice())+ " đ";
        holder.tvProductPrice.setText(_format_price);

        Picasso.get()
                .load(productNew.getProductImage())
                .into(holder.imgProduct);
//        holder.imgProduct.setImageResource(productNew.getProductImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickGotoProductDetail.onClickGotoDetail(arrayProductSearch.get(position).getProductID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayProductSearch.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgProduct;
        public TextView tvProductName, tvProductDescription, tvProductPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product_image_product_search);
            tvProductName = itemView.findViewById(R.id.tv_product_name_product_search);
            tvProductDescription = itemView.findViewById(R.id.tv_product_description_product_search);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price_product_search);
        }
    }
}
