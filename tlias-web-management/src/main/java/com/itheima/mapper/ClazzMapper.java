package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(String name, String begin, String end);

    @Delete("delete from clazz where master_id = #{id}")
    void deleteById(Integer id);

    //新增班级: 所传的参数有 name room begindate enddate masterid subject
    @Select("insert into clazz values (null,#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void save(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);

    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, " +
            "end_date = #{endDate}, master_id = #{masterId}, subject = #{subject} " +
            "where id = #{id}")
    void updateById(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time " +
            "from clazz")
    List<Clazz> selectAll();
}
