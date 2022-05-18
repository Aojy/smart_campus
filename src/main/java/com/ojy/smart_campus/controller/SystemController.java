package com.ojy.smart_campus.controller;

import com.ojy.smart_campus.pojo.Admin;
import com.ojy.smart_campus.pojo.LoginForm;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.pojo.Teacher;
import com.ojy.smart_campus.service.AdminService;
import com.ojy.smart_campus.service.StudentService;
import com.ojy.smart_campus.service.TeacherService;
import com.ojy.smart_campus.uitl.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getVerificationCodeImage")
    public void getVerificationCodeImage(HttpSession session, HttpServletResponse response) {
        // 获取图片
        BufferedImage image = CreateVerIficationCodeImage.getVerificationCodeImage();
        // 获取图片上的验证码
        String verification = new String(CreateVerIficationCodeImage.getVerificationCode());
        // 将验证码放入session域中
        session.setAttribute("verificationCode", verification);
        // 将图片响应到客户端浏览器
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpSession session) {
        String verificationCode = (String)session.getAttribute("verificationCode");
        if (verificationCode == null || "".equals(verificationCode)) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }

        if (!verificationCode.equals(loginForm.getVerificationCode())) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }

        // 移除已校验过的验证码
        session.removeAttribute("verificationCode");

        // 校验账号和密码
        String account = loginForm.getUsername();

        String password = MD5.getMD5(loginForm.getPassword());
        Map<String, Object> map = new HashMap<>();
        switch (loginForm.getUserType()) {
            case 1:
                try {
                    Admin admin = adminService.login(account, password);
                    if (admin != null) {
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), 1));
                    } else {
                        throw new RuntimeException();
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
                }
            case 2:
                try {
                    Student student = studentService.login(account,password);
                    if (student != null) {
                        map.put("token", JwtHelper.createToken(student.getId().longValue(), 2));
                    } else {
                        throw new RuntimeException();
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
                }
            case 3:
                try {
                    Teacher teacher = teacherService.login(account,password);
                    if (teacher != null) {
                        map.put("token", JwtHelper.createToken(teacher.getId().longValue(), 3));
                    } else {
                        throw new RuntimeException();
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
                }
        }
        return Result.fail().message("该操作不在本系统业务范围");
    }

    @GetMapping("/getInfo")
    public Result getInfoByToken(@RequestHeader("token") String token) {
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        // 解析token
        Long userId = JwtHelper.getUserId(token);

        Map<String, Object> map = new HashMap<>();
        switch (JwtHelper.getUserType(token)) {
            case 1:
                Admin admin = adminService.queryAdminById(userId.intValue());
                map.put("userType",1);
                map.put("user", admin);
                break;
            case 2:
                Student student = studentService.queryStudentById(userId.intValue());
                map.put("userType",2);
                map.put("user", student);
                break;
            case 3:
                Teacher teacher = teacherService.queryTeacher(userId.intValue());
                map.put("userType",3);
                map.put("user", teacher);
                break;
        }

        return Result.ok(map);
    }

    @ApiOperation("文件上传统一入口")
    @PostMapping("/headerImgUpload")
    public Result headerImgUpload(@ApiParam("头像文件") @RequestPart("multipartFile") MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
        String filename = uuid.concat(multipartFile.getOriginalFilename());
        String filePath = "E:\\JavaProject\\copy-demo\\demo02\\src\\main\\resources\\public\\upload\\".concat(filename);
        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String headerImg = "upload/" + filename;
        return Result.ok(headerImg);
    }

    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public Result updatePwd(@PathVariable String oldPwd,
                            @PathVariable String newPwd,
                            @RequestHeader("token") String token) {
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        // 解析token
        Long userId = JwtHelper.getUserId(token);

        Map<String, Object> map = new HashMap<>();
        map.put("id", userId.intValue());
        map.put("oldPwd", MD5.getMD5(oldPwd));
        map.put("newPwd", MD5.getMD5(newPwd));

        switch (JwtHelper.getUserType(token)) {
            case 1:
                if (adminService.updatePasswordById(map)) {
                    return Result.ok();
                }
                return Result.fail().message("原始密码输入错误");
            case 2:
                if (studentService.updatePasswordById(map)) {
                    return Result.ok();
                }
                return Result.fail().message("原始密码输入错误");
            case 3:
                if (teacherService.updatePasswordById(map)) {
                    return Result.ok();
                }
                return Result.fail().message("原始密码输入错误");
        }

        return Result.fail().message("该模块不存在");
    }

}
