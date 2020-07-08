package com.niucong.architecture.edit;

import com.niucong.architecture.base.BasePresenter;
import com.niucong.architecture.base.BaseView;

/**
 * @Author: niucong
 * 时  间: 2020/7/8 13:48
 * 项目名: ArchitectureDesignStudy
 * 包  名：com.niucong.architecture.edit
 * 类  名：DiaryEditContract
 * 简  述：<功能简述>
 */
public class DiaryEditContract {

    interface Presenter extends BasePresenter {// 修改日记的主持人

        void saveDiary(String title, String description); // 保存日记信息

        void requestDiary(); // 获取日记信息
    }

    interface View extends BaseView<Presenter> {// 修改日记的视图

        void showError(); // 弹出错误提示信息

        void showDiariesList(); // 显示日记列表

        void setTitle(String title); // 设置标题

        void setDescription(String description); // 设置详情

        boolean isActive(); // 判断Fragment是否已经添加到了Activity中
    }
}
