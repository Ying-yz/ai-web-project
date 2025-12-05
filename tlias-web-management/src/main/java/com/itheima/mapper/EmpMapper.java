package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    //@Select("select emp.*, dept.name as dept_name from emp left join dept on emp.dept_id = dept.id")
    List<Emp> list(EmpQueryPrama empQueryPrama);
}
