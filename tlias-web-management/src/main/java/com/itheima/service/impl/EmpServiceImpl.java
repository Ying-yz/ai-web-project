package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryPrama;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryPrama empQueryPrama) {

        PageHelper.startPage(empQueryPrama.getPage(),empQueryPrama.getPageSize());

        List<Emp> empList = empMapper.list(empQueryPrama);

        Page<Emp> p = (Page<Emp>) empList;


        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.inset(emp);

        //2.保存员工工作经历信息
        List<EmpExpr> emprList = emp.getEmprList();

        if (! CollectionUtils.isEmpty(emprList)){

            emprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });

            empExprMapper.insert(emprList);
        }

    }
}
