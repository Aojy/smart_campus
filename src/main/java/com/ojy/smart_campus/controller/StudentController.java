package com.ojy.smart_campus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Clazz;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.service.StudentService;
import com.ojy.smart_campus.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@PathVariable Integer pageNo,
                                  @PathVariable Integer pageSize,
                                  String clazzName, String name) {
        try {
            Page<Student> page = new Page<>(pageNo, pageSize);
            return Result.ok(studentService.queryStudentList(page, clazzName, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student) {
        try {
            if (studentService.addOrUpdateStudent(student)) {
                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.fail().message("系统繁忙，请稍后重试...");
    }

    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody Integer[] ids) {
        try {
            if (studentService.delStudentById(ids)) {
                return Result.ok().message("删除成功");
            }
            return Result.fail().message("删除失败，该数据不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }

}
