package com.example.oauth2.sso.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oauth2.sso.web.entities.SysRole;


public interface SysRoleService extends IService<SysRole> {

    IPage<SysRole> selectPage(Page<SysRole> page, SysRole sysRole);

    SysRole findById(Long id);

    boolean deleteById(Long id);
}
