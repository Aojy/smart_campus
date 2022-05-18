package com.ojy.smart_campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ojy.smart_campus.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {
    int deleteByIds(Integer[] ids);

    int insert(Teacher teacher);

    Teacher selectById(Integer id);

    int updateById(Teacher teacher);

    Teacher selectTeacherByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    boolean updateTeacherPassword(Map<String, Object> map);
}