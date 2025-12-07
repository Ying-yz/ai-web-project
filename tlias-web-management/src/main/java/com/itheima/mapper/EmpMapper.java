package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    //@Select("select emp.*, dept.name as dept_name from emp left join dept on emp.dept_id = dept.id")
    List<Emp> list(EmpQueryPrama empQueryPrama);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name,gender,job,phone,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{job},#{phone},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime} ")
    void inset(Emp emp);
}
