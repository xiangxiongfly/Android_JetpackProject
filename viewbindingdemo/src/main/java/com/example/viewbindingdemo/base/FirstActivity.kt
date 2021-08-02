package com.example.viewbindingdemo.base;

import android.view.View;
import android.widget.Toast;

import com.example.viewbindingdemo.R;
import com.example.viewbindingdemo.databinding.ActivityFirstBinding;
import com.example.viewbindingdemo.databinding.DetailLayoutBinding;

public class FirstActivity extends BaseActivity<ActivityFirstBinding> {

    private DetailLayoutBinding detailLayoutBinding;

    @Override
    protected void initView() {
        viewBinding.titleBar.title.setText("这是一个标题");
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

        detailLayoutBinding = DetailLayoutBinding.bind(mRootView);
        detailLayoutBinding.ivDetail.setImageResource(R.mipmap.ic_launcher);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FirstFragment())
                .commit();
    }
}