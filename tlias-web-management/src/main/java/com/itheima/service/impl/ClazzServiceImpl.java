package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult page(String name, String begin, String end, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<Clazz> clazzList = clazzMapper.list(name, begin, end);

        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id) {

        clazzMapper.deleteById(id);
    }

    @Override
    public void insert(Clazz clazz) {

        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }
}
