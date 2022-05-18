package com.ojy.smart_campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ojy.smart_campus.mapper.TeacherMapper;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.pojo.Teacher;
import com.ojy.smart_campus.service.TeacherService;
import com.ojy.smart_campus.uitl.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher login(String account, String password) {
        return teacherMapper.selectTeacherByAccountAndPassword(account, password);
    }

    @Override
    public Teacher queryTeacher(Integer id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public Page<Teacher> queryTeacherList(Page<Teacher> page, String clazzName, String name) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(clazzName)) {
            queryWrapper.like("clazz_name", clazzName);
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean addOrUpdateTeacher(Teacher teacher) {
        if (teacher.getId() == null || "".equals(teacher.getId())) {
            teacher.setPassword(MD5.getMD5(teacher.getPassword()));
            return teacherMapper.insert(teacher) > 0;
        }
        return teacherMapper.updateById(teacher) > 0;
    }

    @Override
    public boolean delTeacherById(Integer[] ids) {
        return teacherMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updatePasswordById(Map<String, Object> map) {
        return teacherMapper.updateTeacherPassword(map);
    }
}
