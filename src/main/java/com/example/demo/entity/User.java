package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //version_number
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version; //version number

    @TableLogic
    private Integer deleted;

}
