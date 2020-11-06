package com.example.doancuoiky.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doancuoiky.activity.ProductDetailActivity;
import com.example.doancuoiky.modal.Product;
import com.example.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mListProduct;
    private IClickAddToCartListener iClickAddToCartListener;
    private RelativeLayout relativeLayout;

    // dung interface de callback su kien ra ben ngoai
    public interface IClickAddToCartListener{
        // dinh nghia cho method muon xu ly
        void onClickAddToCart(ImageView imgAddToCart,Product product);
    }

    public void setData(List<Product> list,IClickAddToCartListener listener){
        this.mListProduct = list;
        this.iClickAddToCartListener = listener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        final Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        holder.imgProduct.setImageResource(product.getProductImage());
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductDescription.setText(product.getProductDescription());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvProductPrice.setText(decimalFormat .format(product.getProductPrice()) +" đ");

        if(product.isAddToCart()){
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_gray_corner_6);
        }
        else{
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_red_corner_6);
        }

        // bat su kien
        holder.imgAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"index = " + position,Toast.LENGTH_SHORT).show();
                if(!product.isAddToCart()){
                    iClickAddToCartListener.onClickAddToCart(holder.imgAddToCart,product);
                }
            }
        });

        // den man hinh chi tiet
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(),ProductDetailActivity.class);
                intent.putExtra("productDetail",position);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgProduct;
        private TextView tvProductName;
        private TextView tvProductDescription;
        private TextView tvProductPrice;
        private ImageView imgAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.img_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductDescription = itemView.findViewById(R.id.tv_product_description);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            imgAddToCart = itemView.findViewById(R.id.img_add_to_cart);
            relativeLayout = itemView.findViewById(R.id.abc);
        }
    }

}
