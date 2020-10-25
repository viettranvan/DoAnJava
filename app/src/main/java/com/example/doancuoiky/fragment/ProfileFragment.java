package com.example.doancuoiky.fragment;


import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.R;

import com.example.doancuoiky.activity.ChangeInfoActivity;
import com.example.doancuoiky.activity.ChangePasswordActivity;
import com.example.doancuoiky.activity.MainActivity;


public class ProfileFragment extends Fragment {

    private LinearLayout addAddress,changeInfo,changePassword, profileContainer, toggleProfile;
    private boolean isExpand = true; // biến check xem profile là expand hay collapse
    private ImageView img;

    private LinearLayout profileNotLoggedIn, profileLogged, changeInfoPassword;
    private TextView profileIconNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        anhXa(view);


        toggleProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExpand){
                    isExpand = false;
                    profileContainer.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.icon_down);
                }
                else {
                    isExpand = true;
                    profileContainer.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.icon_right);
                }
            }
        });

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
                Intent intent = new Intent(getActivity().getApplicationContext(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Chuyển đến trang đổi mật khẩu",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), ChangePasswordActivity.class);

                startActivity(intent);
            }
        });

        return  view;
    }

    private void anhXa(View view) {
        addAddress = view.findViewById(R.id.add_address);
        changeInfo = view.findViewById(R.id.change_info);
        changePassword = view.findViewById(R.id.change_password);
        profileContainer = view.findViewById(R.id.layout_profile_container);
        toggleProfile = view.findViewById(R.id.show_hide_profile);
        img = view.findViewById(R.id.img_show_hide_profile);

        profileNotLoggedIn = view.findViewById(R.id.profile_not_logged_in);
        profileLogged = view.findViewById(R.id.profile_logged);
        changeInfoPassword = view.findViewById(R.id.chane_info_password);
        profileIconNext = view.findViewById(R.id.icon_next_profile_fragment);


        if(MainActivity.isLogin){
            profileNotLoggedIn.setVisibility(View.GONE);
            profileLogged.setVisibility(View.VISIBLE);
            toggleProfile.setVisibility(View.VISIBLE);
            changeInfoPassword.setVisibility(View.VISIBLE);
            profileIconNext.setVisibility(View.GONE);
        }
        else{
            profileNotLoggedIn.setVisibility(View.VISIBLE);
            profileLogged.setVisibility(View.GONE);
            toggleProfile.setVisibility(View.GONE);
            changeInfoPassword.setVisibility(View.GONE);
            profileIconNext.setVisibility(View.VISIBLE);
        }
    }
}
