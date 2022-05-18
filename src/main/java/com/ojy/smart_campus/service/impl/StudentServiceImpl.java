package com.ojy.smart_campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ojy.smart_campus.mapper.StudentMapper;
import com.ojy.smart_campus.pojo.Clazz;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.service.StudentService;
import com.ojy.smart_campus.uitl.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    public StudentMapper studentMapper;

    @Override
    public Student login(String account, String password) {
        return studentMapper.selectStudentByAccountAndPassword(account, password);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public Page<Student> queryStudentList(Page<Student> page, String clazzName, String name) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
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
    public boolean addOrUpdateStudent(Student student) {
        if (student.getId() == null || "".equals(student.getId())) {
            student.setPassword(MD5.getMD5(student.getPassword()));
            return studentMapper.insert(student) > 0;
        }
        return studentMapper.updateById(student) > 0;
    }

    @Override
    public boolean delStudentById(Integer[] ids) {
        return studentMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updatePasswordById(Map<String, Object> map) {
        return studentMapper.updateStudentPassword(map);
    }
}
