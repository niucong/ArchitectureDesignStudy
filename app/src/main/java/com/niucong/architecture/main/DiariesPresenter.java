package com.niucong.architecture.main;

import android.app.Activity;
import android.view.View;

import com.niucong.architecture.data.DiariesRepository;
import com.niucong.architecture.main.list.DiariesAdapter;
import com.niucong.architecture.model.Diary;
import com.niucong.architecture.source.DataCallback;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @Author: niucong
 * 时  间: 2020/7/6 13:03
 * 项目名: ArchitectureDesignStudy
 * 包  名：com.niucong.architecture.main
 * 类  名：DiariesPresenter
 * 简  述：<功能简述>
 */
public class DiariesPresenter implements DiariesContract.Presenter {

    private final DiariesRepository mDiariesRepository; // 数据仓库
    private DiariesContract.View mView;// 日记列表视图
    private DiariesAdapter mListAdapter; // 日记列表适配器

    public DiariesPresenter(DiariesContract.View diariesFragment) {// 控制日记显示的Controller
        mDiariesRepository = DiariesRepository.getInstance(); // 获取数据仓库的实例
        mView = diariesFragment;// 将页面对象传入，赋值给日志的成员变量
    }

    @Override
    public void start() {
        initAdapter();
        loadDiaries();
    }

    @Override
    public void destory() {

    }

    @Override
    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() { // 通过数据仓库获取数据
            @Override
            public void onSuccess(List<Diary> diaryList) {
                if (!mView.isActive()) { // 视图未被添加则返回
                    return;
                }
                updateDiaries(diaryList); // 数据获取成功，处理数据
            }

            @Override
            public void onError() {
                if (!mView.isActive()) { // 视图未被添加则返回
                    return;
                }
                mView.showError();  // 数据获取失败，弹出错误提示
            }
        });
    }

    @Override
    public void addDiary() {
        mView.gotoWriteDiary(); // 跳转添加日记
    }

    @Override
    public void updateDiary(@NonNull Diary diary) {
        mView.gotoUpdateDiary(diary.getId()); // 跳转更新日记
    }

    @Override
    public void onResult(int requestCode, int resultCode) {
        if (Activity.RESULT_OK != resultCode) { // 处理结果不是成功则返回
            return;
        }
        mView.showSuccess(); // 弹出成功提示信息
    }

    private void initAdapter() { // 初始化适配器
        mListAdapter = new DiariesAdapter(); // 创建日记列表的适配器
        // 设置列表的条目长按事件
        mListAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                updateDiary(data); // 更新日记
                return false;
            }
        });
        mView.setListAdapter(mListAdapter);
    }

    private void updateDiaries(List<Diary> diaries) {
        mListAdapter.update(diaries); // 更新列表中的日记数据
    }
}
