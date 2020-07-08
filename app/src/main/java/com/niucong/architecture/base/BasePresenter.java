package com.niucong.architecture.base;

/**
 * @Author: niucong
 * 时  间: 2020/7/6 12:59
 * 项目名: ArchitectureDesignStudy
 * 包  名：com.niucong.architecture.base
 * 类  名：BasePresenter
 * 简  述：<功能简述>
 */
public interface BasePresenter {// Presenter基类

    void start();// Presenter生命周期开始

    void destroy();// Presenter生命周期结束

}
