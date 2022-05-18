package com.ojy.smart_campus.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.pojo.Teacher;

import java.util.Map;

public interface TeacherService extends IService<Teacher> {

    Teacher login(String account, String password);

    Teacher queryTeacher(Integer id);

    Page<Teacher> queryTeacherList(Page<Teacher> page, String clazzName, String name);

    boolean addOrUpdateTeacher(Teacher teacher);

    boolean delTeacherById(Integer[] ids);

    boolean updatePasswordById(Map<String, Object> map);
}
