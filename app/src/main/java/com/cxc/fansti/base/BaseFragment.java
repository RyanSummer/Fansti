package com.cxc.fansti.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * BaseActivity基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getContentView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        setListener();
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    public abstract View getContentView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置View的点击事件
     */
    protected abstract void setListener();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
