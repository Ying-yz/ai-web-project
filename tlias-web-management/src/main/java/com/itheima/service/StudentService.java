package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void save(Student student);

    Student selectById(Integer id);

    void deleteById(List<Integer> ids);

    void update(Student student);

    void violation(Integer id, Integer score);
}
