package com.cxc.fansti.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.cxc.fansti.Constants;
import com.cxc.fansti.MainActivity;
import com.cxc.fansti.base.BaseActivity;
import com.cxc.fansti.databinding.ActivitySplashBinding;
import com.cxc.fansti.utils.PreferencesUtil;

import java.lang.ref.WeakReference;

/**
 * 闪屏页
 */
public class SplashActivity extends BaseActivity {

    private final static String TAG = "SplashActivity";

    private ActivitySplashBinding binding;

    private int delayTime = 5000;
    //主线程中的Handler
    private Handler mMainHandler;

    @Override
    public View initView() {
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this));
        return binding.getRoot();
    }

    @Override
    public void initData() {
        //关闭滑动返回
        closeSwipeBack();

        mMainHandler = new InnerHandler(this);
        mMainHandler.sendEmptyMessageAtTime(200,delayTime);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 静态内部类弱引用方式，防止内存溢出
     */
    private static class InnerHandler extends Handler {

        private WeakReference<SplashActivity> mWeakReference;

        public InnerHandler(SplashActivity activity) {
            super();
            this.mWeakReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    SplashActivity activity = mWeakReference.get();
                    //判断是否已登录
                    boolean isLogin = PreferencesUtil.getBoolean(activity, Constants.LOGIN_STATE,false);
                    Log.d(TAG,"isLogin:"+isLogin);
                    if (isLogin){
                        activity.startActivity(new Intent(activity, MainActivity.class));
                    } else {
                        activity.startActivity(new Intent(activity,LoginActivity.class));
                    }

                    activity.finish();
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除MessageQueue内所有的Message、Callback
        mMainHandler.removeCallbacksAndMessages(null);
    }
}
