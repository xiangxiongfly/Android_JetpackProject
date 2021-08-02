package com.example.viewbindingdemo.base;

import android.view.View;
import android.widget.Toast;

import com.example.viewbindingdemo.R;
import com.example.viewbindingdemo.databinding.DetailLayoutBinding;
import com.example.viewbindingdemo.databinding.FragmentFirstBinding;

public class FirstFragment extends BaseFragment<FragmentFirstBinding> {


    @Override
    protected void initView() {
        viewBinding.titleBar.title.setText("这是一个标题2");
        viewBinding.titleBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        viewBinding.titleBar.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
            }
        });

        DetailLayoutBinding detailLayoutBinding = DetailLayoutBinding.bind(mRootView);
        detailLayoutBinding.ivDetail.setImageResource(R.mipmap.ic_launcher);
    }
}