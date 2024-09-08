package org.liushuxue.chaos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName t_user
 */
@Data
@TableName(value ="t_user")
public class UserPo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String avatar;
    private Integer createUserId;
    private String createUserName;
    private LocalDateTime createTime;
    private Integer updateUserId;
    private String updateUserName;
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}