package com.example.myutils.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;


/**
 * Author:wangcaiwen
 * Time:2017/11/12.
 * Description:
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId());
        super.onCreate(savedInstanceState);
       // MobclickAgent. startWithConfigure(UMAnalyticsConfig config)

        initView(savedInstanceState);
        
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    /**
     * 默认不关闭当前activity
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    /**
     * 设置是否关闭当前activity
     * @param clz
     * @param isCloseCurrentActivity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 设置是否关闭当前activity传值
     * @param clz
     * @param isCloseCurrentActivity
     * @param bundle
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    /**
     * 点击两次back退出程序
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
           // ToastShow.showToast(getApplicationContext(),"");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            Log.e(TAG, "exit application");

            this.finish();
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }
}
