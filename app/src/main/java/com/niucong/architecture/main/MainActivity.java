package com.niucong.architecture.main;

import android.os.Bundle;

import com.niucong.architecture.R;
import com.niucong.architecture.util.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity的onCreate生命周期
        super.onCreate(savedInstanceState); // 调用超类方法
        setContentView(R.layout.activity_diaries); // 设置布局文件
        initToolbar(); // 初始化顶栏
        initFragment(); // 初始化Fragment
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
    }

    private void initFragment() {
        DiariesFragment diariesFragment = getDiariesFragment(); // 初始化Fragment
        if (diariesFragment == null) { // 查找是否已经创建过日记Fragment
            diariesFragment = new DiariesFragment(); // 创建日记Fragment
            // 将日记Fragment添加到Activity显示
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }
    }

    private DiariesFragment getDiariesFragment() {
        // 通过FragmentManager查找日记展示Fragment
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

}
