package com.example.myutils.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

/**
 * Author:wangcaiwen
 * Time:2017/11/12.
 * Description:basefragment的封装
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(view==null){
            view = View.inflate(getActivity(), getLayoutid(), null);

        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }

        return view;
    }

    protected abstract int getLayoutid();


    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
