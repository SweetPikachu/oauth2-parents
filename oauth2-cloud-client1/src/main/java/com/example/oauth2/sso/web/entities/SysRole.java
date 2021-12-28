package com.example.oauth2.sso.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;


    private String name;


    private String remark;

    private Date createDate;
    private Date updateDate;

    @TableField(exist = false)
    private List<SysPermission> perList = Lists.newArrayList();


    @TableField(exist = false)
    private List<Long> perIds = Lists.newArrayList();

    public List<Long> getPerIds() {
        if(CollectionUtils.isNotEmpty(perList)) {
            perIds = Lists.newArrayList();
            for(SysPermission per : perList) {
                perIds.add(per.getId());
            }
        }
        return perIds;
    }
}
