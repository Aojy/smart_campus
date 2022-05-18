package com.ojy.smart_campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ojy.smart_campus.pojo.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzMapper extends BaseMapper<Clazz> {
    int deleteByIds(Integer[] ids);

    int insert(Clazz clazz);

    Clazz selectById(Integer id);

    int updateById(Clazz clazz);

    List<Clazz> selectClazzList();
}