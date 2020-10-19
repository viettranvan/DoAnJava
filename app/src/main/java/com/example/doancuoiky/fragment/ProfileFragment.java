package com.example.doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.R;

public class ProfileFragment extends Fragment {

    private LinearLayout addAddress,changeInfo,changePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        addAddress = view.findViewById(R.id.add_address);
        changeInfo = view.findViewById(R.id.change_info);
        changePassword = view.findViewById(R.id.change_password);

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Chuyển đến trang thêm địa chỉ",Toast.LENGTH_SHORT).show();
            }
        });

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Chuyển đến trang thay đổi thông tin",Toast.LENGTH_SHORT).show();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Chuyển đến trang đổi mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }

}
