package com.ojy.smart_campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ojy.smart_campus.pojo.Grade;
import org.springframework.stereotype.Repository;


@Repository
public interface GradeMapper extends BaseMapper<Grade> {
    int deleteByIds(Integer[] ids);

    int insert(Grade grade);

    int updateById(Grade grade);

}