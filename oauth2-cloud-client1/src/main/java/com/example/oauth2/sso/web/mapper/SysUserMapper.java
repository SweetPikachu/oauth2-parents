package com.example.oauth2.sso.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.oauth2.sso.web.entities.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> page, @Param("u") SysUser sysUser);


    boolean deleteUserRoleByUserId(@Param("userId") Long userId);


    boolean saveUserRole(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);

}
