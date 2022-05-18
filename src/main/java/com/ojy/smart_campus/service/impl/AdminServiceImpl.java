package com.ojy.smart_campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ojy.smart_campus.mapper.AdminMapper;
import com.ojy.smart_campus.pojo.Admin;
import com.ojy.smart_campus.pojo.Student;
import com.ojy.smart_campus.pojo.Teacher;
import com.ojy.smart_campus.uitl.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements com.ojy.smart_campus.service.AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String account, String password) {
        return adminMapper.selectAdminByAccountAndPassword(account, password);
    }

    @Override
    public Admin queryAdminById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public Page<Admin> queryAdminList(Page<Admin> page, String name) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean saveOrUpdateAdmin(Admin admin) {
        if (admin.getId() == null || "".equals(admin.getId())) {
            admin.setPassword(MD5.getMD5(admin.getPassword()));
            return adminMapper.insert(admin) > 0;
        }
        return adminMapper.updateById(admin) > 0;
    }

    @Override
    public boolean delAdminById(Integer[] ids) {
        return adminMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updatePasswordById(Map<String, Object> map) {
        return adminMapper.updateAdminPassword(map);
    }

}
