package com.example.doancuoiky.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.adapter.SearchAdapter;
import com.example.doancuoiky.modal.Search;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment {

    private ListView lvData,lvHistory;
    private ArrayList<Search> arrayListData, arrayListHistory;
    private TextView clearHistory;
    private SearchAdapter adapterData,adapterHistory;
    private LinearLayout layoutHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        anhXa(view);
        checkData();

        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListHistory.clear();
                adapterHistory.notifyDataSetChanged();
                checkData();
            }
        });

//        adapterHistory.onCopyKeySearch(new SearchAdapter.IClickOnCopyKeySearch() {
//            @Override
//            public void onClickCopyKeySearch(int index) {
//                String text = arrayListHistory.get(index).getTitle();
//                Toast.makeText(getContext(),"index = " +  text, Toast.LENGTH_SHORT).show();
//            }
//        });

        adapterData.onCopyKeySearch(new SearchAdapter.IClickOnCopyKeySearch() {
            @Override
            public void onClickCopyKeySearch(int index) {
                String text = arrayListData.get(index).getTitle();
                showToast(text);
            }
        });

        setHasOptionsMenu(true);
        return  view;
    }

    private void showToast(String text) {
        Toast.makeText(getContext(),"index = " +  text, Toast.LENGTH_SHORT).show();
    }

    private void anhXa(View view) {
        lvData = view.findViewById(R.id.lv_search);
        lvHistory = view.findViewById(R.id.lv_history_search);
        clearHistory = view.findViewById(R.id.tv_delete_history_search_fragment);
        layoutHistory = view.findViewById(R.id.layout_history_search_search_fragment);

        arrayListData = new ArrayList<Search>();

        arrayListData.add(new Search("iphone"));
        arrayListData.add(new Search("realme"));
        arrayListData.add(new Search("dell"));
        arrayListData.add(new Search("asus"));

        if(arrayListHistory == null){
            arrayListHistory = new ArrayList<Search>();
            arrayListHistory.add(new Search("mon 1"));
            arrayListHistory.add(new Search("mon 2"));
            arrayListHistory.add(new Search("mon 3"));
            arrayListHistory.add(new Search("mon 4"));

        }


        adapterData = new SearchAdapter(getContext(),R.layout.item_search,arrayListData);
        lvData.setAdapter(adapterData);

        adapterHistory = new SearchAdapter(getContext(),R.layout.item_search,arrayListHistory);
        lvHistory.setAdapter(adapterHistory);
    }

    // them icon search len thanh toolbar
    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search,menu);

        // auto focus search
        menu.findItem(R.id.ic_search_toolbar).expandActionView();

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(getContext(), "Expand", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(getContext(), "Collapse", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        menu.findItem(R.id.ic_search_toolbar).setOnActionExpandListener(onActionExpandListener);

        SearchView searchView = (SearchView) menu.findItem(R.id.ic_search_toolbar).getActionView();

        final EditText txtSearch = ((EditText)searchView.findViewById(androidx.appcompat.R.id.search_src_text));
        txtSearch.setHint("Nhập tên sản phẩm bạn muốn tìm kiếm");

        txtSearch.setHintTextColor(Color.LTGRAY);
        txtSearch.setTextColor(Color.WHITE);


        adapterHistory.onCopyKeySearch(new SearchAdapter.IClickOnCopyKeySearch() {
            @Override
            public void onClickCopyKeySearch(int index) {
                String text = arrayListHistory.get(index).getTitle();
                if(menu.findItem(R.id.ic_search_toolbar).expandActionView()){
                    txtSearch.setText(text);
                }
                Toast.makeText(getContext(),"index = " +  text, Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                checkAddHistory(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 0){
                    lvData.setVisibility(View.VISIBLE);
                    adapterData.getFilter().filter(newText);
                    layoutHistory.setVisibility(View.INVISIBLE);
                }
                else{
                    lvData.setVisibility(View.INVISIBLE);

                    checkData();
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    private void checkAddHistory(String query){
        boolean flag = true;
        for(int i = 0;i < arrayListHistory.size();i++){
            if(arrayListHistory.get(i).getTitle().equals(query)){
                flag = false;
                return;
            }
        }
        if(flag){
            Search newHistory = new Search(query);
            arrayListHistory.add(newHistory);
            adapterHistory.notifyDataSetChanged();
        }
    }

    private void checkData(){
        if(arrayListHistory.size() <= 0){
            layoutHistory.setVisibility(View.INVISIBLE);
        }
        else{
            layoutHistory.setVisibility(View.VISIBLE);
        }
    }


}














