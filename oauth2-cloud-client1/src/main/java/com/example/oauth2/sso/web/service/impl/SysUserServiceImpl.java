package com.example.oauth2.sso.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.oauth2.sso.web.entities.SysRole;
import com.example.oauth2.sso.web.entities.SysUser;
import com.example.oauth2.sso.web.mapper.SysRoleMapper;
import com.example.oauth2.sso.web.mapper.SysUserMapper;
import com.example.oauth2.sso.web.service.SysUserService;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final String PASSWORD_DEFAULT = "$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm";

    @Override
    public SysUser findByUsername(String username) {
        if(StringUtils.isNullOrEmpty(username)){
            return null;
        }
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        return baseMapper.selectOne(queryWrapper);

    }

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> page, SysUser sysUser) {
        return baseMapper.selectPage(page, sysUser);
    }

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public SysUser findById(Long id) {
        if(id == null) {
            return new SysUser();
        }


        SysUser sysUser = baseMapper.selectById(id);

        List<SysRole> roleList = sysRoleMapper.findByUserId(id);
        sysUser.setRoleList(roleList);
        return sysUser;
    }

    @Override
    public boolean deleteById(Long id) {

        SysUser sysUser = baseMapper.selectById(id);

        sysUser.setEnabled(false);
        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return true;
    }

    @Transactional //开启事务
    @Override
    public boolean saveOrUpdate(SysUser entity) {
        if(entity != null && entity.getId() == null) {
            //新增设置默认密码1234
            entity.setPassword(PASSWORD_DEFAULT);
        }
        entity.setUpdateDate(new Date());

        boolean flag = super.saveOrUpdate(entity);

        if(flag) {

            baseMapper.deleteUserRoleByUserId(entity.getId());

            if(CollectionUtils.isNotEmpty(entity.getRoleIds())) {
                baseMapper.saveUserRole(entity.getId(), entity.getRoleIds());
            }
        }

        return true;
    }



}