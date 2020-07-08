package com.niucong.architecture.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.niucong.architecture.EnApplication;
import com.niucong.architecture.R;
import com.niucong.architecture.data.DiariesRepository;
import com.niucong.architecture.edit.DiaryEditActivity;
import com.niucong.architecture.edit.DiaryEditFragment;
import com.niucong.architecture.main.list.DiariesAdapter;
import com.niucong.architecture.model.Diary;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DiariesFragment extends Fragment implements DiariesContract.View { // 日记展示页面

    private DiariesContract.Presenter mPresenter;// 日志页面的主持人

    private RecyclerView mRecyclerView;

    // Fragment的生命周期onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载日记页面布局
        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        this.mRecyclerView = root.findViewById(R.id.diaries_list);
        // 将日记列表控件传入控制器
        initDiariesList();

        setHasOptionsMenu(true); // 设置界面有菜单功能
        return root;
    }

    @Override
    public void onResume() { // Fragment的生命周期onResume
        super.onResume(); // 调用父类的onResume方法
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();// 调用父类的onDestroy方法
        mPresenter.destroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { // 创建菜单，重写父类中的方法
        inflater.inflate(R.menu.menu_write, menu); // 加载菜单的布局文件
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 菜单的选择监听，重写父类中的方法
        switch (item.getItemId()) { // 判断点击事件
            case R.id.menu_add: // 点击添加按钮
                mPresenter.addDiary(); // 通知控制器添加新的日记
                return true; // 返回true代表菜单点选择事件已经被处理
        }
        return false; // 返回false代表菜单点选择事件没有被处理
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onResult(requestCode, resultCode); // 返回界面获取结果信息
    }

    public void initDiariesList() { // 配置日记列表
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // 设置日记列表为线性布局显示
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL) // 为列表条目添加分割线
        );
        mRecyclerView.setItemAnimator(new DefaultItemAnimator()); // 设置列表默认动画
    }

    @Override
    public void gotoWriteDiary() {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class); // 构造跳转页面的intent
        startActivity(intent); // 根据intent跳转
    }

    @Override
    public void gotoUpdateDiary(String diaryId) {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class); // 构造跳转页面的intent
        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId); // 设置跳转携带信息
        getContext().startActivity(intent); // 根据intent跳转
    }

    @Override
    public void showInputDialog(final Diary data) {
        final EditText editText = new EditText(getContext()); // 创建输入框
        editText.setText(data.getDescription()); // 设置输入框默认文字为日志详情信息

        new AlertDialog.Builder(getContext()).setTitle(data.getTitle())
                .setView(editText)
                .setPositiveButton(EnApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { // 确认按钮点击监听
                                data.setDescription(editText.getText().toString()); // 修改日记信息为输入框信息
                                DiariesRepository.getInstance().update(data); // 更新日记数据
                                mPresenter.start();
                            }
                        })
                .setNegativeButton(EnApplication.get().getString(R.string.cancel), null).show(); // 弹出对话框
    }

    @Override
    public void showSuccess() {
        showMessage(getString(R.string.success)); // 弹出成功提示信息
    }

    @Override
    public void showError() {
        showMessage(getString(R.string.error)); // 弹出失败提示信息
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show(); // 弹出文字提示信息
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setListAdapter(DiariesAdapter mListAdapter) {
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void setPresenter(DiariesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
