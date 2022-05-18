package com.ojy.smart_campus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_clazz")
public class Clazz {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer number;

    private String introduction;

    private String headmaster;

    private String email;

    private String telephone;

    private String gradeName;

    public Clazz(Integer id, String name, Integer number, String introduction, String headmaster, String email, String telephone, String gradeName) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.introduction = introduction;
        this.headmaster = headmaster;
        this.email = email;
        this.telephone = telephone;
        this.gradeName = gradeName;
    }

    public Clazz() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}