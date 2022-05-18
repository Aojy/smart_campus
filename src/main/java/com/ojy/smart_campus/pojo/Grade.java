package com.ojy.smart_campus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_grade")
public class Grade {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String manager;

    private String email;

    private String telephone;

    private String introduction;

    public Grade() {
    }

    public Grade(String name, Integer id, String manager, String email, String telephone, String introduction) {
        this.name = name;
        this.id = id;
        this.manager = manager;
        this.email = email;
        this.telephone = telephone;
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}