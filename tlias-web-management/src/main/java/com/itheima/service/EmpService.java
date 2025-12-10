package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryPrama empQueryPrama);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp selectById(Integer id);

    void update(Emp emp);

}
