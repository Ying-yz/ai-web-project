package com.itheima.service;

import com.itheima.pojo.Dept;


import java.util.List;


public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id) throws Exception;

    void insert(Dept dept);

    Dept infoById(Integer id);

    void update(Dept dept);
}
