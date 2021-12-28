package com.example.oauth2.sso.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.base.result.ExampleResult;
import com.example.oauth2.sso.web.entities.SysRole;
import com.example.oauth2.sso.web.entities.SysUser;
import com.example.oauth2.sso.web.service.SysRoleService;
import com.example.oauth2.sso.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserController {
    private static final String HTML_PREFIX = "system/user/";


    @GetMapping(value={"/",""})   //  /user/
    public String user() {
        return HTML_PREFIX + "user-list";
    }

    @Autowired
    private SysUserService sysUserService;


    @PostMapping ("/page") // /user/page
    @ResponseBody
    public ExampleResult page(Page<SysUser> page, SysUser sysUser) {
        return ExampleResult.ok(sysUserService.selectPage(page, sysUser));
    }

    @Autowired
    private SysRoleService sysRoleService;


    @GetMapping(value={"/form", "/form/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {

        SysUser user = sysUserService.findById(id);
        model.addAttribute("user", user);


        List<SysRole> roleList = sysRoleService.list();
        model.addAttribute("roleList", roleList);

        return HTML_PREFIX + "user-form";
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "")
    public String saveOrUpdate(SysUser sysUser) {

        sysUserService.saveOrUpdate(sysUser);
        return "redirect:/user";
    }


    @DeleteMapping("/{id}") // /user/{id}
    @ResponseBody
    public ExampleResult deleteById(@PathVariable Long id) {

        sysUserService.deleteById(id);
        return ExampleResult.ok();
    }
}
