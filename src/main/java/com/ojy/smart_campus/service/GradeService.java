package com.ojy.smart_campus.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ojy.smart_campus.pojo.Grade;

import java.util.List;


public interface GradeService extends IService<Grade> {

    IPage<Grade> queryGradeList(Page<Grade> page, String name);

    boolean saveOrUpdateGrade(Grade grade);

    boolean deleteGrade(Integer[] ids);

    List<Grade> queryGradeList();
}
