package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import com.itheima.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> page(EmpQueryPrama empQueryPrama);

    void save(Emp emp);

}
