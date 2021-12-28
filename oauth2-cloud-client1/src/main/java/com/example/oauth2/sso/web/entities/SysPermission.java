package com.example.oauth2.sso.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysPermission implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;


    private Long parentId = 0L ;

    @TableField(exist = false)
    private String parentName = "根菜单";

    private String name;
    private String code;
    private String url;


    private Integer type;
    private String icon;
    private String remark;
    private Date createDate;
    private Date updateDate;




    @TableField(exist = false)
    private List<SysPermission> children;



    @TableField(exist = false)
    private List<String> childrenUrl;
}
