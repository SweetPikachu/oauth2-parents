package com.example.oauth2.sso.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.oauth2.sso.web.entities.SysPermission;
import com.example.oauth2.sso.web.entities.SysRole;
import com.example.oauth2.sso.web.mapper.SysPermissionMapper;
import com.example.oauth2.sso.web.mapper.SysRoleMapper;
import com.example.oauth2.sso.web.service.SysRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> page, SysRole sysRole) {
        return baseMapper.selectPage(page, sysRole);
    }

    @Autowired
    private SysPermissionMapper sysPermissionMapper; // 报错正常， idea原因不识别

    @Override
    public SysRole findById(Long id) {
        if(id == null) {
            return new SysRole();
        }

        SysRole sysRole = baseMapper.selectById(id);

        List<SysPermission> permissions = sysPermissionMapper.findByRoleId(id);

        sysRole.setPerList(permissions);
        return sysRole;
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(SysRole entity) {
        entity.setUpdateDate(new Date());

        boolean flag = super.saveOrUpdate(entity);

        if(flag) {

            baseMapper.deleteRolePermissionByRoleId(entity.getId());


            if(CollectionUtils.isNotEmpty(entity.getPerIds())) {
                baseMapper.saveRolePermission(entity.getId(), entity.getPerIds());
            }
        }
        return flag;
    }

    @Transactional //事务管理
    @Override
    public boolean deleteById(Long id) {

        baseMapper.deleteById(id);

        baseMapper.deleteRolePermissionByRoleId(id);
        return true;
    }

}

