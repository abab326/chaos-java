package org.liushuxue.chaos.bean.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "用户信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo implements Serializable {
    @Schema(description = "用户ID")
    private Integer id;
    @Schema(description = "用户名")
    private String name;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "创建人ID")
    private Integer createUserId;
    @Schema(description = "创建人名称")
    private String createUserName;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新人ID")
    private Integer updateUserId;
    @Schema(description = "更新人名称")
    private String updateUserName;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
