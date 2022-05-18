package com.ojy.smart_campus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Grade;
import com.ojy.smart_campus.service.GradeService;
import com.ojy.smart_campus.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String gradeName) {
        try {
            // 分页查询
            Page<Grade> page = new Page<>(pageNo, pageSize);
            return Result.ok(gradeService.queryGradeList(page, gradeName));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @GetMapping("/getGrades")
    public Result getGrades() {
        try {
            return Result.ok(gradeService.queryGradeList());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade){
        try {
            if (gradeService.saveOrUpdateGrade(grade)) {
                return Result.ok();
            }
            return Result.fail().message("添加失败，该年级已存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }

    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@RequestBody Integer[] ids) {
        try {
            if (gradeService.deleteGrade(ids)) {
                return Result.ok().message("删除成功");
            }
            return Result.fail().message("删除失败，该数据不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }

    }

}
