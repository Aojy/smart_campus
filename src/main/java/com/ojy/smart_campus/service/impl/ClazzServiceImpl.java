package com.ojy.smart_campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ojy.smart_campus.mapper.ClazzMapper;
import com.ojy.smart_campus.pojo.Clazz;
import com.ojy.smart_campus.pojo.Grade;
import com.ojy.smart_campus.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public IPage<Clazz> queryClazzList(Page<Clazz> page, String grade, String name) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(grade)) {
            queryWrapper.like("grade_name", grade);
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean saveOrUpdateClazz(Clazz clazz) {
        if (clazz.getId() == null || "".equals(clazz.getId())) {
            return clazzMapper.insert(clazz) > 0;
        }
        return clazzMapper.updateById(clazz) > 0;
    }

    @Override
    public boolean deleteGrade(Integer[] ids) {
        return clazzMapper.deleteByIds(ids) > 0;
    }

    @Override
    public List<Clazz> queryClazzList() {
        return clazzMapper.selectClazzList();
    }
}
