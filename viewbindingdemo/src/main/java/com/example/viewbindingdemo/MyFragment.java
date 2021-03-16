package com.example.viewbindingdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewbindingdemo.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    private Context context;

    public MyFragment() {
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //方式一
//        binding = FragmentMyBinding.inflate(getLayoutInflater());
//        return binding.getRoot();

        //方式二
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //方式一
//        binding.tvName.setText("Hello ViewBinding");
//        binding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
//        binding.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "hello world", Toast.LENGTH_SHORT).show();
//            }
//        });

        //方式二
        FragmentMyBinding bind = FragmentMyBinding.bind(view);
        bind.tvName.setText("Hello ViewBinding");
        bind.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        bind.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hello world", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}