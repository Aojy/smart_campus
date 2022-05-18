package com.ojy.smart_campus.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Clazz;

import java.util.List;

public interface ClazzService {

    IPage<Clazz> queryClazzList(Page<Clazz> page, String grade, String name);

    boolean saveOrUpdateClazz(Clazz clazz);

    boolean deleteGrade(Integer[] ids);

    List<Clazz> queryClazzList();
}
