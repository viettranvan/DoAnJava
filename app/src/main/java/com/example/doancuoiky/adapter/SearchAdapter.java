package com.example.doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.modal.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {

    private IClickOnCopyKeySearch iClickOnCopyKeySearch;

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null || charSequence.length() == 0 ){
                    filterResults.count = arraySearchData.size();
                    filterResults.values = arraySearchData;
                }
                else{
                    String searchStr = charSequence.toString().toLowerCase();
                    List<Search> resultData = new ArrayList<>();

                    for(Search search:arraySearchData){
                        if(search.getTitle().contains(searchStr)){
                            resultData.add(search);
                        }

                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arraySearchData = (List<Search>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    // dung interface de callback su kien ra ben ngoai -> Fragment Cart
    public interface IClickOnCopyKeySearch{
        // dinh nghia cho method muon xu ly
        void onClickCopyKeySearch(int index); // truyền vào index -> vị trí cần xóa
    }

    public void onCopyKeySearch(SearchAdapter.IClickOnCopyKeySearch listener){
        this.iClickOnCopyKeySearch = listener;
    }

    Context myContex;
    int myLayout;
    List<Search> arraySearchData;
    ImageView ivCopy;

    public SearchAdapter(Context context,int layout,List<Search> array){
        myContex = context;
        myLayout = layout;
        arraySearchData = array;
    }


    @Override
    public int getCount() {
        return arraySearchData.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout,null);

        // anhs xa va gan gia tri
        TextView txtName1 = view.findViewById(R.id.value_search );
        ivCopy = view.findViewById(R.id.icon_copy_to_search);


        // nút copy từ khóa tìm kiếm
        ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                iClickOnCopyKeySearch.onClickCopyKeySearch(i);
            }
        });

        // gan gia tri
        txtName1.setText(arraySearchData.get(i).getTitle());

        return view;
    }
}
