package com.ecnu.mapper;

import com.ecnu.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
     * 查询所有部门信息
     * @return 部门信息列表
     */
    /*@Results({
            //@Result(property = "id", column = "id"),
            //@Result(property = "name", column = "name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })*/
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);
}
