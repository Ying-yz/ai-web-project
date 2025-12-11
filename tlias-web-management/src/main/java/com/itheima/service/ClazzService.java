package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

public interface ClazzService {
    PageResult page(String name, String begin, String end, Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insert(Clazz clazz);

    Clazz selectById(Integer id);
}
