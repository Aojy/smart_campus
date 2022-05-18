package com.ojy.smart_campus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.pojo.Teacher;
import com.ojy.smart_campus.service.TeacherService;
import com.ojy.smart_campus.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(@PathVariable Integer pageNo,
                              @PathVariable Integer pageSize,
                              String clazzName, String name) {
        try {
            Page<Teacher> page = new Page<>(pageNo, pageSize);
            return Result.ok(teacherService.queryTeacherList(page, clazzName, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@RequestBody Teacher teacher) {
        try {
            if (teacherService.addOrUpdateTeacher(teacher)) {
                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail().message("系统繁忙，请稍后重试...");
    }

    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody Integer[] ids) {
        try {
            if (teacherService.delTeacherById(ids)) {
                return Result.ok().message("删除成功");
            }
            return Result.fail().message("删除失败，该数据不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }
}
