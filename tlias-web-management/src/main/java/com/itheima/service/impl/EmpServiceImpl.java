package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;

    private final EmpExprMapper empExprMapper;

    private final EmpLogService empLogService;

    public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprMapper, EmpLogService empLogService) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.empLogService = empLogService;
    }

    @Override
    public PageResult<Emp> page(EmpQueryPrama empQueryPrama) {

        PageHelper.startPage(empQueryPrama.getPage(), empQueryPrama.getPageSize());

        List<Emp> empList = empMapper.list(empQueryPrama);

        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    // 事务管理
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void save(Emp emp) {
        // 1.保存员工基本信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();

            if (!CollectionUtils.isEmpty(exprList)) {

                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insert(exprList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工:" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void delete(List<Integer> ids) {
        // 1.批量删除员工信息
        empMapper.deleteById(ids);
        // 2.批量删除员工工作经历
        empExprMapper.deleteByEmpId(ids);

    }

    @Override
    public Emp selectById(Integer id) {

        return empMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 2.修改员工工作经历
        // 2.1.删除员工工作经历
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        // 2.2.添加员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
                empExprMapper.insert(exprList);
            });
        }
    }

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

    @Override
    public Login login(Emp emp) {
        //1. 调用mapper接口, 根据用户名和密码查询员工信息
        Emp e = empMapper.selectUP(emp);
        //2. 判断: 判断是否存在这个员工, 如果存在, 组装登录成功信息
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new Login(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        return null;
        //3. 不存在, 返回null
    }


}
