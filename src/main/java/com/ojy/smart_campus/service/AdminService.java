package com.ojy.smart_campus.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ojy.smart_campus.pojo.Admin;
import com.ojy.smart_campus.pojo.Admin;

import java.util.Map;

public interface AdminService extends IService<Admin> {

    Admin login(String account, String password);

    Admin queryAdminById(Integer id);

    Page<Admin> queryAdminList(Page<Admin> page, String name);

    boolean saveOrUpdateAdmin(Admin admin);

    boolean delAdminById(Integer[] ids);

    boolean updatePasswordById(Map<String, Object> map);
}
