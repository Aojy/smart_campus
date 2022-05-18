package com.ojy.smart_campus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Clazz;
import com.ojy.smart_campus.service.ClazzService;
import com.ojy.smart_campus.uitl.Result;
import com.ojy.smart_campus.uitl.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/clazz")
public class ClazzController {


    @Autowired
    private ClazzService clazzService;

    @GetMapping("/getClazzs")
    public Result getClazzList() {
        try {
            return Result.ok(clazzService.queryClazzList());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable Integer pageNo,
                                 @PathVariable Integer pageSize,
                                 String gradeName, String name) {
        try {
            Page<Clazz> page = new Page<>(pageNo, pageSize);
            return Result.ok(clazzService.queryClazzList(page, gradeName, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz) {
        try {
            if (clazzService.saveOrUpdateClazz(clazz)) {
                return Result.ok();
            }
            return Result.fail();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("网络繁忙，请稍后重试...");
        }
    }

    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody Integer[] ids) {
        try {
            if (clazzService.deleteGrade(ids)) {
                return Result.ok().message("删除成功");
            }
            return Result.fail().message("删除失败，该数据不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }
}
