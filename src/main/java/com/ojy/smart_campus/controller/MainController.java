package com.ojy.smart_campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/upload/{fileName}")
    public void getFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        File file = new File("E:\\JavaProject\\copy-demo\\demo02\\src\\main\\resources\\public\\upload\\" + fileName);
        InputStream is = new FileInputStream(file);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }

        is.close();
        out.close();

    }
}
