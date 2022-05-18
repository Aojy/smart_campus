package com.ojy.smart_campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ojy.smart_campus.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    int deleteByIds(Integer[] ids);

    int insert(Admin admin);

    Admin selectById(Integer id);

    int updateById(Admin admin);

    Admin selectAdminByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    boolean updateAdminPassword(Map<String, Object> map);
}