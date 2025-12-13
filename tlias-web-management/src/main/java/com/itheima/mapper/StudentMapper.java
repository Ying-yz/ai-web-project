package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(String name, Integer degree, Integer clazzId);

    void save(Student student);

    @Select("select * from student where id = #{id}")
    Student selectById(Integer id);

    void deleteById(List<Integer> ids);

    void update(Student student);

    void violation(Integer id, Integer score);

    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> getStudentCountData();
}
