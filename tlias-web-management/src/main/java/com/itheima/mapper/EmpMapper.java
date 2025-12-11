package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    // @Select("select emp.*, dept.name as dept_name from emp left join dept on
    // emp.dept_id = dept.id")
    List<Emp> list(EmpQueryPrama empQueryPrama);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name,gender,job,phone,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{job},#{phone},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteById(List<Integer> ids);


    Emp selectById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> getEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> getEmpGenderData();

    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> selectAll();
    @Select("select count(*) from emp where dept_id = #{deptId}")
    Long countByDeptId(Integer id);
}
