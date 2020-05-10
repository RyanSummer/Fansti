package com.cxc.fansti;

import android.view.LayoutInflater;
import android.view.View;
import com.cxc.fansti.base.BaseActivity;
import com.cxc.fansti.databinding.ActivityMainBinding;
import com.cxc.fansti.databinding.LayoutTitleBarBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private LayoutTitleBarBinding titleBarBinding;

    @Override
    public View initView() {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        titleBarBinding = LayoutTitleBarBinding.bind(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        titleBarBinding.ivLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left:
                finish();
                break;

            default:
                break;
        }
    }
}
