package org.liushuxue.chaos.bean.menu;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "菜单信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuVo {
    @Schema(description = "菜单ID")
    private Integer id;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单编码")
    private String code;
    @Schema(description = "菜单路径")
    private String path;
    @Pattern(regexp = "^[01]$", message = "菜单状态值只能为 0 或 1")
    @Schema(description = "菜单状态")
    private Byte status;
    @Schema(description = "子菜单")
    private List<MenuVo> children;

}