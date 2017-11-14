package com.example.myutils.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Author:wangcaiwen
 * Time:2017/11/13.
 * Description:
 */

public  class BaseApplication extends Application {
    //Application为整个应用保存全局的RefWatcher
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...


        //检测fragment中的内存泄露
        refWatcher = LeakCanary.install(this);



    }
    //检测fragment中的内存泄露
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    /*还需要创建一个BaseFragment添加如下代码：

    public abstract class BaseFragment extends Fragment {
        @Override
        public void onDestroy() {
            super.onDestroy();
            RefWatcher refWatcher = App.getRefWatcher(getActivity());
            refWatcher.watch(this);
        }
    }*/

}
