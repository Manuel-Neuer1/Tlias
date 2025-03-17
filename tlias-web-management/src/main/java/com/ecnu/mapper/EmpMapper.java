package com.ecnu.mapper;

import com.ecnu.pojo.Emp;
import com.ecnu.pojo.EmpQueryParam;
import com.ecnu.pojo.LoginInfo;
import com.ecnu.pojo.PageResult;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
 * @Mapper 注解表示这是一个 MyBatis 的 Mapper 接口，
 * 这个是员工的Mapper接口
 */
@Mapper
public interface EmpMapper {
    /*
     * 查询总记录数
     */
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
    public Long count();

    /*
     * 分页查询的操作
     */
    @Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc limit #{start},#{pageSize};")
    public List<Emp> list(Integer start,Integer pageSize);

    /*
     * 查询总记录数
     */
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public Long getCount();
    //----------------------------------使用PageHelper插件完成分页操作----------------------------------------
    /*
     * 分页查询
     */
    //@Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc")
    public List<Emp> list2(EmpQueryParam empQueryParam);

    /*
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createDate},#{updateDate})")
    void insert(Emp emp);


    void deleteByIds(Integer[] ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    /*
    ** 统计员工职位人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    LoginInfo selectByUsernameAndPassword(Emp emp);
}
