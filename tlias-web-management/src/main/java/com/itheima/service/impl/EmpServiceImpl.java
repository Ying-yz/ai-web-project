package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;

    private final EmpExprMapper empExprMapper;

    private final EmpLogService empLogService;

    public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprMapper, EmpLogMapper empLogMapper, EmpLogService empLogMapper1, EmpLogService empLogService) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.empLogService = empLogService;
    }

    @Override
    public PageResult<Emp> page(EmpQueryPrama empQueryPrama) {

        PageHelper.startPage(empQueryPrama.getPage(),empQueryPrama.getPageSize());

        List<Emp> empList = empMapper.list(empQueryPrama);

        Page<Emp> p = (Page<Emp>) empList;


        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    //事务管理
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();

            if (! CollectionUtils.isEmpty(exprList)){

                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insert(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工:" + emp);
            empLogService.insertLog(empLog);
        }

    }
}
