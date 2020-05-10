package com.cxc.fansti.base;

import android.Manifest;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.cxc.fansti.R;
import com.cxc.fansti.callback.PermissionsCallback;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener , EasyPermissions.PermissionCallbacks{

    private String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private PermissionsCallback mCallback;

    private SwipeBackLayout mSwipeBackLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 执行初始化方法
        setContentView(initView());

        //初始化沉浸式
        initStatusBar();

        //初始化滑动返回设置
        initSwipeBack();

        initData();

        setListener();
    }

    /**
     * 初始化沉浸式
     */
    public void initStatusBar() {
        ImmersionBar.with(this)
                .barColor(R.color.whiteColor)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 沉浸式图片
     */
    public void setTransparent() {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();
    }

    /**
     * 初始化滑动返回
     */
    public void initSwipeBack(){
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        //mSwipeBackLayout.setEdgeSize(200);
    }

    /**
     * 关闭滑动返回
     */
    public void closeSwipeBack(){
        setSwipeBackEnable(false);
    }

    /**
     * 初始化
     * 设置布局文件
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化操作
     *
     * @return
     */
    public abstract void initData();

    /**
     * 点击事件设置
     *
     * @return
     */
    public abstract void setListener();

    /**
     * 点击事件处理
     * @param v
     */
    @Override
    public abstract void onClick(View v);

    /**
     * 动态权限接口定义
     *
     * @param callback
     */
    public void requestPermissions(PermissionsCallback callback) {
        mCallback = callback;
        if (EasyPermissions.hasPermissions(this, perms)) {
            mCallback.onAccept();
        } else {
            EasyPermissions.requestPermissions(this, "请同意以下权限!", 100, perms);
        }
    }

    /**
     * 动态权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 权限接受回调
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mCallback.onAccept();
    }

    /**
     * 权限拒绝回调
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        mCallback.onDenied();
    }

    /**
     * 点击非输入框的空白区域自动隐藏软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 点击非输入框的空白区域自动隐藏软键盘
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 适配
     * @return
     */
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),1080);
    }
}
