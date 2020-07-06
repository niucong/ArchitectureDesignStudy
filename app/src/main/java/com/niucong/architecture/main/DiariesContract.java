package com.niucong.architecture.main;

import com.niucong.architecture.base.BasePresenter;
import com.niucong.architecture.base.BaseView;
import com.niucong.architecture.main.list.DiariesAdapter;
import com.niucong.architecture.model.Diary;

import androidx.annotation.NonNull;

/**
 * @Author: niucong
 * 时  间: 2020/7/6 13:19
 * 项目名: ArchitectureDesignStudy
 * 包  名：com.niucong.architecture.main
 * 类  名：DiariesContract
 * 简  述：<功能简述>
 */
public interface DiariesContract {

    interface View extends BaseView<Presenter> {// 日记列表视图

        void gotoWriteDiary(); // 跳转添加日记

        void gotoUpdateDiary(String diaryId); // 跳转更新日记

        void showSuccess(); // 弹出成功提示信息

        void showError(); // 弹出失败提示信息

        boolean isActive();  // 判断Fragment是否已经添加到了Activity中

        void setListAdapter(DiariesAdapter mListAdapter); // 设置适配器
    }

    interface Presenter extends BasePresenter {// 日记列表主持人
        void loadDiaries(); // 加载日记数据

        void addDiary(); // 跳转添加日记

        void updateDiary(@NonNull Diary diary); // 跳转更新日记

        void onResult(int requestCode, int resultCode); // 返回界面获取结果信息
    }
}
