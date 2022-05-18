package com.ojy.smart_campus.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ojy.smart_campus.pojo.Clazz;
import com.ojy.smart_campus.pojo.Student;

import java.util.Map;

public interface StudentService extends IService<Student> {

    Student login(String account, String password);

    Student queryStudentById(Integer id);

    Page<Student> queryStudentList(Page<Student> page, String clazzName, String name);

    boolean addOrUpdateStudent(Student student);

    boolean delStudentById(Integer[] ids);

    boolean updatePasswordById(Map<String, Object> map);
}
