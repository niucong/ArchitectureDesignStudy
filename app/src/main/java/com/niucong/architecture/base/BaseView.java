package com.niucong.architecture.base;

/**
 * @Author: niucong
 * 时  间: 2020/7/6 13:01
 * 项目名: ArchitectureDesignStudy
 * 包  名：com.niucong.architecture.base
 * 类  名：BaseView
 * 简  述：<功能简述>
 */
public interface BaseView<T extends BasePresenter> {// View基类

    void setPresenter(T presenter);// 传入Presenter
}
