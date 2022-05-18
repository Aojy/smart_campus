package com.ojy.smart_campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ojy.smart_campus.mapper.GradeMapper;
import com.ojy.smart_campus.pojo.Grade;
import com.ojy.smart_campus.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public IPage<Grade> queryGradeList(Page<Grade> page, String name) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean saveOrUpdateGrade(Grade grade) {
        if (grade.getId() == null || "".equals(grade.getId())) {
            return gradeMapper.insert(grade) > 0;
        }

        return gradeMapper.updateById(grade) > 0;
    }

    @Override
    public boolean deleteGrade(Integer[] ids) {
        return gradeMapper.deleteByIds(ids) > 0;
    }

    @Override
    public List<Grade> queryGradeList() {
        return gradeMapper.selectList(new QueryWrapper<>());
    }
}
