package com.example.oauth2.sso.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oauth2.sso.web.entities.SysPermission;
import com.example.oauth2.sso.web.mapper.SysPermissionMapper;
import com.example.oauth2.sso.web.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> permissionList = baseMapper.selectPermissionByUserId(userId);

//        permissionList.remove(null);
        return permissionList;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {

        baseMapper.deleteById(id);

        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper();
        //delete from sys_permission where parent_id = #{id};
        queryWrapper.eq(SysPermission::getParentId, id);
        baseMapper.delete(queryWrapper);
        return true;
    }

}
