package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(EmpQueryPrama empQueryPrama) {

        PageHelper.startPage(empQueryPrama.getPage(),empQueryPrama.getPageSize());

        List<Emp> empList = empMapper.list(empQueryPrama);

        Page<Emp> p = (Page<Emp>) empList;


        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
}
