package com.angi.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author angi
 * @date 2021/1/22 11:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("name")
    private String name1;
    private Integer age;
    private String email;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public User(Long id, String name1, Integer age, String email) {
        this.id = id;
        this.name1 = name1;
        this.age = age;
        this.email = email;
    }

}
