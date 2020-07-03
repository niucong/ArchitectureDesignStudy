package com.niucong.architecture.source;

import java.util.List;

import androidx.annotation.NonNull;


public interface DataSource<T> {

    // 获得所有数据T
    void getAll(@NonNull DataCallback<List<T>> callback);

    // 获取某个数据T
    void get(@NonNull String id, @NonNull DataCallback<T> callback);

    // 更新某个数据T
    void update(@NonNull T diary);

    // 清空所有数据T
    void clear();

    // 删除某个数据T
    void delete(@NonNull String id);
}
