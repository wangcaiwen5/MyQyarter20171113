package com.example.myutils.base;

/**
 * Author:wangcaiwen
 * Time:2017/11/12.
 * Description:MVP  view的封装
 */

public interface BaseView {
    void onSuccess();//请求成功
    void onFail();//请求失败
    void showLoading();//显示
    void hideLoading();//隐藏
}
