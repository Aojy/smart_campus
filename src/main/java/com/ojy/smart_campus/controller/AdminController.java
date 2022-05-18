package com.ojy.smart_campus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojy.smart_campus.pojo.Admin;
import com.ojy.smart_campus.pojo.Teacher;
import com.ojy.smart_campus.service.AdminService;
import com.ojy.smart_campus.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@PathVariable Integer pageNo,
                              @PathVariable Integer pageSize,
                              String adminName) {
        try {
            Page<Admin> page = new Page<>(pageNo, pageSize);
            return Result.ok(adminService.queryAdminList(page, adminName));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@RequestBody Admin admin) {
        try {
            if (adminService.saveOrUpdateAdmin(admin)) {
                return Result.ok();
            }
            return Result.fail().message("添加失败，该年级已存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }


    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(@RequestBody Integer[] ids) {
        try {
            if (adminService.delAdminById(ids)) {
                return Result.ok().message("删除成功");
            }
            return Result.fail().message("删除失败，该数据不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("系统繁忙，请稍后重试...");
        }
    }

}
