package org.liushuxue.chaos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 菜单
 * @TableName t_menu
 */
@TableName(value ="t_menu")
@Data
public class MenuPo implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 页面路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 状态,1、表示启用
     */
    @TableField(value = "status")
    private byte status;

    @TableField(exist = false)
    private List<MenuPo> children;
}