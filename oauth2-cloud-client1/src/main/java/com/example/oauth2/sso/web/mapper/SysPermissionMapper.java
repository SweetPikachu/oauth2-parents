package com.example.oauth2.sso.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.oauth2.sso.web.entities.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectPermissionByUserId(@Param("userId")  Long userId);
    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);
}
