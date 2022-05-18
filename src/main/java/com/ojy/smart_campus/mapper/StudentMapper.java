package com.ojy.smart_campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ojy.smart_campus.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    int deleteByIds(Integer[] ids);

    int insert(Student student);

    Student selectById(Integer id);

    int updateById(Student student);

    Student selectStudentByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    boolean updateStudentPassword(Map<String, Object> map);
}