package com.example.oauth2.sso.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oauth2.sso.web.entities.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> page, @Param("p") SysRole sysRole);
    boolean deleteRolePermissionByRoleId(@Param("roleId") Long roleId);
    boolean saveRolePermission(@Param("roleId") Long roleId,@Param("perIds") List<Long> perIds);

    List<SysRole> findByUserId(@Param("userId")Long userId);
}
