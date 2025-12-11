package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult page(String name, String begin, String end, Integer page, Integer pageSize);

    void deleteById(Integer id);

    void save(Clazz clazz);

    Clazz selectById(Integer id);

    void update(Clazz clazz);

    List<Clazz> selectAll();
}
