package com.cxc.fansti;

import android.view.LayoutInflater;
import android.view.View;

import com.cxc.fansti.base.BaseFragment;
import com.cxc.fansti.databinding.FragmentTestBinding;

public class TestFragment extends BaseFragment {

    private FragmentTestBinding binding;

    @Override
    public View getContentView() {
        binding = FragmentTestBinding.inflate(LayoutInflater.from(getContext()));
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
