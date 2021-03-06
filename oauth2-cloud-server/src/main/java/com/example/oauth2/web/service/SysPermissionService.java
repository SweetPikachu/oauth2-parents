package com.example.oauth2.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oauth2.web.entities.SysPermission;

import java.util.List;


public interface SysPermissionService extends IService<SysPermission> {

    /**
     * @param userId 用户id
     * @return 用户所拥有的权限
     */
    List<SysPermission> findByUserId(Long userId);

}
