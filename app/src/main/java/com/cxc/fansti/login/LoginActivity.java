package com.cxc.fansti.login;

import android.view.LayoutInflater;
import android.view.View;

import com.cxc.fansti.base.BaseActivity;
import com.cxc.fansti.databinding.ActivityLoginBinding;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    public View initView() {
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
