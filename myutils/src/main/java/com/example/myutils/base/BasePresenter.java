package com.example.myutils.base;

/**
 * Author:wangcaiwen
 * Time:2017/11/12.
 * Description:MVP  presenter的封装
 */

public class BasePresenter<VIEW> {

    public VIEW mView;


    public void onResumePresenter(VIEW mView){
        this.mView = mView;
    }

    public void  onDestroyPresenter(){
        this.mView=null;
    }


}
